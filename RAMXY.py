#!/usr/bin/env python

# Copyright (c) 2013-2015, Rethink Robotics
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are met:
#
# 1. Redistributions of source code must retain the above copyright notice,
#    this list of conditions and the following disclaimer.
# 2. Redistributions in binary form must reproduce the above copyright
#    notice, this list of conditions and the following disclaimer in the
#    documentation and/or other materials provided with the distribution.
# 3. Neither the name of the Rethink Robotics nor the names of its
#    contributors may be used to endorse or promote products derived from
#    this software without specific prior written permission.
#
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
# AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
# IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
# ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
# LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
# CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
# SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
# INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
# CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
# ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
# POSSIBILITY OF SUCH DAMAGE.

import argparse
import random

import rospy

import time

import baxter_interface

from BiLinear import BilinearInterpolation

from baxter_interface import CHECK_VERSION


class RobotArmFacade(object):

    def __init__(self):

	
        """
        'Moves the arm
        """
#        self._done = False
#        self._arm = baxter_interface.arm()

        # verify robot is enabled
#        print("Getting robot state... ")
#        self._rs = baxter_interface.RobotEnable(CHECK_VERSION)
#        self._init_state = self._rs.state().enabled
#        print("Enabling robot... ")
#        self._rs.enable()
#        print("Running. Ctrl-c to quit")
	
    def moveArmTo(self, x, y): 
	ur_angles = {'right_s0': -0.17027186745528092, 'right_s1': -0.2396844981070959, 'right_w0': -0.5982525072753113, 'right_w1': -1.4879613642488514, 'right_w2': -0.12003399665203363, 'right_e0': -0.06634466907604414, 'right_e1': 1.8856458835081449} 
	lr_angles = {'right_s0': -0.04601942363656241, 'right_s1': -0.06902913545484361, 'right_w0': -0.85289331806429, 'right_w1': -0.9898011033830633, 'right_w2': 0.28838838812245776, 'right_e0': 0.10277671278832272, 'right_e1': 1.1309273358685212}
	ll_angles = {'right_s0': 0.013805827090968724, 'right_s1': 0.18676216092504913, 'right_w0': -0.19941750242510378, 'right_w1': -0.48320394818390533, 'right_w2': 0.07133010663667173, 'right_e0': -0.011888351106111956, 'right_e1': 0.4456214188807127}
	ul_angles = {'right_s0': -0.26959712347086146, 'right_s1': -0.21399031991001521, 'right_w0': -0.3647039323197571, 'right_w1': -0.9752282858981518, 'right_w2': 0.060975736318445196, 'right_e0': -0.02454369260616662, 'right_e1': 1.3821166898847579}

     	print(x,y)

        t0 = BilinearInterpolation(
          x_index=(-5.0, 5.0), 
          y_index=(-5.0, 5.0), 
          values=((0.013805827090968724, -0.26959712347086146), (-0.04601942363656241, -0.17027186745528092))
	)

	
	t1 = BilinearInterpolation(
          x_index=(-5.0, 5.0), 
          y_index=(-5.0, 5.0), 
          values=((0.18676216092504913, -0.21399031991001521), (-0.06902913545484361, -0.2396844981070959))
	)


	t2 = BilinearInterpolation(
          x_index=(-5.0, 5.0), 
          y_index=(-5.0, 5.0), 
          values=((-0.19941750242510378, -0.3647039323197571), (-0.85289331806429, -0.5982525072753113))
	)


	t3 = BilinearInterpolation(
          x_index=(-5.0, 5.0), 
          y_index=(-5.0, 5.0), 
          values=((-0.48320394818390533, -0.9752282858981518), (-0.9898011033830633, -1.4879613642488514))
	)


	t4 = BilinearInterpolation(
          x_index=(-5.0, 5.0), 
          y_index=(-5.0, 5.0), 
          values=((0.07133010663667173, 0.060975736318445196), (0.28838838812245776, -0.12003399665203363))
	)


	t5 = BilinearInterpolation(
          x_index=(-5.0, 5.0), 
          y_index=(-5.0, 5.0), 
          values=(( -0.011888351106111956, -0.02454369260616662), (0.10277671278832272, -0.06634466907604414))
	)


	t6 = BilinearInterpolation(
          x_index=(-5.0, 5.0), 
          y_index=(-5.0, 5.0), 
          values=((0.4456214188807127, 1.3821166898847579), (1.1309273358685212, 1.8856458835081449))
	)

	

	temp_angles = {'right_s0': t0(x,y), 'right_s1': t1(x,y), 'right_w0': t2(x,y), 'right_w1': t3(x,y), 'right_w2': t4(x,y), 'right_e0': t5(x,y), 'right_e1': t6(x,y)}  
	print (temp_angles)
    
	rospy.init_node("RAMXY")
	limb = baxter_interface.Limb('right')
        limb.move_to_joint_positions(temp_angles) 

	
    def clean_shutdown(self):
        """
        Exits example cleanly by moving head to neutral position and
        maintaining start state
        """
        print("\nExiting example...")
        if self._done:
            self.set_neutral()
        if not self._init_state and self._rs.state().enabled:
            print("Disabling robot...")
            self._rs.disable()

    def set_neutral(self):
        """
        Sets the arm back into a neutral pose
        """
        self._arm.set_pan(0.0)

    def RobotArmFacade(self):
        self.set_neutral()
        """
        Performs the arm motion
        """
        self._arm.command_nod()
        command_rate = rospy.Rate(1)
        control_rate = rospy.Rate(100)
        start = rospy.get_time()
        while not rospy.is_shutdown() and (rospy.get_time() - start < 10.0):
            angle = random.uniform(-1.5, 1.5)
            while (not rospy.is_shutdown() and
                   not (abs(self._arm.pan() - angle) <=
                       baxter_interface.ARM_PAN_ANGLE_TOLERANCE)):
                self._arm.set_pan(angle, speed=0.3, timeout=0)
                control_rate.sleep()
            command_rate.sleep()

        self._done = True
        rospy.signal_shutdown("Example finished.")

