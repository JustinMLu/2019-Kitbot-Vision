package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.ElectricalConstants;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private static DriveTrain instance;
	private OI oi;
	private Victor left, right;
	private Encoder leftEnc, rightEnc;
	
	public static DriveTrain getInstance() {
		if (instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}
	
	private DriveTrain() {
		oi = OI.getInstance();
		
		left = new Victor(ElectricalConstants.DRIVE_LEFT);
		right = new Victor(ElectricalConstants.DRIVE_RIGHT);
		
		leftEnc = new Encoder(ElectricalConstants.DRIVE_ENC_LEFT_A, ElectricalConstants.DRIVE_ENC_LEFT_B, false);
		rightEnc = new Encoder(ElectricalConstants.DRIVE_ENC_RIGHT_A, ElectricalConstants.DRIVE_ENC_RIGHT_B, false);
	}
	
	public void setLeft(double speed) {
		left.set(speed);
	}
	
	public void setRight(double speed) {
		right.set(speed);
	}
	
	public void drive(double speedFactor) {
		double y = oi.getDriver().getRawAxis(LogitechF310Constants.AXIS_LEFT_Y);
		double x = oi.getDriver().getRawAxis(LogitechF310Constants.AXIS_RIGHT_X);
		
		setLeft((y - x) * speedFactor);
		setRight(-(y + x) * speedFactor);
	}
	
	public double getLeftInches() {
		return leftEnc.getDistance();
	}

	public double getRightInches() {
		return rightEnc.getDistance();
	}

	public double getRightRPM() {
		return rightEnc.getRate();
	}

	public double getLeftRPM() {
		return rightEnc.getRate();
	}

	public void resetEnc() {
		rightEnc.reset();
		leftEnc.reset();
	}
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
