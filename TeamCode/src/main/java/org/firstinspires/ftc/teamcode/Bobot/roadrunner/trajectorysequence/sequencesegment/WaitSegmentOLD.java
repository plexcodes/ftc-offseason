package org.firstinspires.ftc.teamcode.Bobot.roadrunner.trajectorysequence.sequencesegment;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.TrajectoryMarker;

import java.util.List;

public final class WaitSegmentOLD extends SequenceSegmentOLD {
    public WaitSegmentOLD(Pose2d pose, double seconds, List<TrajectoryMarker> markers) {
        super(seconds, pose, pose, markers);
    }
}
