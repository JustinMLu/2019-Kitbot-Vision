package org.usfirst.frc.team610.robot.subsystems;


import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {

	public static Intake instance;
	private TalonSRX leftIntake, rightIntake;
	
	
	public static Intake getInstance() {
		
		if (instance == null) {
			instance = new Intake();
		}
		return instance;
	}
	
	
	private Intake() {

		leftIntake = new TalonSRX(ElectricalConstants.INTAKE_LEFT);
		rightIntake = new TalonSRX(ElectricalConstants.INTAKE_RIGHT);
	}
	
	
	public void setIntake(double speed) {
		leftIntake.set(ControlMode.PercentOutput, speed);
		rightIntake.set(ControlMode.PercentOutput, speed);
	}
	
	public void workAround() {
		leftIntake.getControlMode(); //maybe this will work
		rightIntake.getControlMode(); 
	}
	 
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub	
	}
	
}

