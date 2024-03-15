package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.DriveTrain;
import org.firstinspires.ftc.teamcode.main.Encoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;


@Autonomous(name="PUSH Blue Front")
public class pushBlueF extends OpMode {

    private FirstVisionProcessor visionProcessor;
    private VisionPortal visionPortal;
    private Encoded encoded;
    private DriveTrain driveTrain;

    @Override
    public void init() {
        encoded = new Encoded(hardwareMap, telemetry);
        driveTrain = new DriveTrain(hardwareMap, telemetry);
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
                encoded.closeClaw();
                driveTrain.forward(29, 700);
                driveTrain.turnLeft(20, 700);
                driveTrain.forward(3, 500);
                encoded.openBottomClaw();
                driveTrain.backward(5, 700);

                break;

            case NONE:
            case MIDDLE:
                encoded.closeClaw();
                driveTrain.backward(30,700);
                driveTrain.forward(24, 700);
                break;

            case RIGHT:
                encoded.closeClaw();
                driveTrain.backward(23,700);
                driveTrain.strafeLeft(13, 700);
                driveTrain.backward(3, 500);
                driveTrain.forward(5, 700);
                break;

        }
    }
    @Override
    public void loop() {

    }
}