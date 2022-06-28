package org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake;

import org.firstinspires.ftc.teamcode.CRI.Mechanisms.Outtake;
import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.AutonomousOpMode;

public class OuttakeSetLevel extends Action {

        private Outtake.Level level;

        public OuttakeSetLevel(Outtake.Level level) {
            this.level = level;
        }

        @Override
        public void _start(AutonomousOpMode context) {
            context.outtake.setLevel(level);
        }

        @Override
        public void _tick(AutonomousOpMode state) { }

        @Override
        public boolean _hasFinished(AutonomousOpMode state) {
            return state.outtake.hasFinished();
        }

        @Override
        public void _end(AutonomousOpMode state) {
        }
    }

