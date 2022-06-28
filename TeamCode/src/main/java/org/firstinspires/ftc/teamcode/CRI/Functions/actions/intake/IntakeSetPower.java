package org.firstinspires.ftc.teamcode.CRI.Functions.actions.intake;

import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.AutonomousOpMode;

public class IntakeSetPower extends Action {

    private float run;

    public IntakeSetPower(float run) {
        this.run = run;
    }

    @Override
    public void _start(AutonomousOpMode context) {
        context.intake.intakeMotor.setPower(run);
    }

    @Override
    public void _tick(AutonomousOpMode state) { }

    @Override
    public boolean _hasFinished(AutonomousOpMode state) {
        return true;
    }

    @Override
    public void _end(AutonomousOpMode state) { }
}
