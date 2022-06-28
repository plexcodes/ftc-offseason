package org.firstinspires.ftc.teamcode.CRI.RobotCode.Actual_Autonomous_Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name = "Blue Carousel Park")
public class AutoOpV4_BlueCarouselPark extends AutoOpV4_RedCarouselPark {

    @Override
    protected void setParams() {
        side = Side.BLUE;
    }

}
