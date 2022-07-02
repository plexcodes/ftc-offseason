package org.firstinspires.ftc.teamcode.CRI.RobotCode.Previously_Used_Code.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.CRI.Mechanisms.Outtake;
import org.firstinspires.ftc.teamcode.CRI.Roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.CRI.Roadrunner.util.AssetsTrajectoryManager;
import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.RunCarousel;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.RunTrajectory;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunDelay;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunInline;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunLinear;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunParallelWait;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.intake.IntakeSetPower;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.intake.IntermediarySetRunning;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeDropFreight;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeSetLevel;

@Disabled
@Autonomous(name = "Red Carousel Duck Park")
public class AutoOpV4_RedCarouselDuckPark extends AutoOpV4Base {

    TrajectorySequence hub_to_carousel;
    Trajectory carousel_to_warehouse, carousel_to_park, hub_to_storage;

    @Override
    protected void setParams() {
    }

    @Override
    protected void precompileTrajectories() {
        startLocation = StartLocation.MID;

        start_to_hub = AssetsTrajectoryManager.load(SIDE("cstart_to_hub_q"));
        hub_to_carousel = drive.trajectorySequenceBuilder(new Pose2d(-12, -43.25 * (side == Side.RED? 1 : -1), Math.toRadians(-90)  * (side == Side.RED? 1 : -1)))
            .addTrajectory(AssetsTrajectoryManager.load(SIDE("hub_to_carousel_q")))
            .build();
        carousel_to_warehouse = AssetsTrajectoryManager.load(SIDE("carousel_to_warehouse"));
        carousel_to_park = AssetsTrajectoryManager.load(SIDE("carousel_to_bridge"));
        carousel_to_hub = AssetsTrajectoryManager.load(SIDE("carousel_to_hub_wduck"));
        hub_to_storage = AssetsTrajectoryManager.load(SIDE("hub_to_storage"));

    }

    @Override
    protected Action getRoutine() {
        return new RunLinear(
            new RunInline(ctx -> ctx.outtake.servoCapArm.setPosition(0.65)),
            new RunParallelWait(
                new RunLinear(
                    new RunDelay(600),
                    new OuttakeSetLevel(preloadLevel)
                ),
                new RunTrajectory(start_to_hub)
            ),
            new OuttakeDropFreight(),
            new RunParallelWait(
                new RunLinear(
                    new RunDelay(300),
                    new OuttakeSetLevel(Outtake.Level.loading)
                ),
                new RunTrajectory(hub_to_carousel)
            ),
            new RunCarousel(-1800 * (side == Side.RED? 1 : -1), 0.25),
            new RunParallelWait(
                new RunTrajectory(carousel_to_hub),
                new RunLinear(
                    new IntakeSetPower(-1),
                    new IntermediarySetRunning(true),
                    new RunDelay(8500),
                    new OuttakeSetLevel(Outtake.Level.high)
                )
            ),
            new IntakeSetPower(0),
            new OuttakeDropFreight(),
            new RunParallelWait(
                    new OuttakeSetLevel(Outtake.Level.loading),
                    new RunTrajectory(hub_to_storage)
            )
        );
    }
}
