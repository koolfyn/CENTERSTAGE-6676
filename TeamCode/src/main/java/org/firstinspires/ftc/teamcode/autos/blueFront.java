package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.RobotEncoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;


@Autonomous(name="Blue Front")
public class blueFront extends OpMode {

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
                robotEncoded.backward(27, 700);
                robotEncoded.turnLeft(24, 700);
                robotEncoded.backward(5, 500);
                robotEncoded.forward(5, 700);
                robotEncoded.stopBot(1);
                robotEncoded.turnRight(48, 700);
                robotEncoded.strafeLeft(24, 700);
                robotEncoded.forward(12, 800); // forward enough to pass into back
                robotEncoded.armScoreAuto();
                robotEncoded.forward(35, 800); // resume going to backdrop
                robotEncoded.strafeRight(9, 500);
                robotEncoded.stopBot(1);
                robotEncoded.openTopClaw();

                break;

            case NONE:
            case MIDDLE:
                robotEncoded.closeClaw();
                robotEncoded.backward(30,700);
                robotEncoded.forward(27, 700);
                robotEncoded.turnRight(20, 700);
                robotEncoded.forward(84, 800);
                robotEncoded.armScoreAuto();
                robotEncoded.backdropClawTilt();
                robotEncoded.strafeRight(20, 500);
                robotEncoded.forward(7,800);
                robotEncoded.stopBot(1);
                robotEncoded.openTopClaw();
                robotEncoded.stopBot(1);
                robotEncoded.backward(4,800);
                robotEncoded.strafeLeft(18,800);
                robotEncoded.armtoGround();
                robotEncoded.forward(10,800);
                break;

            case RIGHT:
                robotEncoded.closeClaw();
                robotEncoded.backward(27,700);
                robotEncoded.turnRight(24, 700);
                robotEncoded.backward(5, 500);
                robotEncoded.forward(5, 700);
                robotEncoded.stopBot(1);
                robotEncoded.strafeLeft(24, 700);
                robotEncoded.forward(12, 800);
                robotEncoded.armtoLowSetLine();
                robotEncoded.forward(35, 800);
                robotEncoded.strafeRight(19, 500);
                robotEncoded.stopBot(1);
                robotEncoded.backdropClawTilt();
                robotEncoded.openTopClaw();

                break;

        }
    }
    @Override
    public void loop() {

    }
}
