package org.firstinspires.ftc.teamcode.autos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.main.Encoded;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;


@Autonomous(name = "1testroadrunnerLinear")
public class testRoadRunnerLinear extends LinearOpMode {
    private FirstVisionProcessor visionProcessor;
    private VisionPortal visionPortal;
    private Encoded encoded;

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

        waitForStart();
        visionPortal.stopStreaming();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = (new Pose2d(-35, -61.5, Math.toRadians(90)));
        drive.setPoseEstimate(startPose);

        switch (visionProcessor.getSelection()) {
            case LEFT:
                TrajectorySequence frontL = drive.trajectorySequenceBuilder(startPose)
                        .lineTo(new Vector2d(-47,-52))
                        .waitSeconds(2)
                        .addTemporalMarker(0,()-> {encoded.armtoGround();})
                        .addTemporalMarker(0.5,()-> {encoded.openBottomClaw();})
                        //purple dropped
                        .lineToSplineHeading(new Pose2d(-35,-59,Math.toRadians(0)))
                        .waitSeconds(1)
                        .splineTo(new Vector2d(10,-59),Math.toRadians(0))
                        .splineTo(new Vector2d(48,-31),Math.toRadians(0))
                        .addTemporalMarker(5,()-> {encoded.armScoreAuto();})
                        .addTemporalMarker(6,()-> {encoded.openTopClaw();})
                        .waitSeconds(1)
                        //yellow dropped
                        .setReversed(true)
                        .splineTo(new Vector2d(10,-59),Math.toRadians(180))
                        .splineTo(new Vector2d(-35,-59),Math.toRadians(180))
                        .setReversed(false)
                        .lineToSplineHeading(new Pose2d(-56,-52,Math.toRadians(130)))
                        .addTemporalMarker(8,()-> {encoded.armtoGround();})
                        .addTemporalMarker(9,()-> {encoded.openTopClaw();})
                        .build();
                drive.followTrajectorySequence(frontL);

                break;

            case NONE:
            case MIDDLE:
                TrajectorySequence frontM = drive.trajectorySequenceBuilder(startPose)
                        .lineTo(new Vector2d(-37,-41))
                        //purple dropped
                        .lineToSplineHeading(new Pose2d(-36,-59,Math.toRadians(0)))
                        .waitSeconds(1)
                        .splineTo(new Vector2d(10,-59),Math.toRadians(0))
                        .splineTo(new Vector2d(48,-35),Math.toRadians(0))
                        .waitSeconds(1)
                        //yellow dropped
                        .setReversed(true)
                        .splineTo(new Vector2d(10,-59),Math.toRadians(180))
                        .splineTo(new Vector2d(-35,-59),Math.toRadians(180))
                        .setReversed(false)
                        .lineToSplineHeading(new Pose2d(-56,-52,Math.toRadians(130)))
                        .build();
                drive.followTrajectorySequence(frontM);
                break;

            case RIGHT:
                TrajectorySequence frontR = drive.trajectorySequenceBuilder(startPose)
                        .lineToSplineHeading(new Pose2d(-38,-48,Math.toRadians(45)))
                        //purple dropped
                        .lineToSplineHeading(new Pose2d(-36,-59,Math.toRadians(0)))
                        .waitSeconds(1)
                        .splineTo(new Vector2d(10,-59),Math.toRadians(0))
                        .splineTo(new Vector2d(48,-40),Math.toRadians(0))
                        .waitSeconds(1)
                        //yellow dropped
                        .setReversed(true)
                        .splineTo(new Vector2d(10,-59),Math.toRadians(180))
                        .splineTo(new Vector2d(-35,-59),Math.toRadians(180))
                        .setReversed(false)
                        .lineToSplineHeading(new Pose2d(-56,-52,Math.toRadians(130)))
                        .build();
                drive.followTrajectorySequence(frontR);
                break;
        }
    }


}