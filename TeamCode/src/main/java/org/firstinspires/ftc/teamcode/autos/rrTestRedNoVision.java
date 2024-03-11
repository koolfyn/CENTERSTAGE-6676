package org.firstinspires.ftc.teamcode.autos;

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

@Autonomous(name = "No vision RR Test Red")
public class rrTestRedNoVision extends LinearOpMode{
    public Encoded encoded;

    @Override
    public void runOpMode() {
        encoded = new Encoded(hardwareMap, telemetry);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = (new Pose2d(-35, -61.5, Math.toRadians(90)));
        drive.setPoseEstimate(startPose);

        waitForStart();

        if (isStopRequested()) return;

        TrajectorySequence frontL = drive.trajectorySequenceBuilder(new Pose2d(-35, -61.5, Math.toRadians(90)))
                .lineTo(new Vector2d(-47,-52))
                .waitSeconds(2)
                .addTemporalMarker(0,()-> {encoded.armtoGround();})
                .addTemporalMarker(0.5,()-> {encoded.openBottomClaw();})
                //purple dropped
                .lineToSplineHeading(new Pose2d(-35,-59,Math.toRadians(0)))
                .waitSeconds(1)
                .splineTo(new Vector2d(10,-59),Math.toRadians(0))
                .splineTo(new Vector2d(48,-31),Math.toRadians(0))
                .addTemporalMarker(5,()-> {encoded.armScoreAuto();})
                .addTemporalMarker(6,()-> {encoded.openTopClaw();})
                .waitSeconds(1)
                //yellow dropped
                .setReversed(true)
                .splineTo(new Vector2d(10,-59),Math.toRadians(180))
                .splineTo(new Vector2d(-35,-59),Math.toRadians(180))
                .setReversed(false)
                .lineToSplineHeading(new Pose2d(-56,-52,Math.toRadians(130)))
                .addTemporalMarker(8,()-> {encoded.armtoGround();})
                .addTemporalMarker(9,()-> {encoded.openTopClaw();})
                .build();

        drive.followTrajectorySequence(frontL);

        }
    }

