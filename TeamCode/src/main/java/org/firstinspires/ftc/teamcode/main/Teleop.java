package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name="Teleop main")
public class Teleop extends OpMode {

    double rightslideHeight = 0;
    double leftslideHeight = 0;

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

        if (gamepad1.right_bumper) { // driver movements
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

        if (gamepad2.y) { // suspend
            leftslideHeight = Constants.suspendHeight;
            rightslideHeight = -Constants.suspendHeight;
        } else if (gamepad2.x) {
            leftslideHeight = 0;
            rightslideHeight = 0;
        }

        if (gamepad2.left_bumper) { // open claw
            robotEncoded.claw.setPosition(0.5);
        } else if (gamepad2.right_bumper) { // close claw
            robotEncoded.claw.setPosition(0);
        }

        if (gamepad2.left_trigger > 0.5) { // un-tilt claw
            robotEncoded.clawTilt.setPosition(0);
        } else if (gamepad2.right_trigger > 0.5) {  // tilts claw
            robotEncoded.clawTilt.setPosition(0.6);
        }

        double rawDifference1 = robotEncoded.slideLeft.getCurrentPosition() - leftslideHeight * RobotEncoded.TICKS_PER_INCH_LS;
        double difference1 = Math.abs(rawDifference1);
        double rawDifference2 = robotEncoded.slideRight.getCurrentPosition() - rightslideHeight*RobotEncoded.TICKS_PER_INCH_LS;
        double difference2 = Math.abs(rawDifference2);

        if (difference2 >= 30) {
            robotEncoded.slideRight.setTargetPosition((int)(rightslideHeight * RobotEncoded.TICKS_PER_INCH_LS));
            robotEncoded.slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robotEncoded.slideRight.setVelocity(1000);
        }
        if(difference1 >= 30) {
            robotEncoded.slideLeft.setTargetPosition((int)(leftslideHeight * RobotEncoded.TICKS_PER_INCH_LS));
            robotEncoded.slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robotEncoded.slideLeft.setVelocity(1000);
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
            telemetry.addData("servo pos", robotEncoded.claw.getPosition());
        }
    }



