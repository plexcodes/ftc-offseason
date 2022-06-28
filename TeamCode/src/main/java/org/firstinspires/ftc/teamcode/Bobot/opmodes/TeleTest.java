package org.firstinspires.ftc.teamcode.Bobot.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Bobot.libraries.GamepadExpandedOLD;
import org.firstinspires.ftc.teamcode.Bobot.libraries.MecanumLibraryOLD;

@Disabled
@TeleOp
public class TeleTest extends LinearOpMode {
	@Override
	public void runOpMode() throws InterruptedException {

		MecanumLibraryOLD mecanum = new MecanumLibraryOLD(this);

		GamepadExpandedOLD g1 = new GamepadExpandedOLD(gamepad1);

		waitForStart();

		while(opModeIsActive())
		{

			g1.update();
			mecanum.vectorMove(
					-gamepad1.left_stick_x,
					gamepad1.left_stick_y,
					gamepad1.left_trigger - gamepad1.right_trigger,
					gamepad1.right_bumper ? 0.5 : 1.0
			);

			telemetry.update();
		}
	}
}