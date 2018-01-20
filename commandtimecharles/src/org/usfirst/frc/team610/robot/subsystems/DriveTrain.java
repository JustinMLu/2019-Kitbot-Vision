package org.usfirst.frc.team610.robot.subsystems;


import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private static DriveTrain instance;
	private TalonSRX left, right;
	
	
	public static DriveTrain getInstance() {
		if (instance == null) {
			instance = new DriveTrain();
		}
		return instance; 
	}
	
	private DriveTrain() {
		
		left = new TalonSRX(ElectricalConstants.DRIVE_LEFT);
		right = new TalonSRX(ElectricalConstants.DRIVE_RIGHT);
		
		left.setInverted(true);
		right.setInverted(true);
		
		left.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		right.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

	}
	
	public void setLeft(double speed) {
		left.set(ControlMode.PercentOutput, speed);
	}
	
	public void setRight(double speed) {
		right.set(ControlMode.PercentOutput, speed);
	}
	
	
	public void setBrakeMode() {
		left.setNeutralMode(NeutralMode.Brake);
		right.setNeutralMode(NeutralMode.Brake);
	}
	
	public void setCoastMode() {
		left.setNeutralMode(NeutralMode.Coast);
		right.setNeutralMode(NeutralMode.Coast);
	}
	
	
	public int getLeftTicks() {
		return -left.getSelectedSensorPosition(0) / 2; //returns 256
	}
	
	public int getRightTicks() {
		return right.getSelectedSensorPosition(0);
	}
	
	
	public double getLeftRPM() {
		return -left.getSelectedSensorVelocity(0) * 600 / 256;
	}
	
	public double getRightRPM() {
		return right.getSelectedSensorVelocity(0) * 600 / 128;
	}
	
	
	public double getLeftRotations() {
		return -left.getSelectedSensorPosition(0) / 512;
	}
	
	public double getRightRotations() {
		return right.getSelectedSensorPosition(0) / 256; 
	}
	
	
	public void resetEnc() {
		left.getSensorCollection().setQuadraturePosition(0, 10);
		right.getSensorCollection().setQuadraturePosition(0, 10);
	}

	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub	
	}

	public void workAround() {
		left.set(ControlMode.PercentOutput, 0);
		right.set(ControlMode.PercentOutput, 0);
	}
	

}
