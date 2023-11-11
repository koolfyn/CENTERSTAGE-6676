package org.firstinspires.ftc.teamcode;//package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.teamcode.OpenCV.AprilTagDetectionPipeline;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous
public class auto extends LinearOpMode {
//    OpenCvCamera camera;
//    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    //Tag IDs of sleeve
    int Left = 2;
    int Middle = 4;
    int Right = 6;

    AprilTagDetection tagOfInterest = null;

    @Override
    public void runOpMode() {
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
//
//        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);
//        camera.setPipeline(aprilTagDetectionPipeline);
//
//        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
//            @Override
//            public void onOpened() {
//                camera.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT);
//            }
//
//            @Override
//            public void onError(int errorCode) {
//
//            }
//        });
//
//        telemetry.setMsTransmissionInterval(50);
//
//        while (!isStarted() && !isStopRequested()) {
//            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();
//
//            if (currentDetections.size() != 0) {
//                boolean tagFound = false;
//
//                for (AprilTagDetection tag : currentDetections) {
//                    if (tag.id == Left || tag.id == Middle || tag.id == Right) {
//                        tagOfInterest = tag;
//                        tagFound = true;
//                        break;
//                    }
//                }
//
//                if (tagFound) {
//                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
//                    tagToTelemetry(tagOfInterest);
//                } else {
//                    telemetry.addLine("Don't see tag of interest :(");
//
//                    if (tagOfInterest == null) {
//                        telemetry.addLine("(The tag has never been seen)");
//                    } else {
//                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
//                        tagToTelemetry(tagOfInterest);
//                    }
//                }
//
//            } else {
//                telemetry.addLine("Don't see tag of interest :(");
//
//                if (tagOfInterest == null) {
//                    telemetry.addLine("(The tag has never been seen)");
//                } else {
//                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
//                    tagToTelemetry(tagOfInterest);
//                }
//
//            }
//
//            telemetry.update();
//            sleep(20);
//        }
//
//        if (tagOfInterest != null) {
//            telemetry.addLine("Tag snapshot:\n");
//            tagToTelemetry(tagOfInterest);
//            telemetry.update();
//        } else {
//            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
//            telemetry.update();
//        }
//        RobotEncoded robotencoded = new RobotEncoded(hardwareMap, telemetry);
//
//        if (tagOfInterest == null || tagOfInterest.id == Left) {
//            waitForStart();
//
//            robotencoded.closeClaw();
//            robotencoded.forward(2,1100);
//            sleep(100);
//            robotencoded.strafeLeft(21,1000);
//            sleep(100);
//            robotencoded.forward(22,1100);
//            sleep(100);
//            robotencoded.strafeRight(10,1000);
//            sleep(100);
//            robotencoded.setSlidePosition(1500,Constants.MJ);
//            sleep(100);
//            robotencoded.forward(6,1100);
//            sleep(700);
//            robotencoded.openClaw();
//            sleep(700);
//            robotencoded.backward(7,1100);
//            sleep(100);
//            robotencoded.strafeLeft(14,1000);
//            sleep(100);
//            robotencoded.forward(22,1000);
//            sleep(100);
//            robotencoded.turnRight(19,900);
//            sleep(100);
//            robotencoded.openClaw();
//            sleep(100);
//            robotencoded.setSlidePosition(1500,5.5);
//            sleep(100);
//            robotencoded.forward(49,1100);
//            sleep(300);
//            robotencoded.closeClaw();
//            sleep(500);
//            robotencoded.setSlidePosition(1500,Constants.MJ);
//            sleep(100);
//            robotencoded.backward(37,1100);
//            sleep(100);
//            robotencoded.turnRight(19,900);
//            sleep(100);
//            robotencoded.forward(4.5,1100);
//            sleep(700);
//            robotencoded.openClaw();
//            sleep(700);
//            robotencoded.backward(7,1100);
//            sleep(100);
//            robotencoded.setSlidePosition(1500,Constants.GJ);
//            sleep(100);
//            robotencoded.strafeRight(12,1300);
//            sleep(100);
//
//        }
//        else if(tagOfInterest.id == Middle) {
//            waitForStart();
//
//            robotencoded.closeClaw();
//            robotencoded.forward(2,1100);
//            sleep(100);
//            robotencoded.strafeLeft(21,1000);
//            sleep(100);
//            robotencoded.forward(22,1100);
//            sleep(100);
//            robotencoded.strafeRight(10,1000);
//            sleep(100);
//            robotencoded.setSlidePosition(1500,Constants.MJ);
//            sleep(100);
//            robotencoded.forward(6,1100);
//            sleep(700);
//            robotencoded.openClaw();
//            sleep(700);
//            robotencoded.backward(7,1100);
//            sleep(100);
//            robotencoded.strafeLeft(14,1000);
//            sleep(100);
//            robotencoded.forward(22,1000);
//            sleep(100);
//            robotencoded.turnRight(19,900);
//            sleep(100);
//            robotencoded.openClaw();
//            sleep(100);
//            robotencoded.setSlidePosition(1500,5.5);
//            sleep(100);
//            robotencoded.forward(49,1100);
//            sleep(300);
//            robotencoded.closeClaw();
//            sleep(500);
//            robotencoded.setSlidePosition(1500,Constants.MJ);
//            sleep(100);
//            robotencoded.backward(37,1100);
//            sleep(100);
//            robotencoded.turnRight(19,900);
//            sleep(100);
//            robotencoded.forward(4.5,1100);
//            sleep(700);
//            robotencoded.openClaw();
//            sleep(700);
//            robotencoded.backward(7,1100);
//            sleep(100);
//            robotencoded.setSlidePosition(1500,Constants.GJ);
//            sleep(100);
//
//        }
//        else if(tagOfInterest.id == Right) {
//            waitForStart();
//
//            robotencoded.closeClaw();
//            robotencoded.forward(2,1100);
//            sleep(90);
//            robotencoded.strafeLeft(21,1000);
//            sleep(100);
//            robotencoded.forward(22,1100);
//            sleep(100);
//            robotencoded.strafeRight(10,1000);
//            sleep(100);
//            robotencoded.setSlidePosition(1500,Constants.MJ);
//            sleep(100);
//            robotencoded.forward(6,1100);
//            sleep(700);
//            robotencoded.openClaw();
//            sleep(700);
//            robotencoded.backward(7,1100);
//            sleep(100);
//            robotencoded.strafeLeft(14,1000);
//            sleep(100);
//            robotencoded.forward(22,1000);
//            sleep(100);
//            robotencoded.turnRight(19,900);
//            sleep(100);
//            robotencoded.openClaw();
//            sleep(100);
//            robotencoded.setSlidePosition(1500,5.5);
//            sleep(100);
//            robotencoded.forward(49,1100);
//            sleep(300);
//            robotencoded.closeClaw();
//            sleep(500);
//            robotencoded.setSlidePosition(1500,Constants.MJ);
//            sleep(100);
//            robotencoded.backward(37,1100);
//            sleep(100);
//            robotencoded.turnRight(19,900);
//            sleep(100);
//            robotencoded.forward(4.5,1100);
//            sleep(700);
//            robotencoded.openClaw();
//            sleep(700);
//            robotencoded.backward(7,1100);
//            sleep(100);
//            robotencoded.setSlidePosition(1500,Constants.GJ);
//            sleep(100);
//            robotencoded.strafeLeft(35,1400);
//
//        }
//        while (opModeIsActive()) {sleep(20);}
//    }
//    //robotencoded.forward(28,850);
////
//////        robot.turnPID(90);
////        robotencoded.turnRight(22,800); //turn 90 degrees
////
////        robotencoded.forward(18,850);
////
////        robotencoded.setSlidePosition(850,6);
////        sleep(1000);
////
////        robotencoded.forward(3,800);
////        robotencoded.openClaw();
////        sleep(1000);
////        robotencoded.closeClaw();
////        sleep(1000);
////
////        robotencoded.setSlidePosition(1000, Constants.LJ);
////
////        robotencoded.backward(12,850);
////
//////        robot.turnPID(90);
////        robotencoded.turnRight(22,800);
////
////        robotencoded.forward(3,850);
////
////        robotencoded.openClaw();
////        robotencoded.stopBot();
////        sleep(1000);
//
//    void tagToTelemetry(AprilTagDetection detection)
//    {
//        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
//        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
//        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
//        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
//        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
//        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
//        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
  }
}