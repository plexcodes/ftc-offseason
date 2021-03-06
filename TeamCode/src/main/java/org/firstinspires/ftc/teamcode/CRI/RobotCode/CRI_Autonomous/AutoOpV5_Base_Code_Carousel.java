package org.firstinspires.ftc.teamcode.CRI.RobotCode.CRI_Autonomous;

import android.annotation.SuppressLint;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.CRI.Functions.Action;
import org.firstinspires.ftc.teamcode.CRI.Functions.AutonomousOpMode;
import org.firstinspires.ftc.teamcode.CRI.Functions.ComposedAction;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.RunTrajectory;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunDelay;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunLinear;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunParallelRace;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.controlflow.RunParallelWait;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.intake.IntakeSetExtender;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.intake.IntakeSetPower;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.intake.IntakeWaitForElement;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.intake.IntermediarySetRunning;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeDropFreight;
import org.firstinspires.ftc.teamcode.CRI.Functions.actions.outtake.OuttakeSetLevel;
import org.firstinspires.ftc.teamcode.CRI.Mechanisms.Outtake;
import org.firstinspires.ftc.teamcode.CRI.OpenCV.CapstoneDetectPipeline;
import org.firstinspires.ftc.teamcode.CRI.Static.PoseStorage;

import java.util.ArrayList;

abstract public class AutoOpV5_Base_Code_Carousel extends AutonomousOpMode {

    ArrayList<Trajectory> l_h_to_w = null;
    ArrayList<Trajectory> l_w_to_h = null;

    protected enum Side {
        RED,
        BLUE
    }

    protected enum StartLocation {
        WAREHOUSE,
        MID,
        CAROUSEL
    }

    protected Side side = Side.RED;
    protected StartLocation startLocation = StartLocation.WAREHOUSE;

    Trajectory start_to_carousel, carousel_to_hub, warehouse_to_hub, hub_to_warehouse, start_to_hub, hub_to_park;

    protected String SIDE(String name) {
        return ((side == Side.RED) ? "red_" : "blue_") + name;
    }

    @Override
    protected void initStart() {

        if (side == Side.RED) {
            if (startLocation == StartLocation.WAREHOUSE)
                PoseStorage.poseEstimate = new Pose2d(12, -63.34, Math.toRadians(90));
            else if(startLocation == StartLocation.MID)
                PoseStorage.poseEstimate = new Pose2d(-36, -63.34, Math.toRadians(90));
            else if(startLocation == StartLocation.CAROUSEL)
                PoseStorage.poseEstimate = new Pose2d(-84.00, -63.34, Math.toRadians(180));
        } else {
            if (startLocation == StartLocation.WAREHOUSE)
                PoseStorage.poseEstimate = new Pose2d(12, 63.34, Math.toRadians(-90));
            else if(startLocation == StartLocation.MID)
                PoseStorage.poseEstimate = new Pose2d(-36, 63.34, Math.toRadians(-90));
            else if(startLocation == StartLocation.CAROUSEL)
                PoseStorage.poseEstimate = new Pose2d(-84.00, 63.34, Math.toRadians(180));
        }

        // RESET OUTTAKE TICKS
        outtake.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtake.servo.setPosition(Outtake.SERVO_ARMED);


        // CAMERA INITIALIZATION
        capstoneDetection = new CapstoneDetectPipeline();

//
//		webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
//		int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//
//		camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
//		camera.setPipeline(capstoneDetection);
//
//		camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
//			@Override
//			public void onOpened() {
//				camera.startStreaming(1920, 1080, OpenCvCameraRotation.UPRIGHT);
////                camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
//			}
//
//			@Override
//			public void onError(int errorCode) {
//				telemetry.addLine("Camera couldn't init!!!" + "Error " + errorCode);
//			}
//		});


        telemetry.addLine("init complete");
        telemetry.update();
    }

    @Override
    protected void initTick() {
        preloadLevel = Outtake.Level.high;
        switch (capstoneDetection.capstoneSegment) {
            case 3:
                preloadLevel = Outtake.Level.high;
                telemetry.addLine("high");
                break;
            case 2:
                preloadLevel = Outtake.Level.mid_auto;

                telemetry.addLine("mid");
                break;
            case 1:
                preloadLevel = Outtake.Level.low;
                telemetry.addLine("low");
                break;
            default:
                preloadLevel = Outtake.Level.high;
                telemetry.addLine("not detected but set to high");
        }
    }


    protected class DoOneCycle extends ComposedAction {

        private final Trajectory s_to_fw;
        private final Trajectory fw_to_s;
        private boolean timeoutKills;


        public DoOneCycle(Trajectory s_to_fw, Trajectory fw_to_s, boolean timeoutKills) {

            this.s_to_fw = s_to_fw;
            this.fw_to_s = fw_to_s;
            this.timeoutKills = timeoutKills;
        }

