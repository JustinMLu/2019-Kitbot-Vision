package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.PID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Auton_PIDForwards extends Command {

	boolean commandState;
	private PID fwdPID;
	
    public Auton_PIDForwards() {
        commandState = false;
        fwdPID = PID.getInstance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		fwdPID.PIDStraight(768, 0.1, 0.05); 		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return commandState;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
