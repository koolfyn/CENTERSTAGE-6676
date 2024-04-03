package org.firstinspires.ftc.teamcode.autos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.Encoded;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name = "RR Blue Back Truss")
public class rrBlueBackTruss extends LinearOpMode {
    private FirstVisionProcessor visionProcessor;
    private VisionPortal visionPortal;
    private Encoded encoded;

    @Override
    public void runOpMode() {
        encoded = new Encoded(hardwareMap, telemetry);
        visionProcessor = new FirstVisionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), visionProcessor);

        while (!isStarted()) {
            telemetry.addData("Identified", visionProcessor.getSelection());
            telemetry.update();
        }

        waitForStart();
        visionPortal.stopStreaming();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = (new Pose2d(15, 70, Math.toRadians(270)));
        drive.setPoseEstimate(startPose);

        switch (visionProcessor.getSelection()) {
            case LEFT:
                TrajectorySequence blueBLT = drive.trajectorySequenceBuilder(startPose)
                        //.addDisplacementMarker(()-> {encoded.armtoGroundAuto();})
                        .lineToConstantHeading(new Vector2d(23,40)) // to spikemark
//                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(28, 46)) // back up
                        //   .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .splineToLinearHeading(new Pose2d(50,42), Math.toRadians(0)) // to bd
//                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(47,42)) // safely move from bd
                        .lineToLinearHeading(new Pose2d(42, 58.5, Math.toRadians(180))) // line up for truss
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // fly under truss
                        .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                        .lineToConstantHeading(new Vector2d(-55, 35)) // to stack
//                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
//                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                        .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
//                        .addDisplacementMarker (()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                        .lineToLinearHeading(new Pose2d(-42, 58.5, Math.toRadians(0))) // lineup for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // line up for truss
                        // .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .splineToConstantHeading(new Vector2d(50,42), Math.toRadians(0)) // to bd
//                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 42)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();
                drive.followTrajectorySequence(blueBLT);

                break;

            case NONE:
            case MIDDLE:
               TrajectorySequence blueBMT = drive.trajectorySequenceBuilder(startPose)
                       .addTemporalMarker(0,()-> {encoded.closeClaw();})
                       .lineToConstantHeading(new Vector2d(12,36)) // to spikemark
//                                .addDisplacementMarker(()-> {encoded.armtoGroundAuto();})
//                                .addDisplacementMarker(()->{encoded.openBottomClaw();})
                       .lineToConstantHeading(new Vector2d(12, 41)) // back up
                       .lineToConstantHeading(new Vector2d(30, 45)) // move
                       //  .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                       .splineToLinearHeading(new Pose2d(50,36), Math.toRadians(0)) // to bd
//                                .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                                .addDisplacementMarker(()-> {encoded.closeClaw();})
                       .lineToConstantHeading(new Vector2d(47,36)) // safely move from bd
                       .lineToLinearHeading(new Pose2d(42, 58.5, Math.toRadians(180))) // line up for truss
                       .lineToConstantHeading(new Vector2d(-42, 58.5)) // fly under truss
                       .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                       .lineToConstantHeading(new Vector2d(-55, 35)) // to stack
//                                .addDisplacementMarker(()->{encoded.openBottomClaw();})
//                                .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                                .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
//                                .addDisplacementMarker (()-> {encoded.closeClaw();})
                       .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                       .lineToLinearHeading(new Pose2d(-42, 58.5, Math.toRadians(0))) // lineup for truss
                       .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                       .splineToConstantHeading(new Vector2d(50,36), Math.toRadians(0)) // to bd
//                                .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
//                                .addDisplacementMarker(()-> {encoded.openTopClaw();})
                       .lineToConstantHeading(new Vector2d(42, 36)) // back up from bd
                       .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                       //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                    .build();
               drive.followTrajectorySequence(blueBMT);
                break;

            case RIGHT:
                TrajectorySequence blueBRT = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(23,33)) // safely move
                        .lineToLinearHeading(new Pose2d(19,31, Math.toRadians(180))) // orientate
                        //     .addDisplacementMarker(()-> {encoded.armtoGroundAuto();})
                        .lineToConstantHeading(new Vector2d(9, 32)) // right spikemark
//                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(14, 32)) // back up
                        //   .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .lineToLinearHeading(new Pose2d(50,29, Math.toRadians(0))) // to bd
//                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42,42)) // safely move from bd
                        .lineToLinearHeading(new Pose2d(42, 58.5, Math.toRadians(180))) // line up for truss
                        .lineToConstantHeading(new Vector2d(-50, 58.5)) // fly under truss
                        .lineToConstantHeading(new Vector2d(-55, 35)) // to stack
//                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
//                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                        .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
//                        .addDisplacementMarker (()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-45,35)) // safely move from bd
                        .lineToLinearHeading(new Pose2d(-50, 58.5, Math.toRadians(0))) // lineup for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToConstantHeading(new Vector2d(50,29), Math.toRadians(0)) // to bd
//                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
//                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 29)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                    .build();
                drive.followTrajectorySequence(blueBRT);

                break;
        }
    }

}

