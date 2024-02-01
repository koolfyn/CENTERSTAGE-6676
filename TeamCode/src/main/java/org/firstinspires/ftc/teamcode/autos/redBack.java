package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.RobotEncoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name="midterm RED BACK")
public class redBack extends OpMode {
    private FirstVisionProcessor visionProcessor;
    private VisionPortal visionPortal;
    private RobotEncoded robotEncoded;

    @Override
    public void init() {
        robotEncoded = new RobotEncoded(hardwareMap, telemetry);
        visionProcessor = new FirstVisionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), visionProcessor);
    }

    @Override
    public void init_loop() {
        telemetry.addData("Identified", visionProcessor.getSelection());
    }

    @Override
    public void start() {
        visionPortal.stopStreaming();
        telemetry.addData("Identified", visionProcessor.getSelection());
        switch (visionProcessor.getSelection()) {
            case LEFT:
                robotEncoded.closeClaw();
                robotEncoded.backward(28,700);
                robotEncoded.turnLeft(24,700);
                robotEncoded.backward(4,700);
                //purple scored
                robotEncoded.strafeLeft(5,700);
                robotEncoded.stopBot(1);
                robotEncoded.armtoLowSetLine();
                robotEncoded.backdropClawTilt();
                robotEncoded.forward(20,700);
                robotEncoded.openClaw();
                //yellow pixel scored
                robotEncoded.stopBot(2);
                robotEncoded.closeClaw();
                robotEncoded.backward(5,700);
//                robotEncoded.retractTilt();
                robotEncoded.armtoGround();
                robotEncoded.strafeRight(10,700);
                robotEncoded.forward(5,700);
                //robot parked

                break;

            case NONE:
            case MIDDLE:
                robotEncoded.closeClaw();
                robotEncoded.backward(28,700);
                robotEncoded.forward(2,700);
                robotEncoded.turnLeft(20,700);
                //purple scored
                robotEncoded.strafeRight(5,700);
                robotEncoded.stopBot(1);
                robotEncoded.armtoLowSetLine();
                robotEncoded.backdropClawTilt();
                robotEncoded.forward(20,700);
                robotEncoded.openClaw();
                //yellow scored
                robotEncoded.stopBot(2);
                robotEncoded.closeClaw();
                robotEncoded.backward(5,700);
                robotEncoded.backdropClawTilt();
                robotEncoded.armtoGround();
                robotEncoded.strafeRight(20,700);
                robotEncoded.forward(10,700);
                //park

                break;

            case RIGHT:
                robotEncoded.closeClaw();
                robotEncoded.backward(25,700);
                robotEncoded.turnRight(20,700);
                robotEncoded.backward(5,700);
                //purple pixel scored
                robotEncoded.forward(5,700);
                robotEncoded.turnLeft(20,700);
                robotEncoded.strafeRight(15,700);
                robotEncoded.stopBot(1);
                robotEncoded.armtoLowSetLine();
                robotEncoded.backdropClawTilt();
                robotEncoded.forward(20,700);
                robotEncoded.openClaw();
                //yellow pixel scored
                robotEncoded.stopBot(2);
                robotEncoded.closeClaw();
                robotEncoded.backward(5,700);
//                robotEncoded.retractTilt();
                robotEncoded.armtoGround();
                robotEncoded.strafeRight(10,700);
                robotEncoded.forward(5,700);
                //robot parked

                break;

        }
    }

    @Override
    public void loop() {

    }

}