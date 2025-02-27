package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevator;

public class ManualSetElevator extends Command {
  
  private final Elevator elevator;
  private final XboxController controller;

  /** Creates a new MoveElevatorToHeight. */
  public ManualSetElevator(Elevator elevator, XboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.elevator = elevator;
    this.controller = controller;
    addRequirements(elevator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //if(!elevator.IsElevatorMax() && !elevator.IsElevatorMin())
    double potencia = -controller.getRightY();

    if((!elevator.IsElevatorMax() && potencia <= 0) || (!elevator.IsElevatorMin() && potencia >= 0)){
      elevator.setElevator(0);
    }
    else{
      elevator.setElevator(potencia);
    }
      
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevator.setElevator(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
