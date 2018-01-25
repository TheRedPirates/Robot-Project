package org.usfirst.frc.team4661.robot.commands.arm;

import org.usfirst.frc.team4661.robot.Conts;
import org.usfirst.frc.team4661.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class OpenLeftArm extends Command {
	public OpenLeftArm() {
		requires(Robot.closeArm);
	}

	protected void initialize() {
		// Could potentially add a timeout
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.closeArm.move(Conts.OPEN_ARM);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.closeArm.isOpen();
	}

	protected void end() {
		Robot.closeArm.move(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

}
