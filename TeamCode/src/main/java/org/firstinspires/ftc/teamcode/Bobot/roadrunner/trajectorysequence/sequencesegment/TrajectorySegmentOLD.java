package org.firstinspires.ftc.teamcode.Bobot.roadrunner.trajectorysequence.sequencesegment;

import com.acmerobotics.roadrunner.trajectory.Trajectory;

import java.util.Collections;

public final class TrajectorySegmentOLD extends SequenceSegmentOLD {
    private final Trajectory trajectory;

    public TrajectorySegmentOLD(Trajectory trajectory) {
        // Note: Markers are already stored in the `Trajectory` itself.
        // This class should not hold any markers
        super(trajectory.duration(), trajectory.start(), trajectory.end(), Collections.emptyList());
        this.trajectory = trajectory;
    }

    public Trajectory getTrajectory() {
        return this.trajectory;
    }
}
