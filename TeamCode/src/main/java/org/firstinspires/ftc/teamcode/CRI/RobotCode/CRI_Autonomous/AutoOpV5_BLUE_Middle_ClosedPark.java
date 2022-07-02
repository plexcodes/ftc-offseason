package org.firstinspires.ftc.teamcode.CRI.RobotCode.CRI_Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name = "BLUE Middle | Preload + Alliance Delay + Closed Park")
public class AutoOpV5_BLUE_Middle_ClosedPark extends AutoOpV5_RED_Middle_ClosedPark {

    @Override
    protected void setParams() {
        side = Side.BLUE;
    }

}
