package org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake;

import org.firstinspires.ftc.teamcode.CRI.Mechanisms.Outtake;
import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.AutonomousOpMode;
import org.firstinspires.ftc.teamcode.CRI.Functions.ComposedAction;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunDelay;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunInline;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunLinear;

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
