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
                      .addTemporalMarker(0,()-> {encoded.closeClaw();})
                      .addTemporalMarker(0.08,()-> {encoded.armtoGroundAuto();})
                        .waitSeconds(1)
                        .lineToLinearHeading(new Pose2d(19, 53, Math.toRadians(280))) //spikemark
                        .addTemporalMarker(1.9,()->{encoded.openBottomClaw();})
                      .addTemporalMarker(2.1,()-> {encoded.armScoreAuto();})
                        .splineToLinearHeading(new Pose2d(51,50), Math.toRadians(0)) // to bd
                        .waitSeconds(1)
                      .addTemporalMarker(5.6, ()-> {encoded.openTopClaw();})
                           .addTemporalMarker(7.1, ()-> {encoded.armtoPixelStack();})
                      //  .lineToConstantHeading(new Vector2d(47, 49)) // back up
                        .lineToLinearHeading(new Pose2d(42, 68, Math.toRadians(182))) // lineup for truss
                        .lineToConstantHeading(new Vector2d(-45, 68)) // fly under truss
                        .lineToSplineHeading(new Pose2d(-45, 44, Math.toRadians(177.5))) // to stack
                        .lineToSplineHeading(new Pose2d(-51, 44, Math.toRadians(177.5))) // to stack
                      .addTemporalMarker(13.8, ()-> {encoded.closeClaw();})
                      .addTemporalMarker(14.3,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-50, 68, Math.toRadians(0))) // lineup for truss
                        .lineToConstantHeading(new Vector2d(36, 66)) // fly under truss
                        .splineToConstantHeading(new Vector2d(49.5,42), Math.toRadians(0)) // to bd
                        .waitSeconds(0.5)
                        .lineToConstantHeading(new Vector2d(49.5, 39))
                        .addTemporalMarker(18,()-> {encoded.openBottomClaw();})
                        .waitSeconds(0.5)
                      .addTemporalMarker(18.8, ()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(43, 42)) // back up from bd
                        .splineToConstantHeading(new Vector2d(58,14), Math.toRadians(0)) // spline into park (RIGHT)
//                      .splineToConstantHeading(new Vector2d(60, 58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();
                drive.followTrajectorySequence(blueBLT);
                break;

            case NONE:
            case MIDDLE:
                TrajectorySequence blueBMT = drive.trajectorySequenceBuilder(startPose)
                      .addTemporalMarker(0,()-> {encoded.closeClaw();})
                      .addTemporalMarker(0.08,()-> {encoded.armtoGroundAuto();})
                        .lineToConstantHeading(new Vector2d(16,51)) // to spikemark
                        .addTemporalMarker(1,()->{encoded.openBottomClaw();})
                        .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                      //  .splineToLinearHeading(new Pose2d(49.5,44), Math.toRadians(0)) // to bd
                        .lineToLinearHeading(new Pose2d(51, 45,Math.toRadians(0))) // to bd
                        .addTemporalMarker(3.13, ()-> {encoded.openTopClaw();})
                        .addTemporalMarker(3.9, ()-> {encoded.armtoPixelStack();})
                        //.lineToConstantHeading(new Vector2d(47, 42)) // back up
                        .lineToLinearHeading(new Pose2d(42, 68, Math.toRadians(182))) // line up for truss
                        .lineToConstantHeading(new Vector2d(-45, 68)) // fly under truss
                      .lineToSplineHeading(new Pose2d(-45, 44.5, Math.toRadians(180))) // to stack
                        .lineToSplineHeading(new Pose2d(-51.5, 44.5, Math.toRadians(180))) // to stack
                      .addTemporalMarker(10.2, ()-> {encoded.closeClaw();})
                      .addTemporalMarker(10.6,()-> {encoded.armScoreAuto();})
                        .lineToConstantHeading(new Vector2d(-45, 68)) // fly under truss
                        .lineToLinearHeading(new Pose2d(42, 68, Math.toRadians(182))) // line up for truss

                        .splineToLinearHeading(new Pose2d(50,36), Math.toRadians(0)) // to bd
                        .waitSeconds(1)
                      .addTemporalMarker(17,()-> {encoded.openBottomClaw();})
                      .addTemporalMarker(17.2, ()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 36)) // back up from bd

                        .splineToConstantHeading(new Vector2d(58,14), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueBMT);
                break;

            case RIGHT:
                TrajectorySequence blueBRT = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                      .addTemporalMarker(0.05,()-> {encoded.armtoGroundAuto();})
                        .lineToSplineHeading(new Pose2d(14,54, Math.toRadians(220))) // to spikemark
                      .addTemporalMarker(1,()->{encoded.openBottomClaw();})
                      .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                        .lineToSplineHeading(new Pose2d(52,37, Math.toRadians(0))) // to bd
                        .waitSeconds(0.1)
                      .addTemporalMarker(3.5,()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(47, 36)) // back up from bd
                        .lineToLinearHeading(new Pose2d(42, 67, Math.toRadians(182))) // line up for truss
                        .lineToConstantHeading(new Vector2d(-43, 67)) // fly under truss
                        .lineToSplineHeading(new Pose2d(-51, 52, Math.toRadians(204))) // to stack + 210 bc right pixel
                        .lineToSplineHeading(new Pose2d(-55, 52, Math.toRadians(204))) // to stack + 210 bc right pixel

                        .addTemporalMarker(5.5, ()-> {encoded.armtoPixelStack();})
                      .addTemporalMarker(11.2, ()-> {encoded.closeClaw();})
                      .addTemporalMarker(11.45,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-50, 67, Math.toRadians(0))) // lineup for truss
                        .lineToConstantHeading(new Vector2d(42, 67)) // fly under truss
                        .lineToSplineHeading(new Pose2d(51.7,44, Math.toRadians(0))) // to bd
                        .waitSeconds(0.5)
                      .addTemporalMarker(17.1, ()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(17.4, ()-> {encoded.openTopClaw();})

                        .lineToConstantHeading(new Vector2d(47, 36)) // back up from bd
                        .splineToConstantHeading(new Vector2d(58,14), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();
                drive.followTrajectorySequence(blueBRT);


                break;
        }
    }
}
