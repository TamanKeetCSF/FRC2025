// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.*;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;

import frc.robot.Constants.SensorConstants;

public class Elevator extends SubsystemBase {
  /** Creates a new Elevator. */
  private final SparkMax m_elevator;
  private final DigitalInput limitMagneticSwitch;
  private final DigitalInput limitMechanicalSwitch;


    // Add encoders
    public Elevator() {
        m_elevator = new SparkMax(1, MotorType.kBrushless);
        limitMagneticSwitch = new DigitalInput(SensorConstants.MAGNETICSENSOR_DIGITAL_INPUT_PORT);
        limitMechanicalSwitch = new DigitalInput(SensorConstants.MECHANICALSWITCH_DIGITAL_INPUT_PORT);
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }

    public void setElevator(double potencia) {
      m_elevator.set(-potencia);  
    }

    public void elevatorStop() {
        m_elevator.set(0.0);  

    }

    public boolean IsElevatorMax(){
      return limitMagneticSwitch.get();
    }

    public boolean IsElevatorMin(){
      return limitMechanicalSwitch.get();
    }

    public double getElevatorSpeed() {
        RelativeEncoder encoderElevador = m_elevator.getEncoder();
        return (encoderElevador.getVelocity());
    }

}