package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;


public class SwerveConstants {
   
    public static final double DRIVE_GEAR_RATIO = 5.9;
    
    public static final double WHEEL_DIAMETER_METERS = Units.inchesToMeters(4.0);
    public static final double WHEEL_RADIUS_METERS = WHEEL_DIAMETER_METERS / 2.0;
    
    public static final double MAX_MOTOR_RPM = 5820;
    public static final double MAX_WHEEL_SPEED_MPS = (MAX_MOTOR_RPM / 60.0) * (Math.PI * WHEEL_DIAMETER_METERS) / DRIVE_GEAR_RATIO;
    
    public static final int FRONT_LEFT_DRIVE_MOTOR = 15;
    public static final int FRONT_LEFT_STEER_MOTOR = 14;
    public static final int FRONT_RIGHT_DRIVE_MOTOR = 21;
    public static final int FRONT_RIGHT_STEER_MOTOR = 20;
    public static final int BACK_LEFT_DRIVE_MOTOR = 17;
    public static final int BACK_LEFT_STEER_MOTOR = 16;
    public static final int BACK_RIGHT_DRIVE_MOTOR = 23;
    public static final int BACK_RIGHT_STEER_MOTOR = 22;
    
    public static final int FRONT_LEFT_CANCODER = 10;
    public static final int FRONT_RIGHT_CANCODER = 11;
    public static final int BACK_LEFT_CANCODER = 13;
    public static final int BACK_RIGHT_CANCODER = 12;
    
    public static final double STEER_P = 0.000060;
    public static final double STEER_I = 0.000005;
    public static final double STEER_D = 0.00000;


    public static final Translation2d FRONT_LEFT_POSITION = new Translation2d(0.30, 0.305);
    public static final Translation2d FRONT_RIGHT_POSITION = new Translation2d(0.30, -0.305);
    public static final Translation2d BACK_LEFT_POSITION = new Translation2d(-0.30, 0.305);
    public static final Translation2d BACK_RIGHT_POSITION = new Translation2d(-0.30, -0.305);
}
