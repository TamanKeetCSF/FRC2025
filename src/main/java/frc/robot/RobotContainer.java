package frc.robot;

import frc.robot.commands.ElevatorCommands.SetElevatorMax;
import frc.robot.commands.ElevatorCommands.SetElevatorMin;
import frc.robot.commands.HangingCommands.hangCommand;
import frc.robot.commands.IntakeCommands.ManualSetIntake;
import frc.robot.commands.IntakeCommands.SetIntakeMax;
import frc.robot.commands.ElevatorCommands.ManualSetElevator;
import frc.robot.commands.DriveCommands.DriveWithJoysticks ;

import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.SwerveDriveSubsystem;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class RobotContainer {

  private final PS5Controller Player1Controller = new PS5Controller(0);
  private final XboxController Player2Controller = new XboxController(1);

  private final SwerveDriveSubsystem m_chasis = new SwerveDriveSubsystem();
  private final Elevator m_elevador = new Elevator();
  private final Hanger m_colgador = new Hanger();
  private final BallIntake m_bola = new BallIntake();
  public final Intake m_intake = new Intake();

  public RobotContainer() {
    configureBindings();
    //m_chasis.setDefaultCommand(new DriveWithJoysticks(m_chasis, Player1Controller));
    m_elevador.setDefaultCommand(new ManualSetElevator(m_elevador, Player2Controller));
    m_intake.setDefaultCommand(new ManualSetIntake(m_intake, Player2Controller));
    m_colgador.setDefaultCommand(new hangCommand(m_colgador, Player2Controller));
  }
  
  private void configureBindings() {
    // declaracion control 1

      final JoystickButton button1Start = new JoystickButton(Player1Controller, 8);
      final JoystickButton button1Select = new JoystickButton(Player1Controller, 7);
      final JoystickButton button1A = new JoystickButton(Player1Controller, 1);
      final JoystickButton button1B = new JoystickButton(Player1Controller, 2);
      final JoystickButton button1X = new JoystickButton(Player1Controller, 3);
      final JoystickButton button1Y = new JoystickButton(Player1Controller, 4);

    //declaracion control2
      final JoystickButton button2A = new JoystickButton(Player2Controller, 1);
      final JoystickButton button2B = new JoystickButton(Player2Controller, 2);
      final JoystickButton button2X = new JoystickButton(Player2Controller, 3);
      final JoystickButton button2Y = new JoystickButton(Player2Controller, 4);
      final JoystickButton button2Select = new JoystickButton(Player2Controller, 7);
      final JoystickButton button2Start = new JoystickButton(Player2Controller, 8);

      final JoystickButton button2BumperL = new JoystickButton(Player2Controller, 5);
      final JoystickButton button2BumperR = new JoystickButton(Player2Controller, 6);
      Trigger leftTrigger = new Trigger(() -> Player2Controller.getRawAxis(2) > 0.4); // Left trigger
      Trigger rightTrigger = new Trigger(() -> Player2Controller.getRawAxis(3) > 0.4); // Right trigger

    //bindings 

    //bindings subsistemas

      //intake


       button2A.onTrue(new SetIntakeMax(m_intake)); 
      button2B.onTrue(new InstantCommand(() -> m_intake.ponerAngulo(40)));
      button2X.onTrue(new InstantCommand(() -> m_intake.ponerAngulo(170)));

      rightTrigger.onTrue(new InstantCommand(() -> m_intake.Comer(),m_intake))
      .onFalse(new InstantCommand(() -> m_intake.DejarComer(),m_intake));

      leftTrigger.onTrue(new InstantCommand(() -> m_intake.DesComer(),m_intake))
      .onFalse(new InstantCommand(() -> m_intake.DejarComer(),m_intake));

      //elevator
      new POVButton(Player2Controller, 0).onTrue(new InstantCommand(() -> m_bola.marcoBaja()))
      .onFalse(new InstantCommand(() -> m_bola.marcoStop()));
      new POVButton(Player2Controller, 180).onTrue(new InstantCommand(() -> m_bola.marcoSube()))
      .onFalse(new InstantCommand(() -> m_bola.marcoStop()));;

      //ball intake
      button2BumperR.onTrue(new InstantCommand(() -> m_bola.ballIntakeComer(),m_bola))
      .onFalse(new InstantCommand(() -> m_bola.ballIntakeStop(),m_bola));

      button2BumperL.onTrue(new InstantCommand(() -> m_bola.ballIntakeSacar(),m_bola))
      .onFalse(new InstantCommand(() -> m_bola.ballIntakeStop(),m_bola));

      //colgar
      //button2Y.whileTrue(new InstantCommand(()-> m_colgador.setHanger(Player2Controller)))
      //.onFalse(new InstantCommand(()-> m_colgador.hangerStop()));

  }

  public Command getAutonomousCommand() {
    return null;
  }

}
