package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp (name="Teleop main")
public class Teleop extends OpMode {

    double slideHeight = 0;

//    DcMotorEx frontLeft;
//    DcMotorEx frontRight;
//    DcMotorEx backLeft;
//    DcMotorEx backRight;
//    DcMotorEx actuatorSuspend;
    RobotEncoded robotEncoded;
//    DcMotorEx intakeMotor;
//    DcMotorEx slideLeft;
//    DcMotorEx slideRight;
//    Servo pixelBox;


    @Override
    public void init() {
//        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
//        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
//        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
//        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
//        actuatorSuspend = hardwareMap.get(DcMotorEx.class, "actuatorSuspend");
        robotEncoded = new RobotEncoded(hardwareMap, telemetry);

//        slideLeft = hardwareMap.get(DcMotorEx.class, "slideLeft");
//        slideRight = hardwareMap.get(DcMotorEx.class, "slideRight");
//        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");
//        pixelBox = hardwareMap.get(Servo.class, "pixelBox");
//
//        robotEncoded.frontRight.setDirection(DcMotor.Direction.REVERSE);
//        robotEncoded.backRight.setDirection(DcMotor.Direction.REVERSE);
////
//        robotEncoded.actuatorSuspend.setDirection(DcMotorSimple.Direction.REVERSE);
//        robotEncoded.actuatorSuspend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        robotEncoded.actuatorSuspend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        slideLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        slideLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        slideRight.setDirection(DcMotorSimple.Direction.REVERSE);
//        slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        slideRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        pixelBox.scaleRange(0, 1);
    }

    @Override
    public void loop() {
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double r = gamepad1.right_stick_x;

        if (gamepad1.right_bumper) {
            robotEncoded.frontLeft.setVelocity((y + x + r) * Constants.slowVal);
            robotEncoded.frontRight.setVelocity((y - x - r) * Constants.slowVal);
            robotEncoded.backLeft.setVelocity((y - x + r) * Constants.slowVal);
            robotEncoded.backRight.setVelocity((y + x - r) * Constants.slowVal);
        } else if (gamepad1.left_bumper) {
            robotEncoded.frontLeft.setPower((y + x + r) * Constants.fastVal);
            robotEncoded.frontRight.setPower((y - x - r) * Constants.fastVal);
            robotEncoded.backLeft.setPower((y - x + r) * Constants.fastVal);
           robotEncoded.backRight.setPower((y + x - r) * Constants.fastVal);
        } else {
            robotEncoded.frontLeft.setVelocity((y + x + r) * Constants.defaultVal);
            robotEncoded.frontRight.setVelocity((y - x - r) * Constants.defaultVal);
            robotEncoded.backLeft.setVelocity((y - x + r) * Constants.defaultVal);
            robotEncoded.backRight.setVelocity((y + x - r) * Constants.defaultVal);
        }

        if(gamepad2.b) {
            slideHeight = Constants.lowSetLine;
        }
        else if(gamepad2.x) {
            slideHeight = Constants.medSetLine;
        }
        else if(gamepad2.y) {
            slideHeight = Constants.highSetLine;
        }
        else if(gamepad2.a) {
            slideHeight = 0;
        }

        if(gamepad2.left_trigger > 0.5){
            robotEncoded.intakeWheel.setPosition(-0.5);
        }
        else {
            robotEncoded.intakeWheel.setPosition(0);
        }
        if(gamepad2.right_trigger > 0.5) {
            robotEncoded.intakeWheel.setPosition(0.5);
        }
        else {
            robotEncoded.intakeWheel.setPosition(0);
        }

//        if(gamepad2.left_bumper) {
//            robotEncoded.claw.setPosition(0.3);
//        }
//        else if (gamepad2.right_bumper){
//            robotEncoded.claw.setPosition(0);
//        }
        if(gamepad2.left_bumper) {
            robotEncoded.armLift.setPosition(0);
        }
        else if(gamepad2.right_bumper){
            robotEncoded.armLift.setPosition(0.6);
        }

        if(gamepad2.right_stick_y > 0.1 && slideHeight >= 0) {
            slideHeight = robotEncoded.slideLeft.getCurrentPosition() / RobotEncoded.TICKS_PER_INCH_LS;
            slideHeight -= 1.1;

            slideHeight = robotEncoded.slideRight.getCurrentPosition() / RobotEncoded.TICKS_PER_INCH_LS;
            slideHeight -= 1.1;
        }
        else if(gamepad2.right_stick_y < -0.1) {
            slideHeight = robotEncoded.slideLeft.getCurrentPosition() / RobotEncoded.TICKS_PER_INCH_LS;
            slideHeight += 1.1;

            slideHeight = robotEncoded.slideRight.getCurrentPosition() / RobotEncoded.TICKS_PER_INCH_LS;
            slideHeight += 1.1;
        }

        telemetry.addData("left slide velocity", robotEncoded.slideLeft.getVelocity());
        telemetry.addData("right slide velocity", robotEncoded.slideRight.getVelocity());
        telemetry.addData("left target pos", robotEncoded.slideLeft.getTargetPosition());
        telemetry.addData("right target pos", robotEncoded.slideRight.getTargetPosition());
        telemetry.addData("right cur pos", robotEncoded.slideRight.getCurrentPosition());
        telemetry.addData("left cur pos", robotEncoded.slideLeft.getCurrentPosition());
        telemetry.update();

        telemetry.addLine("Left joystick | ")
            .addData("x", gamepad1.left_stick_x)
            .addData("y", gamepad1.left_stick_y);
        telemetry.addData("servo pos",robotEncoded.claw.getPosition());
        }
    }


