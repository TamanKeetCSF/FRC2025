package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.SwerveConstants;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.geometry.Rotation2d;
import com.ctre.phoenix.sensors.PigeonIMU;
import frc.robot.utils.SwerveModule;

public class SwerveDriveSubsystem extends SubsystemBase {
    
    private final SwerveModule frontLeft = new SwerveModule(
        SwerveConstants.FRONT_LEFT_DRIVE_MOTOR, 
        SwerveConstants.FRONT_LEFT_STEER_MOTOR, 
        SwerveConstants.FRONT_LEFT_CANCODER,
        true);
    
    private final SwerveModule frontRight = new SwerveModule(
        SwerveConstants.FRONT_RIGHT_DRIVE_MOTOR, 
        SwerveConstants.FRONT_RIGHT_STEER_MOTOR, 
        SwerveConstants.FRONT_RIGHT_CANCODER,
        false);
    
    private final SwerveModule backLeft = new SwerveModule(
        SwerveConstants.BACK_LEFT_DRIVE_MOTOR, 
        SwerveConstants.BACK_LEFT_STEER_MOTOR, 
        SwerveConstants.BACK_LEFT_CANCODER,
        true);
    
    private final SwerveModule backRight = new SwerveModule(
        SwerveConstants.BACK_RIGHT_DRIVE_MOTOR, 
        SwerveConstants.BACK_RIGHT_STEER_MOTOR, 
        SwerveConstants.BACK_RIGHT_CANCODER,
        false);
    
    private final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
        SwerveConstants.FRONT_LEFT_POSITION,
        SwerveConstants.FRONT_RIGHT_POSITION,
        SwerveConstants.BACK_LEFT_POSITION,
        SwerveConstants.BACK_RIGHT_POSITION
    );

    private final PigeonIMU gyro = new PigeonIMU(0);
    
    private boolean fieldOriented = false;
    
    public SwerveDriveSubsystem() {}

    /**
    * Habilitar o dehabilitar field.oriented
    * @param fieldOriented verdadero en field-oriented, falso robot-oriented
    */
    public void setFieldOriented(boolean fieldOriented) {
        this.fieldOriented = fieldOriented;
    }
    
    public void setFieldOrientedTrue() {
        fieldOriented = true;
    }

    public void setFieldOrientedFalse() {
        fieldOriented = false;
    }
    
    public boolean isFieldOriented() {
        return fieldOriented;
    }
    
    public void drive(double xSpeed, double ySpeed, double rotation) {
        ChassisSpeeds chassisSpeeds;
        if (fieldOriented) {
            double yaw = gyro.getYaw();
            Rotation2d gyroAngle = Rotation2d.fromDegrees(yaw);
            chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rotation, gyroAngle);
        } 

        else {
            chassisSpeeds = new ChassisSpeeds(ySpeed, xSpeed, rotation);
        }

        SwerveModuleState[] states = kinematics.toSwerveModuleStates(chassisSpeeds);

        frontLeft.setDesiredState(states[0]);
        frontRight.setDesiredState(states[1]);
        backLeft.setDesiredState(states[2]);
        backRight.setDesiredState(states[3]);
    }
    
    public void stop() {
        frontLeft.stop();
        frontRight.stop();
        backLeft.stop();
        backRight.stop();
    }
}


