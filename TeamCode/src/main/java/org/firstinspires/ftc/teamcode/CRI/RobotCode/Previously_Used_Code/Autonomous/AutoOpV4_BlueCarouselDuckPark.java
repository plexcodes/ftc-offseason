package org.firstinspires.ftc.teamcode.CRI.RobotCode.Previously_Used_Code.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name = "Blue Carousel Duck Park")
public class AutoOpV4_BlueCarouselDuckPark extends AutoOpV4_RedCarouselDuckPark{
	@Override
	protected void setParams() {
		side = Side.BLUE;
	}

}
