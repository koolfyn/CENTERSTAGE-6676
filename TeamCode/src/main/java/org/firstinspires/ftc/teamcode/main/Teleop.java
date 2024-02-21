package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name="Teleop main")
public class Teleop extends OpMode {

    double slideRightHeight = 0;
    double slideLeftHeight = 0;
    double armHeight = 0;
    RobotEncoded robotEncoded;

    @Override
    public void init() {
        robotEncoded = new RobotEncoded(hardwareMap, telemetry);
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

        if (gamepad2.dpad_up) { // suspend
            slideLeftHeight = Constants.suspendHeight;
            slideRightHeight = -Constants.suspendHeight;
        } else if (gamepad2.dpad_down) {
            slideLeftHeight = 0;
            slideRightHeight = 0;
        }

        if (gamepad2.left_bumper) { // open claw
            robotEncoded.openClaw();
        } else if (gamepad2.right_bumper) { // close claw
            robotEncoded.closeClaw();
        }

        if(gamepad2.y) { // arm to pixel stack
            robotEncoded.armtoPixelStack();
        }

        if(gamepad2.x) {
            robotEncoded.armtoMidSetLine();
        }
        if(gamepad2.b) {
            robotEncoded.armtoLowSetLine();
        }
        if(gamepad2.a) {
            robotEncoded.armtoGround();
        }

        if(gamepad2.right_trigger > 0.5) {
            robotEncoded.armScoreAuto();
        }
        if(gamepad1.x) {
            robotEncoded.launchDrone();
        }


        if (gamepad2.right_stick_y > 0.1 && armHeight >= 0) { // manual arm control
            armHeight = robotEncoded.arm.getCurrentPosition() / RobotEncoded.TICKS_PER_INCH_LS;
            armHeight -= 10;
        } else if (gamepad2.right_stick_y < -0.1) {
            armHeight = robotEncoded.arm.getCurrentPosition() / RobotEncoded.TICKS_PER_INCH_LS;
            armHeight += 10;
        }

        double rawDifference1 = robotEncoded.slideLeft.getCurrentPosition() - slideLeftHeight * RobotEncoded.TICKS_PER_INCH_LS;
        double difference1 = Math.abs(rawDifference1);
        double rawDifference2 = robotEncoded.slideRight.getCurrentPosition() - slideRightHeight *RobotEncoded.TICKS_PER_INCH_LS;
        double difference2 = Math.abs(rawDifference2);

        if (difference2 >= 30) {
            robotEncoded.slideRight.setTargetPosition((int)(slideRightHeight * RobotEncoded.TICKS_PER_INCH_LS));
            robotEncoded.slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robotEncoded.slideRight.setVelocity(1000);
        }

        if(difference1 >= 30) {
            robotEncoded.slideLeft.setTargetPosition((int)(slideLeftHeight * RobotEncoded.TICKS_PER_INCH_LS));
            robotEncoded.slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robotEncoded.slideLeft.setVelocity(1000);
        }

        telemetry.addData("claw tilt cur pos", robotEncoded.clawTilt.getPosition());
        telemetry.addData("claw tilt target pos", robotEncoded.clawTilt.getPosition());
        telemetry.addData("arm target position", robotEncoded.arm.getTargetPosition());
        telemetry.addData("arm velocity",robotEncoded.arm.getVelocity());
        telemetry.addData("arm curr pos", robotEncoded.arm.getCurrentPosition());
        telemetry.update();

        telemetry.addLine("Left joystick | ")
                .addData("x", gamepad1.left_stick_x)
                .addData("y", gamepad1.left_stick_y);
        telemetry.addData("servo pos", robotEncoded.claw.getPosition());
    }
}