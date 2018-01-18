package org.usfirst.frc.team610.robot.subsystems;


import org.usfirst.frc.team610.robot.constants.ElectricalConstants;


import com.ctre.phoenix.motorcontrol.ControlMode;
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

	}
	
	public void setLeft(double speed) {
		left.set(ControlMode.PercentOutput, speed);
	}
	
	public void setRight(double speed) {
		right.set(ControlMode.PercentOutput, speed);
	}
	
	public double getLeftRPM() {
		return left.getSensorCollection().getQuadratureVelocity() / 2.0;
	}
	
	public double getRightRPM() {
		return right.getSensorCollection().getQuadratureVelocity();
	}
	
	public double getLeftTicks() {
		return -(left.getSensorCollection().getQuadraturePosition() / 8); //returns 128 = 1 rotation
	}
	
	public double getRightTicks() {
		return right.getSensorCollection().getQuadraturePosition() / 4; //returns 128 = 1 rotation
	}
	
	public double getLeftRotations() {
		return -(left.getSensorCollection().getQuadraturePosition() / 1024);
	}
	
	public double getRightRotations() {
		return right.getSensorCollection().getQuadraturePosition() / 512; 
	}
	
	
	public void resetEnc() {
		left.getSensorCollection().setQuadraturePosition(0, 10);
		right.getSensorCollection().setQuadraturePosition(0, 10);
	}

	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub	
	}

	public void workAround() {
		left.setNeutralMode(NeutralMode.Brake);
		right.setNeutralMode(NeutralMode.Brake);
	}
	

}
