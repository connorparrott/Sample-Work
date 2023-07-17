## License: Apache 2.0. See LICENSE file in root directory.
## Copyright(c) 2015-2017 Intel Corporation. All Rights Reserved.

###############################################
##      Open CV and Numpy integration        ##
###############################################

import pyrealsense2 as rs
import numpy as np
import cv2
import sys

p1, p2 = None, None
state = 0

cv2.namedWindow('RealSense', cv2.WINDOW_AUTOSIZE)

# Called every time a mouse event happen
def on_mouse(event, x, y, flags, userdata):
    global state, p1, p2
    
    # Left click
    if event == cv2.EVENT_LBUTTONUP:
        # Select first point
        if state == 0:
            p1 = (x,y)
            state += 1
        # Select second point
        elif state == 1:
            p2 = (x,y)
            state += 1
    # Right click (erase current ROI)
    if event == cv2.EVENT_RBUTTONUP:
        p1, p2 = None, None
        state = 0

# Configure depth and color streams
pipeline = rs.pipeline()
config = rs.config()

# Get device product line for setting a supporting resolution
pipeline_wrapper = rs.pipeline_wrapper(pipeline)
pipeline_profile = config.resolve(pipeline_wrapper)
device = pipeline_profile.get_device()
device_product_line = str(device.get_info(rs.camera_info.product_line))

found_rgb = False
for s in device.sensors:
    if s.get_info(rs.camera_info.name) == 'RGB Camera':
        found_rgb = True
        break
if not found_rgb:
    print("The demo requires Depth camera with Color sensor")
    exit(0)

config.enable_stream(rs.stream.depth, 640, 480, rs.format.z16, 30)

if device_product_line == 'L500':
    config.enable_stream(rs.stream.color, 960, 540, rs.format.bgr8, 30)
else:
    config.enable_stream(rs.stream.color, 640, 480, rs.format.bgr8, 30)

# config.enable_stream(rs.stream.blank, 1280, 480, rs.format.bgr8, 30)

# Start streaming
pipeline.start(config)





try:
    while True:

        # Wait for a coherent pair of frames: depth and color
        frames = pipeline.wait_for_frames()
        depth_frame = frames.get_depth_frame()
        color_frame = frames.get_color_frame()

        cv2.setMouseCallback('RealSense', on_mouse)
        
        if not depth_frame or not color_frame:
            continue

        # Convert images to numpy arrays
        depth_image = np.asanyarray(depth_frame.get_data())
        color_image = np.asanyarray(color_frame.get_data())
        blank_image = np.zeros((480,640,3), np.uint8)

        # Apply colormap on depth image (image must be converted to 8-bit per pixel first)
        depth_colormap = cv2.applyColorMap(cv2.convertScaleAbs(depth_image, alpha=0.03), cv2.COLORMAP_JET)
        
        

        depth_colormap_dim = depth_colormap.shape
        color_colormap_dim = color_image.shape
        blank_colormap_dim = blank_image.shape

        # If depth and color resolutions are different, resize color image to match depth image for display
        if depth_colormap_dim != color_colormap_dim:
            color_image = cv2.resize(color_image, dsize=(depth_colormap_dim[1], depth_colormap_dim[0]), interpolation=cv2.INTER_AREA)

        images = np.hstack((color_image, depth_colormap))
        blank_image = cv2.resize(blank_image, dsize=((depth_colormap_dim[1] + color_colormap_dim[1]), (depth_colormap_dim[0])), interpolation=cv2.INTER_AREA)
        images = np.vstack((images, blank_image))

        # # Show images
        # cv2.namedWindow('RealSense', cv2.WINDOW_AUTOSIZE)
        # cv2.imshow('RealSense', images)
        # cv2.waitKey(1)

        #Tracking part=========================================================================================================
 
        (major_ver, minor_ver, subminor_ver) = (cv2.__version__).split('.')
        
        # Set up tracker.
        # Instead of MIL, you can also use
    
        tracker_types = ['BOOSTING', 'MIL','KCF', 'TLD', 'MEDIANFLOW', 'GOTURN', 'MOSSE', 'CSRT']
        tracker_type = tracker_types[2]
    
        if int(minor_ver) < 3:
            tracker = cv2.Tracker_create(tracker_type)
        else:
            if tracker_type == 'BOOSTING':
                tracker = cv2.TrackerBoosting_create()
            if tracker_type == 'MIL':
                tracker = cv2.TrackerMIL_create()
            if tracker_type == 'KCF':
                tracker = cv2.TrackerKCF_create()
            if tracker_type == 'TLD':
                tracker = cv2.TrackerTLD_create()
            if tracker_type == 'MEDIANFLOW':
                tracker = cv2.TrackerMedianFlow_create()
            if tracker_type == 'GOTURN':
                tracker = cv2.TrackerGOTURN_create()
            if tracker_type == 'MOSSE':
                tracker = cv2.TrackerMOSSE_create()
            if tracker_type == "CSRT":
                tracker = cv2.TrackerCSRT_create()
    
        # Read video
        video = color_image
    
        # # Exit if video not opened.
        # if not video.isOpened():
        #     print("Could not open video")
        #     sys.exit()
    
        # # Read first video.
        # ok, video = video.read()
        # if not ok:
        #     print('Cannot read video file')
        #     sys.exit()
        
        # Define an initial bounding box
        bbox = (287, 23, 86, 320)
    
        # Uncomment the line below to select a different bounding box
        # bbox = cv2.selectROI(video, False)
    
        # Initialize tracker with first video and bounding box
        ok = tracker.init(video, bbox)
    
        # while True:
        #     # Read a new video
        #     ok, video = video.read()
        #     if not ok:
        #         break
        
        # Start timer
        timer = cv2.getTickCount()

        # Update tracker
        ok, bbox = tracker.update(video)

        # Calculate videos per second (FPS)
        fps = cv2.getTickFrequency() / (cv2.getTickCount() - timer);

        # Draw bounding box
        if ok:
            # Tracking success
            p1 = (int(bbox[0]), int(bbox[1]))
            p2 = (int(bbox[0] + bbox[2]), int(bbox[1] + bbox[3]))
            cv2.rectangle(video, p1, p2, (255,0,0), 2, 1)
        else :
            # Tracking failure
            cv2.putText(images, "Tracking failure detected", (100,80), cv2.FONT_HERSHEY_SIMPLEX, 0.75,(0,0,255),2)

        # Display tracker type on video
        # If a ROI is selected, draw it
        if state > 1:
            cv2.rectangle(images, p1, p2, (255, 0, 0), 10)
        # cv2.putText(images, tracker_type + " Tracker", (100,20), cv2.FONT_HERSHEY_SIMPLEX, 0.75, (50,170,50),2);
    
        # Display FPS on video
        cv2.putText(images, "FPS : " + str(int(fps)), (100,50), cv2.FONT_HERSHEY_SIMPLEX, 0.75, (50,170,50), 2);

        # Display result
        #cv2.imshow("Tracking", video)

        # Show images
        
        cv2.imshow('RealSense', images)
        cv2.waitKey(1)

        # Exit if ESC pressed
        k = cv2.waitKey(1) & 0xff
        if k == 27 : break

finally:

    # Stop streaming
    pipeline.stop()