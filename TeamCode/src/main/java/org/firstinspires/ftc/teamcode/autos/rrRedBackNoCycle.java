package org.firstinspires.ftc.teamcode.autos;

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

@Autonomous(name = "rrRedBackNoCycle")
public class rrRedBackNoCycle extends LinearOpMode{
    private Encoded encoded;
    private FirstVisionProcessor visionProcessor;
    private VisionPortal visionPortal;

    @Override
    public void runOpMode() {
        encoded = new Encoded(hardwareMap, telemetry);
        visionProcessor = new FirstVisionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), visionProcessor);
        telemetry.addData("Identified", visionProcessor.getSelection());
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = (new Pose2d(11.5, -61.5, Math.toRadians(90)));
        drive.setPoseEstimate(startPose);

        while (!isStarted() && !isStopRequested()) {
            telemetry.addData("Identified", visionProcessor.getSelection());
        }

        waitForStart();
        visionPortal.stopStreaming();

        if (isStopRequested()) return;
        switch (visionProcessor.getSelection()) {
            case LEFT:
                TrajectorySequence backL = drive.trajectorySequenceBuilder(startPose)
                        .waitSeconds(1)
                        .addTemporalMarker(0,()->{encoded.closeClaw();})
                        .addTemporalMarker(0.5,()->{encoded.armtoGroundAuto();})
                        .lineToSplineHeading(new Pose2d(13,-46,Math.toRadians(135)))
                        .waitSeconds(1)
                        .addTemporalMarker(2,()->{encoded.openBottomClaw();})
                        .addTemporalMarker(2.5,()->{encoded.armScoreAuto();})
                        //purple dropped
                        .lineToSplineHeading(new Pose2d(53.5,-28,Math.toRadians(0)))
                        .waitSeconds(1.5)
                        .addTemporalMarker(5.5,()->{encoded.openTopClaw();})
                        //yellow dropped
                        .back(5)
                        .lineTo(new Vector2d(48,-58.5))
                        .build();
                drive.followTrajectorySequence(backL);
                break;

            case NONE:
            case MIDDLE:
                TrajectorySequence backM = drive.trajectorySequenceBuilder(startPose)
                        .waitSeconds(1)
                        .addTemporalMarker(0,()->{encoded.closeClaw();})
                        .addTemporalMarker(0.5,()->{encoded.armtoGroundAuto();})
                        .lineToSplineHeading(new Pose2d(16,-42,Math.toRadians(90)))
                        .waitSeconds(1)
                        .addTemporalMarker(2,()->{encoded.openBottomClaw();})
                        .addTemporalMarker(2.5,()->{encoded.armScoreAuto();})
                        //purple dropped
                        .lineToSplineHeading(new Pose2d(53.5,-34,Math.toRadians(0)))
                        .waitSeconds(1.5)
                        .addTemporalMarker(5.5,()->{encoded.openTopClaw();})
                        .back(5)
                        .lineTo(new Vector2d(48,-58.5))
                        //yellow dropped
                        .build();
                drive.followTrajectorySequence(backM);
                break;

            case RIGHT:
                TrajectorySequence backR =drive.trajectorySequenceBuilder(startPose)
                        .waitSeconds(1)
                        .addTemporalMarker(0,()->{encoded.closeClaw();})
                        .addTemporalMarker(0.5,()->{encoded.armtoGroundAuto();})
                        .lineToSplineHeading(new Pose2d(23,-52,Math.toRadians(90)))
                        .waitSeconds(1)
                        .addTemporalMarker(2,()->{encoded.openBottomClaw();})
                        .addTemporalMarker(2.5,()->{encoded.armScoreAuto();})
                        //purple dropped
                        .splineTo(new Vector2d(54,-40),Math.toRadians(0))
                        .waitSeconds(1.5)
                        .addTemporalMarker(5,()->{encoded.openTopClaw();})
                        .lineTo(new Vector2d(52,-58.5))
                        //yellow dropped
                        .build();
                drive.followTrajectorySequence(backR);

                break;
        }
    }
}




