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
        Pose2d startPose = (new Pose2d(11.5, 61.5, Math.toRadians(270)));
        drive.setPoseEstimate(startPose);


        waitForStart();


        if (isStopRequested()) return;


        TrajectorySequence backL = drive.trajectorySequenceBuilder(startPose)
                .waitSeconds(1)
                .addTemporalMarker(0,()->{encoded.closeClaw();})
                .addTemporalMarker(0.5,()->{encoded.armtoGroundAuto();})
                .lineToSplineHeading(new Pose2d(12,49,Math.toRadians(225)))
                .waitSeconds(1)
                .addTemporalMarker(2,()->{encoded.openBottomClaw();})
                .addTemporalMarker(2.5,()->{encoded.armScoreAuto();})
                //purple dropped
                .lineToSplineHeading(new Pose2d(53,28,Math.toRadians(0)))
                .waitSeconds(1.5)
                .addTemporalMarker(5.5,()->{encoded.openTopClaw();})
                //yellow dropped
                .back(5)
                .lineTo(new Vector2d(48,58.5))
                .build();
        drive.followTrajectorySequence(backL);




    }
}

