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

@TeleOp (name="main")
public class Teleop extends OpMode {

    double slideHeight = 0;

    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;
    DcMotorEx actuatorSuspend;
//    DcMotorEx intakeMotor;
//    DcMotorEx slideLeft;
//    DcMotorEx slideRight;
//    Servo pixelBox;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        actuatorSuspend = hardwareMap.get(DcMotorEx.class, "actuatorSuspend");
//        slideLeft = hardwareMap.get(DcMotorEx.class, "slideLeft");
//        slideRight = hardwareMap.get(DcMotorEx.class, "slideRight");
//        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");
//        pixelBox = hardwareMap.get(Servo.class, "pixelBox");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        actuatorSuspend.setDirection(DcMotorSimple.Direction.REVERSE);
        actuatorSuspend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        actuatorSuspend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
        double x = -gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double r = -gamepad1.right_stick_x;

        RobotEncoded robotEncoded;

        if (gamepad1.right_bumper) {
            frontLeft.setPower((y + x + r) * Constants.slowVal);
            frontRight.setPower((y - x - r) * Constants.slowVal);
            backLeft.setPower((y - x + r) * Constants.slowVal);
            backRight.setPower((y + x - r) * Constants.slowVal);
        }
        else if (gamepad1.left_bumper) {
            frontLeft.setPower((y + x + r) * Constants.fastVal);
            frontRight.setPower((y - x - r) * Constants.fastVal);
            backLeft.setPower((y - x + r) * Constants.fastVal);
            backRight.setPower((y + x - r) * Constants.fastVal);
        }
        else {
            frontLeft.setPower((y + x + r) * Constants.defaultVal);
            frontRight.setPower((y - x - r) * Constants.defaultVal);
            backLeft.setPower((y - x + r) * Constants.defaultVal);
            backRight.setPower((y + x - r) * Constants.defaultVal);
        }

        if (gamepad1.y) {
            slideHeight = 25;
        }
        else {
            slideHeight = 0;
        }

//        if (gamepad1.a) { //intake motor
//            intakeMotor.setVelocity(1000);
//        }
//        else {
//            intakeMotor.setVelocity(0);
//        }

        // cascade slide movement
//        if(gamepad2.right_stick_y > 0.1 && slideHeight >= 0) {
//            slideHeight = slideLeft.getCurrentPosition() / RobotEncoded.TICKS_PER_INCH_LS;
//            slideHeight -= 1.1;
//
//            slideHeight = slideRight.getCurrentPosition() / RobotEncoded.TICKS_PER_INCH_LS;
//            slideHeight -= 1.1;
//        }
//        else if(gamepad2.right_stick_y < -0.1) {
//            slideHeight = slideLeft.getCurrentPosition() / RobotEncoded.TICKS_PER_INCH_LS;
//            slideHeight += 1.1;
//
//            slideHeight = slideRight.getCurrentPosition() / RobotEncoded.TICKS_PER_INCH_LS;
//            slideHeight += 1.1;
//        }


        // 2 motors for CascadeSlide & hanging mechanism
        // 1 motor for wheel intake
        // 1 servo for box
        telemetry.addData("slide target height", actuatorSuspend.getTargetPosition());
        telemetry.addData("slight cur height", actuatorSuspend.getCurrentPosition());
        telemetry.addData("slide velocity", actuatorSuspend.getVelocity());
    }
}

