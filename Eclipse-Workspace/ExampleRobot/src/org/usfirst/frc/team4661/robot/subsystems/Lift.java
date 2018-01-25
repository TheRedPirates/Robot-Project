package org.usfirst.frc.team4661.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {
	DigitalInput down, up;
	SpeedController lift;

	public Lift(DigitalInput down, DigitalInput up, SpeedController lift) {
		this.down = down;
		this.up = up;
		this.lift = lift;
	}

	public boolean isUp() {
		return up.get();
	}

	public boolean isDown() {
		return down.get();
	}

	public void move(double speed) {
		lift.set(speed);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
