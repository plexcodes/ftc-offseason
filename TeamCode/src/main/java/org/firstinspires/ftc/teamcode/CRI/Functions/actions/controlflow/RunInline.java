package org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow;

import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.AutonomousOpMode;

public class RunInline extends Action {

    private Exec exec;

    public interface Exec {
        void call(AutonomousOpMode context);
    }

    public RunInline(Exec exec) {
        this.exec = exec;
    }

    @Override
    public void _start(AutonomousOpMode context) {
        exec.call(context);
    }

    @Override
    public void _tick(AutonomousOpMode context) {

    }

    @Override
    public boolean _hasFinished(AutonomousOpMode context) {
        return true;
    }

    @Override
    public void _end(AutonomousOpMode context) {

    }
}
