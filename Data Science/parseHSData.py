import pandas as pd
import numpy as np
import os

# Input: array of bytes 16 bytes long
# Output: 16 bit integer
# Notes: this function doesn't perform any error checking
def convert16Bit(byte_array):
    byte_array = np.array(byte_array, dtype=int)  # Ensure byte_array is integer type
    combined = [(byte_array[i] << 8) + byte_array[i + 1] for i in range(0, len(byte_array), 2)] # Bit shift and combine the 2 8 bit words into 16 bit
    return combined

# Input: reshaped_values, These are the values pulled from the known Health and Safety telemetry locations. These values
# need separate conversion factors in order to be logical voltages, currents, temperatures, etc.
# Input: conversion_functions, this is an array of lambda expressions that tells the apply_conversion_factors method what
# it needs to perform to each of the variables. In the current case, there are 6 data fields that need to be converted.
# Output: Array of values that have been converted into their correct units.
def apply_conversion_factors(reshaped_values, conversion_functions):
    # Apply conversion functions to each column
    for i, func in enumerate(conversion_functions):
        reshaped_values[:, i] = func(reshaped_values[:, i].astype(float))
    # After applying functions, round values to 3 decimal places
    reshaped_values = np.round(reshaped_values, 3)
    return reshaped_values

# Input: data_chunk, I've split each operation on this data into 1 packet at a time. The data_chunk is the data taken from a single packet
# Input: output_path, this should be the absolute file path to the location you wish to store the data. (NEEDS TO BE CHANGED FOR YOUR USE)
# Input: conversion functions, his is an array of lambda expressions that tells the apply_conversion_factors method what
# it needs to perform to each of the variables. Passes these into the apply_conversion_factors function above.
def process_and_save(data_chunk, output_path, conversion_functions):
    # Extract the 12-byte segments (assuming it's from columns 1087 to 1099)
    byte_segments = data_chunk.iloc[:, 1087:1099].to_numpy().flatten()

    # Convert to 16-bit values
    hex_values = convert16Bit(byte_segments)

    # Ensure hex_values is treated as float from this point onwards
    reshaped_values = np.array(hex_values, dtype=float).reshape(-1, 6)

    # Apply the conversion functions and round to 3 decimal places
    reshaped_values = apply_conversion_factors(reshaped_values, conversion_functions)

    # Create a DataFrame with the specified column labels
    df = pd.DataFrame(reshaped_values, columns=["Voltage", "Current", "Temp1", "Temp2", "Sun", "tuC"])

    # Save the DataFrame to a CSV file with 3 decimal places
    df.to_csv(output_path, mode='a', header=not os.path.exists(output_path), index=False, float_format='%.3f')

chunk_size = 1000  # Adjust this based on your memory capacity
file_path = '/home/ssel/Documents/EISSFLAIX/support-software/SFXTI Python Reader/data/STP-H10 H&S - 7-18 Through 7-22/IssCcsds.1751_2024-07-18_18~39~39~906'
date = file_path.split('.')[1] # date the file was taken is already included in the file name, so this just grabs that date to use for the name of the new file
data = np.fromfile(file_path, dtype=np.uint8)

total_rows = len(data) // 1328
output_dir = f'/home/ssel/Documents/EISSFLAIX/support-software/SFXTI Python Reader/data/{date}'
os.makedirs(output_dir, exist_ok=True)
output_file_path = f'{output_dir}/HS_processed.csv'

# Define conversion functions for each column
conversion_functions = [
    lambda x: (x * 3.3 * 2 / 4095),           # Conversion for Voltage
    lambda x: (x * 3.3 * 0.56 / 4095),        # Conversion for Current
    lambda x: (x * 3.3 * 34.3 / 4095) - 32.7, # Conversion for Temp1
    lambda x: (x * 3.3 * 34.3 / 4095) - 32.7, # Conversion for Temp2
    lambda x: (x * 3.3 / 4095),               # Conversion for Sun
    lambda x: (x * 3.3 * 377.5 / 4095) - 265.775 # Conversion for tuC
]

# iterates through the data and performs the processing on it.
for start_row in range(0, total_rows, chunk_size):
    end_row = min(start_row + chunk_size, total_rows)
    reshaped_data = data[start_row * 1328:end_row * 1328].reshape((end_row - start_row, 1328))

    df = pd.DataFrame(reshaped_data)
    process_and_save(df, output_file_path, conversion_functions)
