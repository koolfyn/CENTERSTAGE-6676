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


        TrajectorySequence backL = drive.trajectorySequenceBuilder(startPose)
                .waitSeconds(1)
                .addTemporalMarker(0,()->{encoded.closeClaw();})
                .addTemporalMarker(0.5,()->{encoded.armtoGroundAuto();})
                .lineTo(new Vector2d(-47,-50))
                .waitSeconds(2)
                .addTemporalMarker(2,()-> {encoded.openBottomClaw();})
                .addTemporalMarker(2.5,()-> {encoded.armScoreAuto();})
                //purple dropped
                .lineToSplineHeading(new Pose2d(-36,-59,Math.toRadians(0)))
                .waitSeconds(0.1)
                .splineTo(new Vector2d(10,-59),Math.toRadians(0))
                .splineTo(new Vector2d(53,-30),Math.toRadians(0))
                .waitSeconds(1)
                .back(5)
                .addTemporalMarker(9,()-> {encoded.openTopClaw();})
                //.lineTo(new Vector2d(50,-58.5))
                .lineTo(new Vector2d(40,-55))
                //yellow dropped
        //next few line is cycle to pixel stack not done yet
                .setReversed(true)
                .splineTo(new Vector2d(10,-58),Math.toRadians(180))
                .splineTo(new Vector2d(-35,-58),Math.toRadians(180))
                .setReversed(false)
                //in front of stack
                .lineToSplineHeading(new Pose2d(-53,-54,Math.toRadians(135)))
                .forward(3)
                .waitSeconds(1)
                .addTemporalMarker(16,()-> {encoded.armtoPixelStack();})//16
                .addTemporalMarker(18,()-> {encoded.closeClaw();})//17
                .addTemporalMarker(19,()-> {encoded.armScoreAuto();})//18
                .back(5)// grabbed and going
                .lineToSplineHeading(new Pose2d(-36,-58,Math.toRadians(0)))
                .waitSeconds(1)
                .splineTo(new Vector2d(10,-59),Math.toRadians(0))
                .splineTo(new Vector2d(53,-36),Math.toRadians(0))
                .waitSeconds(1)
                .addTemporalMarker(26,()-> {encoded.openBottomClaw();})
                .addTemporalMarker(27,()-> {encoded.openTopClaw();})
                .lineTo(new Vector2d(50,-58.5))
                .build();
        drive.followTrajectorySequence(backL);




    }
}

