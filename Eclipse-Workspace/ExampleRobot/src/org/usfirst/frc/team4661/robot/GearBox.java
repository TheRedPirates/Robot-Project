package org.usfirst.frc.team4661.robot;

import edu.wpi.first.wpilibj.SpeedController;

public class GearBox {
	SpeedController front, rear;

	public GearBox(SpeedController front, SpeedController rear) {
		this.front = front;
		this.rear = rear;
	}

	public void set(double speedValue) {
		front.set(speedValue);
		rear.set(speedValue);
	}

	public void stop() {
		set(0);
	}
}
