package org.firstinspires.ftc.teamcode.CRI.RobotCode.Actual_Autonomous_Opmodes;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.CRI.Mechanisms.Outtake;
import org.firstinspires.ftc.teamcode.CRI.Roadrunner.util.AssetsTrajectoryManager;
import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.RunCarousel;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.RunTrajectory;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunDelay;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunInline;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunLinear;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunParallelWait;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeDropFreight;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeSetLevel;
@Disabled
@Autonomous(name = "Red Carousel Park")
public class AutoOpV4_RedCarouselPark extends AutoOpV4Base {

    Trajectory carousel_to_warehouse, carousel_to_park, hub_to_carousel;

    @Override
    protected void setParams() {
        side = Side.RED;
    }

    @Override
    protected void precompileTrajectories() {
       startLocation = StartLocation.CAROUSEL;

       start_to_hub = AssetsTrajectoryManager.load(SIDE("cstart_to_hub"));
       hub_to_carousel = AssetsTrajectoryManager.load(SIDE("hub_to_carousel"));
       carousel_to_warehouse = AssetsTrajectoryManager.load(SIDE("carousel_to_warehouse"));
       carousel_to_park = AssetsTrajectoryManager.load(SIDE("carousel_to_bridge"));

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
//            new DoNCycles(4, new Vector2d(2.0, -0.5), carousel_to_warehouse),
            new RunTrajectory(carousel_to_park)
        );
    }
}
