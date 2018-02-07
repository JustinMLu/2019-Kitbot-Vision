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
	
	public int leftMaxVelocity = 800; //per 100ms
	public int rightMaxVelocity = 865;

	public static DriveTrain getInstance() {
		if (instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}

	private DriveTrain() {

		left = new TalonSRX(ElectricalConstants.DRIVE_LEFT);
		right = new TalonSRX(ElectricalConstants.DRIVE_RIGHT);

		left.setInverted(false); 
		right.setInverted(true);

		left.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		right.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);


		left.config_kP(0, 0, 10);
		left.config_kI(0, 0, 10);
		left.config_kD(0, 0, 10);
		left.config_kF(0, 0, 10);
		
		
		left.setSensorPhase(true);

		right.config_kP(0, 0, 10); //weird PID shit, have to halve it
		right.config_kI(0, 0, 10);
		right.config_kD(0, 0, 10);
		right.config_kF(0, 0, 10);
		
		right.setSensorPhase(true);
	}

	public TalonSRX getLeft() {
		return left;
	}
	
	public TalonSRX getRight() {
		return right;
	}
	
	public void setLeft(double speed) {
		left.set(ControlMode.PercentOutput, speed);
	}

	public void setRight(double speed) {
		right.set(ControlMode.PercentOutput, speed);
	}

	public void setPIDLeft(double rotations) {
		left.set(ControlMode.Position, rotations * 256 * 4); //electrically reversed
	}

	public void setPIDRight(double rotations) {
		right.set(ControlMode.Position, rotations * 256 * 4); //BS 1024 "encoder units"
	}
	
	public void setVelocityLeft(double command) { //negative
		left.set(ControlMode.Velocity, command); //-command * maxVelocity
	}
	
	public void setVelocityRight(double command) {
		right.set(ControlMode.Velocity, command); //command * maxVelocity 
	}
	
	public void setMagicLeft(double command) { //negative
		left.set(ControlMode.MotionMagic, command); //-command * maxVelocity
	}
	
	public void setMagicRight(double command) {
		right.set(ControlMode.MotionMagic, command); //command * maxVelocity 
	}
	
	public void setLeftCruiseVel(int velocity, int timeoutMs) { //negative
		left.configMotionCruiseVelocity(velocity, timeoutMs);
	}
	
	public void setRightCruiseVel(int velocity, int timeoutMs) {
		right.configMotionCruiseVelocity(velocity, timeoutMs);
	}
	
	public void setLeftAccel(int accel, int timeoutMs) { //negative
		left.configMotionAcceleration(accel, timeoutMs);
	}
	
	public void setRightAccel(int accel, int timeoutMs) {
		right.configMotionAcceleration(accel, timeoutMs);
	}

	public void setLeftMagicPID(double kp, double ki, double kd, double kf) {
		
		left.config_kP(0, kp, 10); 
		left.config_kI(0, ki, 10); 
		left.config_kD(0, kd, 10); 
		left.config_kF(0, kf, 10); 
	}
	
	public void setRightMagicPID(double kp, double ki, double kd, double kf) {
		right.config_kP(0, kp, 10);
		right.config_kI(0, ki, 10);
		right.config_kD(0, kd, 10);
		right.config_kF(0, kf, 10);
	}
	
	public void setPID(double kp, double ki, double kd, double kf) {
		
		left.config_kP(0, kp, 10); //0.265
		left.config_kI(0, ki, 10); //0
		left.config_kD(0, kd, 10); //0.1
		left.config_kF(0, kf, 10); //0
		
		right.config_kP(0, kp, 10);
		right.config_kI(0, ki, 10);
		right.config_kD(0, kd, 10);
		right.config_kF(0, kf, 10);
	}
 	
	public void setBrakeMode() {
		left.setNeutralMode(NeutralMode.Brake);
		right.setNeutralMode(NeutralMode.Brake);
	}

	public void setCoastMode() {
		left.setNeutralMode(NeutralMode.Coast);
		right.setNeutralMode(NeutralMode.Coast);
	}
	
	public void setClosedRampMode(double ramp, int timeout) {
		left.configClosedloopRamp(ramp, timeout); 
		right.configClosedloopRamp(ramp, timeout);
	}
	
	public void setOpenRampMode(double ramp, int timeout) {
		left.configOpenloopRamp(ramp, timeout);
		right.configOpenloopRamp(ramp, timeout);
	}

	public double getLeftTicks() {
		return left.getSelectedSensorPosition(0) / 4.0; //returns 256, divide by 2
	}

	public double getRightTicks() {
		return right.getSelectedSensorPosition(0) / 4.0; 
	}
	
	public double getLeftRPM() {
		return left.getSelectedSensorVelocity(0) * 600 / 256 / 4.0; //the 4 ticks per tick is untested right now
	}

	public double getRightRPM() {
		return right.getSelectedSensorVelocity(0) * 600 / 256 / 4.0;
	}

	public double getLeftRotations() {
		return left.getSelectedSensorPosition(0) / (256 * 4.0);
	}

	public double getRightRotations() {
		return right.getSelectedSensorPosition(0) / (256 * 4.0);
	}
	
	public double getLeftVelocity() {
		return left.getSelectedSensorVelocity(0);
	}
	
	public double getRightVelocity() {
		return right.getSelectedSensorVelocity(0);
	}
	
	public void resetEnc() {
		left.setSelectedSensorPosition(0, 0, 10);
		right.setSelectedSensorPosition(0, 0, 10);
	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void workAround() {
		left.set(ControlMode.PercentOutput, 0);
		right.set(ControlMode.PercentOutput, 0);
	}

}
