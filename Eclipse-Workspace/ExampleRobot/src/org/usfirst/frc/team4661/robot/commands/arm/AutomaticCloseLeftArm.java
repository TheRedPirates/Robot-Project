package org.usfirst.frc.team4661.robot.commands.arm;

import org.usfirst.frc.team4661.robot.Robot;
import org.usfirst.frc.team4661.robot.StringConsts;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutomaticCloseLeftArm extends Command { // CR the two arms are one subsystem!!!
	double timeout;
	double speedValue;

	public AutomaticCloseLeftArm() {
		if (!SmartDashboard.containsKey(StringConsts.LEFT_CLOSING_ARM_DURATION)) {
			SmartDashboard.putNumber(StringConsts.LEFT_CLOSING_ARM_DURATION, 0.5);
		}
		if (!SmartDashboard.containsKey(StringConsts.LEFT_CLOSING_ARM_SPEED)) {
			SmartDashboard.putNumber(StringConsts.LEFT_CLOSING_ARM_SPEED, 0.4);
		}
		requires(Robot.closeArm);
	}

	protected void initialize() {
		if (!SmartDashboard.containsKey(StringConsts.LEFT_CLOSING_ARM_DURATION)) {
			SmartDashboard.putNumber(StringConsts.LEFT_CLOSING_ARM_DURATION, 0.5);
		}
		if (!SmartDashboard.containsKey(StringConsts.LEFT_CLOSING_ARM_SPEED)) {
			SmartDashboard.putNumber(StringConsts.LEFT_CLOSING_ARM_SPEED, 0.4);
		}
		timeout = SmartDashboard.getNumber(StringConsts.LEFT_CLOSING_ARM_DURATION, 0.5);
		speedValue = SmartDashboard.getNumber(StringConsts.LEFT_CLOSING_ARM_SPEED, 0.4);
		setTimeout(timeout);
		// Could potentially add a timeout
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.closeArm.move(speedValue);
	}

	@Override
	protected boolean isFinished() {
		return Robot.closeArm.isClosed() || isTimedOut();// TODO Auto-generated method stub
	}

	protected void end() {
		Robot.closeArm.move(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}

}