if __name__ == '__main__':
    """RSDK ARM Example: MoveArmTo

    Nods the arm and pans side-to-side towards random angles.
    Demonstrates the use of the baxter_interface.Arm class.
    """
    arg_fmt = argparse.RawDescriptionHelpFormatter
    parser = argparse.ArgumentParser(formatter_class=arg_fmt,
                                     description=__doc__)
    parser.parse_args(rospy.myargv()[1:])
    
    print("Initializing node... ")
    rospy.init_node("RAMXY")
    origin_angles = {'right_s0': -0.07133010663667173, 'right_s1': -0.17755827619773665, 'right_w0': -0.1472621556369997, 'right_w1': -1.2003399665203363, 'right_w2': -0.06519418348513008, 'right_e0': 0.07286408742455715, 'right_e1': 1.4971652489761638} 
    
    rm_angles = {'right_s0': -0.190980608091734, 'right_s1': -0.20401944478876002, 'right_w0': -0.8164612743520114, 'right_w1': -1.2237331735355887, 'right_w2': 0.0019174759848567672, 'right_e0': 0.07861651537912745, 'right_e1': 1.5600584612794657} 
     
    lom_angles = {'right_s0': 0.0625097171063306, 'right_s1': -0.08935438089432535, 'right_w0': -0.13805827090968723, 'right_w1': -0.8847234194129123, 'right_w2': -0.05062136600021865, 'right_e0': 0.058674765136617076, 'right_e1': 1.1213399559442374}
     
    lm_angles = {'right_s0': -0.13959225169757264, 'right_s1': -0.1177330254702055, 'right_w0': -0.387330148941067, 'right_w1': -0.8302671014429802, 'right_w2': 0.14841264122791378, 'right_e0': -0.024927187803137973, 'right_e1': 1.0918108257774433} 
     
    um_angles = {'right_s0': -0.1392087565006013, 'right_s1': -0.1695048770613382, 'right_w0': -0.35626703798638737, 'right_w1': -1.3571895020816198, 'right_w2': 0.06672816427301549, 'right_e0': -0.139975746894544, 'right_e1': 1.6402089574464787}
    origin_anglesup = {'right_s0': -0.07133010663667173, 'right_s1': -0.27755827619773665, 'right_w0': -0.1472621556369997, 'right_w1': -1.2003399665203363, 'right_w2': -0.06519418348513008, 'right_e0': 0.07286408742455715, 'right_e1': 1.4971652489761638}
    ur_anglesup= {'right_s0': -0.17027186745528092, 'right_s1': -0.4396844981070959, 'right_w0': -0.5982525072753113, 'right_w1': -1.4879613642488514, 'right_w2': -0.12003399665203363, 'right_e0': -0.06634466907604414, 'right_e1': 1.8856458835081449}
    ll_anglesup = {'right_s0': 0.013805827090968724, 'right_s1': -0.18676216092504913, 'right_w0': -0.19941750242510378, 'right_w1': -0.48320394818390533, 'right_w2': 0.07133010663667173, 'right_e0': -0.011888351106111956, 'right_e1': 0.4456214188807127}
    rm_anglesup = {'right_s0': -0.190980608091734, 'right_s1': -0.30401944478876002, 'right_w0': -0.8164612743520114, 'right_w1': -1.2237331735355887, 'right_w2': 0.0019174759848567672, 'right_e0': 0.07861651537912745, 'right_e1': 1.5600584612794657}
    lr_anglesup = {'right_s0': -0.04601942363656241, 'right_s1': -0.16902913545484361, 'right_w0': -0.85289331806429, 'right_w1': -0.9898011033830633, 'right_w2': 0.28838838812245776, 'right_e0': 0.10277671278832272, 'right_e1': 1.1309273358685212}
    lom_anglesup = {'right_s0': 0.0625097171063306, 'right_s1': -0.18935438089432535, 'right_w0': -0.13805827090968723, 'right_w1': -0.8847234194129123, 'right_w2': -0.05062136600021865, 'right_e0': 0.058674765136617076, 'right_e1': 1.1213399559442374}
    lm_anglesup = {'right_s0': -0.13959225169757264, 'right_s1': -0.2177330254702055, 'right_w0': -0.387330148941067, 'right_w1': -0.8302671014429802, 'right_w2': 0.14841264122791378, 'right_e0': -0.024927187803137973, 'right_e1': 1.0918108257774433}
    ul_anglesup = {'right_s0': -0.26959712347086146, 'right_s1': -0.31399031991001521, 'right_w0': -0.3647039323197571, 'right_w1': -0.9752282858981518, 'right_w2': 0.060975736318445196, 'right_e0': -0.02454369260616662, 'right_e1': 1.3821166898847579}
    um_anglesup = {'right_s0': -0.1392087565006013, 'right_s1': -0.2695048770613382, 'right_w0': -0.35626703798638737, 'right_w1': -1.3571895020816198, 'right_w2': 0.06672816427301549, 'right_e0': -0.139975746894544, 'right_e1': 1.6402089574464787}
    ur_angles = {'right_s0': -0.17027186745528092, 'right_s1': -0.2396844981070959, 'right_w0': -0.5982525072753113, 'right_w1': -1.4879613642488514, 'right_w2': -0.12003399665203363, 'right_e0': -0.06634466907604414, 'right_e1': 1.8856458835081449} 
    lr_angles = {'right_s0': -0.04601942363656241, 'right_s1': -0.06902913545484361, 'right_w0': -0.85289331806429, 'right_w1': -0.9898011033830633, 'right_w2': 0.28838838812245776, 'right_e0': 0.10277671278832272, 'right_e1': 1.1309273358685212}
    ll_angles = {'right_s0': 0.013805827090968724, 'right_s1': 0.18676216092504913, 'right_w0': -0.19941750242510378, 'right_w1': -0.48320394818390533, 'right_w2': 0.07133010663667173, 'right_e0': -0.011888351106111956, 'right_e1': 0.4456214188807127}
    ul_angles = {'right_s0': -0.26959712347086146, 'right_s1': -0.21399031991001521, 'right_w0': -0.3647039323197571, 'right_w1': -0.9752282858981518, 'right_w2': 0.060975736318445196, 'right_e0': -0.02454369260616662, 'right_e1': 1.3821166898847579}

    limb = baxter_interface.Limb('right')
    limb.move_to_joint_positions(origin_anglesup) 
    for _move in range(1): 
	limb.move_to_joint_positions(origin_angles)
	time.sleep(1)

	
	limb.move_to_joint_positions(ur_anglesup)
        limb.move_to_joint_positions(ur_angles) 
	time.sleep(1)
	limb.move_to_joint_positions(ur_anglesup)
	
	time.sleep(1)
	 
	limb.move_to_joint_positions(rm_anglesup)
        limb.move_to_joint_positions(rm_angles) 
	time.sleep(1)
	limb.move_to_joint_positions(rm_anglesup)
	
   
	time.sleep(1)
	 
	limb.move_to_joint_positions(lr_anglesup)
        limb.move_to_joint_positions(lr_angles) 
	time.sleep(1)
	limb.move_to_joint_positions(lr_anglesup)
        
	time.sleep(1)
	 
	limb.move_to_joint_positions(lom_anglesup)
        limb.move_to_joint_positions(lom_angles)
	time.sleep(1) 
	limb.move_to_joint_positions(lom_anglesup)
	
	time.sleep(1)
	limb.move_to_joint_positions(ll_anglesup) 
        limb.move_to_joint_positions(ll_angles) 
	time.sleep(1)
	limb.move_to_joint_positions(ll_anglesup)
	
 
	time.sleep(1)
	
        limb.move_to_joint_positions(lm_anglesup)
	limb.move_to_joint_positions(lm_angles)
	time.sleep(1)
	limb.move_to_joint_positions(lm_anglesup)
	
	time.sleep(1)
	
        limb.move_to_joint_positions(ul_anglesup) 
	limb.move_to_joint_positions(ul_angles)
	time.sleep(1)
	limb.move_to_joint_positions(ul_anglesup)
	
	time.sleep(1)
	
        limb.move_to_joint_positions(um_anglesup) 
	limb.move_to_joint_positions(um_angles)
	time.sleep(1)
	limb.move_to_joint_positions(origin_angles)

 


