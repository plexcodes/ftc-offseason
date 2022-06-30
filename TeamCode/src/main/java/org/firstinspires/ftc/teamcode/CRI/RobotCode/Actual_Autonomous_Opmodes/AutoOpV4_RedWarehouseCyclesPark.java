package org.firstinspires.ftc.teamcode.CRI.RobotCode.Actual_Autonomous_Opmodes;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.CRI.Mechanisms.Outtake;
import org.firstinspires.ftc.teamcode.CRI.Roadrunner.util.AssetsTrajectoryManager;
import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.RunTrajectory;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunInline;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunLinear;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunParallelWait;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeDropFreight;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeSetLevel;
@Disabled
@Autonomous(name = "Old Red Warehouse Cycles Park")
public class AutoOpV4_RedWarehouseCyclesPark extends AutoOpV4Base {

    @Override
    protected void setParams() {
        side = Side.RED;
    }

    @Override
    protected void precompileTrajectories() {
        startLocation = StartLocation.WAREHOUSE;

        hub_to_park = AssetsTrajectoryManager.load(SIDE("hub_to_park"));
        start_to_hub = AssetsTrajectoryManager.load(SIDE("wstart_to_hub"));
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
                    new Vector2d(9, -9),
                },
                //hub
                new Vector2d[] {
                    new Vector2d(0, 0),
                    new Vector2d(0, -4),
                    new Vector2d(0, -4.5),
                    new Vector2d(0, -6)
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
