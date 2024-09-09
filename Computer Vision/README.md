# Computer Vision and Object Recognition

These files detail some of the object and facial recognition software utilized in the final project of the computer vision class. 

## Description

Our robot was capable of recognizing and aligning with a line of tape on the floor and using that to align its driving. It then searched for a second line of tape that indicated a special zone. In this special zone it looked for a face, either mine or my team mates, and drove up to us to receive the color it would be looking for on the way back. it then crossed the two lines as it did before and searched the ground for a piece of paper that matched the color we showed it, driving onto that color of paper. 

## Getting Started

### Dependencies

* This code utilized a raspberry pi on a very specific robotic system. To utilize this code on another system, the peripherals need to be updated to match the system you are using. 

The following packages need to be installed on the robot:
* pyrealsense2
* numpy
* cv2
* time
* math
* maestro - this is the peripheral configuration file and it also includes some of the base functions given.

### Installing

* These files need to be heavily modified outside of the Montana State University Robotics lab. It is intended to be used on Tango robots built by Hunter Lloyd.

### Executing program

* Run the program with the following command
```
python3 Robot-Obstacle-Course-Final.py
```

## Authors

Contributors names and contact info

Connor Parrott
connorparrott123@gmail.com

Zach Jewett
Cooper Senior

