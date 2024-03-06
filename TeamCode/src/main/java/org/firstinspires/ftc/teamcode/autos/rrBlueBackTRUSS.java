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

@Autonomous(name = "rrBlueBackTRUSS")
public class rrBlueBackTRUSS extends OpMode {
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
                        .lineToConstantHeading(new Vector2d(23,34)) // to spikemark
                        .waitSeconds(.5) // drop pixel and put claw at bd height
                        .lineToConstantHeading(new Vector2d(23, 40)) // back up
                        .splineToLinearHeading(new Pose2d(50,42), Math.toRadians(0)) // to bd
                        .waitSeconds(.5) // place yellow
                        .lineToConstantHeading(new Vector2d(47,42)) // safely move from bd
                        .lineToLinearHeading(new Pose2d(42, 58.5, Math.toRadians(180))) // line up for truss
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // fly under truss
                        .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                        .lineToConstantHeading(new Vector2d(-55, 35)) // to stack
                        .waitSeconds(.5) // to pick up stack
                        .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                        .lineToLinearHeading(new Pose2d(-42, 58.5, Math.toRadians(0))) // lineup for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // line up for truss
                        .splineToConstantHeading(new Vector2d(50,42), Math.toRadians(0)) // to bd
                        .waitSeconds(.5) // place 1st stack
                        .lineToConstantHeading(new Vector2d(42, 42)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();

                break;

            case NONE:
            case MIDDLE:
                drive.trajectorySequenceBuilder(new Pose2d(12, 74, Math.toRadians(90)))
                        .lineToConstantHeading(new Vector2d(12,32)) // to spikemark
                    .waitSeconds(.5) // drop pixel and put claw at bd height
                    .lineToConstantHeading(new Vector2d(12, 37)) // back up
                    .splineToLinearHeading(new Pose2d(50,36), Math.toRadians(0)) // to bd
                    .waitSeconds(.5) // place yellow
                    .lineToConstantHeading(new Vector2d(47,42)) // safely move from bd
                    .lineToLinearHeading(new Pose2d(42, 58.5, Math.toRadians(180))) // line up for truss
                    .lineToConstantHeading(new Vector2d(-42, 58.5)) // fly under truss
                    .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                    .lineToConstantHeading(new Vector2d(-55, 35)) // to stack
                    .waitSeconds(.5) // to pick up stack
                    .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                    .lineToLinearHeading(new Pose2d(-42, 58.5, Math.toRadians(0))) // lineup for truss
                    .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                    .splineToConstantHeading(new Vector2d(50,36), Math.toRadians(0)) // to bd
                    .waitSeconds(.5) // place 1st stack
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
                    .lineToConstantHeading(new Vector2d(47,42)) // safely move from bd
                    .lineToLinearHeading(new Pose2d(42, 58.5, Math.toRadians(180))) // line up for truss
                    .lineToConstantHeading(new Vector2d(-42, 58.5)) // fly under truss
                    .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                    .lineToConstantHeading(new Vector2d(-55, 35)) // to stack
                    .waitSeconds(.5) // to pick up stack
                    .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                    .lineToLinearHeading(new Pose2d(-42, 58.5, Math.toRadians(0))) // lineup for truss
                    .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                    .splineToConstantHeading(new Vector2d(50,29), Math.toRadians(0)) // to bd
                    .waitSeconds(.5) // place 1st stack
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

