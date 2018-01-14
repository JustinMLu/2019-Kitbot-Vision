package org.usfirst.frc.team610.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Teleop extends CommandGroup {

    public Teleop() {
    		
    		addParallel(new Teleop_Drive());
    		addParallel(new Teleop_Intake());
    }
}
