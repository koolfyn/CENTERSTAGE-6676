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
//                      .addTemporalMarker(0,()-> {encoded.closeClaw();})
//                      .addTemporalMarker(()-> {encoded.armtoGroundAuto();})
                        .lineToConstantHeading(new Vector2d(24.5,54)) // to spikemark
//                      .addTemporalMarker(()->{encoded.openBottomClaw();})
//                      .addTemporalMarker(()-> {encoded.armtoLowSetLine();})
                        .splineToLinearHeading(new Pose2d(52,49), Math.toRadians(0)) // to bd
//                      .addTemporalMarker(0.5, ()-> {encoded.openTopClaw();})
//                      .addTemporalMarker(0.5,()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(47, 49)) // back up
                        .lineToLinearHeading(new Pose2d(42, 64, Math.toRadians(182))) // lineup for truss
                        .lineToConstantHeading(new Vector2d(-50, 64)) // fly under truss
                        .lineToSplineHeading(new Pose2d(-55, 47, Math.toRadians(180))) // to stack
//                      .addTemporalMarker(0.5, ()-> {encoded.armtoPixelStack();})
//                      .addTemporalMarker(0.5, ()-> {encoded.closeClaw();})
//                      .addTemporalMarker(()-> {encoded.armtoLowSetLine();})
                        .lineToLinearHeading(new Pose2d(-50, 64, Math.toRadians(0))) // lineup for truss
                        .lineToConstantHeading(new Vector2d(42, 64)) // fly under truss
                        .splineToConstantHeading(new Vector2d(50,42), Math.toRadians(0)) // to bd
//                      .addTemporalMarker(0.5, ()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(43, 42)) // back up from bd
                        .splineToConstantHeading(new Vector2d(67,13), Math.toRadians(0)) // spline into park (RIGHT)
//                      .splineToConstantHeading(new Vector2d(60, 58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();
                drive.followTrajectorySequence(blueBLT);

                break;

            case NONE:
            case MIDDLE:
                TrajectorySequence blueBMT = drive.trajectorySequenceBuilder(startPose)
//                      .addTemporalMarker(0,()-> {encoded.closeClaw();})
//                      .addTemporalMarker(()-> {encoded.armtoGroundAuto();})
                        .lineToConstantHeading(new Vector2d(12,54)) // to spikemark
//                      .addTemporalMarker(()->{encoded.openBottomClaw();})
//                      .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .splineToLinearHeading(new Pose2d(52,42), Math.toRadians(0)) // to bd
//                      .addTemporalMarker(()-> {encoded.openTopClaw();})
//                      .addTemporalMarker(()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(47, 42)) // back up
                        .lineToLinearHeading(new Pose2d(42, 64, Math.toRadians(182))) // line up for truss
                        .lineToConstantHeading(new Vector2d(-50, 64)) // fly under truss
                        .lineToSplineHeading(new Pose2d(-55, 47, Math.toRadians(180))) // to stack
//                      .addTemporalMarker(0.5, ()-> {encoded.armtoPixelStack();})
//                      .addTemporalMarker(0.5, ()-> {encoded.closeClaw();})
//                      .addTemporalMarker(()-> {encoded.armtoLowSetLine();})
                        .lineToLinearHeading(new Pose2d(-50, 64, Math.toRadians(0))) // lineup for truss
                        .lineToConstantHeading(new Vector2d(42, 64)) // fly under truss
                        .splineToConstantHeading(new Vector2d(51,36), Math.toRadians(0)) // to bd
//                      .addTemporalMarker(0.5, ()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(43, 36)) // back up from bd
                        .splineToConstantHeading(new Vector2d(67,13), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();
                drive.followTrajectorySequence(blueBMT);

                break;

            case RIGHT:
                TrajectorySequence blueBRT = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
//                      .addTemporalMarker(()-> {encoded.armtoGroundAuto();})
                        .lineToSplineHeading(new Pose2d(15,54, Math.toRadians(240)))
//                      .addTemporalMarker(()->{encoded.openBottomClaw();})
//                      .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .lineToSplineHeading(new Pose2d(52,36, Math.toRadians(0))) // to bd
//                      .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                      .addTemporalMarker(()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(47, 36)) // back up from bd
                        .lineToLinearHeading(new Pose2d(42, 64, Math.toRadians(182))) // line up for truss
                        .lineToConstantHeading(new Vector2d(-50, 64)) // fly under truss
                        .lineToSplineHeading(new Pose2d(-55, 47, Math.toRadians(210))) // to stack + 210 bc right pixel
//                      .addTemporalMarker(0.5, ()-> {encoded.armtoPixelStack();})
//                      .addTemporalMarker(0.5, ()-> {encoded.closeClaw();})
//                      .addTemporalMarker(()-> {encoded.armtoLowSetLine();})
                        .lineToLinearHeading(new Pose2d(-50, 64, Math.toRadians(0))) // lineup for truss
                        .lineToConstantHeading(new Vector2d(42, 64)) // fly under truss
                        .lineToSplineHeading(new Pose2d(52,36, Math.toRadians(0))) // to bd
//                      .addTemporalMarker(0.5, ()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(47, 36)) // back up from bd
                        .splineToConstantHeading(new Vector2d(67,13), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();
                drive.followTrajectorySequence(blueBRT);

                break;
        }
    }
}
