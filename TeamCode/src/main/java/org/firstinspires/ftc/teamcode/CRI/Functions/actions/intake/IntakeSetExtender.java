package org.firstinspires.ftc.teamcode.CRI.Functions.actions.intake;

import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.AutonomousOpMode;

public class IntakeSetExtender extends Action {

    public double pos = 0;

    public IntakeSetExtender(double pos) {
        this.pos = pos;
    }
    @Override
    protected void _start(AutonomousOpMode context) {
        context.intake.servoIntake.setPosition(pos);
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
