/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4661.robot;

import org.usfirst.frc.team4661.robot.commands.arm.CloseLeftArm;
import org.usfirst.frc.team4661.robot.commands.arm.OpenLeftArm;
import org.usfirst.frc.team4661.robot.commands.climber.ClimbDown;
import org.usfirst.frc.team4661.robot.commands.climber.ClimbUp;
import org.usfirst.frc.team4661.robot.commands.drivetrain.DriveStraightByJoystick;
import org.usfirst.frc.team4661.robot.commands.drivetrain.RotateByJoystick;
import org.usfirst.frc.team4661.robot.commands.lift.LiftDown;
import org.usfirst.frc.team4661.robot.commands.lift.LiftUp;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI { // gevald
	Joystick leftJoystick;
	Joystick rightJoystick;
	JoystickButton driveStraightButton;
	JoystickButton rotateButton;
	JoystickButton closeLeftArmButton;
	JoystickButton openLeftArmButton;
	JoystickButton closeRightArmButton;
	JoystickButton openRightArmButton;
	JoystickButton liftUpButton;
	JoystickButton liftDownButton;
	JoystickButton climbUpButton;
	JoystickButton climbDownButton;

	public OI() {
		// Driving system functions
		leftJoystick = new Joystick(RobotMap.LEFT_JOYSTICK_PORT);
		rightJoystick = new Joystick(RobotMap.RIGHT_JOYSTICK_PORT);

		if (!SmartDashboard.containsKey(StringConsts.DRIVE_STRAIGHT_BUTTON)) {
			SmartDashboard.putNumber(StringConsts.DRIVE_STRAIGHT_BUTTON, 1);
		}
		if (!SmartDashboard.containsKey(StringConsts.DRIVE_STRAIGHT_JOYSTICK)) {
			SmartDashboard.putString(StringConsts.DRIVE_STRAIGHT_JOYSTICK, "right");
		}

		int driveStraight = (int) SmartDashboard.getNumber(StringConsts.DRIVE_STRAIGHT_BUTTON, 1);
		String straightJoystick = SmartDashboard.getString(StringConsts.DRIVE_STRAIGHT_JOYSTICK, "right");
		if (straightJoystick == "right") {
			driveStraightButton = new JoystickButton(rightJoystick, driveStraight);

		} else {
			driveStraightButton = new JoystickButton(leftJoystick, driveStraight);
		}
		driveStraightButton.whileHeld(new DriveStraightByJoystick());

		if (!SmartDashboard.containsKey(StringConsts.ROTATE_BUTTON)) {
			SmartDashboard.putNumber(StringConsts.ROTATE_BUTTON, 3);
		}
		if (!SmartDashboard.containsKey(StringConsts.ROTATE_JOYSTICK)) {
			SmartDashboard.putString(StringConsts.ROTATE_JOYSTICK, "right");
		}

		int rotate = (int) SmartDashboard.getNumber(StringConsts.ROTATE_BUTTON, 3);
		String rotateJoystick = SmartDashboard.getString(StringConsts.DRIVE_STRAIGHT_JOYSTICK, "right");
		if (rotateJoystick == "right") {
			rotateButton = new JoystickButton(rightJoystick, rotate);

		} else {
			rotateButton = new JoystickButton(leftJoystick, rotate);
		}
		rotateButton = new JoystickButton(rightJoystick, 3);
		rotateButton.whileHeld(new RotateByJoystick());

		// Closing Arm
		if (!SmartDashboard.containsKey(StringConsts.CLOSE_LEFT_ARM_BUTTON)) {
			SmartDashboard.putNumber(StringConsts.CLOSE_LEFT_ARM_BUTTON, 1);
		}
		if (!SmartDashboard.containsKey(StringConsts.CLOSE_LEFT_ARM_JOYSTICK)) {
			SmartDashboard.putString(StringConsts.CLOSE_LEFT_ARM_JOYSTICK, "left");
		}

		int closeArm = (int) SmartDashboard.getNumber(StringConsts.CLOSE_LEFT_ARM_BUTTON, 1);
		String closeArmJoystick = SmartDashboard.getString(StringConsts.CLOSE_LEFT_ARM_JOYSTICK, "left");
		if (closeArmJoystick == "right") {
			closeLeftArmButton = new JoystickButton(rightJoystick, closeArm);

		} else {
			closeLeftArmButton = new JoystickButton(leftJoystick, closeArm);
		}
		closeLeftArmButton.whenPressed(new CloseLeftArm());

		if (!SmartDashboard.containsKey(StringConsts.OPEN_LEFT_ARM_BUTTON)) {
			SmartDashboard.putNumber(StringConsts.OPEN_LEFT_ARM_BUTTON, 2);
		}
		if (!SmartDashboard.containsKey(StringConsts.OPEN_LEFT_ARM_JOYSTICK)) {
			SmartDashboard.putString(StringConsts.OPEN_LEFT_ARM_JOYSTICK, "left");
		}

		int openArm = (int) SmartDashboard.getNumber(StringConsts.OPEN_LEFT_ARM_BUTTON, 2);
		String openArmJoystick = SmartDashboard.getString(StringConsts.OPEN_LEFT_ARM_JOYSTICK, "left");
		if (openArmJoystick == "right") {
			openLeftArmButton = new JoystickButton(rightJoystick, openArm);

		} else {
			openLeftArmButton = new JoystickButton(leftJoystick, openArm);
		}
		openLeftArmButton.whenPressed(new OpenLeftArm());

		// Lift
		if (!SmartDashboard.containsKey(StringConsts.LIFT_UP_BUTTON)) {
			SmartDashboard.putNumber(StringConsts.LIFT_UP_BUTTON, 3);
		}
		if (!SmartDashboard.containsKey(StringConsts.LIFT_UP_JOYSTICK)) {
			SmartDashboard.putString(StringConsts.LIFT_UP_JOYSTICK, "left");
		}

		int liftUp = (int) SmartDashboard.getNumber(StringConsts.LIFT_UP_BUTTON, 3);
		String liftUpJoystick = SmartDashboard.getString(StringConsts.LIFT_UP_JOYSTICK, "left");
		if (liftUpJoystick == "right") {
			liftUpButton = new JoystickButton(rightJoystick, liftUp);

		} else {
			liftUpButton = new JoystickButton(leftJoystick, liftUp);
		}
		liftUpButton.whenPressed(new LiftUp());

		if (!SmartDashboard.containsKey(StringConsts.LIFT_DOWN_BUTTON)) {
			SmartDashboard.putNumber(StringConsts.LIFT_DOWN_BUTTON, 4);
		}
		if (!SmartDashboard.containsKey(StringConsts.LIFT_DOWN_JOYSTICK)) {
			SmartDashboard.putString(StringConsts.LIFT_DOWN_JOYSTICK, "left");
		}

		int liftDown = (int) SmartDashboard.getNumber(StringConsts.LIFT_DOWN_BUTTON, 4);
		String liftDownJoystick = SmartDashboard.getString(StringConsts.LIFT_DOWN_JOYSTICK, "left");
		if (liftDownJoystick == "right") {
			liftDownButton = new JoystickButton(rightJoystick, liftDown);
		} else {
			liftDownButton = new JoystickButton(leftJoystick, liftDown);
		}
		liftDownButton.whenPressed(new LiftDown());

		// Climb
		if (!SmartDashboard.containsKey(StringConsts.CLIMB_UP_BUTTON)) {
			SmartDashboard.putNumber(StringConsts.CLIMB_UP_BUTTON, 5);
		}
		if (!SmartDashboard.containsKey(StringConsts.CLIMB_UP_JOYSTICK)) {
			SmartDashboard.putString(StringConsts.CLIMB_UP_JOYSTICK, "left");
		}

		int climbUp = (int) SmartDashboard.getNumber(StringConsts.CLIMB_UP_BUTTON, 5);
		String climbUpJoystick = SmartDashboard.getString(StringConsts.CLIMB_UP_JOYSTICK, "left");
		if (climbUpJoystick == "right") {
			climbUpButton = new JoystickButton(rightJoystick, climbUp);
		} else {
			climbUpButton = new JoystickButton(leftJoystick, climbUp);
		}
		climbUpButton.whenPressed(new ClimbUp());
		
		if (!SmartDashboard.containsKey(StringConsts.CLIMB_DOWN_BUTTON)) {
			SmartDashboard.putNumber(StringConsts.CLIMB_DOWN_BUTTON, 5);
		}
		if (!SmartDashboard.containsKey(StringConsts.CLIMB_DOWN_JOYSTICK)) {
			SmartDashboard.putString(StringConsts.CLIMB_DOWN_JOYSTICK, "left");
		}

		int climbDown = (int) SmartDashboard.getNumber(StringConsts.CLIMB_DOWN_BUTTON, 5);
		String climbDownJoystick = SmartDashboard.getString(StringConsts.CLIMB_DOWN_JOYSTICK, "left");
		if (climbDownJoystick == "right") {
			climbDownButton = new JoystickButton(rightJoystick, climbDown);
		} else {
			climbDownButton = new JoystickButton(leftJoystick, climbDown);
		}		climbDownButton.whenPressed(new ClimbDown());
	}

	public double getLeftY() {
		return -leftJoystick.getY();
	}

	public double getLeftX() {
		return leftJoystick.getX();
	}

	public double getRightY() {
		return -rightJoystick.getY();
	}

	public double getRightX() {
		return rightJoystick.getX();
	}
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
