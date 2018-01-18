package org.usfirst.frc.team610.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PID {

	public static PID instance;
	private DriveTrain driveTrain;
	
	public static PID getInstance() {
		
		if (instance == null) {
			instance = new PID();
		}		
		return instance;
	}
	
	private PID() {
		driveTrain = DriveTrain.getInstance();
	}
	
	public void PIDStraight(double target, double Kp, double fwdKp) {
		
		double avgFwd = (driveTrain.getLeftTicks() + driveTrain.getRightTicks()) / 2;
		
		//if error is negative it means its turning 
		double turnError = Kp * (driveTrain.getLeftTicks() - driveTrain.getRightTicks());
		
		double fwdError = fwdKp * (target - avgFwd); 
		
		driveTrain.setLeft(fwdError - turnError);
		driveTrain.setRight(fwdError + turnError);
		
		SmartDashboard.putNumber("Distance error:", fwdError);
	}
	
// {-}7	
}
