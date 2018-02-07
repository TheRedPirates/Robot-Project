package org.usfirst.frc.team4661.robot.subsystems;

import org.usfirst.frc.team4661.robot.GearBox;
import org.usfirst.frc.team4661.robot.StringConsts;
import org.usfirst.frc.team4661.robot.commands.drivetrain.TankDriveByJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {
	GearBox left;
	GearBox right;

	public DriveTrain(GearBox left, GearBox right) {
		this.left = left;
		this.right = right;
		if (!SmartDashboard.containsKey(StringConsts.DRIVETRAIN_LIMIT)) { // CR we don't need this limit anymore.
			SmartDashboard.putNumber(StringConsts.DRIVETRAIN_LIMIT, 0.4);
		}
		if (!SmartDashboard.containsKey(StringConsts.LEFT_CORRECTION)) {
			SmartDashboard.putNumber(StringConsts.LEFT_CORRECTION, 1);
		}
	}

	public void tank(double leftSpeed, double rightSpeed) {
		// CR you already ensured these exist in the c'tor. Remember: DRY - don't repeat yourselves.
		if (!SmartDashboard.containsKey(StringConsts.LEFT_CORRECTION)) {
			SmartDashboard.putNumber(StringConsts.LEFT_CORRECTION, 1);
		}
		if (!SmartDashboard.containsKey(StringConsts.DRIVETRAIN_LIMIT)) {
			SmartDashboard.putNumber(StringConsts.DRIVETRAIN_LIMIT, 0.4);
		}
		double correction = SmartDashboard.getNumber(StringConsts.LEFT_CORRECTION, 1);
		double factor = SmartDashboard.getNumber(StringConsts.DRIVETRAIN_LIMIT, 1);

		leftSpeed *= factor * correction;
		rightSpeed *= factor;  // CR we don't need this limit anymore.

		left.set(leftSpeed);
		right.set(-rightSpeed);
	}

	public void stop() {
		tank(0, 0);
	}

	/*
	 * public void rotate(double speed) { tank(speed,-speed); }
	 */
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TankDriveByJoystick());
	}
}
