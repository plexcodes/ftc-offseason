package org.firstinspires.ftc.teamcode.CRI.Mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;

@Config
public class Intake {

	public interface runAfterAction {
		void action();
	}

	class runAfter {
		runAfterAction action;
		double targetTime;

		runAfter(double targetTime, runAfterAction action)
		{
			this.action = action;
			this.targetTime = targetTime;
		}
	}

	ArrayList<runAfter> actionQueue;


	public DcMotor intakeMotor;
	public DcMotor intermediaryMotor;

	OpMode opMode;


	public Servo servoIntake;
	public DigitalChannel[] touchSensors;


	boolean isCurrentlyWorking = false;

	public Intake(OpMode opMode) {

		this.opMode = opMode;

		actionQueue = new ArrayList<>();

		intakeMotor = opMode.hardwareMap.get(DcMotor.class, "motorIntake");
		intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

		intermediaryMotor = opMode.hardwareMap.get(DcMotor.class, "motorIntermediar");

		servoIntake = opMode.hardwareMap.get(Servo.class, "servoIntake");

		touchSensors = new DigitalChannel[]{
			opMode.hardwareMap.get(DigitalChannel.class, "touchSensor1"),
			opMode.hardwareMap.get(DigitalChannel.class, "touchSensor2"),
			opMode.hardwareMap.get(DigitalChannel.class, "touchSensor3")

		};

		servoIntake.setPosition(0.15);

		for(DigitalChannel sensor : touchSensors) {
			sensor.setMode(DigitalChannel.Mode.INPUT);
		}

	}

	public void work()
	{
		intakeMotor.setPower(-1);
		intermediaryMotor.setPower(-1);
	}

	public void workFor(double time)
	{
		intakeMotor.setPower(-1);
		intermediaryMotor.setPower(-1);

		isCurrentlyWorking = true;
		runAfter(time, () -> {
			intakeMotor.setPower(0);
			intermediaryMotor.setPower(0);
			isCurrentlyWorking = false;
		});
	}

	public void ejectFor(double time)
	{
		intakeMotor.setPower(1);
		intermediaryMotor.setPower(1);
		isCurrentlyWorking = true;
		runAfter(time, () -> {
			intakeMotor.setPower(0);
			intermediaryMotor.setPower(0);
			isCurrentlyWorking = false;
		});
	}

	public void complexEject(double time, double intermediaryDelay)
	{
		intakeMotor.setPower(1);
		intermediaryMotor.setPower(-1);
		runAfter(intermediaryDelay, () -> {
			intermediaryMotor.setPower(1);
		});
		runAfter(time, () -> {
			intakeMotor.setPower(0);
			intermediaryMotor.setPower(0);
		});
	}

	public void ejectForWithDelay(double time, double delay)
	{
		runAfter(delay, () -> {
			ejectFor(time);
		});
	}

	public boolean isWorking()
	{
		return isCurrentlyWorking;
	}

	void runAfter(double runAfter, runAfterAction action)
	{
		actionQueue.add(new runAfter(runAfter + System.currentTimeMillis(), action));
	}

	public void stop()
	{
		intakeMotor.setPower(0);
		intermediaryMotor.setPower(0);
	}

	public void update()
	{

		for(int i = 0; i < actionQueue.size(); i++)
		{
			if(actionQueue.get(i).targetTime <= System.currentTimeMillis())
			{
				actionQueue.get(i).action.action();
				actionQueue.remove(i);
			}
		}
	}
}
