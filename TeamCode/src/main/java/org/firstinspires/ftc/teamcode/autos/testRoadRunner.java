package org.firstinspires.ftc.teamcode.autos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.RobotEncoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;


@Autonomous(name = "test roadrunner1")
public class testRoadRunner extends OpMode {
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
        Pose2d startPose = (new Pose2d(-35, -61.5, Math.toRadians(90)));
        drive.setPoseEstimate(startPose);
        Trajectory a = drive.trajectoryBuilder(startPose)
                .lineTo(new Vector2d(1,1))
                .build();

        TrajectorySequence l = drive.trajectorySequenceBuilder(startPose)
                .lineTo(new Vector2d(-47,-52))
                .waitSeconds(2)
                .addTemporalMarker(0,()-> {robotEncoded.armtoGround();})
                .addTemporalMarker(0.5,()-> {robotEncoded.openClaw();})
                //purple dropped
                .lineToSplineHeading(new Pose2d(-35,-59,Math.toRadians(0)))
                .waitSeconds(1)
                .splineTo(new Vector2d(10,-59),Math.toRadians(0))
                .splineTo(new Vector2d(48,-31),Math.toRadians(0))
                .addTemporalMarker(5,()-> {robotEncoded.armScoreAuto();})
                .addTemporalMarker(6,()-> {robotEncoded.openClaw();})
                .waitSeconds(1)
                //yellow dropped
                .setReversed(true)
                .splineTo(new Vector2d(10,-59),Math.toRadians(180))
                .splineTo(new Vector2d(-35,-59),Math.toRadians(180))
                .setReversed(false)
                .lineToSplineHeading(new Pose2d(-56,-52,Math.toRadians(130)))
                .addTemporalMarker(8,()-> {robotEncoded.armtoGround();})
                .addTemporalMarker(9,()-> {robotEncoded.openClaw();})
                .build();
        visionPortal.stopStreaming();

        telemetry.addData("Identified", visionProcessor.getSelection());
        switch (visionProcessor.getSelection()) {
            case LEFT:
             // remember to create a start pose before trajectory creation and name them appropriately
                //drive.followTrajectorySequence(l);
                drive.followTrajectory(a);
                break;

            case NONE:
            case MIDDLE:
                TrajectorySequence m = drive.trajectorySequenceBuilder(startPose)
                        .waitSeconds(3) // Waits 3 seconds
                        .build();

                break;

            case RIGHT:

                break;
        }
    }

    @Override
    public void loop() {

    }

}
