package org.firstinspires.ftc.teamcode.Bobot.opencv;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class opencv_detectionOLD extends OpenCvPipeline {
    Telemetry telemetry;
    Mat mat = new Mat();
    public enum Location {
        LEFT,
        RIGHT,
        NOT_FOUND
    }
    static final Rect LEFT_ROI = new Rect(
            new Point(60,35),
            new Point(120,75));
    static final Rect RIGHT_ROI = new Rect(
            new Point(140,35),
            new Point(200,75));
    static double PERCENT_THRESHOLD = 0.4;
    public opencv_detectionOLD(Telemetry t) {telemetry = t;}
    private Location location;
    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        Scalar lowHSV = new Scalar(23, 50, 70);
        Scalar highHSV = new Scalar(32,255,255);

        Core.inRange(mat, lowHSV, highHSV, mat);
        Mat left = mat.submat(LEFT_ROI);
        Mat right = mat.submat(RIGHT_ROI);

        double leftRatio = Core.sumElems(left).val[0] / LEFT_ROI.area() / 255;
        double rightRatio = Core.sumElems(right).val[0] / RIGHT_ROI.area() / 255;

        left.release();
        right.release();

        telemetry.addData("Left Percentage", Math.round(leftRatio*100)+"%");
        telemetry.addData("RightPercentage", Math.round(rightRatio*100)+"%");

        boolean stoneLeft = leftRatio > PERCENT_THRESHOLD;
        boolean stoneRight = rightRatio > PERCENT_THRESHOLD;

        if(stoneLeft && stoneRight){
            location = Location.NOT_FOUND;
            telemetry.addData("","Not Found");
        }
        if(stoneLeft){
            location = Location.RIGHT;
            telemetry.addData("","Right");
        }
        else{
            location = Location.LEFT;
            telemetry.addData("","Left");
        }
        telemetry.update();

        Imgproc.cvtColor(mat,mat,Imgproc.COLOR_GRAY2RGB);
        Scalar colorStone = new Scalar(255,0,0);
        Scalar colorSkyStone = new Scalar(0,255,0);

        Imgproc.rectangle(mat,LEFT_ROI, location == Location.LEFT? colorSkyStone:colorStone);
        Imgproc.rectangle(mat, RIGHT_ROI, location == Location.RIGHT? colorSkyStone:colorStone);

        return mat;
    }

    public Location getLocation(){
        return location;
    }

}
