/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4661.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	
	// CR rename ALL of these. Decide on a naming scheme that makes sense and follow it.
	
	public final static int CLOSING_ARM_SPEED_CONTROLLER_PORT = 4;
	public final static int LEFT_REAR_TALON_PORT = 3;
	public final static int RIGHT_REAR_TALON_PORT = 1;
	public final static int LEFT_FRONT_TALON_PORT = 2;
	public final static int RIGHT_FRONT_TALON_PORT = 0;
	public final static int LIFT_SPEED_CONTROLLER_PORT = 5;
	public final static int CLIMBER_SPEED_CONTROLLER_PORT = 6;
	public final static int CLOSING_ARM_MAX_MICRO_SWITCH_PORT = 7;
	public final static int CLOSING_ARM_MIN_MICRO_SWITCH_PORT = 8;
	public final static int LIFT_MAX_MICRO_SWITCH_PORT = 9;
	public final static int LIFT_MIN_MICRO_SWITCH_PORT = 10;
	public final static int CLIMB_MAX_MICRO_SWITCH_PORT = 11;
	public final static int CLIMB_MIN_MICRO_SWITCH_PORT = 12;
	public final static int LEFT_JOYSTICK_PORT = 0;
	public final static int RIGHT_JOYSTICK_PORT = 1;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
