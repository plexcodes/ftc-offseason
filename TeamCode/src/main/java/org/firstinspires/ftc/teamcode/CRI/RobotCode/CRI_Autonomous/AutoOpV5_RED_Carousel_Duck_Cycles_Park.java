package org.firstinspires.ftc.teamcode.CRI.RobotCode.CRI_Autonomous;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.RunCarousel;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.RunTrajectory;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunInline;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunLinear;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunParallelWait;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeDropFreight;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeSetLevel;
import org.firstinspires.ftc.teamcode.CRI.Mechanisms.Outtake;
import org.firstinspires.ftc.teamcode.CRI.Roadrunner.util.AssetsTrajectoryManager;


@Autonomous(name = "RED Carousel | 1 Duck + Preload + Cycles")
public class AutoOpV5_RED_Carousel_Duck_Cycles_Park extends AutoOpV5_Base_Code_Warehouse_And_Middle {

    Trajectory carousel_to_shared, shared_to_warehouse;

    @Override
    protected void setParams() {
        side = Side.RED;
    }

    @Override
    protected void precompileTrajectories() {
       startLocation = StartLocation.CAROUSEL;
       carousel_to_shared = AssetsTrajectoryManager.load(SIDE("carousel_to_shared"));
       start_to_carousel = AssetsTrajectoryManager.load(SIDE("cstart_to_carousel"));
       shared_to_warehouse = AssetsTrajectoryManager.load(SIDE("shared_to_warehouse"));
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
                new OuttakeDropFreight(),
        new DoNCycles(
                3,
                //warehouse
                new Vector2d[] {
                        new Vector2d(0, 0),
                        new Vector2d(0, 0),
                        new Vector2d(0, 0),
                },
                //hub
                new Vector2d[] {
                        new Vector2d(0, 0),
                        new Vector2d(0, 0),
                        new Vector2d(0, 0),
                        new Vector2d(0, 0)
                },
                side
        )
        );
    }
}
