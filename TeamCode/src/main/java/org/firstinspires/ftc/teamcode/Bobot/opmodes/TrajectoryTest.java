package org.firstinspires.ftc.teamcode.Bobot.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Bobot.roadrunner.drive.SampleMecanumDriveOLD;
@Disabled
@Autonomous(group = "drive")
public class TrajectoryTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDriveOLD drive = new SampleMecanumDriveOLD(hardwareMap);
        waitForStart();
        if (isStopRequested()) return;

        Trajectory traj = drive.trajectoryBuilder(new Pose2d())
                .splineToConstantHeading(new Vector2d(45, 40),Math.toRadians(0))
                .build();

        drive.followTrajectory(traj);
        sleep(500);

        drive.followTrajectory(
                drive.trajectoryBuilder(traj.end())
                        .splineTo(new Vector2d(10, -20), Math.toRadians(180))
                        .build()
        );
    }
}
