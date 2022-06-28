package org.firstinspires.ftc.teamcode.CRI.bt.actions.outtake;

import org.firstinspires.ftc.teamcode.CRI.Outtake;
import org.firstinspires.ftc.teamcode.CRI.bt.Action;
import org.firstinspires.ftc.teamcode.CRI.bt.AutonomousOpMode;
import org.firstinspires.ftc.teamcode.CRI.bt.ComposedAction;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.controlflow.RunDelay;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.controlflow.RunInline;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.controlflow.RunLinear;

public class OuttakeDropFreight extends ComposedAction {

    @Override
    protected Action constructGroupAtStart(AutonomousOpMode ctx) {
        return new RunLinear(
            new RunInline((context) -> context.containerServo.setPosition(Outtake.SERVO_DROP_NORMAL)),
            new RunDelay(400),
            new RunInline((context) -> context.containerServo.setPosition(Outtake.SERVO_LOADING))
        );
    }
}
