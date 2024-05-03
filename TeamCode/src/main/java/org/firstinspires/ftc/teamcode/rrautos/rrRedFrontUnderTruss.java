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

@Autonomous(name = "RR Red Front UnderTruss")
public class rrRedFrontUnderTruss extends LinearOpMode{
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
        TrajectorySequence frontL = drive.trajectorySequenceBuilder(new Pose2d(-37, -61.5, Math.toRadians(90)))
                .waitSeconds(1)
                .addTemporalMarker(0,()->{encoded.closeClaw();})
                .addTemporalMarker(1,()->{encoded.armtoGroundAuto();})
                .lineTo(new Vector2d(-45,-50))
                .waitSeconds(0.5)
                .addTemporalMarker(2,()-> {encoded.openBottomClaw();})
                .addTemporalMarker(2.5,()-> {encoded.armScoreAuto();})
                //purple dropped
                .lineToSplineHeading(new Pose2d(-36,-59,Math.toRadians(0)))
                .waitSeconds(0.5)
                .splineTo(new Vector2d(10,-59),Math.toRadians(0))
                .splineTo(new Vector2d(53,-29),Math.toRadians(0))
                .waitSeconds(1)
                .addTemporalMarker(8.5,()-> {encoded.openTopClaw();})
                .lineTo(new Vector2d(48,-58.5))
                //yellow dropped
                //next few line is cycle to pixel stack not done yet
                .setReversed(true)
                .splineTo(new Vector2d(10,-58),Math.toRadians(180))
                .splineTo(new Vector2d(-35,-58),Math.toRadians(180))
                .setReversed(false)
                .lineToSplineHeading(new Pose2d(-49,-50,Math.toRadians(140)))
                .forward(5)
                .waitSeconds(0.75 )
                //at pixel stack
                .addTemporalMarker(13,()-> {encoded.armtoPixelStack();})
                .addTemporalMarker(16,()-> {encoded.closeClaw();})
                .addTemporalMarker(17,()-> {encoded.armScoreAuto();})
                .lineToSplineHeading(new Pose2d(-36,-57,Math.toRadians(0)))
                .waitSeconds(1)
                .splineTo(new Vector2d(10,-57),Math.toRadians(0))
                .splineTo(new Vector2d(56,-34),Math.toRadians(0))
                .waitSeconds(0.5)
                .addTemporalMarker(23,()-> {
                    encoded.openTopClaw();
                    encoded.openBottomClaw();})
                .lineTo(new Vector2d(48,-58.5))
                .build();
        drive.followTrajectorySequence(frontL);
                break;

            case NONE:
            case MIDDLE:
                TrajectorySequence frontM = drive.trajectorySequenceBuilder(startPose)
                        .waitSeconds(1)
                        .addTemporalMarker(0,()->{encoded.closeClaw();})
                        .addTemporalMarker(1,()->{encoded.armtoGroundAuto();})
                        .lineTo(new Vector2d(-39,-42))
                        .waitSeconds(.5)
                        .addTemporalMarker(2,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(2.5,()-> {encoded.armScoreAuto();})
                        //purple dropped
                        .lineToSplineHeading(new Pose2d(-36,-59,Math.toRadians(0)))
                        .waitSeconds(0.5)
                        .splineTo(new Vector2d(10,-59),Math.toRadians(0))
                        .splineTo(new Vector2d(54,-35),Math.toRadians(0))
                        .waitSeconds(1)
                        .addTemporalMarker(8.5,()-> {encoded.openTopClaw();})
                        //yellow dropped
                        .lineTo(new Vector2d(48,-58.5))
                        .setReversed(true)
                        .splineTo(new Vector2d(10,-58),Math.toRadians(180))
                        .splineTo(new Vector2d(-35,-58),Math.toRadians(180))
                        .setReversed(false)
                        .lineToSplineHeading(new Pose2d(-49,-50,Math.toRadians(140)))
                        .forward(5)
                        .waitSeconds(1)
                        .addTemporalMarker(14,()-> {encoded.armtoPixelStack();})
                        .addTemporalMarker(15.5,()-> {encoded.closeClaw();})
                        .addTemporalMarker(16  ,()-> {encoded.armScoreAuto();})
                        //at pixel stack
                        .lineToSplineHeading(new Pose2d(-36,-57,Math.toRadians(0)))
                        .waitSeconds(1)
                        .splineTo(new Vector2d(10,-57),Math.toRadians(0))
                        .splineTo(new Vector2d(56,-42),Math.toRadians(0))
                        .waitSeconds(0.5)
                        .addTemporalMarker(23,()-> {
                            encoded.openTopClaw();
                            encoded.openBottomClaw();})
                        .lineTo(new Vector2d(48,-58.5))
                        .build();
                drive.followTrajectorySequence(frontM);
                break;

            case RIGHT:
                TrajectorySequence frontR = drive.trajectorySequenceBuilder(startPose)
                        .waitSeconds(1)
                        .addTemporalMarker(0,()->{encoded.closeClaw();})
                        .addTemporalMarker(1,()->{encoded.armtoGroundAuto();})
                        .lineToSplineHeading(new Pose2d(-35.5,-48,Math.toRadians(45)))
                        .waitSeconds(.1)
                        .addTemporalMarker(1.7,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(2,()-> {encoded.armScoreAuto();})
                        //purple dropped
                        .lineToSplineHeading(new Pose2d(-40,-59,Math.toRadians(0)))
                        .waitSeconds(.1)
                        .splineTo(new Vector2d(10,-59),Math.toRadians(0))
                        .splineTo(new Vector2d(54,-42),Math.toRadians(0))
                        .waitSeconds(1)
                        .addTemporalMarker(7.5,()-> {encoded.openTopClaw();})
                        .lineTo(new Vector2d(48,-58.5))
                        //yellow dropped
                        .setReversed(true)
                        .splineTo(new Vector2d(10,-58),Math.toRadians(180))
                        .splineTo(new Vector2d(-35,-58),Math.toRadians(180))
                        .setReversed(false)
                        .lineToSplineHeading(new Pose2d(-49,-50,Math.toRadians(140)))
                        .forward(5)
                        .waitSeconds(1)
                        //at pixel stack
                        .addTemporalMarker(12,()-> {encoded.armtoPixelStack();})
                        .addTemporalMarker(14,()-> {encoded.closeClaw();})
                        .addTemporalMarker(15,()-> {encoded.armScoreAuto();})
                        .lineToSplineHeading(new Pose2d(-36,-57,Math.toRadians(0)))
                        .waitSeconds(.5)
                        .splineTo(new Vector2d(10,-57),Math.toRadians(0))
                        .splineTo(new Vector2d(56,-34),Math.toRadians(0))
                        .waitSeconds(0.5)
                        .addTemporalMarker(21,()-> {
                            encoded.openTopClaw();
                            encoded.openBottomClaw();})
                        .lineTo(new Vector2d(48,-58.5))
                        .build();
                drive.followTrajectorySequence(frontR);

                break;
        }
    }
}




