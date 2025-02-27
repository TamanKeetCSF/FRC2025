package frc.robot.commands.DriveCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDriveSubsystem;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj.XboxController;

public class DriveWithJoysticks extends Command {
    
    private final SwerveDriveSubsystem swerveDrive;
    private final PS5Controller controller;
    
    public DriveWithJoysticks(SwerveDriveSubsystem swerveDrive, PS5Controller player1Controller) {
        this.swerveDrive = swerveDrive;
        this.controller = player1Controller;
        addRequirements(swerveDrive);
    }
    
    @Override
    public void execute() {
        double xSpeed = -controller.getLeftX(); 
        double ySpeed = -controller.getLeftY(); 
        double rotation = controller.getRightX(); 

        double leftTrigger = controller.getL2Axis(); 
        if (leftTrigger > 0.1) {
            double slowModeFactor = 0.5; 
            xSpeed *= slowModeFactor;
            ySpeed *= slowModeFactor;
            rotation *= slowModeFactor;
        }
        //System.out.println("lectura del control x" + xSpeed);
        //System.out.println("lectura del control y" + ySpeed);
        //System.out.println("lectura del control r" + rotation);
        swerveDrive.drive(xSpeed, ySpeed, rotation);
    }
    
    @Override
    public void end(boolean interrupted) {
        swerveDrive.stop();
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
}
