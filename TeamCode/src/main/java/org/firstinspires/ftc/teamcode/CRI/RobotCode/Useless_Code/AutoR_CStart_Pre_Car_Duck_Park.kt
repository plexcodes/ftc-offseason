//package org.firstinspires.ftc.teamcode.CRI.specialized.Useless_Code
//
//import com.acmerobotics.roadrunner.trajectory.Trajectory
//import org.firstinspires.ftc.teamcode.CRI.RR.util.AssetsTrajectoryManager
//import org.firstinspires.ftc.teamcode.CRI.bt.Action
//import org.firstinspires.ftc.teamcode.CRI.bt.actions.RunCarousel
//import org.firstinspires.ftc.teamcode.CRI.bt.actions.RunTrajectory
//import org.firstinspires.ftc.teamcode.CRI.bt.actions.controlflow.RunLinear
//import org.firstinspires.ftc.teamcode.CRI.bt.actions.controlflow.RunParallelWait
//import org.firstinspires.ftc.teamcode.CRI.bt.actions.outtake.OuttakeDropFreight
//import org.firstinspires.ftc.teamcode.CRI.bt.actions.outtake.OuttakeSetLevel
//import org.firstinspires.ftc.teamcode.CRI.specialized.AutoOpV4Base
//
//class AutoR_CStart_Pre_Car_Duck_Park : AutoOpV4Base() {
//
//    var hub_to_carousel : Trajectory? = null
//    var carousel_to_warehouse: Trajectory? = null
//
//
//    override fun precompileTrajectories() {
//        side = Side.RED
//
//        hub_to_carousel = AssetsTrajectoryManager.load(SIDE("hub_to_carousel"))
//        carousel_to_warehouse = AssetsTrajectoryManager.load(SIDE("carousel_to_warehouse_wduck"))
//        hub_to_park = AssetsTrajectoryManager.load(SIDE("hub_to_park_bridge"))
//
//    }
//
//    override fun getRoutine(): Action =
//        RunLinear(
//            RunParallelWait(
//                OuttakeSetLevel(preloadLevel),
//                RunTrajectory(start_to_hub),
//            ),
//            RunTrajectory(hub_to_carousel),
//            RunCarousel(2125, 0.25),
//            DoNCycles(4, wPoints, carousel_to_warehouse, side),
//            RunTrajectory(hub_to_park)
//    )
//}