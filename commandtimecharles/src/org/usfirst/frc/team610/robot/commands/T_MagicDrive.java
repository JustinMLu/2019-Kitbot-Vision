package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class T_MagicDrive extends Command {

	private DriveTrain driveTrain;
	private OI oi;
	double y, x;
	double left, right; 
	double leftTargetVelocity, rightTargetVelocity;
	
    public T_MagicDrive() {
        driveTrain = DriveTrain.getInstance();
        driveTrain.setBrakeMode();
        oi = OI.getInstance();
        
        requires(driveTrain);
        
        driveTrain.setMagicPID(1, 0, 0, (1023 / driveTrain.maxVelocity));
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    		y = oi.getDriver().getRawAxis(LogitechF310Constants.AXIS_LEFT_Y); //LogitechF310Constants.AXIS_LEFT_Y
		x = oi.getDriver().getRawAxis(LogitechF310Constants.AXIS_RIGHT_X); //LogitechF310Constants.AXIS_RIGHT_X
    	
		left = (x - y);
		right = (x + y);
		
		/*
		255 Units/Rev * 500 RPM / 600 100ms/min in either direction:
		velocity setpoint is in units/100ms
		*/
		
		//experimental calculations
		leftTargetVelocity = left * 255 * 465.0 / 600; 
		rightTargetVelocity = right * 255 * 510.0 / 600;
		
		driveTrain.setVelocityLeft(leftTargetVelocity);
		driveTrain.setVelocityRight(rightTargetVelocity);
		
		driveTrain.setLeftCruiseVel(driveTrain.maxVelocity, 10); //normally 10
		driveTrain.setRightCruiseVel(driveTrain.maxVelocity, 10);
		
		driveTrain.setLeftAccel(driveTrain.maxVelocity, 10);
		driveTrain.setRightAccel(driveTrain.maxVelocity, 10);
		
		SmartDashboard.putNumber("Left Enc Ticks:", driveTrain.getLeftTicks());
		SmartDashboard.putNumber("Right Enc Ticks:", driveTrain.getRightTicks());
		
		SmartDashboard.putNumber("Left Enc Rotations:", driveTrain.getLeftRotations());
		SmartDashboard.putNumber("Right Enc Rotations:", driveTrain.getRightRotations());
		
		SmartDashboard.putNumber("Left RPM:", driveTrain.getLeftRPM());
		SmartDashboard.putNumber("Right RPM:", driveTrain.getRightRPM());
		
		SmartDashboard.putNumber("Left V:", driveTrain.getLeftVelocity());
		SmartDashboard.putNumber("Right V:", driveTrain.getRightVelocity());		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
