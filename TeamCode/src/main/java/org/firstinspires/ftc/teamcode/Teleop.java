package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp (name="main")
public class Teleop extends OpMode {

    double slideHeight = 0;

    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;
    DcMotorEx intakeMotor;
    DcMotorEx slideLeft;
    DcMotorEx slideRight;
    Servo pixelBox;

    @Override //declaring motors
    public void init() {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        slideLeft = hardwareMap.get(DcMotorEx.class, "slideLeft");
        slideRight = hardwareMap.get(DcMotorEx.class, "slideRight");
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");
        pixelBox = hardwareMap.get(Servo.class, "pixelBox");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        slideLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideRight.setDirection(DcMotorSimple.Direction.REVERSE);
        slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pixelBox.scaleRange(0, 1);
    }

    @Override
    public void loop() {   // runs on multiple times
        double x = -gamepad1.left_stick_x; // stores data in gp
        double y = gamepad1.left_stick_y;
        double r = -gamepad1.right_stick_x;

        if (gamepad1.right_bumper) {
            frontLeft.setPower((y + x + r) * Constants.slowVal);
            frontRight.setPower((y + x + r) * Constants.slowVal);
            backLeft.setPower((y + x + r) * Constants.slowVal);
            backRight.setPower((y + x + r) * Constants.slowVal);
        } else if (gamepad1.left_bumper){
            frontLeft.setPower((y + x + r) * Constants.defaultVal);
            frontRight.setPower((y + x + r) * Constants.defaultVal);
            backLeft.setPower((y + x + r) * Constants.defaultVal);
            backRight.setPower((y + x + r) * Constants.defaultVal);
        } else {
            frontLeft.setPower((y + x + r) * Constants.fastVal);
            frontRight.setPower((y + x + r) * Constants.fastVal);
            backLeft.setPower((y + x + r) * Constants.fastVal);
            backRight.setPower((y + x + r) * Constants.fastVal);
        }

        if (gamepad1.a) { //intake motor
            intakeMotor.setVelocity(1000);
        }
        else {
            intakeMotor.setVelocity(0);
        }

        // cascade slide movement
        if(gamepad2.right_stick_y > 0.1 && slideHeight >= 0) {
            slideHeight = slideLeft.getCurrentPosition() / RobotEncoded.TICKS_PER_INCH_LS;
            slideHeight -= 1.1;

            slideHeight = slideRight.getCurrentPosition() / RobotEncoded.TICKS_PER_INCH_LS;
            slideHeight -= 1.1;
        }
        else if(gamepad2.right_stick_y < -0.1) {
            slideHeight = slideLeft.getCurrentPosition() / RobotEncoded.TICKS_PER_INCH_LS;
            slideHeight += 1.1;

            slideHeight = slideRight.getCurrentPosition() / RobotEncoded.TICKS_PER_INCH_LS;
            slideHeight += 1.1;
        }



        // 2 motors for CascadeSlide & hanging mechanism
        // 1 motor for wheel intake
        // 1 servo for box
    }
}

