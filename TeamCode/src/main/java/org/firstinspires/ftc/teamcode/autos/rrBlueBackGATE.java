package org.firstinspires.ftc.teamcode.autos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import org.firstinspires.ftc.teamcode.main.Encoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name = "rrBlueBackGATE")
public class rrBlueBackGATE extends OpMode {
    private FirstVisionProcessor visionProcessor;
    private VisionPortal visionPortal;
    private Encoded encoded;

    @Override
    public void init() {
        encoded = new Encoded(hardwareMap, telemetry);
        visionProcessor = new FirstVisionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), visionProcessor);
    }
    @Override
    public void init_loop() {
        telemetry.addData("Identified", visionProcessor.getSelection());
    }

    @Override
    public void start() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = (new Pose2d(-35, -61.5, Math.toRadians(90)));
        drive.setPoseEstimate(startPose);
        visionPortal.stopStreaming();
        telemetry.addData("Identified", visionProcessor.getSelection());
        switch (visionProcessor.getSelection()) {
            case LEFT:
                drive.trajectorySequenceBuilder(new Pose2d(12, 74, Math.toRadians(90))) // 20 seconds
                        .lineToConstantHeading(new Vector2d(23,35)) // to spikemark
                        //purple down
                        .addTemporalMarker(0,()-> {encoded.armtoGround();})
                        .addTemporalMarker(0.5,()->{encoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(23, 40)) // back up
                        .splineToLinearHeading(new Pose2d(50,42), Math.toRadians(0)) // to bd
                        //yellow on bd
                        .addTemporalMarker(0,()-> {encoded.armtoLowSetLine();})
                        .addTemporalMarker(0.5, ()-> {encoded.openTopClaw();})
                        .addTemporalMarker(0.5,()-> {encoded.closeClaw();})
                        .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
                        .lineToLinearHeading(new Pose2d(25, 11, Math.toRadians(180))) // middle + orientate
                        .lineToConstantHeading(new Vector2d(-55,11)) // to white stack
                        //pick up pixel stack
                        .addTemporalMarker(0.5, ()-> {encoded.armtoPixelStack();})
                        .addTemporalMarker(0.5, ()-> {encoded.closeClaw();})
                        .lineToLinearHeading(new Pose2d(-40, 11, Math.toRadians(0))) //  orientate
                        .lineToConstantHeading(new Vector2d(38,11)) // "safe spot"
                        .splineToConstantHeading(new Vector2d(50,42), Math.toRadians(0)) // to bd
                        //drop pixel stack on bd
                        .addTemporalMarker(0,()-> {encoded.armtoLowSetLine();})
                        .addTemporalMarker(0.5, ()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 42)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        //if we have enough time
                        //.lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
                        //.lineToLinearHeading(new Pose2d(25, 11, Math.toRadians(180))) // middle + orientate
                        //.lineToConstantHeading(new Vector2d(-55,11)) // to white stack
                        //  .waitSeconds(.5) // pick up stack
                        //  .lineToLinearHeading(new Pose2d(-40, 11, Math.toRadians(0))) // orientate
                        // .lineToConstantHeading(new Vector2d(38,11)) // // "safe spot"
                        // .splineToConstantHeading(new Vector2d(50,42), Math.toRadians(0)) // to bd
                        // .waitSeconds(.5) // place 2nd stack
                        // .lineToConstantHeading(new Vector2d(42, 42)) // back up from bd


                        .build();

                break;

            case NONE:
            case MIDDLE:
                drive.trajectorySequenceBuilder(new Pose2d(12, 74, Math.toRadians(90))) // 21 seconds
                        .lineToConstantHeading(new Vector2d(12,32)) // to spikemark
                        .addTemporalMarker(0,()-> {encoded.armtoGround();})
                        .addTemporalMarker(0.5,()->{encoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(12, 37)) // back up
                        .splineToLinearHeading(new Pose2d(50,36), Math.toRadians(0)) // to bd
                        .addTemporalMarker(0,()-> {encoded.armtoLowSetLine();})
                        .addTemporalMarker(0.5, ()-> {encoded.openTopClaw();})
                        .addTemporalMarker(0.5,()-> {encoded.closeClaw();})
                        .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
                        .lineToLinearHeading(new Pose2d(0, 11, Math.toRadians(180))) // middle + orientate
                        .lineToConstantHeading(new Vector2d(-60,11)) // to white stack
                        .addTemporalMarker(0.5, ()-> {encoded.armtoPixelStack();})
                        .addTemporalMarker(0.5, ()-> {encoded.closeClaw();})
                        .lineToLinearHeading(new Pose2d(0, 11, Math.toRadians(0))) // middle + orientate
                        .lineToConstantHeading(new Vector2d(38,11)) // "safe spot"
                        .splineToConstantHeading(new Vector2d(50,36), Math.toRadians(0)) // to bd
                        .addTemporalMarker(0,()-> {encoded.armtoLowSetLine();})
                        .addTemporalMarker(0.5, ()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 36)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        //if we have enough time to go for a 2nd time
                        // .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
                        // .lineToLinearHeading(new Pose2d(0, 11, Math.toRadians(180))) // middle + orientate
                        //.lineToConstantHeading(new Vector2d(-60,11)) // to white stack
                        // .waitSeconds(.5) // pick up stack
                        // .lineToLinearHeading(new Pose2d(0, 11, Math.toRadians(0))) // middle + orientate
                        //.lineToConstantHeading(new Vector2d(38,11)) // // "safe spot"
                        //.splineToConstantHeading(new Vector2d(50,36), Math.toRadians(0)) // to bd
                        // .waitSeconds(.5) // place 2nd stack
                        //.lineToConstantHeading(new Vector2d(42, 36)) // back up from bd
                        //  .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();
                break;

            case RIGHT:
                drive.trajectorySequenceBuilder(new Pose2d(12, 74, Math.toRadians(90))) // 21 seconds
                        .lineToConstantHeading(new Vector2d(23,31)) // safely move
                        .lineToLinearHeading(new Pose2d(19,31, Math.toRadians(0))) // orientate
                        .lineToConstantHeading(new Vector2d(9, 30)) // right spikemark
                        .addTemporalMarker(0,()-> {encoded.armtoGround();})
                        .addTemporalMarker(0.5,()->{encoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(12, 30)) // back up
                        .splineToLinearHeading(new Pose2d(50,29), Math.toRadians(0)) // to bd
                        .addTemporalMarker(0,()-> {encoded.armtoLowSetLine();})
                        .addTemporalMarker(0.5, ()-> {encoded.openTopClaw();})
                        .addTemporalMarker(0.5,()-> {encoded.closeClaw();})
                        .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
                        .lineToLinearHeading(new Pose2d(25, 11, Math.toRadians(180))) // middle + orientate
                        .lineToConstantHeading(new Vector2d(-60,11)) // to white stack
                        .addTemporalMarker(0.5, ()-> {encoded.armtoPixelStack();})
                        .addTemporalMarker(0.5, ()-> {encoded.closeClaw();})
                        .lineToLinearHeading(new Pose2d(-40, 11, Math.toRadians(0))) //  orientate
                        .lineToConstantHeading(new Vector2d(38,11)) // "safe spot"
                        .splineToConstantHeading(new Vector2d(50,29), Math.toRadians(0)) // to bd
                        .addTemporalMarker(0,()-> {encoded.armtoLowSetLine();})
                        .addTemporalMarker(0.5, ()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 29)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        //if we have enough time
                        // .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
                        // .lineToLinearHeading(new Pose2d(25, 11, Math.toRadians(180))) // middle + orientate
                        //   .lineToConstantHeading(new Vector2d(-60,11)) // to white stack
                        // .waitSeconds(.5) // pick up stack
                        // .lineToLinearHeading(new Pose2d(-40, 11, Math.toRadians(0))) // orientate
                        //  .lineToConstantHeading(new Vector2d(38,11)) // // "safe spot"
                        //  .splineToConstantHeading(new Vector2d(50,29), Math.toRadians(0)) // to bd
                        //   .waitSeconds(.5) // place 2nd stack
                        // .lineToConstantHeading(new Vector2d(42, 29)) // back up from bd
                        // .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
//                TrajectorySequence test = drive.trajectorySequenceBuilder(startPose)
//                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0))
//                                .build();
//                        drive.followTrajectorySequence(test);
                break;
        }
    }

    @Override
    public void loop() {

    }

}