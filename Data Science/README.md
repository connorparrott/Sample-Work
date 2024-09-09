# EISSFLAIX Data Processing

These files are files I wrote to process and format data received from the ISS via the EISSFLAIX mission. They separate the original packet of information into the data needed by scientists at Montana State University.

## Description

The parseHSData file takes in a ISSCCSDS file and reads out the EISSFLAIX mission health and safety data. It expects a CCSDS header that is added after processing via their bus, rather than being taken directly from the instrument. The parseNasaData file takes in a ISSCCSDS file and reads out EISSFLAIX mission data which includes emorpho sensor readings. This data must then be converted back into the original binning (specific discrete EV values) before it can be used. 

## Getting Started

### Dependencies

You will need the following python packages:
* Numpy
* Pandas
* Os
* Date Time

### Installing

* In order to use, you will need to update the pathing for your machines file structure. 
* In addition, this program is intended for the EISSFLAIX instrument and use outside of this intended use will require heavy modification to match the telemetry of the other instrument.

### Executing program

* Change the input file name to match the ISS CCSDS file given by NASA
* Change the output file to a known directory
* Run the program


## Authors

Contributors names and contact info

Connor Parrott
connorparrott123@gmail.com

