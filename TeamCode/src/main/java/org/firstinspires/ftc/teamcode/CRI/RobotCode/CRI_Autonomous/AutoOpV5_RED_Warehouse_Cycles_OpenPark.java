package org.firstinspires.ftc.teamcode.CRI.RobotCode.CRI_Autonomous;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CRI.Mechanisms.Outtake;
import org.firstinspires.ftc.teamcode.CRI.Roadrunner.util.AssetsTrajectoryManager;
import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.RunTrajectory;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunInline;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunLinear;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunParallelWait;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeDropFreight;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeSetLevel;

@Autonomous(name = "RED Warehouse | Preload + 3 Cycles + Open Park")
public class AutoOpV5_RED_Warehouse_Cycles_OpenPark extends AutoOpV5_Base_Code {
Trajectory hub_to_park;

    @Override
    protected void setParams() {
        side = Side.RED;
    }

    @Override
    protected void precompileTrajectories() {
        startLocation = StartLocation.WAREHOUSE;

        hub_to_park = AssetsTrajectoryManager.load(SIDE("hub_to_park"));
        start_to_hub = AssetsTrajectoryManager.load(SIDE("start_to_hub"));
        outtake.servoCapArm.setPosition(0.65);
    }

    @Override
    protected Action getRoutine() {
        return new RunLinear(
                new RunInline(ctx -> ctx.outtake.servoCapArm.setPosition(0.4)),
                new RunParallelWait(
                        new RunTrajectory(start_to_hub),
                        new OuttakeSetLevel(preloadLevel)
                ),
                new OuttakeDropFreight(),
                new DoNCycles(
                        3,
                        //warehouse
                        new Vector2d[] {
                                new Vector2d(0, -4),
                                new Vector2d(5.5, -7),
                                new Vector2d(9, -10),
                        },
                        //hub
                        new Vector2d[] {
                                new Vector2d(3.5, 0),
                                new Vector2d(3.5, -5),
                                new Vector2d(4.5, -7.5),
                                new Vector2d(6, -10)
                        },
                        side
                ),
                new RunParallelWait(
                        new RunTrajectory(hub_to_park),
                        new OuttakeSetLevel(Outtake.Level.loading)
                )
        );
    }
}
