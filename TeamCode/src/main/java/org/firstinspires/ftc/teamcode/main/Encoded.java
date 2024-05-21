package org.firstinspires.ftc.teamcode.main;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Encoded {

    DcMotorEx slideLeft;
    DcMotorEx slideRight;
    static DcMotorEx arm;
    Telemetry telemetry;
    Servo clawBottom;
    Servo clawTop;
    Servo clawTilt;
    Servo droneLauncher;

    static final double TICKS_PER_MOTOR_ROTATION = 537.7;
    static final double GEAR_REDUCTION = 1;
    static final double WHEEL_DIAMETER_INCHES = 3.77953;
    static final double TICKS_PER_INCH = (TICKS_PER_MOTOR_ROTATION * GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);

    static final double LS_DIAMETER_INCHES = 1.404;
    static final double TICKS_PER_INCH_LS = (TICKS_PER_MOTOR_ROTATION * GEAR_REDUCTION) / (LS_DIAMETER_INCHES * Math.PI);
    static final double MAX_TICKS_LS = 34;
    static final double MIN_TICKS_LS = 10;

    public Encoded(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        slideLeft = hardwareMap.get(DcMotorEx.class, "slideLeft");
        slideRight = hardwareMap.get(DcMotorEx.class,"slideRight");
        clawBottom = hardwareMap.get(Servo.class,"clawBottom");
        clawTop = hardwareMap.get(Servo.class,"clawTop");
        clawTilt = hardwareMap.get(Servo.class,"clawTilt");
        arm = hardwareMap.get(DcMotorEx.class,"arm");
        droneLauncher = hardwareMap.get(Servo.class,"drone");

        slideLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setDirection(DcMotorSimple.Direction.REVERSE);
        slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        arm.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        clawBottom.scaleRange(0,1);
        clawTop.scaleRange(0,1);
    }

    public void openBottomClaw() {
        clawBottom.setPosition(0.38);
    }
    public void openTopClaw() {
        clawTop.setPosition(0.1);
    }

    public void closeClaw() {
        clawBottom.setPosition(0.10);
        clawTop.setPosition(0.4);
    }

    public void closeTopClaw() {
        clawTop.setPosition(0.4);
    }
    public void closeBottomClaw() {
        clawBottom.setPosition(0.10);
    }

    public void armtoGround() {
        clawTilt.setPosition(0.2);
        arm.setTargetPosition(Constants.ground);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setVelocity(400);
    }
    public void armtoGroundAuto() {
        clawTilt.setPosition(0.19);
        arm.setTargetPosition(Constants.ground);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setVelocity(400);
    }

    public void armScoreAuto() {
        clawTilt.setPosition(0);
        arm.setTargetPosition(5);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setVelocity(1000);
    }

    public void armtoLowSetLine() {
        clawTilt.setPosition(0.02);
        arm.setTargetPosition(Constants.lowSetLine);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setVelocity(1000);
    }

    public void armtoMidSetLine() {
        arm.setTargetPosition(Constants.medSetLine);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setVelocity(1000);
        clawTilt.setPosition(0.5);
    }

    public void armtoPixelStack() {
        arm.setTargetPosition(32);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setVelocity(1000);
        clawTilt.setPosition(0.154);
    }

    public void launchDrone() {
        armtoGround();
        stopBot(1);
        droneLauncher.setPosition(0.5);
    }

    public void retractTilt() {
        clawTilt.setPosition(0.2);
    }

    public void backdropClawTilt() { clawTilt.setPosition(0.18); }

    public void tiltToGround() {
        clawTilt.setPosition(0.19);
    }

    public void setSlidePosition(double velocity, double distanceInches) {

        if (distanceInches > MAX_TICKS_LS || distanceInches < 0)
            return;

        slideRight.setTargetPosition((int) (distanceInches * TICKS_PER_INCH_LS));
        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideRight.setVelocity(velocity);

        slideLeft.setTargetPosition((int) (distanceInches * TICKS_PER_INCH_LS));
        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideLeft.setVelocity(velocity);

        while(slideLeft.isBusy()) { }
        while(slideRight.isBusy()) { }

    }

    public void stopBot(double seconds) {

        long startTime = System.currentTimeMillis();
        long duration = (long) (seconds * 1000);

        while (System.currentTimeMillis() - startTime < duration) {
            try {
                Thread.sleep(10); // Adjust the delay as needed
            } catch (InterruptedException e) {
                // Handle the exception if needed
                e.printStackTrace();
            }
        }

    }

    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }
}