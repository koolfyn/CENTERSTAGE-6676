package org.firstinspires.ftc.teamcode.rrautos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.Encoded;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name = "RR Blue Back Yellow Only")
public class rrBlueBackYellowOnly extends LinearOpMode {

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
                TrajectorySequence blueBLYO = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .addTemporalMarker(0.25,()-> {encoded.armtoGroundAuto();})
                        .lineToConstantHeading(new Vector2d(22,58)) // to spikemark
                        .addTemporalMarker(1,()->{encoded.openBottomClaw();})
                        .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                        .splineToLinearHeading(new Pose2d(49.5,52), Math.toRadians(0)) // to bd
                        .addTemporalMarker(2.7, ()-> {encoded.openTopClaw();})
                        .splineToConstantHeading(new Vector2d(58,16), Math.toRadians(0)) // spline into park (RIGHT)

                        .build();
                drive.followTrajectorySequence(blueBLYO);

                break;

            case NONE:
            case MIDDLE:
               TrajectorySequence blueBMYO = drive.trajectorySequenceBuilder(startPose)
                       .addTemporalMarker(0,()-> {encoded.closeClaw();})
                       .addTemporalMarker(0.25,()-> {encoded.armtoGroundAuto();})
                       .lineToConstantHeading(new Vector2d(16,51)) // to spikemark
                       .addTemporalMarker(1,()->{encoded.openBottomClaw();})
                       .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                       //  .splineToLinearHeading(new Pose2d(49.5,44), Math.toRadians(0)) // to bd
                       .lineToLinearHeading(new Pose2d(51, 45,Math.toRadians(0))) // to bd
                       .addTemporalMarker(3.13, ()-> {encoded.openTopClaw();})
                       .splineToConstantHeading(new Vector2d(58,16), Math.toRadians(0)) // spline into park (RIGHT)

                        .build();
               drive.followTrajectorySequence(blueBMYO);

                break;

            case RIGHT:
               TrajectorySequence blueBRYO = drive.trajectorySequenceBuilder(startPose)
                       .addTemporalMarker(0,()-> {encoded.closeClaw();})
                       .addTemporalMarker(0.05,()-> {encoded.armtoGroundAuto();})
                       .lineToSplineHeading(new Pose2d(14,54, Math.toRadians(220))) // to spikemark
                       .addTemporalMarker(1,()->{encoded.openBottomClaw();})
                       .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                       .lineToSplineHeading(new Pose2d(52,37, Math.toRadians(0))) // to bd
                       .waitSeconds(0.1)
                       .addTemporalMarker(3.5,()-> {encoded.openTopClaw();})
                       .lineToConstantHeading(new Vector2d(47, 37)) // back up from bd
                       .splineToConstantHeading(new Vector2d(58,16), Math.toRadians(0)) // spline into park (RIGHT)


                        .build();
               drive.followTrajectorySequence(blueBRYO);

                break;
        }
    }


}