package org.firstinspires.ftc.teamcode.Bobot.roadrunner.drive;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@Config
public class DriveConstantsOLD {

    public static final double TICKS_PER_REV = 384.5;
    public static final double MAX_RPM = 435;
    public static double WHEEL_RADIUS = 1.9685;
    public static double GEAR_RATIO = 1;
    public static double TRACK_WIDTH = 14.4;
    public static double MAX_VEL = 40;
    public static double MAX_ACCEL = 30;
    public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(
            50,
            0,
            20,
            13.344324
    );

    public static final boolean RUN_USING_ENCODER = true;
    public static double kV = 1.0 / rpmToVelocity(MAX_RPM);
    public static double kA = 0;
    public static double kStatic = 0;
    public static double MAX_ANG_VEL = Math.toRadians(90);
    public static double MAX_ANG_ACCEL = Math.toRadians(90);
    public static double encoderTicksToInches(double ticks) {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
    }
    public static double rpmToVelocity(double rpm) {
        return rpm * GEAR_RATIO * 2 * Math.PI * WHEEL_RADIUS / 60.0;
    }
    public static double getMotorVelocityF(double ticksPerSecond) {
        return 32767 / ticksPerSecond;
    }
}
