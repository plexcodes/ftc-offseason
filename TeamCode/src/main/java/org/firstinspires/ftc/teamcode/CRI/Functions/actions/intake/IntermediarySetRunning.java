package org.firstinspires.ftc.teamcode.CRI.Functions.actions.intake;

import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.AutonomousOpMode;

public class IntermediarySetRunning extends Action {

    private boolean run;

    public IntermediarySetRunning(boolean run) {
        this.run = run;
    }
    @Override
    protected void _start(AutonomousOpMode context) {
        context.intake.intermediaryMotor.setPower(run? -1 : 0);
    }

    @Override
    protected void _tick(AutonomousOpMode context) {

    }

    @Override
    protected boolean _hasFinished(AutonomousOpMode context) {
        return true;
    }

    @Override
    protected void _end(AutonomousOpMode context) {

    }
}
