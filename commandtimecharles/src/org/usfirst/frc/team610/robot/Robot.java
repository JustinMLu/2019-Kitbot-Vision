
package org.usfirst.frc.team610.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.subsystems.Intake;
import org.usfirst.frc.team610.robot.commands.Auton_CommandPID;
import org.usfirst.frc.team610.robot.commands.Auton_TalonPID;
import org.usfirst.frc.team610.robot.commands.T_MagicDrive;
import org.usfirst.frc.team610.robot.commands.T_VelocityDrive;
import org.usfirst.frc.team610.robot.commands.Teleop;
import org.usfirst.frc.team610.robot.commands.Teleop_Drive;
import org.usfirst.frc.team610.robot.commands.Teleop_Intake;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	CommandGroup teleop;
	Command auton, customAuton;
	Command magicTeleop, velTeleop;
	
	public static OI oi;
	private DriveTrain driveTrain;
	private Intake intake;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		oi = OI.getInstance();
		
		teleop = new Teleop();
		velTeleop = new T_VelocityDrive();
		magicTeleop = new T_MagicDrive();
		
		auton = new Auton_TalonPID();
		customAuton = new Auton_CommandPID();
		
		driveTrain = DriveTrain.getInstance();
		intake = Intake.getInstance();
		
		driveTrain.resetEnc();
		
	}

	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() { //nothing
	}

	@Override
	public void disabledPeriodic() {
		
		driveTrain.workAround();
		intake.workAround();
		
		Scheduler.getInstance().run();
	}

	
	@Override
	public void autonomousInit() {
		teleop.cancel();
		auton.start();
		driveTrain.resetEnc();
	}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		teleop.start();
		auton.cancel();
		driveTrain.resetEnc();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}
