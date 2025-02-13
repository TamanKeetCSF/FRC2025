package frc.robot;

import frc.robot.commands.Autos;
import edu.wpi.first.wpilibj2.command.Command;



public class RobotContainer {
  
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {

  }

  public Command getAutonomousCommand() {
    return Autos.exampleAuto(null);
  }

}
