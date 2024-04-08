package org.firstinspires.ftc.teamcode.rrautos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.main.Encoded;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name = "RR Red Front No Cycle")
public class rrRedFrontNoCycle extends LinearOpMode{
    private Encoded encoded;
    private FirstVisionProcessor visionProcessor;
    private VisionPortal visionPortal;

    @Override
    public void runOpMode() {
        encoded = new Encoded(hardwareMap, telemetry);
        visionProcessor = new FirstVisionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), visionProcessor);
//
//        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
//        Pose2d startPose = (new Pose2d(-35, -61.5, Math.toRadians(90)));
//        drive.setPoseEstimate(startPose);

//        TrajectorySequence backL =drive.trajectorySequenceBuilder(startPose)
//                .build();
//        TrajectorySequence backM =drive.trajectorySequenceBuilder(startPose)
//                .build();
//
//        TrajectorySequence backR =drive.trajectorySequenceBuilder(startPose)
//                .lineToSplineHeading(new Pose2d(23.5,-54,Math.toRadians(90)))
//                //purple dropped
//                .waitSeconds(1)
//                .lineToSplineHeading(new Pose2d(48,-42,Math.toRadians(0)))
//                .waitSeconds(1)
//                //yellow dropped
//                .build();

//
        telemetry.addData("Identified", visionProcessor.getSelection());
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = (new Pose2d(-37, -61.5, Math.toRadians(90)));
        drive.setPoseEstimate(startPose);

        while (!isStarted()) {
            telemetry.addData("Identified", visionProcessor.getSelection());
            telemetry.update();
        }

        waitForStart();
        visionPortal.stopStreaming();

        if (isStopRequested()) return;
        switch (visionProcessor.getSelection()) {
            case LEFT:
                TrajectorySequence frontL = drive.trajectorySequenceBuilder(new Pose2d(-35, -61.5, Math.toRadians(90)))
                        .waitSeconds(1)
                        .addTemporalMarker(0,()->{encoded.closeClaw();})
                        .addTemporalMarker(0.5,()->{encoded.armtoGroundAuto();})
                        .lineTo(new Vector2d(-47,-50))
                        .waitSeconds(2)
                        .addTemporalMarker(2,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(2.5,()-> {encoded.armScoreAuto();})
                        //purple dropped
                        .lineToSplineHeading(new Pose2d(-36,-59,Math.toRadians(0)))
                        .waitSeconds(0.5)
                        .splineTo(new Vector2d(10,-59),Math.toRadians(0))
                        .splineTo(new Vector2d(53,-31),Math.toRadians(0))
                        .waitSeconds(1)
                        //yellow drop
                        .addTemporalMarker(12,()-> {encoded.openTopClaw();})
                        .lineTo(new Vector2d(50,-58.5))
                        .build();
                drive.followTrajectorySequence(frontL);
                break;

            case NONE:
            case MIDDLE:
                TrajectorySequence frontM = drive.trajectorySequenceBuilder(startPose)
                        .waitSeconds(1)
                        .addTemporalMarker(0,()->{encoded.closeClaw();})
                        .addTemporalMarker(0.5,()->{encoded.armtoGroundAuto();})
                        .lineTo(new Vector2d(-39,-42))
                        .waitSeconds(1)
                        .addTemporalMarker(2.5,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(3,()-> {encoded.armScoreAuto();})
                        //purple dropped
                        .lineToSplineHeading(new Pose2d(-36,-59,Math.toRadians(0)))
                        .waitSeconds(0.5)
                        .splineTo(new Vector2d(10,-59),Math.toRadians(0))
                        .splineTo(new Vector2d(54,-36),Math.toRadians(0))
                        .waitSeconds(2)
                        .addTemporalMarker(9.5,()-> {encoded.openTopClaw();})
                        //yellow dropped
                        .lineTo(new Vector2d(50,-58.5))
                        .build();
                drive.followTrajectorySequence(frontM);
                break;

            case RIGHT:
                TrajectorySequence frontR = drive.trajectorySequenceBuilder(startPose)
                        .waitSeconds(1)
                        .addTemporalMarker(0,()->{encoded.closeClaw();})
                        .addTemporalMarker(0.5,()->{encoded.armtoGroundAuto();})
                        .lineToSplineHeading(new Pose2d(-35,-48,Math.toRadians(45)))
                        .waitSeconds(1)
                        .addTemporalMarker(2,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(2.5,()-> {encoded.armScoreAuto();})
                        //purple dropped
                        .lineToSplineHeading(new Pose2d(-40,-59,Math.toRadians(0)))
                        .waitSeconds(1)
                        .splineTo(new Vector2d(10,-59),Math.toRadians(0))
                        .splineTo(new Vector2d(54,-44),Math.toRadians(0))
                        .waitSeconds(1)
                        .addTemporalMarker(9.5,()-> {encoded.openTopClaw();})
                        //yellow dropped
                        .lineTo(new Vector2d(50,-58.5))
                        .build();
                drive.followTrajectorySequence(frontR);

                break;
        }
    }
}



