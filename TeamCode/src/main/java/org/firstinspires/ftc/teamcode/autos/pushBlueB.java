package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.DriveTrain;
import org.firstinspires.ftc.teamcode.main.Encoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name="PUSH blue Back")
public class pushBlueB extends OpMode {
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
                driveTrain.backward(23,800);
                driveTrain.strafeRight(12,800);
                driveTrain.backward(4,800);
                driveTrain.forward(8,500);
                break;

            case NONE:
            case MIDDLE:
                encoded.closeClaw();
                driveTrain.backward(30,800);
                driveTrain.forward(4,800);
                driveTrain.turnRight(19.5,700);
                driveTrain.forward(10,800);
                driveTrain.strafeRight(2,800);
                break;

            case RIGHT:
                encoded.closeClaw();
                driveTrain.backward(29,800);
                driveTrain.turnRight(20,300);
                driveTrain.backward(3,700);
                driveTrain.forward(4,800);
                break;
        }
    }

    @Override
    public void loop() {

    }

}
