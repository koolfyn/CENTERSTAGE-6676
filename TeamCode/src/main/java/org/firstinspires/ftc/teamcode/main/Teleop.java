
package org.firstinspires.ftc.teamcode.main;

import org.firstinspires.ftc.teamcode.main.Encoded;
import org.firstinspires.ftc.teamcode.main.DriveTrain;
import org.firstinspires.ftc.teamcode.main.LED;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


@TeleOp (name="Teleop main")
public class Teleop extends OpMode {

    double slideRightHeight = 0;
    double slideLeftHeight = 0;
    double armHeight = 0;

    Encoded encoded;
    DriveTrain driveTrain;
    LED led;

    @Override
    public void init() {
        encoded = new Encoded(hardwareMap, telemetry);
        driveTrain = new DriveTrain(hardwareMap, telemetry);
        led = new LED(this);
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
            led.setPattern(RevBlinkinLedDriver.BlinkinPattern.YELLOW);
        } else if (gamepad1.left_bumper) {
            driveTrain.frontLeft.setPower((y + x + r) * Constants.fastVal);
            driveTrain.frontRight.setPower((y - x - r) * Constants.fastVal);
            driveTrain.backLeft.setPower((y - x + r) * Constants.fastVal);
            driveTrain.backRight.setPower((y + x - r) * Constants.fastVal);
            led.setPattern(RevBlinkinLedDriver.BlinkinPattern.LIGHT_CHASE_RED);
        } else {
            driveTrain.frontLeft.setVelocity((y + x + r) * Constants.defaultVal);
            driveTrain.frontRight.setVelocity((y - x - r) * Constants.defaultVal);
            driveTrain.backLeft.setVelocity((y - x + r) * Constants.defaultVal);
            driveTrain.backRight.setVelocity((y + x - r) * Constants.defaultVal);
        }

        if (gamepad1.dpad_up) { // suspend
            slideLeftHeight = Constants.suspendHeight;
            slideRightHeight = -Constants.suspendHeight;
            led.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);

        } else if (gamepad1.dpad_down) {
            slideLeftHeight = 0;
            slideRightHeight = 0;
            led.setPattern(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_OCEAN_PALETTE);
        }

        if(gamepad1.x) {
            encoded.launchDrone();
            led.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED_ORANGE);
        }

        if (gamepad2.left_bumper) { // open top claw
            encoded.openTopClaw();
            led.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);

        }
        else if(gamepad2.left_trigger > 0.4) { // open bottom claw
            encoded.openBottomClaw();
            led.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE_VIOLET);

        }
        else if(gamepad2.right_bumper) {
            encoded.closeTopClaw();
            led.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
        }
        else if(gamepad2.right_trigger > 0.4) {
            encoded.closeBottomClaw();
            led.setPattern(RevBlinkinLedDriver.BlinkinPattern.LAWN_GREEN);
        }
        else if (gamepad2.dpad_down) { // close both claws
            encoded.closeClaw();
            led.setPattern(RevBlinkinLedDriver.BlinkinPattern.DARK_GREEN);
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
}