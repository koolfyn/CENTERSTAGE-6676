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
                        .lineToLinearHeading(new Pose2d(-34, 52, Math.toRadians(310))) // to spikemark
                        .addTemporalMarker(1,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-46, 65, Math.toRadians(0))) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 65)) // fly under truss
                        .splineToLinearHeading(new Pose2d(49.5,46), Math.toRadians(0)) // to bd
                        .addTemporalMarker(5.7,()-> {encoded.openTopClaw();})
                        .addTemporalMarker(5.85,()-> {encoded.closeClaw();})
                        .lineToLinearHeading(new Pose2d(42,42, Math.toRadians(182))) // back up & turn
                        .lineToConstantHeading(new Vector2d(42, 63)) // line up for truss
                        .lineToConstantHeading(new Vector2d(-42, 63)) // fly under truss
                        .lineToLinearHeading(new Pose2d(-49.8, 45, Math.toRadians(195))) // orientate + to stack
                        .lineToLinearHeading(new Pose2d(-53.8, 45, Math.toRadians(195))) // orientate + to stack
                        .addTemporalMarker(8,()->{encoded.openBottomClaw();})
                        .addTemporalMarker(8,()-> {encoded.openTopClaw();})
                        .addTemporalMarker(8.2,()-> {encoded.armtoPixelStack();})
                        .addTemporalMarker (13.1,()-> {encoded.closeClaw();})
                        .addTemporalMarker(13.4,()-> {encoded.armScoreAuto();})
                        //  .lineToConstantHeading(new Vector2d(-42, 67)) // orientate + line up for truss
                        .lineToLinearHeading(new Pose2d(-42,63, Math.toRadians(180)))
                        .lineToConstantHeading(new Vector2d(42, 63)) // fly under truss
                        .lineToSplineHeading(new Pose2d(49.5,38, Math.toRadians(0))) // to bd
                        .addTemporalMarker(19.3,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(19.7,()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 40)) // back up from bd
                        .splineToConstantHeading(new Vector2d(58,16), Math.toRadians(0)) // spline into park (RIGHT)
//                       .splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueFLT);


                break;


            case NONE:
            case MIDDLE:
                TrajectorySequence blueFMT = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .addTemporalMarker(0.25,()-> {encoded.armtoGroundAuto();})


                        .lineToConstantHeading(new Vector2d(-32, 51)) // to spikemark
                        .addTemporalMarker(1,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-46, 65, Math.toRadians(0))) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 65)) // fly under truss
                        .splineToLinearHeading(new Pose2d(49.5,40), Math.toRadians(0)) // to bd
                        .addTemporalMarker(6.15,()-> {encoded.openTopClaw();})
                        .addTemporalMarker(6.3,()-> {encoded.closeClaw();})
                        //.lineToConstantHeading(new Vector2d(43, 36)) // back up from bd
                        .lineToLinearHeading(new Pose2d(42,42, Math.toRadians(182))) // back up & turn




                        .lineToConstantHeading(new Vector2d(42, 63)) // line up for truss
                        .lineToConstantHeading(new Vector2d(-42, 63)) // fly under truss
                        .lineToLinearHeading(new Pose2d(-49.8, 48.5, Math.toRadians(200))) // orientate + to stack
                        .lineToLinearHeading(new Pose2d(-53.8, 48.5, Math.toRadians(200))) // orientate + to stack
                        .addTemporalMarker(8.5,()->{encoded.openBottomClaw();})
                        .addTemporalMarker(8.5,()-> {encoded.openTopClaw();})
                        .addTemporalMarker(8.7,()-> {encoded.armtoPixelStack();})
                        .addTemporalMarker (13.6,()-> {encoded.closeClaw();})
                        .addTemporalMarker(13.9,()-> {encoded.armScoreAuto();})
                        //  .lineToConstantHeading(new Vector2d(-42, 67)) // orientate + line up for truss
                        .lineToLinearHeading(new Pose2d(-42,63, Math.toRadians(180)))
                        .lineToConstantHeading(new Vector2d(42, 63)) // fly under truss
                       // .lineToConstantHeading(new Vector2d(42, 60)) // to not hit wall
                      //  .splineToLinearHeading(new Pose2d(49, 44), Math.toRadians(0)) // to bd
                        .lineToSplineHeading(new Pose2d(49.5,44, Math.toRadians(0))) // to bd
                        .addTemporalMarker(19.5,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(19.9,()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 44)) // back up from bd
                        .splineToConstantHeading(new Vector2d(58,16), Math.toRadians(0)) // spline into park (RIGHT)
//                        .splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueFMT);
                break;


            case RIGHT:
                TrajectorySequence blueFRT =  drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .addTemporalMarker(0.25,()-> {encoded.armtoGroundAuto();})


                        .lineToConstantHeading(new Vector2d(-45, 55)) // to right spikemark
                        .addTemporalMarker(1,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-46, 67, Math.toRadians(0))) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 67)) // fly under truss
                        .splineToLinearHeading(new Pose2d(49.5,36), Math.toRadians(0)) // to bd
                        .addTemporalMarker(6.15,()-> {encoded.openTopClaw();})
                        .addTemporalMarker(6.3,()-> {encoded.closeClaw();})
                        //.lineToConstantHeading(new Vector2d(43, 36)) // back up from bd
                        .lineToLinearHeading(new Pose2d(42,42, Math.toRadians(182))) // back up & turn




                        .lineToConstantHeading(new Vector2d(42, 64)) // line up for truss
                        .lineToConstantHeading(new Vector2d(-42, 64)) // fly under truss
                        .lineToLinearHeading(new Pose2d(-49.8, 48.5, Math.toRadians(200))) // orientate + to stack
                        .lineToLinearHeading(new Pose2d(-53.8, 48.5, Math.toRadians(200))) // orientate + to stack
                        .addTemporalMarker(8.5,()->{encoded.openBottomClaw();})
                        .addTemporalMarker(8.5,()-> {encoded.openTopClaw();})
                        .addTemporalMarker(8.7,()-> {encoded.armtoPixelStack();})
                        .addTemporalMarker (13.6,()-> {encoded.closeClaw();})
                        .addTemporalMarker(13.9,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-42,63, Math.toRadians(180)))
                        .lineToConstantHeading(new Vector2d(42, 63)) // fly under truss
                        .splineToLinearHeading(new Pose2d(49.5, 42), Math.toRadians(0)) // to bd + angle
                        .addTemporalMarker(19.5,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(19.9,()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 42)) // back up from bd
                        .splineToConstantHeading(new Vector2d(58,16), Math.toRadians(0)) // spline into park (RIGHT)//                        .splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueFRT);
                break;
        }
    }








}



