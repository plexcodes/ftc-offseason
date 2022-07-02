package org.firstinspires.ftc.teamcode.CRI.RobotCode.CRI_Autonomous;


import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.RunTrajectory;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunAsync;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunInline;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunLinear;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunParallelWait;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.intake.IntakeSetExtender;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeDropFreight;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeSetLevel;
import org.firstinspires.ftc.teamcode.CRI.Mechanisms.Outtake;
import org.firstinspires.ftc.teamcode.CRI.RobotCode.Actual_Autonomous_Opmodes.AutoOpV4_RedWarehouseCyclesPark;

@Disabled
@Autonomous(name = "gwfawfes")
public class AutoOpV5_BLUE_Warehouse_Cycles_OpenPark extends AutoOpV5_RED_Warehouse_Cycles_OpenPark {
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
                            new Vector2d(0, -4),
                            new Vector2d(5.5, -7),
                            new Vector2d(9, -10),
                    },
                    //hub
                    new Vector2d[] {
                            new Vector2d(0, 0),
                            new Vector2d(2, -4),
                            new Vector2d(2, -4.5),
                            new Vector2d(5, -6)
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
