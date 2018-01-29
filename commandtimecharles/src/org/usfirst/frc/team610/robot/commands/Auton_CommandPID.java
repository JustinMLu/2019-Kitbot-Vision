package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Auton_CommandPID extends Command {

	private double turnError;
	private double prevTurnError;
	
	private double fwdError;
	private double prevFwdError;
	
	private double fwdP;
	private double fwdD;
	private double turnP;
	private double turnD;
	
	private double target;
	
	private double turnKp;
	private double fwdKp;
	private double turnKd;
	private double fwdKd;
	
	private DriveTrain driveTrain;
	
    public Auton_CommandPID() {
        driveTrain = DriveTrain.getInstance();
        
        driveTrain.setBrakeMode();
        
        requires(driveTrain);
        
        driveTrain.setOpenRampMode(0.075, 10);
        
        target = 4; //rotations
        fwdKp = 0.01;
        fwdKd = 0.0;
        
        turnKp = 0.006;
        turnKd = 0.0005;
        
        prevTurnError = 0;
        prevFwdError = 0;        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		
    		double avgTicks = (driveTrain.getRightTicks() + driveTrain.getLeftTicks()) / 2;
    		
    		//if positive, drivetrain is swerving to the left
    		turnP = (driveTrain.getRightTicks() - driveTrain.getLeftTicks()); //proportional
    		
    		turnD = (turnError - prevTurnError); //derivative
    		
    		fwdP = (target * 256) - avgTicks; //proportional	
    		
    		fwdD = (fwdError - prevFwdError); //derivative
    		
    		fwdError = (fwdP * fwdKp) + (fwdD * fwdKd); //final output
    		turnError = (turnP * turnKp) + (turnD * turnKd); //final output
    		
    		driveTrain.setLeft(fwdError + turnError);
    		driveTrain.setRight(fwdError - turnError);
    		
    		prevTurnError = turnError;
    		prevFwdError = fwdError;
    		
    		SmartDashboard.putNumber("Left Enc Ticks:", driveTrain.getLeftTicks());
    		SmartDashboard.putNumber("Right Enc Ticks:", driveTrain.getRightTicks());
    		
    		SmartDashboard.putNumber("Left Enc Rotations:", driveTrain.getLeftRotations());
    		SmartDashboard.putNumber("Right Enc Rotations:", driveTrain.getRightRotations());
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
