package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.RobotEncoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;


@Autonomous(name="PUSH Blue Front")
public class pushBlueF extends OpMode {

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
                robotEncoded.backward(29, 700);
                robotEncoded.turnLeft(20, 700);
                robotEncoded.backward(3, 500);
                robotEncoded.forward(5, 700);

                break;

            case NONE:
            case MIDDLE:
                robotEncoded.closeClaw();
                robotEncoded.backward(30,700);
                robotEncoded.forward(24, 700);
                break;

            case RIGHT:
                robotEncoded.closeClaw();
                robotEncoded.backward(23,700);
                robotEncoded.strafeLeft(13, 700);
                robotEncoded.backward(3, 500);
                robotEncoded.forward(5, 700);
                break;

        }
    }
    @Override
    public void loop() {

    }
}