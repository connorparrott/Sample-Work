import os
import numpy as np
import pandas as pd
from datetime import datetime, timedelta

# Input: coarse_time, array of 4 bytes that are used to calculate the coarse time
# Converts the coarse time bytes into a number of seconds since january 6th 1980 (EPOCH). Converted time is in UTC.
def convert_coarse_time(coarse_time):
    gps_epoch = datetime(1980, 1, 6)
    leap_seconds = 18

    try:
        TimeByte1 = 16777216 * coarse_time[0]
        TimeByte2 = 65536 * coarse_time[1]
        TimeByte3 = 256 * coarse_time[2]
        TimeByte4 = 1 * coarse_time[3]

        total_seconds = int(TimeByte1 + TimeByte2 + TimeByte3 + TimeByte4 - leap_seconds)
        utc_time = gps_epoch + timedelta(seconds=total_seconds)
        return utc_time.strftime('%Y-%m-%d %H:%M:%S')
    except Exception as e:
        print(f"Error converting coarse time: {e}")
        return None

# Input: array of bytes 16 bytes long
# Output: 16 bit integer
# Notes: this function doesn't perform any error checking
def convert16Bit(byte_array):
    byte_array = np.array(byte_array, dtype=int)  # Ensure byte_array is integer type
    if len(byte_array) % 2 != 0:
        byte_array = np.append(byte_array, 0)  # Append zero if length is odd
    combined = [(byte_array[i] << 8) + byte_array[i + 1] for i in range(0, len(byte_array), 2)]
    return combined

# Input: fine_time, array of 2 bytes that are used to calculate fine time
# Converts to fractions of a second, used to find subsecond precision of data.
def convert_fine_time(fine_time):
    try:
        fine_time_combined = (fine_time[0] << 8) | fine_time[1]
        fine_subseconds = fine_time_combined / 65536.0
        return fine_subseconds
    except Exception as e:
        print(f"Error converting fine time: {e}")
        return None


# Define parameters
chunk_size = 1000  # Adjust this based on your memory capacity
num_data_entries = 4
data_entry_size = 260

# Read and process data in chunks
file_path = '/home/ssel/Documents/EISSFLAIX/support-software/SFXTI Python Reader/data/IssCcsds.1777_2024-06-04_21~35~10'
date = file_path.split('.')
date = date[1]
data = np.fromfile(file_path, dtype=np.uint8)

total_rows = len(data) // 1306
output_dir = f'/home/ssel/Documents/EISSFLAIX/support-software/SFXTI Python Reader/data/{date}'
os.makedirs(output_dir, exist_ok=True)

# Grab the data row by row and pull necessary information out of it. If you wish to change this section, look into
# the packet structure and ensure you understand what data fields are stored in what column of the data packet
for start_row in range(0, total_rows, chunk_size):
    end_row = min(start_row + chunk_size, total_rows)
    reshaped_data = data[start_row * 1306:end_row * 1306].reshape((end_row - start_row, 1306))

    df = pd.DataFrame(reshaped_data)

    # Extract columns 5:11
    basic_info = df.iloc[:, 6:18]

    # Extract the 4 data sets (each 260 columns long)
    data_entries = [df.iloc[:, 24 + i * data_entry_size: 24 + (i + 1) * data_entry_size] for i in
                    range(num_data_entries)]

    # Initialize lists to store processed rows and corresponding emorpho numbers
    processed_rows = []
    coarse_times = []
    fine_times = []
    emorpho_numbers = []
    accepted_counts = []
    rejected_counts = []
    dead_times = []

    # Combine the columns 5:11 with each of the 4 data entries
    for i in range(end_row - start_row):
        row_basic_info = basic_info.iloc[i].values
        coarse_time = convert_coarse_time(row_basic_info[:4])
        fine_time = convert_fine_time(row_basic_info[4:6])

        for j in range(num_data_entries):
            start_index = 24 + j * data_entry_size  # Calculate the start index for each data entry block
            end_index = start_index + data_entry_size

            row_data_entry = df.iloc[i, start_index:end_index].values

            # Extract emorpho number from the correct position within each block
            emorpho_number = row_data_entry[0]  # Assuming emorpho number is at the start of each block

            # Store the data corresponding to each emorpho number
            emorpho_numbers.append(emorpho_number)
            accepted_counts.append(convert16Bit(row_data_entry[3:5]))
            rejected_counts.append(convert16Bit(row_data_entry[5:7]))
            dead_times.append(convert16Bit(row_data_entry[7:9]))

            # Process the data entry to combine 8-bit words into 16-bit words
            processed_row = row_data_entry[9:]  # The remaining data after the first 9 bytes
            processed_row_16bit = convert16Bit(processed_row)  # Convert the list of 8-bit words to 16-bit words

            processed_rows.append(processed_row_16bit)

            # Format the coarse time and fine time
            coarse_times.append(coarse_time)  # Append coarse time to the list
            fine_times.append(f"{fine_time:.6f}" if fine_time is not None else None)  # Append fine time to the list

    # Create a new DataFrame with the processed rows
    column_names = [f'Col_{i}' for i in range(126)]  # Updated to 126 columns for 16-bit words
    new_df = pd.DataFrame(processed_rows, columns=column_names)

    # Add the emorpho number, coarse time, and fine time columns at the beginning
    new_df.insert(0, 'CoarseDate CoarseTime', coarse_times)
    new_df.insert(1, 'FineTime', fine_times)
    new_df.insert(2, 'Emorpho', emorpho_numbers)
    new_df.insert(3, 'AcceptedCounts', accepted_counts)
    new_df.insert(4, 'RejectedCounts', rejected_counts)
    new_df.insert(5, 'DeadTime', dead_times)

    # Save the DataFrame to CSV (Append Mode)
    grouped = new_df.groupby('Emorpho')

    for emorpho_number, group in grouped:
        file_name = os.path.join(output_dir, f'{date}_emorpho_{emorpho_number}.csv')

        # Append to the file if it exists; otherwise, create it with the header
        if not os.path.isfile(file_name):
            group.to_csv(file_name, index=False, mode='w', header=True)
        else:
            group.to_csv(file_name, index=False, mode='a', header=False)

        print(f"Appended data for emorpho number {emorpho_number} to {file_name}")

# Notify when all chunks are processed
print("All chunks processed.")
