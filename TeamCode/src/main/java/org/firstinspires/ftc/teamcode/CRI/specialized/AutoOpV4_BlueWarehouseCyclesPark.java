package org.firstinspires.ftc.teamcode.CRI.specialized;


import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CRI.Outtake;
import org.firstinspires.ftc.teamcode.CRI.bt.Action;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.RunTrajectory;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.controlflow.RunAsync;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.controlflow.RunInline;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.controlflow.RunLinear;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.controlflow.RunParallelWait;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.intake.IntakeSetExtender;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.outtake.OuttakeDropFreight;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.outtake.OuttakeSetLevel;
@Autonomous(name = "Blue Warehouse Cycles Park")
public class AutoOpV4_BlueWarehouseCyclesPark extends AutoOpV4_RedWarehouseCyclesPark{
    @Override
    protected void setParams() {
        side = Side.BLUE;
    }

    @Override
    protected Action getRoutine() {
        return new RunLinear(
            new RunInline(ctx -> ctx.outtake.servoCapArm.setPosition(0.65)),
            new RunParallelWait(
                new RunTrajectory(start_to_hub),
                new OuttakeSetLevel(preloadLevel)
            ),
            new OuttakeDropFreight(),
            new DoNCycles(
                3,
                //warehouse
                new Vector2d[] {
//                    new Vector2d(-8.5, -2),
//                    new Vector2d(-6, -3),
//                    new Vector2d(-5, -4)
                    new Vector2d(1, -3),
                    new Vector2d(5, -4),
                    new Vector2d(9, -5.5)
                },
                //hub
                new Vector2d[] {
                    new Vector2d(0, 0),
                    new Vector2d(0, -2),
                    new Vector2d(0, -2.5),
                    new Vector2d(0, -2.5)
                },
                side
            ),
            new IntakeSetExtender(0.0),
            new RunAsync((ctx) -> hub_to_park = drive.trajectoryBuilder(drive.getPoseEstimate(), 0)
                .splineToConstantHeading(new Vector2d(-60, -35), 0)
                .build()),
            new RunParallelWait(
                new RunTrajectory(hub_to_park),
                new OuttakeSetLevel(Outtake.Level.loading)
            )
        );
    }
}