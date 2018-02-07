package org.usfirst.frc.team4661.robot.commands.drivetrain;

import org.usfirst.frc.team4661.robot.Robot;
import org.usfirst.frc.team4661.robot.StringConsts;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PassBaseLine extends Command {
	// CR this class needs command that explain it.
	double timeout;
	double k1; // CR rename
	double p; // CR rename
	double slowdownTime;
	int counter;

	public PassBaseLine() {
		requires(Robot.drive);
		if (!SmartDashboard.containsKey(StringConsts.PASS_LINE_SPEED)) {
			SmartDashboard.putNumber(StringConsts.PASS_LINE_SPEED, 0.4);
		}
		if (!SmartDashboard.containsKey(StringConsts.BASE_LINE_TIMEOUT)) {
			SmartDashboard.putNumber(StringConsts.BASE_LINE_TIMEOUT, 2.65);
		}
		if (!SmartDashboard.containsKey(StringConsts.MATH_A)) { // CR rename these consts.
			SmartDashboard.putNumber(StringConsts.MATH_A, 1);
		}
		if (!SmartDashboard.containsKey(StringConsts.MATH_B)) {
			SmartDashboard.putNumber(StringConsts.MATH_B, 1);
		}
		if (!SmartDashboard.containsKey(StringConsts.SLOWDOWN)) {
			SmartDashboard.putNumber(StringConsts.SLOWDOWN, 0.4);
		}
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		// CR don't repeat yourselves.
		
		if (!SmartDashboard.containsKey(StringConsts.PASS_LINE_SPEED)) {
			SmartDashboard.putNumber(StringConsts.PASS_LINE_SPEED, 0.4);
		}
		if (!SmartDashboard.containsKey(StringConsts.BASE_LINE_TIMEOUT)) {
			SmartDashboard.putNumber(StringConsts.BASE_LINE_TIMEOUT, 5);
		}
		if (!SmartDashboard.containsKey(StringConsts.MATH_A)) {
			SmartDashboard.putNumber(StringConsts.MATH_A, 1);
		}
		if (!SmartDashboard.containsKey(StringConsts.MATH_B)) {
			SmartDashboard.putNumber(StringConsts.MATH_B, 1);
		}
		if (!SmartDashboard.containsKey(StringConsts.SLOWDOWN)) {
			SmartDashboard.putNumber(StringConsts.SLOWDOWN, 0.0);
		}
		k1 = SmartDashboard.getNumber(StringConsts.MATH_A, 1);
		p = SmartDashboard.getNumber(StringConsts.MATH_B, 1);
		// timeout = SmartDashboard.getNumber(StringConsts.BASE_LINE_TIMEOUT, 5);
		slowdownTime = SmartDashboard.getNumber(StringConsts.SLOWDOWN, 0.5);
		timeout = SmartDashboard.getNumber(StringConsts.BASE_LINE_TIMEOUT, 0.4);
		setTimeout(timeout);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double speedValue = SmartDashboard.getNumber(StringConsts.PASS_LINE_SPEED, 0.4);

		if (timeSinceInitialized() >= timeout - slowdownTime && Math.abs(speedValue) > 0.1) {
			speedValue = k1 / (timeSinceInitialized() + p);
		} else {

		}
		Robot.drive.tank(speedValue, speedValue);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drive.stop();
		counter++;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}
