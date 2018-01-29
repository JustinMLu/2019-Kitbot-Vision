package org.usfirst.frc.team610.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.subsystems.NavX;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 *
 */
public class Teleop_Drive extends Command {
	
	private OI oi;
	private DriveTrain driveTrain;
	private NavX navX;
	
	double y;
	double x;
	
	public Teleop_Drive() {
		oi = OI.getInstance(); 
		driveTrain = DriveTrain.getInstance();
		navX = NavX.getInstance();
		
		driveTrain.setBrakeMode();
		navX.reset();
		navX.resetYaw();
		
		requires(navX);
		requires(driveTrain);
	}
	
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		y = -oi.getDriver().getRawAxis(LogitechF310Constants.AXIS_LEFT_Y);
		x = oi.getDriver().getRawAxis(LogitechF310Constants.AXIS_RIGHT_X);
		
		driveTrain.setLeft(y + x); //set to -(y + x) if not working
		driveTrain.setRight(y - x);
		
		SmartDashboard.putNumber("Left Enc Ticks:", driveTrain.getLeftTicks());
		SmartDashboard.putNumber("Right Enc Ticks:", driveTrain.getRightTicks());
		
		SmartDashboard.putNumber("Left Enc Rotations:", driveTrain.getLeftRotations());
		SmartDashboard.putNumber("Right Enc Rotations:", driveTrain.getRightRotations());
		
		SmartDashboard.putNumber("NavX Angle:", navX.getAngle());
		
		SmartDashboard.putNumber("VelocityX:", navX.getVelocityX());
		SmartDashboard.putNumber("VelocityY:", navX.getVelocityY());
		SmartDashboard.putNumber("VelocityZ:", navX.getVelocityZ());
		
		System.out.println("NavX:" + navX.getAngle());	
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
