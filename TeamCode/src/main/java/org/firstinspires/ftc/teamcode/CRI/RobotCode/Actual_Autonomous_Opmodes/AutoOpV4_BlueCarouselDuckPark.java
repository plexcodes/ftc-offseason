package org.firstinspires.ftc.teamcode.CRI.RobotCode.Actual_Autonomous_Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Blue Carousel Duck Park")
public class AutoOpV4_BlueCarouselDuckPark extends AutoOpV4_RedCarouselDuckPark{
	@Override
	protected void setParams() {
		side = Side.BLUE;
	}

}
