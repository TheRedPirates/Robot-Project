package org.usfirst.frc.team4661.robot.commands.arm;

import org.usfirst.frc.team4661.robot.Conts;
import org.usfirst.frc.team4661.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CloseRightArm extends Command {
	public CloseRightArm() {

		requires(Robot.openArm);
	}

	protected void initialize() {
		// Could potentially add a timeout
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.openArm.move(Conts.LIFT_DOWN_SPEED);
	}

	@Override
	protected boolean isFinished() {
		return Robot.openArm.isClosed();// TODO Auto-generated method stub
	}

	protected void end() {
		Robot.openArm.move(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

}
