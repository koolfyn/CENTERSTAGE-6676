package org.firstinspires.ftc.teamcode.autos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.RobotEncoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name = "rrBlueBackYellowOnly")
public class rrBlueBackYellowOnly extends OpMode {
    private FirstVisionProcessor visionProcessor;
    private VisionPortal visionPortal;
    private RobotEncoded robotEncoded;

    @Override
    public void init() {
        robotEncoded = new RobotEncoded(hardwareMap, telemetry);
        visionProcessor = new FirstVisionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), visionProcessor);
    }
    @Override
    public void init_loop() {
        telemetry.addData("Identified", visionProcessor.getSelection());
    }

    @Override
    public void start() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        visionPortal.stopStreaming();
        telemetry.addData("Identified", visionProcessor.getSelection());
        switch (visionProcessor.getSelection()) {
            case LEFT:
                drive.trajectorySequenceBuilder(new Pose2d(12, 74, Math.toRadians(90)))
                        .lineToConstantHeading(new Vector2d(23,35)) // to spikemark
                        .addTemporalMarker(0,()-> {robotEncoded.armtoGround();})
                        .addTemporalMarker(0.5,()->{robotEncoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(23, 40)) // back up
                        .splineToLinearHeading(new Pose2d(50,42), Math.toRadians(0)) // to bd
                        .addTemporalMarker(0,()-> {robotEncoded.armtoLowSetLine();})
                        .addTemporalMarker(0.5, ()-> {robotEncoded.openTopClaw();})
                        .addTemporalMarker(0.5,()-> {robotEncoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(43, 42)) // back up
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();

                break;

            case NONE:
            case MIDDLE:
                drive.trajectorySequenceBuilder(new Pose2d(12, 74, Math.toRadians(90)))
                        .lineToConstantHeading(new Vector2d(12,30)) // to spikemark
                        .addTemporalMarker(0,()-> {robotEncoded.armtoGround();})
                        .addTemporalMarker(0.5,()->{robotEncoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(12, 35)) // back up
                        .splineToLinearHeading(new Pose2d(50,36), Math.toRadians(0)) // to bd
                        .addTemporalMarker(0,()-> {robotEncoded.armtoLowSetLine();})
                        .addTemporalMarker(0.5, ()-> {robotEncoded.openTopClaw();})
                        .addTemporalMarker(0.5,()-> {robotEncoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(43, 36)) // back up
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();
                break;

            case RIGHT:
                drive.trajectorySequenceBuilder(new Pose2d(12, 74, Math.toRadians(90)))
                        .lineToConstantHeading(new Vector2d(23,31)) // safely move
                        .lineToLinearHeading(new Pose2d(19,31, Math.toRadians(0))) // orientate
                        .lineToConstantHeading(new Vector2d(9, 30)) // right spikemark
                        .addTemporalMarker(0,()-> {robotEncoded.armtoGround();})
                        .addTemporalMarker(0.5,()->{robotEncoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(12, 30)) // back up
                        .splineToLinearHeading(new Pose2d(50,29), Math.toRadians(0)) // to bd
                        .addTemporalMarker(0,()-> {robotEncoded.armtoLowSetLine();})
                        .addTemporalMarker(0.5, ()-> {robotEncoded.openTopClaw();})
                        .addTemporalMarker(0.5,()-> {robotEncoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(43, 29)) // back up
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();
                break;
        }
    }

    @Override
    public void loop() {

    }

}