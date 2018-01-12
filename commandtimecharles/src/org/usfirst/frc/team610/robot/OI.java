package org.usfirst.frc.team610.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

import org.usfirst.frc.team610.robot.commands.Teleop_Drive;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Joystick driver;
	public static OI instance; //declares a static oi instance w/ the name 'instance'
	
	private OI (){ //so that no other classes can create instances
		driver = new Joystick(0);
	}
	
	public static OI getInstance() { //this must be static so you can return the instance w/o need of initializing another instance
		if(instance == null){
			instance = new OI();
		}
		return instance;
	}
	
	public Joystick getDriver() {
		return driver;
	}
	
	/* 
	public double getDriverAxis(int axis) {
		return driver.getRawAxis(axis);
	}
	*/
}
