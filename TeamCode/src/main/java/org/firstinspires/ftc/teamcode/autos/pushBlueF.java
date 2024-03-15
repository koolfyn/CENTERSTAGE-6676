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
                driveTrain.backward(3, 500);
                encoded.armtoGroundAuto();
                encoded.stopBot(1);
                encoded.openBottomClaw();
                driveTrain.backward(5, 700);

                break;

            case NONE:
            case MIDDLE:
                encoded.closeClaw();
                driveTrain.forward(20,700);
                encoded.armtoGroundAuto();
                encoded.stopBot(1);
                encoded.openBottomClaw();
                driveTrain.backward(10, 700);
                break;

            case RIGHT:
                encoded.closeClaw();
                driveTrain.forward(23,700);
                driveTrain.turnRight(20, 700);
                driveTrain.backward(3, 500);
                encoded.armtoGroundAuto();
                encoded.stopBot(1);
                encoded.openBottomClaw();
                driveTrain.forward(5, 700);
                break;

        }
    }
    @Override
    public void loop() {

    }
}