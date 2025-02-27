package frc.robot;

public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final double MaxElevatorSpeed = 0.4;
    public static final double MaxArmSpeed = 0.07;

    public static final double MaxElevatorPosition = -100;
    public static final double MinElevatorPosition = -4;

    public static final double DesiredMaxElevatorPosition = -100;
    public static final double DesiredMinElevatorPosition = -4;

    public static final double MaxArmPosition = 70;
    public static final double MinArmPosition = 5;

    public static final double DesiredMaxArmPosition = 30;
    public static final double DesiredMinArmPosition = 5;
  }

  public static class SensorConstants {
    public static final int MAGNETICSENSOR_DIGITAL_INPUT_PORT = 2;
    public static final int MECHANICALSWITCH_DIGITAL_INPUT_PORT = 0;
  }
  
}