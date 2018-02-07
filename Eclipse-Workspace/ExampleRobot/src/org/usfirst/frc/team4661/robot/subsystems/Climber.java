package org.usfirst.frc.team4661.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	SpeedController lifter;
	DigitalInput lifted; // CR be consistent: the opposite of "down" is "up"!
	DigitalInput down;

	public Climber(SpeedController lifter, DigitalInput lifted, DigitalInput down) {
		this.down = down;
		this.lifted = lifted;
		this.lifter = lifter;
	}

	// CR the code needs to handle case when the DigitalInput is null:
	// the isLifted method needs to return false always in this case.
	public boolean isLifted() {
		return lifted.get();
	}

	public boolean isDown() {
		return down.get();
	}

	public void move(double movementSpeed) {
		lifter.set(movementSpeed);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

}
