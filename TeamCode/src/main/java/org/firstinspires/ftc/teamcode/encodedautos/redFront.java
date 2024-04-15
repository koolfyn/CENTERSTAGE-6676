package org.firstinspires.ftc.teamcode.encodedautos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.DriveTrain;
import org.firstinspires.ftc.teamcode.main.Encoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;
@Disabled
@Autonomous(name="red Front")
public class redFront extends OpMode {
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
                driveTrain.forward(28,700);
                driveTrain.turnLeft(23,700);
                driveTrain.forward(4,700);
                driveTrain.backward(2,700);
                break;

            case NONE:
            case MIDDLE:
                driveTrain.forward(30,900);
                driveTrain.backward(3,900);
                break;

            case RIGHT:
                driveTrain.forward(28,800);
                driveTrain.turnRight(25,900);
                driveTrain.forward(5,700);
                driveTrain.backward(2,700);
                break;

        }
    }

    @Override
    public void loop() {

    }

}