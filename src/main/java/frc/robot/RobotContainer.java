package frc.robot;


import frc.robot.commands.ElevatorCommands.potenciaElevador;
import frc.robot.commands.HangingCommands.HangRobot;
import frc.robot.commands.IntakeCommands.SetIntakeAngle;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;




public class RobotContainer {


  
    private final XboxController Player1Controller = new XboxController(0);
    private final XboxController Player2Controller = new XboxController(1);

    private final Elevator m_elevador = new Elevator();
    private final Hanger m_colgador = new Hanger();
    private final BallIntake m_bola = new BallIntake();
      public final Intake m_intake = new Intake();


  
  public RobotContainer() {
    configureBindings();
    m_intake.setDefaultCommand(new SetIntakeAngle(m_intake));
    m_elevador.setDefaultCommand(new potenciaElevador(m_elevador, Player2Controller.getRightX()));

  }
  
  
  private void configureBindings() {
    // control 1
      final JoystickButton button1Start = new JoystickButton(Player1Controller, 8);
      final JoystickButton button1Select = new JoystickButton(Player1Controller, 7);
      final JoystickButton button1A = new JoystickButton(Player1Controller, 1);
      final JoystickButton button1B = new JoystickButton(Player1Controller, 2);
      final JoystickButton button1X = new JoystickButton(Player1Controller, 3);
      final JoystickButton button1Y = new JoystickButton(Player1Controller, 4);

      //control2
      //botones
      final JoystickButton button2A = new JoystickButton(Player2Controller, 1);
      final JoystickButton button2B = new JoystickButton(Player2Controller, 2);
      final JoystickButton button2X = new JoystickButton(Player2Controller, 3);
      //final JoystickButton button2Y = new JoystickButton(Player2Controller, 4);
      //final JoystickButton button2Select = new JoystickButton(Player2Controller, 7);
      final JoystickButton button2Start = new JoystickButton(Player2Controller, 8);

    // gatillos
      final JoystickButton button2BumperL = new JoystickButton(Player2Controller, 5);
      final JoystickButton button2BumperR = new JoystickButton(Player2Controller, 6);
      Trigger leftTrigger = new Trigger(() -> Player2Controller.getRawAxis(2) > 0.4); // Left trigger
      Trigger rightTrigger = new Trigger(() -> Player2Controller.getRawAxis(3) > 0.4); // Right trigger


    

//bindings
      button2BumperR.onTrue(new InstantCommand(() -> m_bola.ballIntakeComer(),m_bola))
      .onFalse(new InstantCommand(() -> m_bola.ballIntakeStop(),m_bola));

      button2BumperL.onTrue(new InstantCommand(() -> m_bola.ballIntakeSacar(),m_bola))
      .onFalse(new InstantCommand(() -> m_bola.ballIntakeStop(),m_bola));

      rightTrigger.onTrue(new InstantCommand(() -> m_intake.comer(),m_intake))
      .onFalse(new InstantCommand(() -> m_intake.dejarComer(),m_intake));

      leftTrigger.onTrue(new InstantCommand(() -> m_intake.desComer(),m_intake))
      .onFalse(new InstantCommand(() -> m_intake.dejarComer(),m_intake));

      button2A.onTrue(new InstantCommand(() -> m_intake.ponerAngulo(90)));
      button2B.onTrue(new InstantCommand(() -> m_intake.ponerAngulo(40)));
      button2X.onTrue(new InstantCommand(() -> m_intake.ponerAngulo(170)));

      button2Start.whileTrue(new InstantCommand(()-> m_colgador.setHanger(Player2Controller.getLeftX())))
      .whileFalse(new InstantCommand(()-> m_colgador.setHanger(0)));

  }

  public Command getAutonomousCommand() {
    return null;
  }

}
