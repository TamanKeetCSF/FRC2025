
package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevator;
import frc.robot.Constants;
import frc.robot.Constants.OperatorConstants;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class SetElevatorMax extends Command {

  private final Elevator elevator;
  /** Creates a new SetElevatorMax. */
  public SetElevatorMax(Elevator elevator) {
    this.elevator = elevator;
    addRequirements(elevator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    int signDif =(elevator.getElevatorPosition()-Constants.OperatorConstants.DesiredMaxElevatorPosition > 0)?1:-1;
    elevator.setElevator(OperatorConstants.MaxElevatorSpeed*signDif);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevator.setElevator(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return elevator.IsElevatorMaxDesired();
  }
}