package org.firstinspires.ftc.teamcode.CRI.RobotCode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.CRI.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.CRI.Mechanisms.FreightSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@TeleOp(name = "Distance Test")
public class DistanceSensorTest extends OpMode {

    Intake intake;
    FreightSensor freightSensor;
    int freight;
    boolean gate = false;
    long nextTimeout;

    @Override
    public void init() {
        intake = new Intake(this);
        freightSensor = new FreightSensor(hardwareMap);
    }

    @Override
    public void start() {
        super.start();
        intake.work();
    }

    @Override
    public void loop() {
        freightSensor.update();
        if(System.currentTimeMillis() < nextTimeout) return;
        intake.work();
        if(freightSensor.isDetectingFreight()) {
            if(!gate) {
                freight++;
                gate = true;
                intake.stop();
                nextTimeout = System.currentTimeMillis() + 1000;
            }
        } else gate = false;
        telemetry.addData("freight", freight);
    }
}
