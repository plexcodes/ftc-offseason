package org.firstinspires.ftc.teamcode.CRI.RobotCode.Tests;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.CRI.Mechanisms.Outtake;

@Disabled
@Autonomous(name="OuttakeTest")
public class OuttakeTest extends OpMode {

//    @Override
//    protected void precompileTrajectories() {
//
//    }
//
//    @Override
//    protected Action getRoutine() {
//        return new RunParallelWait(
//            new IntakeSetPower(-1),
//            new IntermediarySetRunning(true),
//            new RunDelay(30000),
//            new IntakeWaitForElement()
//        );
//    }

    Outtake outtake;

    @Override
    public void init() {
        outtake = new Outtake(this);
    }

    @Override
    public void loop() {
        Log.d("XX", outtake.motor.getCurrentPosition() + "/" + outtake.motor.getTargetPosition());
    }
}
