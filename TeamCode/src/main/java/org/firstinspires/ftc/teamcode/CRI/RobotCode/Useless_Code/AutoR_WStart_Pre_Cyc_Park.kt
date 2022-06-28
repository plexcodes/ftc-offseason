//package org.firstinspires.ftc.teamcode.CRI.specialized.Useless_Code
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous
//import org.firstinspires.ftc.teamcode.CRI.RR.util.AssetsTrajectoryManager
//import org.firstinspires.ftc.teamcode.CRI.bt.Action
//import org.firstinspires.ftc.teamcode.CRI.bt.actions.RunTrajectory
//import org.firstinspires.ftc.teamcode.CRI.bt.actions.controlflow.RunLinear
//import org.firstinspires.ftc.teamcode.CRI.bt.actions.controlflow.RunParallelWait
//import org.firstinspires.ftc.teamcode.CRI.bt.actions.outtake.OuttakeDropFreight
//import org.firstinspires.ftc.teamcode.CRI.bt.actions.outtake.OuttakeSetLevel
//import org.firstinspires.ftc.teamcode.CRI.specialized.AutoOpV4Base
//
//@Autonomous(name = "Red W Pre Cyc Park", preselectTeleOp = "TeleOp Red")
//class AutoR_WStart_Pre_Cyc_Park: AutoOpV4Base() {
//
//    override fun precompileTrajectories() {
//        side = Side.RED
//        startLocation = StartLocation.WAREHOUSE
//
//        start_to_hub = AssetsTrajectoryManager.load(SIDE("wstart_to_hub"))
//        hub_to_park = AssetsTrajectoryManager.load(SIDE("hub_to_park"))
//
//    }
//
//    override fun getRoutine(): Action =
//        RunLinear(
//            RunParallelWait(
//                OuttakeSetLevel(preloadLevel),
//                RunTrajectory(start_to_hub)
//            ),
//            OuttakeDropFreight(),
//            DoNCycles(4, wPoints, hPoints, side),
//            RunTrajectory(hub_to_park)
//        )
//}