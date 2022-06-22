//package org.firstinspires.ftc.teamcode.opencv;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.openftc.easyopencv.OpenCvCamera;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//import org.openftc.easyopencv.OpenCvCameraRotation;
//import org.openftc.easyopencv.OpenCvInternalCamera;
//
//@Autonomous(name="opencv_test", group="Auto")
//public class opencv_opmode extends LinearOpMode {
//    OpenCvCamera phoneCam;
//    @Override
//    public void runOpMode() throws InterruptedException {
//        int cameraMonitorViewId = hardwareMap.appContext
//                .getResources().getIdentifier("cameraMonitorViewId",
//                        "id", hardwareMap.appContext.getPackageName());
//        phoneCam = OpenCvCameraFactory.getInstance()
//                .createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
//        opencv_detection detector = new opencv_detection(telemetry);
//        phoneCam.setPipeline(detector);
//        phoneCam.openCameraDeviceAsync(
//                () -> phoneCam.startStreaming (320, 240, OpenCvCameraRotation.UPRIGHT)
//        );
//
//        waitForStart();
//        switch (detector.getLocation()) {
//            case LEFT:
//                // ...
//                break;
//            case RIGHT:
//                // ...
//                break;
//            case NOT_FOUND:
//                // ...
//        }
//        phoneCam.stopStreaming();
//    }
//}