package org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow;

import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.AutonomousOpMode;

public class RunAsync extends Action {

    public interface Exec {
        void call(AutonomousOpMode state);
    }

    Exec e;
    Thread execThread;

    public RunAsync(Exec e) {
        this.e = e;
    }

    @Override
    public void _start(AutonomousOpMode context) {
        execThread = new Thread(() -> e.call(context));
        execThread.start();
    }

    @Override
    public void _tick(AutonomousOpMode context) {
        // Should not have to tick
    }

    @Override
    public boolean _hasFinished(AutonomousOpMode context) {
        return execThread != null && !execThread.isAlive();
    }

    @Override
    public void _end(AutonomousOpMode context) {
        execThread.interrupt();
    }
}
