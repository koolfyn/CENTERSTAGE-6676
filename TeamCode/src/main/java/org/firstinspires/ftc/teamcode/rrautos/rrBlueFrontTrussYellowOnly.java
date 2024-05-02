package org.firstinspires.ftc.teamcode.rrautos;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.main.Encoded;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;




@Autonomous(name = "RR Blue Front Yellow Only")
public class rrBlueFrontTrussYellowOnly extends LinearOpMode {
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
                TrajectorySequence blueLTYO = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()->{encoded.closeClaw();})
//
                        .addTemporalMarker(0.25,()-> {encoded.armtoGroundAuto();})
                        .lineToLinearHeading(new Pose2d(-35, 55, Math.toRadians(310))) // to spikemark
                        .addTemporalMarker(2,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(2.5,()-> {encoded.armScoreAuto();})
                        .splineToLinearHeading(new Pose2d(-38,67), Math.toRadians(0)) // lineup for truss
                        .lineToConstantHeading(new Vector2d(42, 67)) // fly under truss
                        .splineToLinearHeading(new Pose2d(43,45), Math.toRadians(0)) // to bd
                        .addTemporalMarker(()-> {encoded.openTopClaw();})
                        .addTemporalMarker(()-> {encoded.closeClaw();})
                        // .lineToConstantHeading(new Vector2d(43, 42)) // back up from bd
                        .splineToConstantHeading(new Vector2d(65,13), Math.toRadians(0)) // spline into park (RIGHT)
//                    .splineToConstantHeading(new Vector2d(65,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueLTYO);
                break;




            case NONE:
            case MIDDLE:
                TrajectorySequence blueMTYO = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})


                        //    1.  push,  2. go under truss,  3. backdrop,   4. park
                        .addTemporalMarker(0.25,()-> {encoded.armtoGroundAuto();})


                        .lineToConstantHeading(new Vector2d(-34, 55)) // to spikemark
                        .addTemporalMarker(2,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(2.5,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-46, 67, Math.toRadians(0))) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 67)) // fly under truss
                        .splineToLinearHeading(new Pose2d(43,45), Math.toRadians(0)) // to bd
                        .addTemporalMarker(()-> {encoded.openTopClaw();})
                        .addTemporalMarker(()-> {encoded.closeClaw();})
                        //     .lineToConstantHeading(new Vector2d(43, 36)) // back up from bd
                        .splineToConstantHeading(new Vector2d(65,16), Math.toRadians(0)) // spline into park (RIGHT)
//                      //.splineToConstantHeading(new Vector2d(65,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueMTYO);
                break;


            case RIGHT:
                TrajectorySequence blueRTYO = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})


                        //    1. push,  2. go under truss,  3. backdrop,   4. park
                        .addTemporalMarker(0.25,()-> {encoded.armtoGroundAuto();})


                        .lineToConstantHeading(new Vector2d(-45, 55)) // to right spikemark
                        .addTemporalMarker(2,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(2.5,()-> {encoded.armScoreAuto();})


                        .addTemporalMarker(2.5,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-46, 67, Math.toRadians(0))) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 67)) // fly under truss
                        .lineToSplineHeading(new Pose2d(43,36, Math.toRadians(0))) // to backdrop
                        .addTemporalMarker(()-> {encoded.openTopClaw();})
                        .addTemporalMarker(()-> {encoded.closeClaw();})
                        // .lineToConstantHeading(new Vector2d(47, 36)) // back up from bd
                        .splineToConstantHeading(new Vector2d(67, 18), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(65,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueRTYO);
                break;
        }
    }
}







