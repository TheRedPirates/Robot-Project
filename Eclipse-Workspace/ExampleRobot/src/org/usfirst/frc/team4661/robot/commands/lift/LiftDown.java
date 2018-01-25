package org.usfirst.frc.team4661.robot.commands.lift;

import org.usfirst.frc.team4661.robot.Conts;
import org.usfirst.frc.team4661.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftDown extends Command {

	public LiftDown() {
		requires(Robot.lift);

	}

	protected void initialize() {
		// Could potentially add a timeout

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.lift.move(Conts.LIFT_DOWN_SPEED);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.lift.isDown(); // execute already checks for this
	}

	// TODO Auto-generated method stub

	protected void end() {
		Robot.lift.move(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

}
