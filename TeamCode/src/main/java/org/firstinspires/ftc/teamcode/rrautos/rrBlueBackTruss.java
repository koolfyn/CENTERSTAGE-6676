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
                      .addTemporalMarker(0,()-> {encoded.closeClaw();})
                      .addTemporalMarker(0.25,()-> {encoded.armtoGroundAuto();})
                        .lineToConstantHeading(new Vector2d(22,58)) // to spikemark
                      .addTemporalMarker(1,()->{encoded.openBottomClaw();})
                      .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                        .splineToLinearHeading(new Pose2d(49.5,52), Math.toRadians(0)) // to bd
                      .addTemporalMarker(2.7, ()-> {encoded.openTopClaw();})
                           .addTemporalMarker(3.9, ()-> {encoded.armtoPixelStack();})
                      //  .lineToConstantHeading(new Vector2d(47, 49)) // back up
                        .lineToLinearHeading(new Pose2d(42, 68, Math.toRadians(182))) // lineup for truss
                        .lineToConstantHeading(new Vector2d(-45, 68)) // fly under truss
                        .lineToSplineHeading(new Pose2d(-45, 45, Math.toRadians(180))) // to stack
                        .lineToSplineHeading(new Pose2d(-52, 45, Math.toRadians(180))) // to stack
                      .addTemporalMarker(9.5, ()-> {encoded.closeClaw();})
                      .addTemporalMarker(10.4,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-50, 68, Math.toRadians(0))) // lineup for truss
                        .lineToConstantHeading(new Vector2d(36, 68)) // fly under truss
                        .splineToConstantHeading(new Vector2d(49.5,42), Math.toRadians(0)) // to bd
                        .addTemporalMarker(15.5,()-> {encoded.openBottomClaw();})
                      .addTemporalMarker(15.7, ()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(43, 48)) // back up from bd
                        .splineToConstantHeading(new Vector2d(58,16), Math.toRadians(0)) // spline into park (RIGHT)
//                      .splineToConstantHeading(new Vector2d(60, 58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();
                drive.followTrajectorySequence(blueBLT);
                break;

            case NONE:
            case MIDDLE:
                TrajectorySequence blueBMT = drive.trajectorySequenceBuilder(startPose)
                      .addTemporalMarker(0,()-> {encoded.closeClaw();})
                      .addTemporalMarker(0.25,()-> {encoded.armtoGroundAuto();})
                        .lineToConstantHeading(new Vector2d(12,50)) // to spikemark
                        .addTemporalMarker(1,()->{encoded.openBottomClaw();})
                        .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                        .splineToLinearHeading(new Pose2d(49.5,44), Math.toRadians(0)) // to bd
                        .addTemporalMarker(3.1, ()-> {encoded.openTopClaw();})
                        .addTemporalMarker(3.9, ()-> {encoded.armtoPixelStack();})
                        //.lineToConstantHeading(new Vector2d(47, 42)) // back up
                        .lineToLinearHeading(new Pose2d(42, 68, Math.toRadians(182))) // line up for truss
                        .lineToConstantHeading(new Vector2d(-45, 68)) // fly under truss
                      .lineToSplineHeading(new Pose2d(-45, 48, Math.toRadians(180))) // to stack
                        .lineToSplineHeading(new Pose2d(-52, 48, Math.toRadians(180))) // to stack
                      .addTemporalMarker(9.5, ()-> {encoded.closeClaw();})
                      .addTemporalMarker(10.4,()-> {encoded.armScoreAuto();})
                        .splineToConstantHeading(new Vector2d(49.5,36), Math.toRadians(0)) // to bd
                      .addTemporalMarker(15.5,()-> {encoded.openBottomClaw();})
                      .addTemporalMarker(15.7, ()-> {encoded.openTopClaw();})
                        .splineToConstantHeading(new Vector2d(58,16), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueBMT);
                break;

            case RIGHT:
                TrajectorySequence blueBRT = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                      .addTemporalMarker(0.25,()-> {encoded.armtoGroundAuto();})
                        .lineToSplineHeading(new Pose2d(15,54, Math.toRadians(240)))
                      .addTemporalMarker(1,()->{encoded.openBottomClaw();})
                      .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                        .lineToSplineHeading(new Pose2d(52,36, Math.toRadians(0))) // to bd
                      .addTemporalMarker(3,()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(47, 36)) // back up from bd
                        .lineToLinearHeading(new Pose2d(42, 64, Math.toRadians(182))) // line up for truss
                        .lineToConstantHeading(new Vector2d(-50, 64)) // fly under truss
                        .lineToSplineHeading(new Pose2d(-55, 47, Math.toRadians(210))) // to stack + 210 bc right pixel
                      .addTemporalMarker(3.5, ()-> {encoded.armtoPixelStack();})
                      .addTemporalMarker(9.8, ()-> {encoded.closeClaw();})
                      .addTemporalMarker(10.2,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-50, 64, Math.toRadians(0))) // lineup for truss
                        .lineToConstantHeading(new Vector2d(42, 64)) // fly under truss
                        .lineToSplineHeading(new Pose2d(52,36, Math.toRadians(0))) // to bd
                      .addTemporalMarker(16, ()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(47, 36)) // back up from bd
                        .splineToConstantHeading(new Vector2d(67,13), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();
                drive.followTrajectorySequence(blueBRT);


                break;
        }
    }
}
