//package org.firstinspires.ftc.teamcode.vision;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.teamcode.drive.RobotEncoded;
//
//import org.firstinspires.ftc.vision.VisionPortal;
//
//    @Autonomous(name = "blueFront")
//        public class blueFront extends LinearOpMode {
//        private FirstVisionProcessor visionProcessor;
//        private VisionPortal visionPortal;
//
////        @Override
////        public void init_loop() {
////            visionProcessor = new FirstVisionProcessor();
////            visionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), visionProcessor);
////            telemetry.addData("Identified", visionProcessor.getSelection());
////            telemetry.update();
////        }
//
//        @Override
//        public void runOpMode() {
//            RobotEncoded robotencoded = new RobotEncoded(hardwareMap, telemetry);
//            telemetry.addData("Identified", visionProcessor.getSelection());
//            telemetry.update();
//
//        }
