package org.usfirst.frc.team610.robot.commands;


import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Auton_TalonPID extends Command {

	private DriveTrain driveTrain;
	

    public Auton_TalonPID() {
        driveTrain = DriveTrain.getInstance();
        
        driveTrain.setBrakeMode();
        
        driveTrain.setClosedRampMode(0.27, 10);
        
        driveTrain.setPID(0.265, 0, 0.1, 0);
        
        requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    		SmartDashboard.putNumber("Left Enc Ticks:", driveTrain.getLeftTicks());
		SmartDashboard.putNumber("Right Enc Ticks:", driveTrain.getRightTicks());
		
		SmartDashboard.putNumber("Left Enc Rotations:", driveTrain.getLeftRotations());
		SmartDashboard.putNumber("Right Enc Rotations:", driveTrain.getRightRotations()); 
		
		driveTrain.setPIDRight(5);
    		driveTrain.setPIDLeft(5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; //TODO: make proper exit condition
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
