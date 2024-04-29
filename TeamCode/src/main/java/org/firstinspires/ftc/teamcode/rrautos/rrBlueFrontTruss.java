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
                        //     .addTemporalMarker(0,()->{encoded.closeClaw();})
//
                        .addTemporalMarker(0.25,()-> {encoded.armtoGroundAuto();})
                        .lineToLinearHeading(new Pose2d(-35, 55, Math.toRadians(310))) // to spikemark
                        .addTemporalMarker(0.7,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(2.5,()-> {encoded.armScoreAuto();})
                        .splineToLinearHeading(new Pose2d(-38,67), Math.toRadians(0)) // lineup for truss
                        .lineToConstantHeading(new Vector2d(42, 67)) // fly under truss
                        .splineToLinearHeading(new Pose2d(43,45), Math.toRadians(0)) // to bd
                        .addTemporalMarker(()-> {encoded.openTopClaw();})
                        .addTemporalMarker(()-> {encoded.closeClaw();})
                        .lineToLinearHeading(new Pose2d(42,42, Math.toRadians(182))) // back up & turn
                        .lineToConstantHeading(new Vector2d(42, 65)) // line up for truss
                        .lineToConstantHeading(new Vector2d(-50, 65)) // fly under truss
                        .lineToSplineHeading(new Pose2d(-55, 47, Math.toRadians(180))) // to stack
                        .addTemporalMarker(0.5, ()-> {encoded.armtoPixelStack();})
                        .addTemporalMarker(0.5, ()-> {encoded.closeClaw();})
                        .lineToLinearHeading(new Pose2d(-50, 67, Math.toRadians(0))) // lineup for truss
                        .lineToConstantHeading(new Vector2d(42, 67)) // fly under truss
                        .splineToConstantHeading(new Vector2d(43,45), Math.toRadians(0)) // to bd
                        .addTemporalMarker(()-> {encoded.armtoLowSetLine();})
                        .addTemporalMarker(()-> {encoded.openTopClaw();})
                        //  .lineToConstantHeading(new Vector2d(42, 35.5)) // back up from bd
                        .splineToConstantHeading(new Vector2d(65,13), Math.toRadians(0)) // spline into park (RIGHT)
//                       .splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueFLT);


                break;


            case NONE:
            case MIDDLE:
                TrajectorySequence blueFMT = drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .addTemporalMarker(0.25,()-> {encoded.armtoGroundAuto();})


                        .lineToConstantHeading(new Vector2d(-34, 51)) // to spikemark
                        .addTemporalMarker(1,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-46, 67, Math.toRadians(0))) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 67)) // fly under truss
                        .splineToLinearHeading(new Pose2d(49,41), Math.toRadians(0)) // to bd
                        .addTemporalMarker(6.15,()-> {encoded.openTopClaw();})
                        .addTemporalMarker(6.3,()-> {encoded.closeClaw();})
                        //.lineToConstantHeading(new Vector2d(43, 36)) // back up from bd
                        .lineToLinearHeading(new Pose2d(42,42, Math.toRadians(182))) // back up & turn




                        .lineToConstantHeading(new Vector2d(42, 64)) // line up for truss
                        .lineToConstantHeading(new Vector2d(-42, 64)) // fly under truss
                        .lineToLinearHeading(new Pose2d(-52, 49.8, Math.toRadians(210))) // orientate + to stack
                        .addTemporalMarker(8,()->{encoded.openBottomClaw();})
                        .addTemporalMarker(8,()-> {encoded.openTopClaw();})
                        .addTemporalMarker(8.2,()-> {encoded.armtoPixelStack();})
                        .addTemporalMarker (8.3,()-> {encoded.closeClaw();})
                      //  .lineToConstantHeading(new Vector2d(-42, 67)) // orientate + line up for truss
                        .lineToLinearHeading(new Pose2d(-42,67, Math.toRadians(180)))
                        .lineToConstantHeading(new Vector2d(42, 67)) // fly under truss
                        .splineToLinearHeading(new Pose2d(49, 44), Math.toRadians(0)) // to bd
                        .addTemporalMarker(()-> {encoded.armtoLowSetLine();})
                        .addTemporalMarker(()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 35.5)) // back up from bd
                        .splineToConstantHeading(new Vector2d(65,16), Math.toRadians(0)) // spline into park (RIGHT)
//                        .splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueFMT);
                break;


            case RIGHT:
                TrajectorySequence blueFRT =  drive.trajectorySequenceBuilder(startPose)
                        .addTemporalMarker(0,()-> {encoded.closeClaw();})
                        .addTemporalMarker(0.25,()-> {encoded.armtoGroundAuto();})


                        .lineToConstantHeading(new Vector2d(-45, 55)) // to right spikemark
                        .addTemporalMarker(0.7,()-> {encoded.openBottomClaw();})
                        .addTemporalMarker(2.5,()-> {encoded.armScoreAuto();})
                        .lineToLinearHeading(new Pose2d(-46, 67, Math.toRadians(0))) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 67)) // fly under truss
                        .lineToSplineHeading(new Pose2d(43,36, Math.toRadians(0))) // to backdrop
                        .addTemporalMarker(()-> {encoded.openTopClaw();})
                        .addTemporalMarker(()-> {encoded.closeClaw();})
//                        .lineToConstantHeading(new Vector2d(47, 36)) // back up from bd
                        .lineToLinearHeading(new Pose2d(42,42, Math.toRadians(182))) // back up & turn
                        .lineToLinearHeading(new Pose2d(42, 64, Math.toRadians(182))) // line up for truss
                        .lineToConstantHeading(new Vector2d(-34, 64)) // fly under truss
                        .lineToLinearHeading(new Pose2d(-55, 47, Math.toRadians(182))) // orientate + to stack
                        .addTemporalMarker(()->{encoded.openBottomClaw();})
                        .addTemporalMarker(()-> {encoded.openTopClaw();})
                        .addTemporalMarker(()-> {encoded.armtoPixelStack();})
                        .addTemporalMarker (()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-34, 67)) // line up for truss
                        .lineToConstantHeading(new Vector2d(42, 67)) // fly under truss
                        .splineToLinearHeading(new Pose2d(43, 36), Math.toRadians(0)) // to bd + angle
                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        //  .lineToConstantHeading(new Vector2d(42, 28.5)) // back up from bd
                        .splineToConstantHeading(new Vector2d(67,18), Math.toRadians(0)) // spline into park (RIGHT)
//                        .splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                        .build();
                drive.followTrajectorySequence(blueFRT);
                break;
        }
    }








}



