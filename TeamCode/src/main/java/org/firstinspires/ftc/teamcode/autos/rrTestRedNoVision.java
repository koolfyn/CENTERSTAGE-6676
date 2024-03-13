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

        TrajectorySequence frontM = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(0,()->{encoded.closeClaw();})
                .waitSeconds(2)
                .lineToSplineHeading(new Pose2d(-36.5,-44.5,Math.toRadians(45)))
                .waitSeconds(2)
                .addTemporalMarker(1,()-> {
                    encoded.armtoGroundAuto();})
                .addTemporalMarker(3,()-> {encoded.openBottomClaw();})
                .addTemporalMarker(4,()-> {encoded.armScoreAuto();})
                //purple dropped
                .lineToSplineHeading(new Pose2d(-36,-59,Math.toRadians(0)))
                .waitSeconds(0.5)
                .splineTo(new Vector2d(10,-59),Math.toRadians(0))
                .splineTo(new Vector2d(54,-42),Math.toRadians(0))
                .waitSeconds(2)
                .addTemporalMarker(11,()-> {encoded.openTopClaw();})
                //yellow dropped
                .lineTo(new Vector2d(50,-60))
                .build();
        drive.followTrajectorySequence(frontM);

    }
}
