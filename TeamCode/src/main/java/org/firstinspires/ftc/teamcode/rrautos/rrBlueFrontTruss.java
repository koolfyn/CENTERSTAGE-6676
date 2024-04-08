package org.firstinspires.ftc.teamcode.rrautos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.main.Encoded;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name = "RR Blue Front Truss")
public class rrBlueFrontTruss extends LinearOpMode {

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

        Pose2d startPose = (new Pose2d(-30, 70, Math.toRadians(270)));
        drive.setPoseEstimate(startPose);

        switch (visionProcessor.getSelection()) {
            case LEFT:
               TrajectorySequence blueFLT = drive.trajectorySequenceBuilder(startPose)
                       .addTemporalMarker(0,()-> {encoded.closeClaw();})
                       .lineToConstantHeading(new Vector2d(-40, 50)) // positioning
                        .lineToLinearHeading(new Pose2d(-37, 39, Math.toRadians(0))) // orientation
                        .lineToConstantHeading(new Vector2d(-35, 39)) // slow push to spikemark
                       .addDisplacementMarker(()-> {encoded.armtoGroundAuto();})
                       .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(-38, 39)) // safe backup
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 35.5), Math.toRadians(0)) // to bd
                       .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
                       .addDisplacementMarker(()-> {encoded.closeClaw();})
                        // if we have should
                        .lineToConstantHeading(new Vector2d(42, 35.5)) // back up from bd
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // line up for truss
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // fly under truss
                        .lineToLinearHeading(new Pose2d(-47, 35, Math.toRadians(180))) // orientate + to stack
                        .lineToLinearHeading(new Pose2d(-55, 35, Math.toRadians(180))) // orientate + to stack
                       .addDisplacementMarker(()->{encoded.openBottomClaw();})
                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
                       .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
                       .addDisplacementMarker (()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-50, 35))
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 35.5), Math.toRadians(0)) // to bd
                       .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 35.5)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
               drive.followTrajectorySequence(blueFLT);

                break;

            case NONE:
            case MIDDLE:
                TrajectorySequence blueFMT = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-35, 34)) // to spikemark
                        .addDisplacementMarker(()-> {encoded.armtoGroundAuto();})
                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 35.5), Math.toRadians(0)) // to bd
                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .addDisplacementMarker(()-> {encoded.closeClaw();})
                        // if we have should
                        .lineToConstantHeading(new Vector2d(42, 35.5)) // back up from bd
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // line up for truss
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // fly under truss
                        .lineToLinearHeading(new Pose2d(-47, 35, Math.toRadians(180))) // orientate + to stack
                        .lineToLinearHeading(new Pose2d(-55, 35, Math.toRadians(180))) // orientate + to stack
                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
                        .addDisplacementMarker (()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-50, 35))
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 35.5), Math.toRadians(0)) // to bd
                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 35.5)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueFMT);


                break;

            case RIGHT:
                TrajectorySequence blueFRT =  drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-46, 38)) // to right spikemark
                        .addDisplacementMarker(()-> {encoded.armtoGroundAuto();})
                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(-46, 49)) // backup
                        .lineToLinearHeading(new Pose2d(-46, 58.5, Math.toRadians(0))) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 28.5), Math.toRadians(0)) // to bd
                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .addDisplacementMarker(()-> {encoded.closeClaw();})
                        // if we have should
                        .lineToConstantHeading(new Vector2d(42, 28.5)) // back up from bd
                        .lineToLinearHeading(new Pose2d(42, 58.5, Math.toRadians(180))) // line up for truss + orientate
                        .lineToConstantHeading(new Vector2d(-34, 58.5)) // fly under truss
                        .lineToConstantHeading(new Vector2d(-34, 23.5)) // drop down to middle stack y-cord
                        .lineToConstantHeading(new Vector2d(-55, 23.5)) // to middle stack
                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
                        .addDisplacementMarker (()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-34, 23.5)) // back up
                        .lineToConstantHeading(new Vector2d(-34, 58.5)) // line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 28.5), Math.toRadians(0)) // to bd + angle
                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 28.5)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueFRT);
                break;
        }
    }


}

