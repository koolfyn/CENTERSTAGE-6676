package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.RobotEncoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name="Red Back")
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
                robotEncoded.backward(28,600);
                robotEncoded.turnLeft(20,500);
                robotEncoded.backward(3,600);
                robotEncoded.forward(2,600);
                //purple scored
                robotEncoded.strafeLeft(8,700);
                robotEncoded.stopBot(0.5);
                robotEncoded.armScoreAuto();
                robotEncoded.backdropClawTilt();
                robotEncoded.forward(40,700);
                robotEncoded.openClaw();
                robotEncoded.stopBot(1);
                robotEncoded.backward(10,700);
                //yellow pixel scored
                robotEncoded.closeClaw();
                robotEncoded.armtoGround();
                robotEncoded.strafeRight(32,700);
                robotEncoded.forward(18,700);
                //robot parked

                break;

            case NONE:
            case MIDDLE:
                robotEncoded.closeClaw();
                robotEncoded.backward(31,600);
                robotEncoded.forward(3,700);
                robotEncoded.turnLeft(20,400);
                robotEncoded.stopBot(1);
                //purple scored
                robotEncoded.armScoreAuto();
                robotEncoded.backdropClawTilt();
                robotEncoded.forward(40,700);
                robotEncoded.openClaw();
                robotEncoded.stopBot(0.8);
                //yellow scored
                robotEncoded.backward(10,700);
                robotEncoded.closeClaw();
                robotEncoded.armtoGround();
                robotEncoded.strafeRight(27,700);
                robotEncoded.forward(15,700);
                //park

                break;

            case RIGHT:
                robotEncoded.closeClaw();
                robotEncoded.backward(20,500);
                robotEncoded.strafeLeft(12,500);
                robotEncoded.stopBot(1);
                robotEncoded.backward(1,400);
                robotEncoded.forward(5,600);
                //purple pixel scored
                robotEncoded.strafeLeft(16,700);
                robotEncoded.backward(4,600);
                robotEncoded.turnLeft(21,700);
                robotEncoded.stopBot(1);
                robotEncoded.armScoreAuto();
                robotEncoded.backdropClawTilt();
                robotEncoded.forward(18,700);
                robotEncoded.openClaw();
                //yellow pixel scored
                robotEncoded.stopBot(1);
                robotEncoded.backward(10,700);
                robotEncoded.closeClaw();
                robotEncoded.armtoGround();
                robotEncoded.strafeRight(18,700);
                robotEncoded.forward(15,700);
                //robot parked

                break;

        }
    }

    @Override
    public void loop() {

    }

}