        @SuppressLint("NewApi")
        @Override
        protected Action constructGroupAtStart(AutonomousOpMode context) {
            return new RunLinear(
                    new RunParallelWait(
                            new RunTrajectory(s_to_fw),
                            new IntakeSetPower(-1),
                            new IntermediarySetRunning(true),
                            new OuttakeSetLevel(Outtake.Level.loading),
                            new RunLinear(
                                    new RunParallelRace(
                                            new RunLinear(
                                                    new IntakeSetExtender(1),
                                                    new IntakeWaitForElement()
                                            ),
                                            new RunDelay(4000)
                                    ),
                                    new IntakeSetExtender(0),
                                    new IntakeSetPower(1)
                            )

                    ),
                    new RunParallelWait(
                            new RunTrajectory(fw_to_s),
                            new RunLinear(
                                    new RunDelay(900),
                                    new OuttakeSetLevel(Outtake.Level.low)
                            )
                    ),
                    new OuttakeDropFreight()
            );
        }
    }

    protected class DoNCycles extends ComposedAction {
        private final int noCycles;
        private Vector2d[] wPoints;
        private Vector2d[] hPoints;
        private Side side;
        private Trajectory transitionTrajectory;


        public DoNCycles(int noCycles, Vector2d[] wPoints, Vector2d[] hPoints, Side side) {
            this.noCycles = noCycles;
            this.wPoints = wPoints;
            this.hPoints = hPoints;
            this.side = side;

        }

        public DoNCycles(int noCycles, Vector2d[] wPoints, Vector2d[] hPoints, Side side, Trajectory transitionTrajectory) {
            this.noCycles = noCycles;
            this.wPoints = wPoints;
            this.hPoints = hPoints;
            this.side = side;
            this.transitionTrajectory = transitionTrajectory;
        }

        Trajectory get_h_to_w(AutonomousOpMode context, int cycleNo) {
            return context.drive.trajectoryBuilder(new Pose2d(-74.00 + hPoints[cycleNo].getX(), (-15.0 + hPoints[cycleNo].getY()) * (side == Side.RED ? 1 : -1), Math.toRadians(-90) * (side == Side.RED ? 1 : -1)), Math.toRadians(-90) * (side == Side.RED ? 1 : -1))
                    .splineToSplineHeading(new Pose2d(-100.00, (-25.00 + wPoints[cycleNo].getY()) * (side == Side.RED ? 1 : -1), Math.toRadians(180) * (side == Side.RED ? 1 : -1)), Math.toRadians(180) * (side == Side.RED ? 1 : -1))
                    .splineToSplineHeading(new Pose2d(-112.50 + wPoints[cycleNo].getX(), (-15.00 + wPoints[cycleNo].getY()) * (side == Side.RED ? 1 : -1), Math.toRadians(90) * (side == Side.RED ? 1 : -1)),Math.toRadians(90) * (side == Side.RED ? 1 : -1))
                    .build();
        }

        Trajectory get_w_to_h(AutonomousOpMode context, int cycleNo) {
            return context.drive.trajectoryBuilder(new Pose2d(-112.50 + wPoints[cycleNo].getX(), (-15.00 + wPoints[cycleNo].getY()) * (side == Side.RED ? 1 : -1), Math.toRadians(90) * (side == Side.RED ? 1 : -1)), Math.toRadians(-90) * (side == Side.RED ? 1 : -1))
                    .splineToSplineHeading(new Pose2d(-100.00, (-25.00 + wPoints[cycleNo].getY()) * (side == Side.RED ? 1 : -1), Math.toRadians(180) * (side == Side.RED ? 1 : -1)), Math.toRadians(0) * (side == Side.RED ? 1 : -1))
                    .splineToSplineHeading(new Pose2d(-74.50 + wPoints[cycleNo].getX(), (-15.00 + wPoints[cycleNo].getY()) * (side == Side.RED ? 1 : -1), Math.toRadians(-90) * (side == Side.RED ? 1 : -1)),Math.toRadians(90) * (side == Side.RED ? 1 : -1))
                    .build();
        }

        @Override
        protected Action constructGroupAtStart(AutonomousOpMode context) {
            ArrayList<Action> cycles = new ArrayList<>();

            for (int i = 0; i < noCycles; i++) {
                cycles.add(
                        new DoOneCycle(
                                (i == 0 && transitionTrajectory != null) ?
                                        transitionTrajectory : get_h_to_w(context, i),
                                get_w_to_h(context, i),
                                i == noCycles - 1
                        )
                );

            }

            return new RunLinear(cycles.toArray(new Action[0]));
        }
    }

}

