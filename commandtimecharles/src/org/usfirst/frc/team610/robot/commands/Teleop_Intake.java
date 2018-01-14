package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Teleop_Intake extends Command {

	private OI oi;
	private Intake intake;
	
    public Teleop_Intake() {
       oi = OI.getInstance();
       intake = Intake.getInstance();
       
       requires(intake); 
    }

    // Called just before this Command runs the first time
    protected void initialize() { //use constructor instead
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    		if (oi.getDriver().getRawButton(7)) {
			intake.setIntake(1);
		}
		
		else if (oi.getDriver().getRawButton(8)) {
			intake.setIntake(-1);
		}
		
		else {
			intake.setIntake(0);
		}	
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
