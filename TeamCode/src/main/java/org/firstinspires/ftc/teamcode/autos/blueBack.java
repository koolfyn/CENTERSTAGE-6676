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
                robotEncoded.strafeRight(12.5,800);
                robotEncoded.backward(4,800);
                robotEncoded.forward(6,500);
                robotEncoded.turnRight(20,800);
                robotEncoded.armScoreAuto();
                robotEncoded.forward(27,800);
                robotEncoded.stopBot(1);
                robotEncoded.openClaw();
                robotEncoded.stopBot(1);
                robotEncoded.backward(4,800);
                robotEncoded.strafeLeft(22,800);
                robotEncoded.armtoGround();
                robotEncoded.forward(6,800);
                break;

            case NONE:
            case MIDDLE:
                robotEncoded.closeClaw();
                robotEncoded.backward(29,800);
                robotEncoded.retractTilt();
                robotEncoded.forward(4,800);
                robotEncoded.turnRight(19.5,700);
                robotEncoded.forward(10,800);
                robotEncoded.strafeRight(3,800);
                robotEncoded.armScoreAuto();
                robotEncoded.forward(25,800);
                robotEncoded.stopBot(2);
                robotEncoded.openClaw();
                robotEncoded.stopBot(1);
                robotEncoded.backward(4,800);
                robotEncoded.strafeLeft(30,800);
                robotEncoded.armtoGround();
                robotEncoded.forward(4,800);
                break;

            case RIGHT:
                robotEncoded.closeClaw();
                robotEncoded.backward(29,800);
                robotEncoded.turnRight(20.5,300);
                robotEncoded.backward(3,700);
                robotEncoded.armScoreAuto();
                robotEncoded.forward(32,900);
                robotEncoded.strafeRight(6,900);
                robotEncoded.forward(8,900);
                robotEncoded.stopBot(1);
                robotEncoded.openClaw();
                robotEncoded.stopBot(2);
                robotEncoded.backward(4, 900);
                robotEncoded.strafeLeft(38,900);
                robotEncoded.armtoGround();
                robotEncoded.forward(4,800);
                break;
        }
    }

    @Override
    public void loop() {

    }

}
