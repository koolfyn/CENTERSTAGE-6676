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

@Autonomous(name = "RR Blue Front Yellow Pixel Only")
public class rrBlueFrontTrussYellowOnly extends LinearOpMode {
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

        Pose2d startPose = (new Pose2d(-35, 70, Math.toRadians(270)));
        drive.setPoseEstimate(startPose);

        switch (visionProcessor.getSelection()) {
            case LEFT:
              TrajectorySequence blueLTYO = drive.trajectorySequenceBuilder(startPose)
                        .lineToConstantHeading(new Vector2d(-40, 50)) // positioning
                        .lineToLinearHeading(new Pose2d(-37, 29, Math.toRadians(180))) // orientation
                        .lineToConstantHeading(new Vector2d(-35, 29)) //slow push to spikemark
                        .addTemporalMarker(0, () -> {
                            encoded.armtoGround();
                        })
                        .addTemporalMarker(0.5, () -> {
                            encoded.openBottomClaw();
                        })
                        .lineToConstantHeading(new Vector2d(-38, 29)) // safe backup
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 35.5), Math.toRadians(0)) // to bd
                        .addTemporalMarker(0, () -> {
                            encoded.armtoLowSetLine();
                        })
                        .addTemporalMarker(0.5, () -> {
                            encoded.openTopClaw();
                        })
                        .addTemporalMarker(0.5, () -> {
                            encoded.closeClaw();
                        })
                        .lineToConstantHeading(new Vector2d(42, 35.5)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60, 9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)


                        .build();

                drive.followTrajectorySequence(blueLTYO);


                break;

            case NONE:
            case MIDDLE:
                TrajectorySequence blueMTYO = drive.trajectorySequenceBuilder(startPose)
                        .lineToConstantHeading(new Vector2d(-35, 34)) // to spikemark
                        .addTemporalMarker(0, () -> {
                            encoded.armtoGround();
                        })
                        .addTemporalMarker(0.5, () -> {
                            encoded.openBottomClaw();
                        })
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 35.5), Math.toRadians(0)) // to bd
                        .addTemporalMarker(0, () -> {
                            encoded.armtoLowSetLine();
                        })
                        .addTemporalMarker(0.5, () -> {
                            encoded.openTopClaw();
                        })
                        .addTemporalMarker(0.5, () -> {
                            encoded.closeClaw();
                        })
                        .lineToConstantHeading(new Vector2d(42, 35.5)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60, 9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();
                drive.followTrajectorySequence(blueMTYO);

                break;

            case RIGHT:
                TrajectorySequence blueRTYO = drive.trajectorySequenceBuilder(startPose)
                        .lineToConstantHeading(new Vector2d(-46, 38)) // to right spikemark
                        .addTemporalMarker(0, () -> {
                            encoded.armtoGround();
                        })
                        .addTemporalMarker(0.5, () -> {
                            encoded.openBottomClaw();
                        })
                        .lineToConstantHeading(new Vector2d(-46, 49)) // backup
                        .lineToLinearHeading(new Pose2d(-46, 58.5, Math.toRadians(0))) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 28.5), Math.toRadians(0)) // to bd
                        .addTemporalMarker(0, () -> {
                            encoded.armtoLowSetLine();
                        })
                        .addTemporalMarker(0.5, () -> {
                            encoded.openTopClaw();
                        })
                        .addTemporalMarker(0.5, () -> {
                            encoded.closeClaw();
                        })
                        .lineToConstantHeading(new Vector2d(42, 28.5)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60, 9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueRTYO);
                break;
        }
    }
}

