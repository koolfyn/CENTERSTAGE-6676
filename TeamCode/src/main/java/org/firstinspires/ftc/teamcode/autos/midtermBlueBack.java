package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.RobotEncoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name="midterm BLUE BACK")
public class midtermBlueBack extends OpMode {
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
                robotEncoded.backward(28,800);
                robotEncoded.turnLeft(24,700);
                

                break;

            case NONE:
            case MIDDLE:
                robotEncoded.backward(31,800);
                robotEncoded.forward(4,800);
                robotEncoded.raiseArm();
                robotEncoded.turnRight(24,700);
                robotEncoded.forward(30,800);
                robotEncoded.stopBot(2);
                robotEncoded.tiltClaw();
                robotEncoded.openClaw();
                robotEncoded.backward(2,800);
                robotEncoded.strafeLeft(20,800);
                robotEncoded.lowerArm();
                robotEncoded.forward(2,800);
                break;

            case RIGHT:
                robotEncoded.backward(28,800);
                robotEncoded.turnRight(24,700);
                robotEncoded.backward(4,700);
                robotEncoded.raiseArm();
                robotEncoded.forward(30,900);
                robotEncoded.strafeLeft(4,700);
                robotEncoded.stopBot(2);
                robotEncoded.tiltClaw();
                robotEncoded.openClaw();
                robotEncoded.backward(1,700);
                robotEncoded.strafeLeft(20,800);
                robotEncoded.lowerArm();
                robotEncoded.forward(2,700);
                break;

        }
    }

    @Override
    public void loop() {

    }

}
