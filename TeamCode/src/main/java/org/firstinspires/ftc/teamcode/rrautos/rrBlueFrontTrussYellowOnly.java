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
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .addTemporalMarker(0.1,()-> {encoded.armtoGroundAuto();})
                        .lineToLinearHeading(new Pose2d(-37, 52, Math.toRadians(315))) // to spikemark
                        .addTemporalMarker(1,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-46, 65, Math.toRadians(0))) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 65)) // fly under truss
                        .splineToLinearHeading(new Pose2d(48.5,42), Math.toRadians(0)) // to bd
                        .waitSeconds(0.9)
                        .addTemporalMarker(6.1,()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 42)) // back up from bd
                        .splineToConstantHeading(new Vector2d(58,13), Math.toRadians(0)) // spline into park (RIGHT)

                        .build();
                drive.followTrajectorySequence(blueLTYO);
                break;




            case NONE:
            case MIDDLE:
                TrajectorySequence blueMTYO = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .addTemporalMarker(0.09,()-> {encoded.armtoGroundAuto();})


                        .lineToConstantHeading(new Vector2d(-35, 51)) // to spikemark
                        .addTemporalMarker(1,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-46, 66, Math.toRadians(0))) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 66)) // fly under truss
                        .splineToLinearHeading(new Pose2d(46.5,39.5), Math.toRadians(0)) // to bd
                        .waitSeconds(0.9)
                        .addTemporalMarker(6.5,()-> {encoded.openTopClaw();})
                        //.lineToConstantHeading(new Vector2d(43, 36)) // back up from bd
                        .lineToLinearHeading(new Pose2d(42,39.5, Math.toRadians(0))) // back up
                        .splineToConstantHeading(new Vector2d(58,13), Math.toRadians(0)) // spline into park (RIGHT)
//                      //.splineToConstantHeading(new Vector2d(65,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueMTYO);
                break;


            case RIGHT:
                TrajectorySequence blueRTYO = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .addTemporalMarker(0.09,()-> {encoded.armtoGroundAuto();})


                        .lineToConstantHeading(new Vector2d(-45.5, 57)) // to right spikemark
                        .addTemporalMarker(1,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-46, 65, Math.toRadians(0))) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(39, 65)) // fly under truss
                        .splineToLinearHeading(new Pose2d(48.5,33), Math.toRadians(0)) // to bd
                        .waitSeconds(1)
                        .addTemporalMarker(6.2,()-> {encoded.openTopClaw();})
                        // .addTemporalMarker(6.1,()-> {encoded.closeClaw();})
                        //.lineToConstantHeading(new Vector2d(43, 36)) // back up from bd
                        .lineToLinearHeading(new Pose2d(42,33, Math.toRadians(0))) // back up
                        .splineToConstantHeading(new Vector2d(58,13), Math.toRadians(0)) // spline into park (RIGHT)
                        //.splineToConstantHeading(new Vector2d(65,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueRTYO);
                break;
        }
    }
}







