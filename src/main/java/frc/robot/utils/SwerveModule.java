package frc.robot.utils;

import com.revrobotics.spark.*;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.ctre.phoenix6.hardware.CANcoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.SwerveConstants;


public class SwerveModule extends SubsystemBase {
    
    private final SparkMax driveMotor;
    private final SparkMax steerMotor;
    private final CANcoder encoder;
    private final PIDController steerPID;
    
    public SwerveModule(int driveMotorID, int steerMotorID, int encoderID, boolean isInverted) {
        driveMotor = new SparkMax(driveMotorID, MotorType.kBrushless);
        steerMotor = new SparkMax(steerMotorID, MotorType.kBrushless);
        encoder = new CANcoder(encoderID);
        driveMotor.setInverted(isInverted);
        steerPID = new PIDController(SwerveConstants.STEER_P, SwerveConstants.STEER_I, SwerveConstants.STEER_D);
        SmartDashboard.putData("SwerveSteerPID", steerPID);
        steerPID.enableContinuousInput(-180, 180); 
        steerPID.setTolerance(5);
        }

    public void setDesiredState(SwerveModuleState desiredState) {
        // Get the current angle in degrees from the encoder.
        double rawEncoderAngle = encoder.getAbsolutePosition().getValueAsDouble() * 360;
        Rotation2d currentRotation = Rotation2d.fromDegrees(rawEncoderAngle);
       
        // Optimize desired state to reduce the amount of steering rotation.
        desiredState.optimize(desiredState, currentRotation);
        double targetAngle = desiredState.angle.getDegrees();
       
        // Update SmartDashboard for debugging.
        SmartDashboard.putNumber("CurrentAngle", rawEncoderAngle);
        SmartDashboard.putNumber("TargetAngle", targetAngle);
       
        // Calculate PID output.
        double steerOutput = steerPID.calculate(rawEncoderAngle, targetAngle);
       
        // Optional: If within deadband, force output to zero.
        if (Math.abs(rawEncoderAngle - targetAngle) <  10|| Math.abs(Math.abs(encoder.getAbsolutePosition().getValueAsDouble()*360 - targetAngle)-180) < 20) {
            steerOutput = 0;
        }
       
        // Drive output calculation remains unchanged, but check if cosine scaling is needed.
        double driveOutput = desiredState.speedMetersPerSecond
                    * Math.cos(Math.toRadians(rawEncoderAngle - targetAngle))
                    * SwerveConstants.MAX_WHEEL_SPEED_MPS / 10;

        double currentAngle = encoder.getAbsolutePosition().getValueAsDouble()*360; 

        double error= currentAngle - targetAngle;
        System.out.println("error" + error);
        System.out.println("PIDangulo" + steerOutput);
       
        driveMotor.set(driveOutput);
        steerMotor.set(steerOutput);  // Adjust scale factor if needed.
    }
    
    public Rotation2d getModuleAngle() {
        return Rotation2d.fromDegrees(encoder.getAbsolutePosition().getValueAsDouble());
    }
    
    public void stop() {
        driveMotor.set(0);
        steerMotor.set(0);
   }
}





