package org.firstinspires.ftc.teamcode.CRI.RobotCode.CRI_Autonomous;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.RunCarousel;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.RunTrajectory;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunDelay;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunInline;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunLinear;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunParallelWait;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeDropFreight;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeSetLevel;
import org.firstinspires.ftc.teamcode.CRI.Mechanisms.Outtake;
import org.firstinspires.ftc.teamcode.CRI.Roadrunner.util.AssetsTrajectoryManager;
import org.firstinspires.ftc.teamcode.CRI.RobotCode.Actual_Autonomous_Opmodes.AutoOpV4Base;


@Autonomous(name = "RED Carousel | DUCK + PRELOAD + CYCLE")
public class AutoOpV5_RED_Carousel_Duck_Cycle_Park extends AutoOpV5_Base_Code {

    Trajectory carousel_to_warehouse, carousel_to_park, hub_to_carousel, carousel_to_shared;

    @Override
    protected void setParams() {
        side = Side.RED;
    }

    @Override
    protected void precompileTrajectories() {
       startLocation = StartLocation.CAROUSEL;
        carousel_to_shared = AssetsTrajectoryManager.load(SIDE("carousel_to_shared"));
       start_to_carousel = AssetsTrajectoryManager.load(SIDE("cstart_to_carousel"));

    }

    @Override
    protected Action getRoutine() {
        return new RunLinear(
            new RunInline(ctx -> ctx.outtake.servoCapArm.setPosition(0.65)),
            new RunTrajectory(start_to_carousel),
            new RunCarousel(-1800 * (side == Side.RED? 1 : -1), 0.25),
                new RunParallelWait(
                        new OuttakeSetLevel(Outtake.Level.low),
                        new RunTrajectory(carousel_to_shared)
                        ),
                new OuttakeSetLevel(Outtake.Level.loading),
                new OuttakeDropFreight()
        );
//                new RunParallelWait(
//                new RunLinear(
//                    new RunDelay(600),
//                    new OuttakeSetLevel(preloadLevel)
//                ),
//                new RunTrajectory(start_to_hub)
//            ),
//            new OuttakeDropFreight(),
//            new RunParallelWait(
//                new RunLinear(
//                    new RunDelay(300),
//                    new OuttakeSetLevel(Outtake.Level.loading)
//                ),
//                new RunTrajectory(hub_to_carousel)
//            ),
//            new RunTrajectory(carousel_to_park)
//        );
    }
}
