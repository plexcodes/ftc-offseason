package org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow;

import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.AutonomousOpMode;

public class RunDelay extends Action {

    long waitMs;
    long targetTimeMs;

    public RunDelay(long waitMs) {
        this.waitMs = waitMs;
    }

    @Override
    public void _start(AutonomousOpMode context) {
        targetTimeMs = System.currentTimeMillis() + waitMs;
    }

    @Override
    public void _tick(AutonomousOpMode context) {

    }

    @Override
    public boolean _hasFinished(AutonomousOpMode context) {
        return targetTimeMs <= System.currentTimeMillis();
    }

    @Override
    public void _end(AutonomousOpMode context) {


    }

    @Override
    public String getCustomDisplay(AutonomousOpMode context) {
       return String.valueOf(Math.max(0, targetTimeMs - System.currentTimeMillis()));
    }
}
