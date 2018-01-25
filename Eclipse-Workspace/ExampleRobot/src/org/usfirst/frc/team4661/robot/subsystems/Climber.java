package org.usfirst.frc.team4661.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	SpeedController lifter;
	DigitalInput lifted;
	DigitalInput down;

	public Climber(SpeedController lifter, DigitalInput lifted, DigitalInput down) {
		this.down = down;
		this.lifted = lifted;
		this.lifter = lifter;
	}

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
