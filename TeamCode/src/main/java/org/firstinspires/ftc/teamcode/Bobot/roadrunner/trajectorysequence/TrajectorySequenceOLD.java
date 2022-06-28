package org.firstinspires.ftc.teamcode.Bobot.roadrunner.trajectorysequence;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Bobot.roadrunner.trajectorysequence.sequencesegment.SequenceSegmentOLD;

import java.util.Collections;
import java.util.List;

public class TrajectorySequenceOLD {
    private final List<SequenceSegmentOLD> sequenceList;

    public TrajectorySequenceOLD(List<SequenceSegmentOLD> sequenceList) {
        if (sequenceList.size() == 0) throw new EmptySequenceExceptionOLD();

        this.sequenceList = Collections.unmodifiableList(sequenceList);
    }

    public Pose2d start() {
        return sequenceList.get(0).getStartPose();
    }

    public Pose2d end() {
        return sequenceList.get(sequenceList.size() - 1).getEndPose();
    }

    public double duration() {
        double total = 0.0;

        for (SequenceSegmentOLD segment : sequenceList) {
            total += segment.getDuration();
        }

        return total;
    }

    public SequenceSegmentOLD get(int i) {
        return sequenceList.get(i);
    }

    public int size() {
        return sequenceList.size();
    }
}
