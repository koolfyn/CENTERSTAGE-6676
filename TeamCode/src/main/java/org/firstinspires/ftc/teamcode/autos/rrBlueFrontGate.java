package org.firstinspires.ftc.teamcode.autos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.Encoded;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name = "RR Blue Front Gate")
public class rrBlueFrontGate extends LinearOpMode {
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
                TrajectorySequence blueFLG = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-40, 50)) // positioning
                        .lineToLinearHeading(new Pose2d(-37, 29, Math.toRadians(0))) // orientation
                        .lineToConstantHeading(new Vector2d(-35, 29)) //slow push to spikemark
                        .addDisplacementMarker(()-> {encoded.armtoGroundAuto();})
                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(-38, 29)) // safe backup
                        .lineToConstantHeading(new Vector2d(-49,11)) // line up for gate
                        .lineToConstantHeading(new Vector2d(42, 11)) // fly under gate
                        .splineToLinearHeading(new Pose2d(50,41.5), Math.toRadians(0)) // to bd
                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .addDisplacementMarker(()-> {encoded.closeClaw();})
                        // if we have should
                        .lineToConstantHeading(new Vector2d(42, 41.5)) // back up from bd
                        .lineToSplineHeading(new Pose2d(38,11, Math.toRadians(180))) // get into position
                        .lineToConstantHeading(new Vector2d(-41, 11)) // fly under truss
                        .lineToConstantHeading(new Vector2d(-56, 23.7)) // go to stack
                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
                        .addDisplacementMarker (()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-41, 11)) // line up for truss
                        .lineToConstantHeading(new Vector2d(38,11)) // get into position
                        .splineToLinearHeading(new Pose2d(50,41.5), Math.toRadians(0)) // to bd
                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 41.5)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueFLG);


                break;

            case NONE:
            case MIDDLE:
                TrajectorySequence blueFMG = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-33.5,32)) // to middle spikemark
                        .addDisplacementMarker(()-> {encoded.armtoGroundAuto();})
                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(-35, 41)) // backup
                        .lineToLinearHeading(new Pose2d(-49,41, Math.toRadians(0))) // out the way of spikemark
                        .lineToConstantHeading(new Vector2d(-49,11)) // line up for gate
                        .lineToConstantHeading(new Vector2d(42, 11)) // fly under gate
                        .splineToLinearHeading(new Pose2d(50,35), Math.toRadians(0)) // to bd
                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .addDisplacementMarker(()-> {encoded.closeClaw();})
                        // if we have should
                        .lineToConstantHeading(new Vector2d(42, 35)) // back up from bd
                        .lineToSplineHeading(new Pose2d(38,11, Math.toRadians(180))) // get into position
                        .lineToConstantHeading(new Vector2d(-41, 11)) // fly under truss
                        .lineToConstantHeading(new Vector2d(-56, 23.7)) // go to stack
                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
                        .addDisplacementMarker (()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-41, 11)) // line up for truss
                        .lineToConstantHeading(new Vector2d(38,11)) // get into position
                        .splineToLinearHeading(new Pose2d(50,35), Math.toRadians(0)) // to bd
                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 35)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueFMG);

                break;

            case RIGHT:
                TrajectorySequence blueFRG = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-46,38)) // to right spikemark
                        .addDisplacementMarker(()-> {encoded.armtoGroundAuto();})
                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(-46, 49)) // backup
                        .lineToLinearHeading(new Pose2d(-35,49, Math.toRadians(0))) // out the way of spikemark
                        .lineToConstantHeading(new Vector2d(-35,11))
                        .lineToConstantHeading(new Vector2d(42,11))
                        .splineToLinearHeading(new Pose2d(50,28.5), Math.toRadians(0)) // to bd
                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .addDisplacementMarker(()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(42, 28.5)) // back up from bd
                        .lineToSplineHeading(new Pose2d(38,11, Math.toRadians(180))) // get into position
                        .lineToConstantHeading(new Vector2d(-35, 11)) // fly under truss
                        .lineToConstantHeading(new Vector2d(-56, 23.7)) // go to stack
                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
                        .addDisplacementMarker (()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-35, 11)) // line up for truss
                        .lineToConstantHeading(new Vector2d(38,11)) // get into position
                        .splineToLinearHeading(new Pose2d(50,28.5), Math.toRadians(0)) // to bd
                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 28.5)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueFRG);

                break;
        }
    }

}

