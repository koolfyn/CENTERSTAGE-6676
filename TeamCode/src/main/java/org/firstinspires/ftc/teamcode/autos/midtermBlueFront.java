package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.RobotEncoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;


@Autonomous(name="midterm Blue Front")
public class midtermBlueFront extends OpMode {

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
                robotEncoded.backward(27, 700);
                robotEncoded.turnLeft(24, 700);
                robotEncoded.backward(5, 500);
                robotEncoded.forward(5, 700);
                robotEncoded.stopBot(1);
                robotEncoded.turnRight(48, 700);
                robotEncoded.strafeLeft(24, 700);
                robotEncoded.forward(12, 800); // forward enough to pass into back
                robotEncoded.raiseArm(); // raise when in back to avoid hitting truss
                robotEncoded.forward(35, 800); // resume going to backdrop
                robotEncoded.strafeRight(9, 500);
                robotEncoded.stopBot(1);
                robotEncoded.tiltClaw();
                robotEncoded.openClaw();

                break;

            case NONE:
            case MIDDLE:
                robotEncoded.backward(31,700);
                robotEncoded.forward(28, 700);
                robotEncoded.turnRight(26, 700);
                robotEncoded.forward(12, 800);
                robotEncoded.raiseArm();
                robotEncoded.forward(35,800);
                robotEncoded.strafeRight(14, 500);
                robotEncoded.stopBot(2);
                robotEncoded.tiltClaw();
                robotEncoded.openClaw();

                break;

            case RIGHT:
                robotEncoded.backward(27,700);
                robotEncoded.turnRight(24, 700);
                robotEncoded.backward(5, 500);
                robotEncoded.forward(5, 700);
                robotEncoded.stopBot(1);
                robotEncoded.strafeLeft(24, 700);
                robotEncoded.forward(12, 800);
                robotEncoded.raiseArm();
                robotEncoded.forward(35, 800);
                robotEncoded.strafeRight(19, 500);
                robotEncoded.stopBot(1);
                robotEncoded.tiltClaw();
                robotEncoded.openClaw();

                break;

        }
    }
    @Override
    public void loop() {

    }
}


