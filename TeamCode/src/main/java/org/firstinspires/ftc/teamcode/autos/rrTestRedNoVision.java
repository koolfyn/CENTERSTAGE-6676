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
                .waitSeconds(5)
                .addTemporalMarker(0,()-> {
                    encoded.armtoGround();
                    encoded.closeClaw();})
                .addTemporalMarker(1,()-> {encoded.openBottomClaw();})
                .addTemporalMarker(3,()-> {encoded.armScoreAuto();})
                //purple dropped
                .lineToSplineHeading(new Pose2d(-35,-59,Math.toRadians(0)))
                .waitSeconds(1)
                .splineTo(new Vector2d(10,-59),Math.toRadians(0))
                .splineTo(new Vector2d(49,-31),Math.toRadians(0))
                .waitSeconds(1)
                .addTemporalMarker(13,()-> {encoded.openTopClaw();})
                //yellow dropped
                .setReversed(true)
                .splineTo(new Vector2d(10,-58),Math.toRadians(180))
                .splineTo(new Vector2d(-35,-58),Math.toRadians(180))
                .setReversed(false)
                .lineToSplineHeading(new Pose2d(-55,-47,Math.toRadians(135)))
                .addTemporalMarker(14,()-> {encoded.openTopClaw();})
                .build();
        drive.followTrajectorySequence(frontL);

        }
    }

