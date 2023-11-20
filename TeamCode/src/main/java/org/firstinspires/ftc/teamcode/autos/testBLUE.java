//package org.firstinspires.ftc.teamcode.autos;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.teamcode.drive.RobotEncoded;
//import org.firstinspires.ftc.teamcode.drive.Robot;
//import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
//import org.firstinspires.ftc.vision.VisionPortal;
//
//@Autonomous(name="testBLUE")
//public class testBLUE extends LinearOpMode {
//    Robot robot = new Robot();
//    RobotEncoded robotEncoded = new RobotEncoded(hardwareMap, telemetry);
//    private FirstVisionProcessor visionProcessor;
//    private VisionPortal visionPortal;
//
//    @Override
//    public void init() {
//        visionProcessor = new FirstVisionProcessor();
//        visionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), visionProcessor);
//    }
//
//    @Override
//    public void init_loop() {
//        telemetry.addData("Identified", visionProcessor.getSelection());
//    }
//
//    @Override
//    public void start() {
//        visionPortal.stopStreaming();
//    }
//
//    while (opModeInInit()) {
//
//    }
//
//
//    @Override
//    public void runOpMode() {
//        telemetry.addData("Identified", visionProcessor.getSelection());
//        switch (visionProcessor.getSelection()) {
//            case LEFT:
//                robotEncoded.forward(20,800);
//                robotEncoded.turnLeft(20,900);
//                break;
//
//            case NONE:
//            case MIDDLE:
//                robotEncoded.forward(28,900);
//                break;
//
//            case RIGHT:
//                robotEncoded.forward(20,800);
//                robotEncoded.turnRight(20,900);
//                break;
//
//        }
//    }
//
//}