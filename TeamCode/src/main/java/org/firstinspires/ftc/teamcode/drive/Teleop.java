package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.RobotEncoded;

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



        if (gamepad1.y) {
            slideHeight = -115;
        } else if (gamepad1.x){
            slideHeight = -1;
        }

        double rawDifference = robotEncoded.actuatorSuspend.getCurrentPosition() - slideHeight * RobotEncoded.TICKS_PER_INCH_LS;
        double difference = Math.abs(rawDifference);

        if (difference >= 30) {
            robotEncoded.actuatorSuspend.setTargetPosition((int)(slideHeight * RobotEncoded.TICKS_PER_INCH_LS));
            robotEncoded.actuatorSuspend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robotEncoded.actuatorSuspend.setVelocity(2000);
        }

//        if (gamepad1.a) { //intake motor
//            intakeMotor.setVelocity(1000);
//        }
//        else {
//            intakeMotor.setVelocity(0);
//        }

        if (gamepad2.right_stick_y > 0.1 && slideHeight >= 0) {
            slideHeight = robotEncoded.actuatorSuspend.getCurrentPosition() / RobotEncoded.TICKS_PER_INCH_LS;
            slideHeight -= 1.1;
        } else if (gamepad2.right_stick_y < -0.1) {
            slideHeight = robotEncoded.actuatorSuspend.getCurrentPosition() / RobotEncoded.TICKS_PER_INCH_LS;
            slideHeight += 1.1;
        }


        telemetry.addData("slide target height", robotEncoded.actuatorSuspend.getTargetPosition());
        telemetry.addData("slight cur height", robotEncoded.actuatorSuspend.getCurrentPosition());
        telemetry.addData("slide velocity", robotEncoded.actuatorSuspend.getVelocity());
        telemetry.addLine("Left joystick | ")
            .addData("x", gamepad1.left_stick_x)
            .addData("y", gamepad1.left_stick_y);
        }
    }


