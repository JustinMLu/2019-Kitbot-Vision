package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.subsystems.NavX;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auton_PIDTurn extends Command {

	private DriveTrain driveTrain;
	private NavX navX;
	
	private double targetAngle;
	private double error;
	private double prevError;
	private double motorPower;
	private double iCounter;
	
	private double p;
	private double i;
	private double d;
	
	private double Kp;
	private double Ki;
	private double Kd;
	
    public Auton_PIDTurn(double gyroAngle, double kp, double ki, double kd) {
        navX = NavX.getInstance();
        driveTrain = DriveTrain.getInstance();
        
        this.targetAngle = gyroAngle;
        this.prevError = 0;
        
        this.Kp = kp;
        this.Ki = ki;
        this.Kd = kd;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    		error = targetAngle - navX.getAngle();
    		
    		p = Kp * error;
    		
    		iCounter += error;
    		i = Ki * iCounter;
    		d = Kd * (error - prevError);
    		
    		motorPower = p + i + d;
    		prevError = error;
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
