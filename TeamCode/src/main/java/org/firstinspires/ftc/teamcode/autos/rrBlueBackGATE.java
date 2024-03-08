package org.firstinspires.ftc.teamcode.autos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.Encoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name = "rrBlueBackGATE")
public class rrBlueBackGATE extends OpMode {
    private FirstVisionProcessor visionProcessor;
    private VisionPortal visionPortal;
    private Encoded encoded;

    @Override
    public void init() {
        encoded = new Encoded(hardwareMap, telemetry);
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
                        .waitSeconds(.5) // drop pixel and put claw at bd height
                        .lineToConstantHeading(new Vector2d(23, 40)) // back up
                        .splineToLinearHeading(new Pose2d(50,42), Math.toRadians(0)) // to bd
                        .waitSeconds(.5) // place yellow
                        .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
                        .lineToLinearHeading(new Pose2d(25, 11, Math.toRadians(180))) // middle + orientate
                        .lineToConstantHeading(new Vector2d(-55,11)) // to white stack
                        .waitSeconds(.5) // pick up stack
                        .lineToLinearHeading(new Pose2d(-40, 11, Math.toRadians(0))) //  orientate
                        .lineToConstantHeading(new Vector2d(38,11)) // "safe spot"
                        .splineToConstantHeading(new Vector2d(50,42), Math.toRadians(0)) // to bd
                        .waitSeconds(.5) // place 1st stack
                        //if we have enough time
                        .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
                        .lineToLinearHeading(new Pose2d(25, 11, Math.toRadians(180))) // middle + orientate
                        .lineToConstantHeading(new Vector2d(-55,11)) // to white stack
                        .waitSeconds(.5) // pick up stack
                        .lineToLinearHeading(new Pose2d(-40, 11, Math.toRadians(0))) // orientate
                        .lineToConstantHeading(new Vector2d(38,11)) // // "safe spot"
                        .splineToConstantHeading(new Vector2d(50,42), Math.toRadians(0)) // to bd
                        .waitSeconds(.5) // place 2nd stack
                        .lineToConstantHeading(new Vector2d(42, 42)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)


                        .build();

                break;

            case NONE:
            case MIDDLE:
                           drive.trajectorySequenceBuilder(new Pose2d(12, 74, Math.toRadians(90)))
                            .lineToConstantHeading(new Vector2d(12,24)) // to spikemark
                            .waitSeconds(.5) // drop pixel and put claw at bd height
                            .lineToConstantHeading(new Vector2d(12, 35)) // back up
                            .splineToLinearHeading(new Pose2d(50,36), Math.toRadians(0)) // to bd
                            .waitSeconds(.5) // place yellow
                            .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
                            .lineToLinearHeading(new Pose2d(0, 11, Math.toRadians(180))) // middle + orientate
                            .lineToConstantHeading(new Vector2d(-60,11)) // to white stack
                            .waitSeconds(.5) // pick up stack
                            .lineToLinearHeading(new Pose2d(0, 11, Math.toRadians(0))) // middle + orientate
                            .lineToConstantHeading(new Vector2d(38,11)) // "safe spot"
                            .splineToConstantHeading(new Vector2d(50,36), Math.toRadians(0)) // to bd
                            .waitSeconds(.5) // place 1st stack
                            //if we have enough time to go for a 2nd time
                            .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
                            .lineToLinearHeading(new Pose2d(0, 11, Math.toRadians(180))) // middle + orientate
                            .lineToConstantHeading(new Vector2d(-60,11)) // to white stack
                            .waitSeconds(.5) // pick up stack
                            .lineToLinearHeading(new Pose2d(0, 11, Math.toRadians(0))) // middle + orientate
                            .lineToConstantHeading(new Vector2d(38,11)) // // "safe spot"
                            .splineToConstantHeading(new Vector2d(50,36), Math.toRadians(0)) // to bd
                            .waitSeconds(.5) // place 2nd stack
                            .lineToConstantHeading(new Vector2d(42, 36)) // back up from bd
                                   .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                                   //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                            .build();
                break;

            case RIGHT:
                drive.trajectorySequenceBuilder(new Pose2d(12, 74, Math.toRadians(90)))
                        .lineToConstantHeading(new Vector2d(23,31)) // safely move
                        .lineToLinearHeading(new Pose2d(19,31, Math.toRadians(0))) // orientate
                        .lineToConstantHeading(new Vector2d(9, 30)) // right spikemark
                        .waitSeconds(.5) // drop pixel and put claw at bd height
                        .lineToConstantHeading(new Vector2d(12, 30)) // back up
                        .splineToLinearHeading(new Pose2d(50,29), Math.toRadians(0)) // to bd
                        .waitSeconds(.5) // place yellow
                        .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
                        .lineToLinearHeading(new Pose2d(25, 11, Math.toRadians(180))) // middle + orientate
                        .lineToConstantHeading(new Vector2d(-60,11)) // to white stack
                        .waitSeconds(.5) // pick up stack
                        .lineToLinearHeading(new Pose2d(-40, 11, Math.toRadians(0))) //  orientate
                        .lineToConstantHeading(new Vector2d(38,11)) // "safe spot"
                        .splineToConstantHeading(new Vector2d(50,29), Math.toRadians(0)) // to bd
                        .waitSeconds(.5) // place 1st stack
                        //if we have enough time
                        .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
                        .lineToLinearHeading(new Pose2d(25, 11, Math.toRadians(180))) // middle + orientate
                        .lineToConstantHeading(new Vector2d(-60,11)) // to white stack
                        .waitSeconds(.5) // pick up stack
                        .lineToLinearHeading(new Pose2d(-40, 11, Math.toRadians(0))) // orientate
                        .lineToConstantHeading(new Vector2d(38,11)) // // "safe spot"
                        .splineToConstantHeading(new Vector2d(50,29), Math.toRadians(0)) // to bd
                        .waitSeconds(.5) // place 2nd stack
                        .lineToConstantHeading(new Vector2d(42, 29)) // back up from bd
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