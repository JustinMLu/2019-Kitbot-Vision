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
	
    public T_MagicDrive() {
        driveTrain = DriveTrain.getInstance();
        driveTrain.setBrakeMode();
        oi = OI.getInstance();
        
        requires(driveTrain);
        
        driveTrain.setMagicPID(1, 0, 0, 1023 / driveTrain.maxVelocity);
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
		
		driveTrain.setVelocityLeft(left);
		driveTrain.setVelocityRight(right);
		
		driveTrain.setLeftCruiseVel(driveTrain.maxVelocity, 10); //normally 10
		driveTrain.setRightCruiseVel(driveTrain.maxVelocity, 10);
		
		driveTrain.setLeftAccel(driveTrain.maxVelocity, 10);
		driveTrain.setRightAccel(driveTrain.maxVelocity, 10);
		
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
