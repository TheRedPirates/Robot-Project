package org.usfirst.frc.team4661.robot.commands.lift;

import org.usfirst.frc.team4661.robot.Robot;
import org.usfirst.frc.team4661.robot.StringConsts;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutomaticLiftDown extends Command {
	double timeout;
	double speedValue;

	public AutomaticLiftDown() {
		if (!SmartDashboard.containsKey(StringConsts.LIFT_DURATION)) {
			SmartDashboard.putNumber(StringConsts.LIFT_DURATION, 0.5);
		}
		if (!SmartDashboard.containsKey(StringConsts.LIFT_SPEED)) {
			SmartDashboard.putNumber(StringConsts.LIFT_SPEED, 0.4);
		}
		requires(Robot.lift);
	}

	protected void initialize() {
		if (!SmartDashboard.containsKey(StringConsts.LIFT_DURATION)) {
			SmartDashboard.putNumber(StringConsts.LIFT_DURATION, 0.5);
		}
		if (!SmartDashboard.containsKey(StringConsts.LIFT_SPEED)) {
			SmartDashboard.putNumber(StringConsts.LIFT_SPEED, 0.4);
		}
		timeout = SmartDashboard.getNumber(StringConsts.LIFT_DURATION, 0.5);
		speedValue = SmartDashboard.getNumber(StringConsts.LIFT_SPEED, 0.4);
		setTimeout(timeout);
		// Could potentially add a timeout
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.lift.move(-speedValue);
	}

	@Override
	protected boolean isFinished() {
		return Robot.lift.isDown() || isTimedOut();// TODO Auto-generated method stub
	}

	protected void end() {
		Robot.lift.move(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}

}
