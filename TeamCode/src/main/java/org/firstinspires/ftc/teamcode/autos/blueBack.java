package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.RobotEncoded;
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
                robotEncoded.forward(28,700);
                robotEncoded.turnLeft(24,700);
                robotEncoded.forward(4,700);
                robotEncoded.backward(4,700);
                robotEncoded.strafeLeft(30,700);
                robotEncoded.forward(50,800);
                robotEncoded.backward(3,800);
                robotEncoded.forward(3,800);
                break;

            case NONE:
            case MIDDLE:
                robotEncoded.forward(31,900);
                robotEncoded.backward(30,700);
                robotEncoded.strafeLeft(52,700);
                robotEncoded.forward(3,700);
                robotEncoded.backward(3,700);
                break;

            case RIGHT:
                robotEncoded.forward(26,800);
                robotEncoded.turnRight(24,900);
                robotEncoded.forward(5,700);
                robotEncoded.backward(5,700);
                robotEncoded.strafeRight(35,700);
                robotEncoded.backward(50,700);
                robotEncoded.backward(3,700);
                robotEncoded.forward(3,700);
                break;

        }
    }

    @Override
    public void loop() {

    }

}
