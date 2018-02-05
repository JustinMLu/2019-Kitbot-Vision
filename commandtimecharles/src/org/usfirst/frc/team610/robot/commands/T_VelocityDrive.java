package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class T_VelocityDrive extends Command {

	private DriveTrain driveTrain;
	private OI oi;
	double y, x;
	double left, right; 
	double leftTargetVelocity, rightTargetVelocity, leftTargetPos, rightTargetPos;
	
    public T_VelocityDrive() {
        driveTrain = DriveTrain.getInstance();
        driveTrain.setBrakeMode();
        oi = OI.getInstance();
        
        requires(driveTrain);
        
        driveTrain.setLeftMagicPID(0, 0, 0, 1.294);
        driveTrain.setRightMagicPID(0, 0, 0, 1.183);
        
        driveTrain.setLeftCruiseVel(600, 10); 
		driveTrain.setRightCruiseVel(649, 10);
		
		driveTrain.setLeftAccel(600, 10);
		driveTrain.setRightAccel(649, 10);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    		y = -oi.getDriver().getRawAxis(LogitechF310Constants.AXIS_LEFT_Y); //LogitechF310Constants.AXIS_LEFT_Y
		x = oi.getDriver().getRawAxis(LogitechF310Constants.AXIS_RIGHT_X); //LogitechF310Constants.AXIS_RIGHT_X
    	
		left = (y + x);
		right = (y - x);
		
		/*
		Joystick Input * 255 Units/Rev * 10 rotations in either direction
		*/
		
		leftTargetVelocity = left * driveTrain.leftMaxVelocity; //original: left * 1024 * 465.0 / 600;
		rightTargetVelocity = right * driveTrain.rightMaxVelocity; //original: right * 1024 * 510.0 / 600
		
		//position = velocity * time
		leftTargetPos = left * 1024 * 10.0; //left * 255 * 10.0
		rightTargetPos = right * 1024 * 10.0; 
		
		driveTrain.setVelocityLeft(leftTargetVelocity); //accepts POSITION as a param
		driveTrain.setVelocityRight(rightTargetVelocity); //rightTargetPos
		
		
		SmartDashboard.putNumber("Left Enc Ticks:", driveTrain.getLeftTicks());
		SmartDashboard.putNumber("Right Enc Ticks:", driveTrain.getRightTicks());
		
		SmartDashboard.putNumber("Left Enc Rotations:", driveTrain.getLeftRotations());
		SmartDashboard.putNumber("Right Enc Rotations:", driveTrain.getRightRotations());
		
		SmartDashboard.putNumber("Left RPM:", driveTrain.getLeftRPM());
		SmartDashboard.putNumber("Right RPM:", driveTrain.getRightRPM());
		
		SmartDashboard.putNumber("Left V:", driveTrain.getLeftVelocity());
		SmartDashboard.putNumber("Right V:", driveTrain.getRightVelocity());
		
		SmartDashboard.putNumber("Left TargetV:", leftTargetVelocity);
		SmartDashboard.putNumber("Right TargetV:", rightTargetVelocity);
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
