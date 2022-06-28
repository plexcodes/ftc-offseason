package org.firstinspires.ftc.teamcode.CRI.specialized;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.CRI.Outtake;
import org.firstinspires.ftc.teamcode.CRI.RR.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.CRI.RR.util.AssetsTrajectoryManager;
import org.firstinspires.ftc.teamcode.CRI.bt.Action;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.RunCarousel;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.RunTrajectory;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.controlflow.RunDelay;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.controlflow.RunInline;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.controlflow.RunLinear;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.controlflow.RunParallelWait;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.outtake.OuttakeDropFreight;
import org.firstinspires.ftc.teamcode.CRI.bt.actions.outtake.OuttakeSetLevel;
@Disabled
@Autonomous(name = "Blue Carousel Park")
public class AutoOpV4_BlueCarouselPark extends AutoOpV4_RedCarouselPark {

    @Override
    protected void setParams() {
        side = Side.BLUE;
    }

}
