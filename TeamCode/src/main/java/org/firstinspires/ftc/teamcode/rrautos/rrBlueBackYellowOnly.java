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

@Autonomous(name = "RR Blue Back Yellow Only")
public class rrBlueBackYellowOnly extends LinearOpMode {

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
                TrajectorySequence blueBLYO = drive.trajectorySequenceBuilder(startPose)

//                       .addTemporalMarker(0,()->{encoded.closeClaw();})
//                       .addTemporalMarker(0.5,()-> {encoded.armtoGroundAuto();})
                         .lineToConstantHeading(new Vector2d(24.5,54)) // to spikemark
//                       .addTemporalMarker(2, ()->{encoded.openBottomClaw();})
//                       .addTemporalMarker(2.5,()-> {encoded.armtoLowSetLine();})
                         .splineToLinearHeading(new Pose2d(52,49), Math.toRadians(0)) // to bd
//                       .addTemporalMarker(0.5, ()-> {encoded.openTopClaw();})
//                       .addTemporalMarker(0.5,()-> {encoded.closeClaw();})
                         .lineToConstantHeading(new Vector2d(47, 49)) // back up
                         .splineToConstantHeading(new Vector2d(67,13), Math.toRadians(0)) // spline into park (RIGHT)
//                       .splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();
                drive.followTrajectorySequence(blueBLYO);

                break;

            case NONE:
            case MIDDLE:
               TrajectorySequence blueBMYO = drive.trajectorySequenceBuilder(startPose)
//                       .addTemporalMarker(0,()-> {encoded.closeClaw();})
//                       .addTemporalMarker(()-> {encoded.armtoGroundAuto();})
                         .lineToConstantHeading(new Vector2d(12,54)) // to spikemark
//                       .addTemporalMarker(2, ()->{encoded.openBottomClaw();})
//                       .addTemporalMarker(2.5,()-> {encoded.armtoLowSetLine();})
                         .splineToLinearHeading(new Pose2d(52,42), Math.toRadians(0)) // to bd
//                       .addTemporalMarker(0.5, ()-> {encoded.openTopClaw();})
//                       .addTemporalMarker(0.5,()-> {encoded.closeClaw();})
                         .lineToConstantHeading(new Vector2d(47, 42)) // back up from bd
                         .splineToConstantHeading(new Vector2d(67,13), Math.toRadians(0)) // spline into park (RIGHT)
//                       .splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)


                        .build();
               drive.followTrajectorySequence(blueBMYO);

                break;

            case RIGHT:
               TrajectorySequence blueBRYO = drive.trajectorySequenceBuilder(startPose)
//                       .addTemporalMarker(0,()-> {encoded.closeClaw();})
                         .lineToSplineHeading(new Pose2d(15,54, Math.toRadians(240)))
//                       .addTemporalMarker(()->{encoded.openBottomClaw();})
                         .lineToConstantHeading(new Vector2d(14, 32)) // back up
//                       .addTemporalMarker(()-> {encoded.armtoLowSetLine();})
                         .lineToSplineHeading(new Pose2d(52,36, Math.toRadians(0)))
//                       .addTemporalMarker(()-> {encoded.openTopClaw();})
                         .lineToConstantHeading(new Vector2d(47, 36)) // back up from bd
                         .splineToConstantHeading(new Vector2d(67,13), Math.toRadians(0))// spline into park (RIGHT)
//                       .splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)


                        .build();
               drive.followTrajectorySequence(blueBRYO);

                break;
        }
    }


}