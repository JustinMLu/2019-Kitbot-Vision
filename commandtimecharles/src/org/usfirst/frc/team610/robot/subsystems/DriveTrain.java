package org.usfirst.frc.team610.robot.subsystems;


import org.usfirst.frc.team610.robot.constants.ElectricalConstants;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private static DriveTrain instance;
	private TalonSRX left, right;

	private Encoder leftEnc, rightEnc;
	
	
	public static DriveTrain getInstance() {
		if (instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}
	
	private DriveTrain() {
		
		left = new TalonSRX(ElectricalConstants.DRIVE_LEFT);
		right = new TalonSRX(ElectricalConstants.DRIVE_RIGHT);
		
		leftEnc = new Encoder(ElectricalConstants.DRIVE_ENC_LEFT_A, ElectricalConstants.DRIVE_ENC_LEFT_B, false);
		rightEnc = new Encoder(ElectricalConstants.DRIVE_ENC_RIGHT_A, ElectricalConstants.DRIVE_ENC_RIGHT_B, false);
	}

	
	public void setLeft(double speed) {
		left.set(ControlMode.PercentOutput, speed);
	}
	
	public void setRight(double speed) {
		right.set(ControlMode.PercentOutput, speed);
	}
	
	
	public void workAround() {
		left.getControlMode(); //idk if this works lol
		right.getControlMode();
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
