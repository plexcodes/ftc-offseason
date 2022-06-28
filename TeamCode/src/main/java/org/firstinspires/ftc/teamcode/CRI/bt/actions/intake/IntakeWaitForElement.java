package org.firstinspires.ftc.teamcode.CRI.bt.actions.intake;

import org.firstinspires.ftc.teamcode.CRI.bt.Action;
import org.firstinspires.ftc.teamcode.CRI.bt.AutonomousOpMode;

public class IntakeWaitForElement extends Action {
    @Override
    protected void _start(AutonomousOpMode context) {
        context.intake.servoIntake.setPosition(1);
        context.freightSensor.update();

    }

    @Override
    protected void _tick(AutonomousOpMode context) {
        context.freightSensor.update();
    }

    @Override
    protected boolean _hasFinished(AutonomousOpMode context) {
        return context.freightSensor.isDetectingFreight();
    }

    @Override
    protected void _end(AutonomousOpMode context) {
        context.intake.servoIntake.setPosition(0);
    }

}
