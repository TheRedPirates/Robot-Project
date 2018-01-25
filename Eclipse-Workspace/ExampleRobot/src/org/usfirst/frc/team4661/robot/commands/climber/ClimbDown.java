package org.usfirst.frc.team4661.robot.commands.climber;

import org.usfirst.frc.team4661.robot.Conts;
import org.usfirst.frc.team4661.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbDown extends Command {
	public ClimbDown() {
		requires(Robot.climber);
	}

	protected void initialize() {
		// Could potentially add a timeout

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.climber.move(Conts.CLIMB_DOWN_SPEED);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.climber.isDown(); // execute already checks for this
	}

	protected void end() {
		Robot.climber.move(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

}
