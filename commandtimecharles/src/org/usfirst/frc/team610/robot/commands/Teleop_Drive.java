package org.usfirst.frc.team610.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.Robot;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

/**
 *
 */
public class Teleop_Drive extends Command {
	
	private OI oi;
	private DriveTrain driveTrain;
	
	double y;
	double x;
	
	public Teleop_Drive() {
		oi = OI.getInstance(); 
		driveTrain = DriveTrain.getInstance();
		
		requires(driveTrain);
	}
	
	protected void initialize() {
		driveTrain.resetEnc();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		y = oi.getDriver().getRawAxis(LogitechF310Constants.AXIS_LEFT_Y);
		x = oi.getDriver().getRawAxis(LogitechF310Constants.AXIS_RIGHT_X);
		
		driveTrain.setLeft(-(y + x));
		driveTrain.setRight((y - x));
		
		SmartDashboard.putNumber("leftRPM", Math.abs(driveTrain.getLeftRPM()));
		SmartDashboard.putNumber("rightRPM", Math.abs(driveTrain.getRightRPM()));
		
		SmartDashboard.putNumber("leftInches", driveTrain.getLeftInches());
		SmartDashboard.putNumber("rightInches", driveTrain.getRightInches());
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
