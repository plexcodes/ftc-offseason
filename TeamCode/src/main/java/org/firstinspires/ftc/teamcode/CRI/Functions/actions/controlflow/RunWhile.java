package org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow;

import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.AutonomousOpMode;

public class RunWhile extends Action {

    public interface Cond {
        boolean call(AutonomousOpMode state);
    }

    Cond cond;

    public RunWhile(Cond cond) {
        this.cond = cond;
    }

    @Override
    public void _start(AutonomousOpMode context) { }

    @Override
    public void _tick(AutonomousOpMode context) { }

    @Override
    public boolean _hasFinished(AutonomousOpMode context) {
        return !cond.call(context);
    }

    @Override
    public void _end(AutonomousOpMode context) { }

}
