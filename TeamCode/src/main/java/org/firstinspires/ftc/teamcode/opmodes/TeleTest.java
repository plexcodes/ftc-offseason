package org.firstinspires.ftc.teamcode.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmodes.MecanumLibrary;


@TeleOp
public class TeleTest extends LinearOpMode {
	@Override
	public void runOpMode() throws InterruptedException {
		MecanumLibrary mecanum = new MecanumLibrary(this);
		org.firstinspires.ftc.teamcode.X.GamepadExpanded g1 = new org.firstinspires.ftc.teamcode.X.GamepadExpanded(gamepad1);
		waitForStart();
		while(opModeIsActive())
		{
			g1.update();
			mecanum.vectorMove(-gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.left_trigger - gamepad1.right_trigger, gamepad1.right_bumper ? 0.5 : 1.0);
			telemetry.update();
		}
	}
}