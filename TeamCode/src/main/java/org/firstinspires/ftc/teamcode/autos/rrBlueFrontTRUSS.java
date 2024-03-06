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

@Autonomous(name = "rrBlueFrontTRUSS")
public class rrBlueFrontTRUSS extends OpMode {
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
                drive.trajectorySequenceBuilder(new Pose2d(-35, 70, Math.toRadians(90)))
                        .lineToConstantHeading(new Vector2d(-40, 50)) // positioning
                        .lineToLinearHeading(new Pose2d(-37, 29, Math.toRadians(180))) // orientation
                        .lineToConstantHeading(new Vector2d(-35, 29)) //slow push to spikemark
                        .waitSeconds(.5) // drop pixel
                        .lineToConstantHeading(new Vector2d(-38, 29)) // safe backup
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 35.5), Math.toRadians(0)) // to bd
                        .waitSeconds(.5) // place yellow
                        // if we have should
                        .lineToConstantHeading(new Vector2d(42, 35.5)) // back up from bd
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // line up for truss
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // fly under truss
                        .lineToLinearHeading(new Pose2d(-47, 35, Math.toRadians(180))) // orientate + to stack
                        .lineToLinearHeading(new Pose2d(-55, 35, Math.toRadians(180))) // orientate + to stack
                        .waitSeconds(.5) // to pick up stack
                        .lineToConstantHeading(new Vector2d(-50, 35))
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 35.5), Math.toRadians(0)) // to bd
                        .waitSeconds(.5) // place whites
                        .lineToConstantHeading(new Vector2d(42, 35.5)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();

                break;

            case NONE:
            case MIDDLE:
                drive.trajectorySequenceBuilder(new Pose2d(-35, 70, Math.toRadians(90)))
                        .lineToConstantHeading(new Vector2d(-35, 34)) // to spikemark
                        .waitSeconds(.5) // drop pixel and put claw at bd height
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 35.5), Math.toRadians(0)) // to bd
                        .waitSeconds(.5) // place yellow
                        // if we have should
                        .lineToConstantHeading(new Vector2d(42, 35.5)) // back up from bd
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // line up for truss
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // fly under truss
                        .lineToLinearHeading(new Pose2d(-47, 35, Math.toRadians(180))) // orientate + to stack
                        .lineToLinearHeading(new Pose2d(-55, 35, Math.toRadians(180))) // orientate + to stack
                        .waitSeconds(.5) // to pick up stack
                        .lineToConstantHeading(new Vector2d(-50, 35))
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 35.5), Math.toRadians(0)) // to bd
                        .waitSeconds(.5) // place yellow
                        .lineToConstantHeading(new Vector2d(42, 35.5)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();


                break;

            case RIGHT:
                drive.trajectorySequenceBuilder(new Pose2d(-35, 70, Math.toRadians(90)))
                        .lineToConstantHeading(new Vector2d(-46, 38)) // to right spikemark
                        .waitSeconds(.5) // drop pixel
                        .lineToConstantHeading(new Vector2d(-46, 49)) // backup
                        .lineToLinearHeading(new Pose2d(-46, 58.5, Math.toRadians(0))) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 28.5), Math.toRadians(0)) // to bd
                        .waitSeconds(.5) // place yellow
                        // if we have should
                        .lineToConstantHeading(new Vector2d(42, 28.5)) // back up from bd
                        .lineToLinearHeading(new Pose2d(42, 58.5, Math.toRadians(180))) // line up for truss + orientate
                        .lineToConstantHeading(new Vector2d(-34, 58.5)) // fly under truss
                        .lineToConstantHeading(new Vector2d(-34, 23.5)) // drop down to middle stack y-cord
                        .lineToConstantHeading(new Vector2d(-55, 23.5)) // to middle stack
                        .waitSeconds(.5) // to pick up stack
                        .lineToConstantHeading(new Vector2d(-34, 23.5)) // back up
                        .lineToConstantHeading(new Vector2d(-34, 58.5)) // line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 28.5), Math.toRadians(0)) // to bd + angle
                        .waitSeconds(.5) // place whites
                        .lineToConstantHeading(new Vector2d(42, 28.5)) // back up from bd
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

