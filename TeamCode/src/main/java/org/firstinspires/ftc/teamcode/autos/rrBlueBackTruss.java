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

        Pose2d startPose = (new Pose2d(12, 70, Math.toRadians(270)));
        drive.setPoseEstimate(startPose);

        switch (visionProcessor.getSelection()) {
            case LEFT:
                TrajectorySequence blueBLT = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(23,34)) // to spikemark
                        .addDisplacementMarker(()-> {encoded.armtoGround();})
                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(23, 40)) // back up
                        .splineToLinearHeading(new Pose2d(50,42), Math.toRadians(0)) // to bd
                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .addDisplacementMarker(()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(47,42)) // safely move from bd
                        .lineToLinearHeading(new Pose2d(42, 58.5, Math.toRadians(180))) // line up for truss
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // fly under truss
                        .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                        .lineToConstantHeading(new Vector2d(-55, 35)) // to stack
                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
                        .addDisplacementMarker (()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                        .lineToLinearHeading(new Pose2d(-42, 58.5, Math.toRadians(0))) // lineup for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // line up for truss
                        .splineToConstantHeading(new Vector2d(50,42), Math.toRadians(0)) // to bd
                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
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
                       .lineToConstantHeading(new Vector2d(12,32)) // to spikemark
                       .addDisplacementMarker(()-> {encoded.armtoGround();})
                       .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(12, 37)) // back up
                    .splineToLinearHeading(new Pose2d(50,36), Math.toRadians(0)) // to bd
                       .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
                       .addDisplacementMarker(()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(47,42)) // safely move from bd
                    .lineToLinearHeading(new Pose2d(42, 58.5, Math.toRadians(180))) // line up for truss
                    .lineToConstantHeading(new Vector2d(-42, 58.5)) // fly under truss
                    .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                    .lineToConstantHeading(new Vector2d(-55, 35)) // to stack
                       .addDisplacementMarker(()->{encoded.openBottomClaw();})
                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
                       .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
                       .addDisplacementMarker (()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                    .lineToLinearHeading(new Pose2d(-42, 58.5, Math.toRadians(0))) // lineup for truss
                    .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                    .splineToConstantHeading(new Vector2d(50,36), Math.toRadians(0)) // to bd
                       .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 36)) // back up from bd
                    .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                    //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                    .build();
               drive.followTrajectorySequence(blueBMT);
                break;

            case RIGHT:
                TrajectorySequence blueBRT = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(23,31)) // safely move
                        .lineToLinearHeading(new Pose2d(19,31, Math.toRadians(180))) // orientate
                        .lineToConstantHeading(new Vector2d(9, 30)) // right spikemark
                        .addDisplacementMarker(()-> {encoded.armtoGround();})
                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(12, 30)) // back up
                        .splineToLinearHeading(new Pose2d(50,29), Math.toRadians(0)) // to bd
                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .addDisplacementMarker(()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(47,42)) // safely move from bd
                        .lineToLinearHeading(new Pose2d(42, 58.5, Math.toRadians(180))) // line up for truss
                        .lineToConstantHeading(new Vector2d(-50, 58.5)) // fly under truss
                        .lineToConstantHeading(new Vector2d(-55, 35)) // to stack
                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
                        .addDisplacementMarker (()-> {encoded.closeClaw();})
                        .lineToLinearHeading(new Pose2d(-50, 58.5, Math.toRadians(0))) // lineup for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToConstantHeading(new Vector2d(50,29), Math.toRadians(0)) // to bd
                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 29)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                    .build();
                drive.followTrajectorySequence(blueBRT);

                break;
        }
    }

}

