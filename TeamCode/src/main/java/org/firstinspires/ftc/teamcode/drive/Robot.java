package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    public void stopBot() {
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }

    public void forward(double Power){
        frontRight.setPower(-Power);
        frontLeft.setPower(-Power);
        backRight.setPower(-Power);
        backLeft.setPower(-Power);
    }

    public void backward(double Power) {
        frontRight.setPower(Power);
        frontLeft.setPower(Power);
        backRight.setPower(Power);
        backLeft.setPower(Power);
    }

    public void strafeR(double Power) {
        frontRight.setPower(Power);
        frontLeft.setPower(-Power);
        backRight.setPower(-Power);
        backLeft.setPower(Power);
    }

    public void strafeL(double Power) {
        frontRight.setPower(-Power);
        frontLeft.setPower(Power);
        backRight.setPower(Power);
        backLeft.setPower(-Power);
    }

    public void turnR(double Power) {
        frontRight.setPower(Power);
        frontLeft.setPower(-Power);
        backRight.setPower(Power);
        backLeft.setPower(-Power);
    }

    public void turnL(double Power) {
        frontRight.setPower(-Power);
        frontLeft.setPower(Power);
        backRight.setPower(-Power);
        backLeft.setPower(Power);
    }

    public void forwardRight(double Power) {
        frontRight.setPower(0);
        frontLeft.setPower(Power);
        backRight.setPower(Power);
        backLeft.setPower(0);
    }

    public void forwardLeft(double Power) {
        frontRight.setPower(Power);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(Power);
    }

    public void backwardRight(double Power) {
        frontRight.setPower(-Power);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(-Power);
    }

    public void backwardLeft(double Power) {
        frontRight.setPower(0);
        frontLeft.setPower(-Power);
        backRight.setPower(-Power);
        backLeft.setPower(0);
    }
}