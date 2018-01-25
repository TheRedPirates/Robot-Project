/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4661.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4661.robot.commands.arm.AutomaticCloseLeftArm;
import org.usfirst.frc.team4661.robot.commands.arm.AutomaticOpenLeftArm;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4661.robot.commands.drivetrain.PassBaseLine;
import org.usfirst.frc.team4661.robot.commands.drivetrain.Turn90Left;
import org.usfirst.frc.team4661.robot.commands.drivetrain.Turn90Right;
import org.usfirst.frc.team4661.robot.commands.lift.AutomaticLiftDown;
import org.usfirst.frc.team4661.robot.commands.lift.AutomaticLiftUp;
import org.usfirst.frc.team4661.robot.subsystems.Lift;
import org.usfirst.frc.team4661.robot.subsystems.OpeningArm;
import org.usfirst.frc.team4661.robot.subsystems.Vision;
import org.usfirst.frc.team4661.robot.subsystems.Climber;
import org.usfirst.frc.team4661.robot.subsystems.ClosingArm;
import org.usfirst.frc.team4661.robot.subsystems.DriveTrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	public static OI oi;
	public static ClosingArm closeArm;
	public static OpeningArm openArm;
	public static Lift lift;
	public static DriveTrain drive;
	public static Climber climber;
	public static Vision vision;
	Command autonomousCommand;
	SendableChooser<Command> chooser;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code. //
	 */

	@Override
	public void robotInit() {
		System.out.println(" roobot init  ---");

		vision = new Vision(CameraServer.getInstance().startAutomaticCapture());

		Talon armCloser = new Talon(RobotMap.CLOSING_ARM_SPEED_CONTROLLER_PORT);
		DigitalInput closingArmOpenLimit = new DigitalInput(RobotMap.CLOSING_ARM_MIN_MICRO_SWITCH_PORT);
		DigitalInput closingArmClosedLimit = new DigitalInput(RobotMap.CLOSING_ARM_MAX_MICRO_SWITCH_PORT);
		closeArm = new ClosingArm(armCloser, closingArmOpenLimit, closingArmClosedLimit);

		Talon liftMover = new Talon(RobotMap.LIFT_SPEED_CONTROLLER_PORT);
		DigitalInput liftUpLimit = new DigitalInput(RobotMap.LIFT_MAX_MICRO_SWITCH_PORT);
		DigitalInput liftDownLimit = new DigitalInput(RobotMap.LIFT_MIN_MICRO_SWITCH_PORT);
		lift = new Lift(liftDownLimit, liftUpLimit, liftMover);

		Talon leftRear = new Talon(RobotMap.LEFT_REAR_TALON_PORT);
		Talon rightRear = new Talon(RobotMap.RIGHT_REAR_TALON_PORT);
		Talon leftFront = new Talon(RobotMap.LEFT_FRONT_TALON_PORT);
		Talon rightFront = new Talon(RobotMap.RIGHT_FRONT_TALON_PORT);
		GearBox right = new GearBox(rightFront, rightRear);
		GearBox left = new GearBox(leftFront, leftRear);
		drive = new DriveTrain(left, right);
		SmartDashboard.putData(drive);

		Talon climbingArm = new Talon(RobotMap.CLIMBER_SPEED_CONTROLLER_PORT);
		DigitalInput climbUpLimit = new DigitalInput(RobotMap.CLIMB_MAX_MICRO_SWITCH_PORT);
		DigitalInput climbDownLimit = new DigitalInput(RobotMap.CLIMB_MIN_MICRO_SWITCH_PORT);
		climber = new Climber(climbingArm, climbUpLimit, climbDownLimit);

		chooser = new SendableChooser<>();
		AutomaticCloseLeftArm autoArmClose = new AutomaticCloseLeftArm();
		AutomaticOpenLeftArm autoArmOpen = new AutomaticOpenLeftArm();
		AutomaticLiftDown autoLiftUp = new AutomaticLiftDown();
		AutomaticLiftUp autoLiftDown = new AutomaticLiftUp();
		PassBaseLine passbaseline = new PassBaseLine();
		Turn90Right turn90right = new Turn90Right();
		Turn90Left turn90left = new Turn90Left();
		chooser.addDefault("by constant speed", passbaseline);
		chooser.addObject("closeArm", autoArmClose);
		chooser.addObject("openArm", autoArmOpen);
		chooser.addObject("LiftUp", autoLiftUp);
		chooser.addObject("LiftDown", autoLiftDown);
		chooser.addObject("rotateRight", turn90right);
		chooser.addObject("rotateLeft", turn90left);
		SmartDashboard.putData(passbaseline);
		SmartDashboard.putData(turn90right);
		SmartDashboard.putData(turn90left);
		SmartDashboard.putData(autoArmClose);
		SmartDashboard.putData(autoArmOpen);
		SmartDashboard.putData(autoLiftUp);
		SmartDashboard.putData(autoLiftDown);
		SmartDashboard.putData("Auto mode", chooser);

		oi = new OI();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
		 * ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
