package org.firstinspires.ftc.teamcode.rrautos;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.main.Encoded;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;




@Autonomous(name = "RR Blue Front Truss")
public class rrBlueFrontTruss extends LinearOpMode {


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
                TrajectorySequence blueFLT = drive.trajectorySequenceBuilder(startPose)
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
                        .lineToLinearHeading(new Pose2d(42,42, Math.toRadians(0))) // back up
                        .lineToLinearHeading(new Pose2d(42,63, Math.toRadians(182))) // truss lineup
                        .lineToConstantHeading(new Vector2d(-42, 63)) // fly under truss
                        .lineToLinearHeading(new Pose2d(-49.8, 45, Math.toRadians(192))) // orientate + to stack
                        .lineToLinearHeading(new Pose2d(-53.8, 45, Math.toRadians(192))) // orientate + to stack
                        .addTemporalMarker(8.9,()->{encoded.openBottomClaw();})
                        .addTemporalMarker(8.9,()-> {encoded.openTopClaw();})
                        .addTemporalMarker(9.1,()-> {encoded.armtoPixelStack();})
                        .addTemporalMarker (14.25,()-> {encoded.closeClaw();})
                        .addTemporalMarker(14.35,()-> {encoded.armScoreAuto();})
                        //  .lineToConstantHeading(new Vector2d(-42, 67)) // orientate + line up for truss
                        .lineToLinearHeading(new Pose2d(-42,63, Math.toRadians(180)))
                        .lineToConstantHeading(new Vector2d(42, 63)) // fly under truss
                        .lineToSplineHeading(new Pose2d(48.5,38, Math.toRadians(0))) // to bd
                        .addTemporalMarker(20.6,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(21,()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 40)) // back up from bd
                        .splineToConstantHeading(new Vector2d(58,14), Math.toRadians(0)) // spline into park (RIGHT)
//                       .splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueFLT);


                break;


            case NONE:
            case MIDDLE:
                TrajectorySequence blueFMT = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .addTemporalMarker(0.09,()-> {encoded.armtoGroundAuto();})


                        .lineToConstantHeading(new Vector2d(-35, 51)) // to spikemark
                        .addTemporalMarker(1,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-46, 66, Math.toRadians(0))) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 66)) // fly under truss
                        .splineToLinearHeading(new Pose2d(46.5,37.5), Math.toRadians(0)) // to bd
                        .waitSeconds(0.9)
                        .addTemporalMarker(6.5,()-> {encoded.openTopClaw();})
                        //.lineToConstantHeading(new Vector2d(43, 36)) // back up from bd
                        .lineToLinearHeading(new Pose2d(42,39.5, Math.toRadians(0))) // back up
                        .lineToLinearHeading(new Pose2d(42,62, Math.toRadians(182))) // truss lineup
                        .lineToConstantHeading(new Vector2d(-42, 62)) // fly under truss
                        .lineToLinearHeading(new Pose2d(-50.8, 48.5, Math.toRadians(201))) // orientate + to stack
                        .lineToLinearHeading(new Pose2d(-56.8, 48.5, Math.toRadians(201))) // orientate + to stack
                        .addTemporalMarker(9.4,()->{encoded.openBottomClaw();})
                        .addTemporalMarker(9.4,()-> {encoded.openTopClaw();})
                        .addTemporalMarker(9.6,()-> {encoded.armtoPixelStack();})
                        .addTemporalMarker (14.5,()-> {encoded.closeClaw();})
                        .addTemporalMarker(14.8,()-> {encoded.armScoreAuto();})
                        //  .lineToConstantHeading(new Vector2d(-42, 67)) // orientate + line up for truss
                        .lineToLinearHeading(new Pose2d(-42,62, Math.toRadians(180)))
                        .lineToConstantHeading(new Vector2d(42, 62)) // fly under truss
                       // .lineToConstantHeading(new Vector2d(42, 60)) // to not hit wall
                      //  .splineToLinearHeading(new Pose2d(49, 44), Math.toRadians(0)) // to bd
                        .lineToSplineHeading(new Pose2d(46.5,44, Math.toRadians(0))) // to bd
                        .waitSeconds(1)
                        .addTemporalMarker(20.4,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(20.6,()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 44)) // back up from bd
                        .splineToConstantHeading(new Vector2d(58,13), Math.toRadians(0)) // spline into park (RIGHT)
//                        .splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueFMT);
                break;


            case RIGHT:
                TrajectorySequence blueFRT =  drive.trajectorySequenceBuilder(startPose)
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
                        .lineToLinearHeading(new Pose2d(42,38, Math.toRadians(0))) // back up
                        .lineToLinearHeading(new Pose2d(42,62, Math.toRadians(182))) // truss lineup
                        .lineToConstantHeading(new Vector2d(-42, 63)) // fly under truss
                        .lineToLinearHeading(new Pose2d(-49.8, 47.5, Math.toRadians(201))) // orientate + to stack
                        .lineToLinearHeading(new Pose2d(-56, 47.5, Math.toRadians(201))) // orientate + to stack
                        .addTemporalMarker(9.5,()->{encoded.openBottomClaw();})
                        .addTemporalMarker(9.5,()-> {encoded.openTopClaw();})
                        .addTemporalMarker(9.7,()-> {encoded.armtoPixelStack();})
                        .addTemporalMarker (14.7,()-> {encoded.closeClaw();})
                        .addTemporalMarker(15,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-42,62, Math.toRadians(180)))
                        .lineToConstantHeading(new Vector2d(42, 62)) // fly under truss
                        .lineToSplineHeading(new Pose2d(46.5,37, Math.toRadians(0))) // to bd
                        .waitSeconds(1)
                        .addTemporalMarker(20.8,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(21,()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(46, 37)) // back up from bd
                        .splineToConstantHeading(new Vector2d(58,9), Math.toRadians(0)) // spline into park (RIGHT)//                        .splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueFRT);
                break;
        }
    }








}



