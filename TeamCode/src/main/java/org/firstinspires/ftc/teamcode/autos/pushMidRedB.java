package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.RobotEncoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name="midterm push RED BACK")
public class pushMidRedB extends OpMode {
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
                robotEncoded.armOffGround();
                robotEncoded.backward(28,700);
                robotEncoded.turnLeft(20,800);
                robotEncoded.forward(4,800);
                break;

            case NONE:
            case MIDDLE:
                robotEncoded.armOffGround();
                robotEncoded.backward(28,800);
                robotEncoded.forward(6,800);
                break;

            case RIGHT:
                robotEncoded.armOffGround();
                robotEncoded.backward(25,800);
                robotEncoded.turnRight(20,900);
                robotEncoded.backward(2,700);
                robotEncoded.forward(4,800);
                break;

        }
    }

    @Override
    public void loop() {

    }

}