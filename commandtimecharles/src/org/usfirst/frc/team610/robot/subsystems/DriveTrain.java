package org.usfirst.frc.team610.robot.subsystems;


import org.usfirst.frc.team610.robot.constants.ElectricalConstants;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem implements TalonWork {

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
	}

	
	public void setLeft(double speed) {
		left.set(ControlMode.PercentOutput, speed);
	}
	
	public void setRight(double speed) {
		right.set(ControlMode.PercentOutput, speed);
	}
	
	public TalonSRX getLeftTalon() {
		return left;
	}
	
	public int getLeftEnc() {
		return -left.getSensorCollection().getQuadraturePosition();
	}
	
	//might need to be inverted: not checked yet
	public int getRightEnc() {
		return right.getSensorCollection().getQuadraturePosition();
	}

	
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void talonWorkAround() {
		left.getControlMode(); //idk if this works lol
		right.getControlMode();
	}

}
