package org.usfirst.frc.team4661.robot.commands.drivetrain;

import org.usfirst.frc.team4661.robot.Robot;
import org.usfirst.frc.team4661.robot.StringConsts;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turn90Right extends Command {
	double timeout;

	public Turn90Right() {
		requires(Robot.drive);
		if (!SmartDashboard.containsKey(StringConsts.RIGHT_ROTATE_SPEED)) {
			SmartDashboard.putNumber(StringConsts.RIGHT_ROTATE_SPEED, 0.4);
		}
		if (!SmartDashboard.containsKey(StringConsts.ROTATE_TIME)) {
			SmartDashboard.putNumber(StringConsts.ROTATE_TIME, 1);
		}
	}

	protected void initialize() {
		if (!SmartDashboard.containsKey(StringConsts.RIGHT_ROTATE_SPEED)) {
			SmartDashboard.putNumber(StringConsts.RIGHT_ROTATE_SPEED, 0.4);
		}
		if (!SmartDashboard.containsKey(StringConsts.ROTATE_TIME)) {
			SmartDashboard.putNumber(StringConsts.ROTATE_TIME, 1);
		}
		timeout = SmartDashboard.getNumber(StringConsts.ROTATE_TIME, 1);
		setTimeout(timeout);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double speedValue = SmartDashboard.getNumber(StringConsts.RIGHT_ROTATE_SPEED, 0.4);
		Robot.drive.tank(speedValue, -speedValue);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drive.stop();
	}

	protected void interrupted() {
		end();
	}

}
