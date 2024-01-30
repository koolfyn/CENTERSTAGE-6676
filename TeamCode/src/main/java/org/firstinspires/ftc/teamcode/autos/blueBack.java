package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.RobotEncoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name="blue Back")
public class blueBack extends OpMode {
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
                robotEncoded.backward(23,800);
                robotEncoded.strafeRight(12,800);
                robotEncoded.backward(4,800);
                robotEncoded.forward(8,500);
                robotEncoded.turnRight(20,800);
                robotEncoded.armScoreAuto();
                robotEncoded.backdropClawTilt();
                robotEncoded.forward(32,800);
                robotEncoded.stopBot(1);
                robotEncoded.openClaw();
                robotEncoded.stopBot(1);
                robotEncoded.backward(3,800);
                robotEncoded.strafeLeft(24,800);
                robotEncoded.armtoGround();
                robotEncoded.forward(8,800);
                break;

            case NONE:
            case MIDDLE:
                robotEncoded.closeClaw();
                robotEncoded.backward(30,800);
                robotEncoded.forward(4,800);
                robotEncoded.turnRight(19.5,700);
                robotEncoded.forward(10,800);
                robotEncoded.strafeRight(2,800);
                robotEncoded.armScoreAuto();
                robotEncoded.backdropClawTilt();
                robotEncoded.forward(31,800);
                robotEncoded.stopBot(2);
                robotEncoded.openClaw();
                robotEncoded.stopBot(1);
                robotEncoded.backward(4,800);
                robotEncoded.strafeLeft(30,800);
                robotEncoded.armtoGround();
                robotEncoded.forward(8,800);
                break;

            case RIGHT:
                robotEncoded.closeClaw();
                robotEncoded.backward(29,800);
                robotEncoded.turnRight(20,300);
                robotEncoded.backward(3,700);
                robotEncoded.forward(34,900);
                robotEncoded.armScoreAuto();
                robotEncoded.backdropClawTilt();
                robotEncoded.strafeRight(8,900);
                robotEncoded.forward(5,900);
                robotEncoded.stopBot(2);
                robotEncoded.openClaw();
                robotEncoded.backward(2, 900);
                robotEncoded.strafeLeft(38,900);
                robotEncoded.armtoGround();
                robotEncoded.forward(12,900);
                break;
        }
    }

    @Override
    public void loop() {

    }

}
