package org.firstinspires.ftc.teamcode.CRI.RobotCode.CRI_Autonomous;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CRI.Mechanisms.Outtake;
import org.firstinspires.ftc.teamcode.CRI.Roadrunner.util.AssetsTrajectoryManager;
import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.RunTrajectory;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunDelay;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunInline;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunLinear;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunParallelWait;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeDropFreight;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeSetLevel;


@Autonomous(name = "RED Middle | Preload + Alliance Delay + Closed Park")
public class AutoOpV5_RED_Middle_ClosedPark extends AutoOpV5_Base_Code_Warehouse_And_Middle {
    Trajectory hub_to_wait, wait_to_park;
    @Override
    protected void setParams() {
        side = Side.RED;
    }

    @Override
    protected void precompileTrajectories() {
        startLocation = StartLocation.MID;
        start_to_hub = AssetsTrajectoryManager.load(SIDE("mstart_to_hub"));
        hub_to_wait = AssetsTrajectoryManager.load(SIDE("hub_to_wait"));
        wait_to_park = AssetsTrajectoryManager.load(SIDE("wait_to_park"));
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
                new RunLinear(
                        new RunDelay(300),
                        new OuttakeSetLevel(Outtake.Level.loading)
                ),
                new RunTrajectory(hub_to_wait),
                new RunDelay(1000),
                new RunTrajectory(wait_to_park)
        );
    }
}

