//package org.firstinspires.ftc.teamcode.rrautos;
//
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.geometry.Vector2d;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.teamcode.main.Encoded;
//import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
//import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
//import org.firstinspires.ftc.vision.VisionPortal;
//
//@Autonomous(name = "RR Blue Back Gate")
//public class rrBlueBackGate extends LinearOpMode {
//    private FirstVisionProcessor visionProcessor;
//    private VisionPortal visionPortal;
//    private Encoded encoded;
//
//    @Override
//    public void runOpMode() {
//        encoded = new Encoded(hardwareMap, telemetry);
//        visionProcessor = new FirstVisionProcessor();
//        visionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), visionProcessor);
//
//        while (!isStarted()) {
//            telemetry.addData("Identified", visionProcessor.getSelection());
//            telemetry.update();
//        }
//
//        waitForStart();
//        visionPortal.stopStreaming();
//
//        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
//
//        Pose2d startPose = (new Pose2d(15, 70, Math.toRadians(270)));
//        drive.setPoseEstimate(startPose);
//
//        switch (visionProcessor.getSelection()) {
//            case LEFT:
//               TrajectorySequence blueBLG = drive.trajectorySequenceBuilder(startPose) // 20 seconds
//                       .addTemporalMarker(0,()-> {encoded.closeClaw();})
//                        .lineToConstantHeading(new Vector2d(23,29)) // to spikemark
//                        .addDisplacementMarker(()-> {encoded.armtoGround();})
//                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
//                        .lineToConstantHeading(new Vector2d(23, 23)) // back up
//                        .splineToLinearHeading(new Pose2d(50,42), Math.toRadians(0)) // to bd
//                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
//                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                        .addDisplacementMarker(()-> {encoded.closeClaw();})
//                        .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
//                        .lineToLinearHeading(new Pose2d(25, 11, Math.toRadians(180))) // middle + orientate
//                        .lineToConstantHeading(new Vector2d(-55,11)) // to white stack
//                       .addDisplacementMarker(()->{encoded.openBottomClaw();})
//                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                        .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
//                        .addDisplacementMarker (()-> {encoded.closeClaw();})
//                        .lineToLinearHeading(new Pose2d(-40, 11, Math.toRadians(0))) //  orientate
//                        .lineToConstantHeading(new Vector2d(38,11)) // "safe spot"
//                        .splineToConstantHeading(new Vector2d(50,42), Math.toRadians(0)) // to bd
//                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
//                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                        .lineToConstantHeading(new Vector2d(42, 42)) // back up from bd
//                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
//                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
//
//                        .build();
//               drive.followTrajectorySequence(blueBLG);
//
//                break;
//
//            case NONE:
//            case MIDDLE:
//               TrajectorySequence blueBMG = drive.trajectorySequenceBuilder(startPose) // 21 seconds
//                       .addTemporalMarker(0,()-> {encoded.closeClaw();})
//                       .lineToConstantHeading(new Vector2d(12,32)) // to spikemark
//                       .addDisplacementMarker(()-> {encoded.armtoGround();})
//                       .addDisplacementMarker(()->{encoded.openBottomClaw();})
//                        .lineToConstantHeading(new Vector2d(12, 37)) // back up
//                        .splineToLinearHeading(new Pose2d(50,36), Math.toRadians(0)) // to bd
//                       .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
//                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                       .addDisplacementMarker(()-> {encoded.closeClaw();})
//                        .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
//                        .lineToLinearHeading(new Pose2d(0, 11, Math.toRadians(180))) // middle + orientate
//                        .lineToConstantHeading(new Vector2d(-60,11)) // to white stack
//                       .addDisplacementMarker(()->{encoded.openBottomClaw();})
//                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                       .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
//                       .addDisplacementMarker (()-> {encoded.closeClaw();})
//                        .lineToLinearHeading(new Pose2d(0, 11, Math.toRadians(0))) // middle + orientate
//                        .lineToConstantHeading(new Vector2d(38,11)) // "safe spot"
//                        .splineToConstantHeading(new Vector2d(50,36), Math.toRadians(0)) // to bd
//                       .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
//                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                        .lineToConstantHeading(new Vector2d(42, 36)) // back up from bd
//                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
//                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
//
//                            .build();
//               drive.followTrajectorySequence(blueBMG);
//
//                break;
//
//            case RIGHT:
//               TrajectorySequence blueBRG = drive.trajectorySequenceBuilder(startPose) // 21 seconds
//                       .addTemporalMarker(0,()-> {encoded.closeClaw();})
//                       .lineToConstantHeading(new Vector2d(23,31)) // safely move
//                        .lineToLinearHeading(new Pose2d(19,31, Math.toRadians(180))) // orientate
//                        .lineToConstantHeading(new Vector2d(9, 30)) // right spikemark
//                       .addDisplacementMarker(()-> {encoded.armtoGround();})
//                       .addDisplacementMarker(()->{encoded.openBottomClaw();})
//                        .lineToConstantHeading(new Vector2d(12, 30)) // back up
//                        .splineToLinearHeading(new Pose2d(50,29), Math.toRadians(0)) // to bd
//                       .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
//                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                       .addDisplacementMarker(()-> {encoded.closeClaw();})
//                        .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
//                        .lineToLinearHeading(new Pose2d(25, 11, Math.toRadians(180))) // middle + orientate
//                        .lineToConstantHeading(new Vector2d(-60,11)) // to white stack
//                       .addDisplacementMarker(()->{encoded.openBottomClaw();})
//                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                       .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
//                       .addDisplacementMarker (()-> {encoded.closeClaw();})
//                        .lineToLinearHeading(new Pose2d(-40, 11, Math.toRadians(0))) //  orientate
//                        .lineToConstantHeading(new Vector2d(38,11)) // "safe spot"
//                        .splineToConstantHeading(new Vector2d(50,29), Math.toRadians(0)) // to bd
//                       .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
//                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                        .lineToConstantHeading(new Vector2d(42, 29)) // back up from bd
//                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
//                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
//
//                        .build();
//               drive.followTrajectorySequence(blueBRG);
//
//                break;
//        }
//    }
//
//}