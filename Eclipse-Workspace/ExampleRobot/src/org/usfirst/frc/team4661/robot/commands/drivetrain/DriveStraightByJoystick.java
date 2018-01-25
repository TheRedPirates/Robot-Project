package org.usfirst.frc.team4661.robot.commands.drivetrain;

import org.usfirst.frc.team4661.robot.Conts;
import org.usfirst.frc.team4661.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightByJoystick extends Command {

	public DriveStraightByJoystick() {
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Unlimited
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double speedValue = Robot.oi.getRightY();

		if (Math.abs(speedValue) < Conts.LIMIT) {
			speedValue = 0;
		}

		speedValue *= Math.abs(speedValue);

		Robot.drive.tank(speedValue, speedValue);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
