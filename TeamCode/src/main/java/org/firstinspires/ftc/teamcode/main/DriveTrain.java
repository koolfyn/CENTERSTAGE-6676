package org.firstinspires.ftc.teamcode.main;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveTrain {
    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;

    static final double TICKS_PER_MOTOR_ROTATION = 537.7;
    static final double GEAR_REDUCTION = 1;
    static final double WHEEL_DIAMETER_INCHES = 3.77953;
    static final double TICKS_PER_INCH = (TICKS_PER_MOTOR_ROTATION * GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);

    public DriveTrain(HardwareMap hardwareMap, Telemetry telemetry) {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void turnR(double Power) {
        frontRight.setPower(Power);
        frontLeft.setPower(-Power);
        backRight.setPower(Power);
        backLeft.setPower(-Power);
    }

    public void forward(double distanceInches, double velocity) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setVelocity(velocity);
        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
        }

        frontLeft.setVelocity(0);
        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
        backRight.setVelocity(0);
    }

    public void backward(int distanceInches, double velocity) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setVelocity(velocity);
        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
        }

        frontLeft.setVelocity(0);
        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
        backRight.setVelocity(0);
    }

    public void strafeRight(double distanceInches, double velocity) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setVelocity(velocity);
        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
        }

        frontLeft.setVelocity(0);
        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
        backRight.setVelocity(0);
    }

    public void strafeLeft(double distanceInches, double velocity) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setVelocity(velocity);
        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
        }

        frontLeft.setVelocity(0);
        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
        backRight.setVelocity(0);
    }

    public void turnLeft(double distanceInches, double velocity) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setVelocity(velocity);
        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
        }

        frontLeft.setVelocity(0);
        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
        backRight.setVelocity(0);
    }

    public void turnRight(double distanceInches, double velocity) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setVelocity(velocity);
        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
        }

        frontLeft.setVelocity(0);
        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
        backRight.setVelocity(0);

    }

    public void diagonalUpLeft(int distanceInches, double velocity) {
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));

        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);

        while (frontRight.isBusy() && backLeft.isBusy()) {
        }

        frontRight.setVelocity(0);
        backLeft.setVelocity(0);

    }

    public void upRight(int distanceInches, double velocity) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);

        while (frontLeft.isBusy() && backRight.isBusy()) {
        }

        frontLeft.setVelocity(0);
        backRight.setVelocity(0);
    }

    public void downLeft(int distanceInches, double velocity) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);

        while (frontLeft.isBusy() && backRight.isBusy()) {
        }

        frontLeft.setVelocity(0);
        backRight.setVelocity(0);
    }

    public void downRight(int distanceInches, double velocity) {
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));

        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);

        while (frontRight.isBusy() && backLeft.isBusy()) {
        }

        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
    }

}
