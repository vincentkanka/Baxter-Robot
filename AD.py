
import argparse
import random

import rospy

import time

import baxter_interface

from RAMXY import RobotArmFacade

from baxter_interface import CHECK_VERSION


 
ram = RobotArmFacade()

with open('myFile.dat') as f:
   for line in f:
        pair = [float(s) for s in line.split(" ")]
        ram.moveArmTo (pair[0],pair[1])
	time.sleep(1)
   




