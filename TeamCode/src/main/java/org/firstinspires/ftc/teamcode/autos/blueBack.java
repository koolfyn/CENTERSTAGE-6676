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
                robotEncoded.forward(28,700); // 28
                robotEncoded.turnLeft(24,700); // 24
                robotEncoded.forward(4,700);
                robotEncoded.backward(3,700);
                robotEncoded.strafeLeft(28,700);
                robotEncoded.turnRight(48,400);
                robotEncoded.backward(50,800);
                robotEncoded.forward(8,1900);
                robotEncoded.backward(4,1900);
                break;

            case NONE:
            case MIDDLE:
                robotEncoded.forward(31,900); //27
                robotEncoded.backward(28,700); //35
                robotEncoded.turnRight(24,500);
                robotEncoded.backward(48,700);
                robotEncoded.forward(8,2000);
                robotEncoded.backward(4,1900);
                break;

            case RIGHT:
                robotEncoded.forward(26,800); // 26
                robotEncoded.turnRight(24,900); // 24
                robotEncoded.forward(5,700);
                robotEncoded.backward(5,700);
                robotEncoded.strafeRight(29,700);
                robotEncoded.backward(45,700);
                robotEncoded.forward(10,2000);
                robotEncoded.backward(8,2000);
                break;

        }
    }

    @Override
    public void loop() {

    }

}
