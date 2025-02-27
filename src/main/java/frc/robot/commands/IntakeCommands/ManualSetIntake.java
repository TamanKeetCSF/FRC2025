package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ManualSetIntake extends Command {

  private final Intake intake;
  private final XboxController controller;


  /** Creates a new ManualSetIntake. */
 public ManualSetIntake(Intake intake, XboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intake = intake;
    this.controller= controller; 
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double potencia = -controller.getRightX();
    //System.out.println("minimo alcanzado" + elevator.IsElevatorMin());
    //System.out.println("maximo alcanzado" + elevator.IsElevatorMax());
    System.out.println("angulo intake"+ intake.getArmAngle());

   
    

    if((intake.getArmAngle() < Constants.OperatorConstants.MinArmPosition && potencia <= 0) || (intake.getArmAngle() > Constants.OperatorConstants.MaxArmPosition && potencia >= 0)){
    intake.setIntake(0);
    }
    else{
      intake.setIntake(potencia*0.2);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.setIntake(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}