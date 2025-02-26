// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

public class potenciaElevador extends Command {

  private final Elevator elevador;
  private final double potencia;


  /** Creates a new MoveElevatorToHeight. */
  public potenciaElevador(Elevator elevador, double potencia) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.elevador = elevador;
    this.potencia = potencia;
    addRequirements(elevador);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    elevador.ponerElevador(potencia);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevador.ponerElevador(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
