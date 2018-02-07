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
	double leftTargetVelocity, rightTargetVelocity, leftTargetPos, rightTargetPos;
	double driveMultiplier;
	
    public T_MagicDrive() {
        driveTrain = DriveTrain.getInstance();
        driveTrain.setCoastMode();
        oi = OI.getInstance();
        
        requires(driveTrain);
        
        driveTrain.setLeftMagicPID(2.5575, 0, 0, 1.294); //1.27875
        driveTrain.setRightMagicPID(2.5575, 0, 0, 1.294);
        
		driveTrain.setLeftCruiseVel(800, 10); //normally 600 (75%), maxVel is usually measured at 800
		driveTrain.setRightCruiseVel(800, 10);
		
		driveTrain.setLeftAccel(50000, 10); //normally 600 (75%), maxVel is usually measured at 800
		driveTrain.setRightAccel(50000, 10);
		
		driveMultiplier = 10; 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    		y = -oi.getDriver().getRawAxis(LogitechF310Constants.AXIS_LEFT_Y); //LogitechF310Constants.AXIS_LEFT_Y
		x = oi.getDriver().getRawAxis(LogitechF310Constants.AXIS_RIGHT_X); //LogitechF310Constants.AXIS_RIGHT_X
    		
		//deadzone
		if (y < 0.1 && y > -0.1) {
			y = 0;
		}
		
		if (x < 0.1 && x > -0.1) {
			x = 0;
		}
		
		left = (y + x);
		right = (y - x);
		
		//left * 1024 ticks/rev * rotations + current ticks in 1024 format
		leftTargetPos = left * left * left * 1024 * driveMultiplier + (driveTrain.getLeftTicks() * 4);
		rightTargetPos = right * right * right * 1024 * driveMultiplier + (driveTrain.getRightTicks() * 4); 
		
		driveTrain.setMagicLeft(leftTargetPos); 
		driveTrain.setMagicRight(rightTargetPos);

		
		SmartDashboard.putNumber("Left Enc Ticks:", driveTrain.getLeftTicks());
		SmartDashboard.putNumber("Right Enc Ticks:", driveTrain.getRightTicks());
		
		SmartDashboard.putNumber("Left Enc Rotations:", driveTrain.getLeftRotations());
		SmartDashboard.putNumber("Right Enc Rotations:", driveTrain.getRightRotations());
		
		SmartDashboard.putNumber("Left RPM:", driveTrain.getLeftRPM());
		SmartDashboard.putNumber("Right RPM:", driveTrain.getRightRPM());
		
		SmartDashboard.putNumber("Left V:", driveTrain.getLeftVelocity());
		SmartDashboard.putNumber("Right V:", driveTrain.getRightVelocity());
		
		SmartDashboard.putNumber("Left SideV", driveTrain.getLeft().getMotorOutputVoltage() / 12.0);
		SmartDashboard.putNumber("Left SideC", driveTrain.getLeft().getOutputCurrent());
		
		SmartDashboard.putNumber("Right SideV", driveTrain.getRight().getMotorOutputVoltage() / 12.0);
		SmartDashboard.putNumber("Right SideC", driveTrain.getRight().getOutputCurrent());
		
		
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
