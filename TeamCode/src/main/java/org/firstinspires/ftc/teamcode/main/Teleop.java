package org.firstinspires.ftc.teamcode.main;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp (name="Teleop main")
public class Teleop extends OpMode {

    double slideRightHeight = 0;
    double slideLeftHeight = 0;
    double armHeight = 0;

    Encoded encoded;
    DriveTrain driveTrain;

    @Override
    public void init() {
        encoded = new Encoded(hardwareMap, telemetry);
        driveTrain = new DriveTrain(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double r = gamepad1.right_stick_x;

        if (gamepad1.right_bumper) { // driver movements
            driveTrain.frontLeft.setVelocity((y + x + r) * Constants.slowVal);
            driveTrain.frontRight.setVelocity((y - x - r) * Constants.slowVal);
            driveTrain.backLeft.setVelocity((y - x + r) * Constants.slowVal);
            driveTrain.backRight.setVelocity((y + x - r) * Constants.slowVal);
        } else if (gamepad1.left_bumper) {
            driveTrain.frontLeft.setPower((y + x + r) * Constants.fastVal);
            driveTrain.frontRight.setPower((y - x - r) * Constants.fastVal);
            driveTrain.backLeft.setPower((y - x + r) * Constants.fastVal);
            driveTrain.backRight.setPower((y + x - r) * Constants.fastVal);
        } else {
            driveTrain.frontLeft.setVelocity((y + x + r) * Constants.defaultVal);
            driveTrain.frontRight.setVelocity((y - x - r) * Constants.defaultVal);
            driveTrain.backLeft.setVelocity((y - x + r) * Constants.defaultVal);
            driveTrain.backRight.setVelocity((y + x - r) * Constants.defaultVal);
        }

        if (gamepad1.dpad_up) { // suspend
            slideLeftHeight = Constants.suspendHeight;
            slideRightHeight = -Constants.suspendHeight;
        } else if (gamepad1.dpad_down) {
            slideLeftHeight = 0;
            slideRightHeight = 0;
        }
        if(gamepad1.x) {
            encoded.launchDrone();
        }

        if (gamepad2.left_bumper) { // open top claw
            encoded.openTopClaw();
        }
        else if(gamepad2.left_trigger > 0.4) { // open bottom claw
            encoded.openBottomClaw();
        }
        else if(gamepad2.right_bumper) {
            encoded.closeTopClaw();
        }
        else if(gamepad2.right_trigger > 0.4) {
            encoded.closeBottomClaw();
        }
        else if (gamepad2.dpad_down) { // close both claws
            encoded.closeClaw();
        }

        if(gamepad2.y) {
            encoded.armtoMidSetLine();
        }
        if(gamepad2.x) {
            encoded.armtoLowSetLine();
        }
        if(gamepad2.b) {
            encoded.armScoreAuto();
        }
        if(gamepad2.a) {
            encoded.armtoGround();
        }
        if(gamepad2.dpad_up) {
            encoded.armtoPixelStack();
        }

        if (gamepad2.right_stick_y > 0.1 && armHeight >= 0) { // manual arm control
            armHeight = Encoded.arm.getCurrentPosition() / Encoded.TICKS_PER_INCH_LS;
            armHeight -= 10;
        } else if (gamepad2.right_stick_y < -0.1) {
            armHeight = Encoded.arm.getCurrentPosition() / Encoded.TICKS_PER_INCH_LS;
            armHeight += 10;
        }

        double rawDifference1 = encoded.slideLeft.getCurrentPosition() - slideLeftHeight * Encoded.TICKS_PER_INCH_LS;
        double difference1 = Math.abs(rawDifference1);
        double rawDifference2 = encoded.slideRight.getCurrentPosition() - slideRightHeight * Encoded.TICKS_PER_INCH_LS;
        double difference2 = Math.abs(rawDifference2);

        if (difference2 >= 30) {
            encoded.slideRight.setTargetPosition((int)(slideRightHeight * Encoded.TICKS_PER_INCH_LS));
            encoded.slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            encoded.slideRight.setVelocity(1000);
        }

        if(difference1 >= 30) {
            encoded.slideLeft.setTargetPosition((int)(slideLeftHeight * Encoded.TICKS_PER_INCH_LS));
            encoded.slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            encoded.slideLeft.setVelocity(1000);
        }

        telemetry.addData("top claw position", encoded.clawTop.getPosition());
        telemetry.update();

        telemetry.addLine("Left joystick | ")
                .addData("x", gamepad1.left_stick_x)
                .addData("y", gamepad1.left_stick_y);
    }

    @Autonomous(name = "RR Blue Back Truss")
    public static class rrBlueBackTruss extends LinearOpMode {
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

            Pose2d startPose = (new Pose2d(15, 70, Math.toRadians(270)));
            drive.setPoseEstimate(startPose);

            switch (visionProcessor.getSelection()) {
                case LEFT:
                    TrajectorySequence blueBLT = drive.trajectorySequenceBuilder(startPose)
                            //.addDisplacementMarker(()-> {encoded.armtoGroundAuto();})
                            .lineToConstantHeading(new Vector2d(23,40)) // to spikemark
    //                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                            .lineToConstantHeading(new Vector2d(28, 46)) // back up
                            //   .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                            .splineToLinearHeading(new Pose2d(50,42), Math.toRadians(0)) // to bd
    //                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                            .lineToConstantHeading(new Vector2d(47,42)) // safely move from bd
                            .lineToLinearHeading(new Pose2d(42, 58.5, Math.toRadians(180))) // line up for truss
                            .lineToConstantHeading(new Vector2d(-42, 58.5)) // fly under truss
                            .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                            .lineToConstantHeading(new Vector2d(-55, 35)) // to stack
    //                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
    //                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
    //                        .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
    //                        .addDisplacementMarker (()-> {encoded.closeClaw();})
                            .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                            .lineToLinearHeading(new Pose2d(-42, 58.5, Math.toRadians(0))) // lineup for truss
                            .lineToConstantHeading(new Vector2d(42, 58.5)) // line up for truss
                            // .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                            .splineToConstantHeading(new Vector2d(50,42), Math.toRadians(0)) // to bd
    //                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                            .lineToConstantHeading(new Vector2d(42, 42)) // back up from bd
                            .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                            //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                            .build();
                    drive.followTrajectorySequence(blueBLT);

                    break;

                case NONE:
                case MIDDLE:
                   TrajectorySequence blueBMT = drive.trajectorySequenceBuilder(startPose)
                           .addTemporalMarker(0,()-> {encoded.closeClaw();})
                           .lineToConstantHeading(new Vector2d(12,36)) // to spikemark
    //                                .addDisplacementMarker(()-> {encoded.armtoGroundAuto();})
    //                                .addDisplacementMarker(()->{encoded.openBottomClaw();})
                           .lineToConstantHeading(new Vector2d(12, 41)) // back up
                           .lineToConstantHeading(new Vector2d(30, 45)) // move
                           //  .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                           .splineToLinearHeading(new Pose2d(50,36), Math.toRadians(0)) // to bd
    //                                .addDisplacementMarker(()-> {encoded.openTopClaw();})
    //                                .addDisplacementMarker(()-> {encoded.closeClaw();})
                           .lineToConstantHeading(new Vector2d(47,36)) // safely move from bd
                           .lineToLinearHeading(new Pose2d(42, 58.5, Math.toRadians(180))) // line up for truss
                           .lineToConstantHeading(new Vector2d(-42, 58.5)) // fly under truss
                           .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                           .lineToConstantHeading(new Vector2d(-55, 35)) // to stack
    //                                .addDisplacementMarker(()->{encoded.openBottomClaw();})
    //                                .addDisplacementMarker(()-> {encoded.openTopClaw();})
    //                                .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
    //                                .addDisplacementMarker (()-> {encoded.closeClaw();})
                           .lineToConstantHeading(new Vector2d(-47,35)) // to stack y cord
                           .lineToLinearHeading(new Pose2d(-42, 58.5, Math.toRadians(0))) // lineup for truss
                           .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                           .splineToConstantHeading(new Vector2d(50,36), Math.toRadians(0)) // to bd
    //                                .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
    //                                .addDisplacementMarker(()-> {encoded.openTopClaw();})
                           .lineToConstantHeading(new Vector2d(42, 36)) // back up from bd
                           .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                           //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();
                   drive.followTrajectorySequence(blueBMT);
                    break;

                case RIGHT:
                    TrajectorySequence blueBRT = drive.trajectorySequenceBuilder(startPose)
                            .addTemporalMarker(0,()-> {encoded.closeClaw();})
                            .lineToConstantHeading(new Vector2d(23,33)) // safely move
                            .lineToLinearHeading(new Pose2d(19,31, Math.toRadians(180))) // orientate
                            //     .addDisplacementMarker(()-> {encoded.armtoGroundAuto();})
                            .lineToConstantHeading(new Vector2d(9, 32)) // right spikemark
    //                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
                            .lineToConstantHeading(new Vector2d(14, 32)) // back up
                            //   .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
                            .lineToLinearHeading(new Pose2d(50,29, Math.toRadians(0))) // to bd
    //                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                            .lineToConstantHeading(new Vector2d(42,42)) // safely move from bd
                            .lineToLinearHeading(new Pose2d(42, 58.5, Math.toRadians(180))) // line up for truss
                            .lineToConstantHeading(new Vector2d(-50, 58.5)) // fly under truss
                            .lineToConstantHeading(new Vector2d(-55, 35)) // to stack
    //                        .addDisplacementMarker(()->{encoded.openBottomClaw();})
    //                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
    //                        .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
    //                        .addDisplacementMarker (()-> {encoded.closeClaw();})
                            .lineToConstantHeading(new Vector2d(-45,35)) // safely move from bd
                            .lineToLinearHeading(new Pose2d(-50, 58.5, Math.toRadians(0))) // lineup for truss
                            .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                            .splineToConstantHeading(new Vector2d(50,29), Math.toRadians(0)) // to bd
    //                        .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
    //                        .addDisplacementMarker(()-> {encoded.openTopClaw();})
                            .lineToConstantHeading(new Vector2d(42, 29)) // back up from bd
                            .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                            //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                        .build();
                    drive.followTrajectorySequence(blueBRT);

                    break;
            }
        }

    }
}