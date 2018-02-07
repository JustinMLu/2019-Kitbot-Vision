package org.usfirst.frc.team610.robot.subsystems;


import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pigeon extends Subsystem {

    public static Pigeon instance;
    
    private PigeonIMU pigeon;
    

    private Intake intake;
   
    public static Pigeon getInstance() {
    	if (instance == null){
			instance = new Pigeon();
		}
		return instance;
    }
    
    
    private Pigeon() {
    	intake = Intake.getInstance();
    	
    	pigeon = new PigeonIMU(intake.getLeftIntake());	
    }
    
    //resets yaw and compass to 0
    public void reset() {
    	pigeon.setCompassAngle(0, 10);
    }
    
   //returns angle between 0 and 360
    public double getYaw() {
    	return pigeon.getFusedHeading();
    }
    
    public void resetYaw() {
    	pigeon.setYaw(0, 10);
    }
    
    //returns total degrees turned (capped at +-23040) (doesnt really wokr)
    public double getCumulativeCompass() {
    	return pigeon.getCompassHeading();
    }
    
    
    //returns how long the pigeon has been running in seconds (capped at 255)
    public int getTime() {
    	return pigeon.getUpTime();
    }


	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
    
    
    
}

