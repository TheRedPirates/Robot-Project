package org.usfirst.frc.team4661.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

// CR see comments on Climber
// CR rename
public class ClosingArm extends Subsystem {
	SpeedController closer; // CR you need a better name
	DigitalInput open;
	DigitalInput closed;

	public ClosingArm(SpeedController closer, DigitalInput open, DigitalInput closed) {
		this.closed = closed;
		this.open = open;
		this.closer = closer;
	}

	public boolean isOpen() {
		return open.get();
	}

	public boolean isClosed() {
		return closed.get();
	}

	public void move(double movementSpeed) {
		closer.set(movementSpeed);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

}
