package frc.robot.commands.HangingCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hanger;

public class HangRobot extends Command {

  private final Hanger hang;
  private final  boolean button;
  private final double potencia;

  /** Creates a new HangRobot. */
  public HangRobot(Hanger hang, boolean button, double potencia) {
    this.hang = hang;
    this.button = button;
    this.potencia = potencia;
    addRequirements(hang);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(button){
        hang.setHanger(potencia);
    }
    else{
      hang.setHanger(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
