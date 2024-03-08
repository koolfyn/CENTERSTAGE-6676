package org.firstinspires.ftc.teamcode.autos;

import com.acmerobotics.roadrunner.drive.Drive;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.DriveTrain;
import org.firstinspires.ftc.teamcode.main.Encoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous (name="push Red Front")
public class pushRedF extends OpMode{
    private FirstVisionProcessor visionProcessor;
    private VisionPortal visionPortal;
    private Encoded encoded;
    private DriveTrain driveTrain;

    @Override
    public void init() {
        encoded = new Encoded(hardwareMap, telemetry);
        driveTrain = new DriveTrain(hardwareMap, telemetry);
        visionProcessor = new FirstVisionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"),visionProcessor);;

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
                driveTrain.backward(33, 700);
                driveTrain.turnLeft(20, 350);
                driveTrain.backward(2, 700);
                driveTrain.forward(3, 700);

                break;

            case NONE:
            case MIDDLE:
                encoded.closeClaw();
                driveTrain.backward(31, 500);
                driveTrain.forward(8, 700);

                break;

            case RIGHT:
                encoded.closeClaw();
                driveTrain.backward(34, 700);
                driveTrain.turnRight(20, 350);
                driveTrain.backward(3, 500);
                driveTrain.forward(4, 700);

                break;

        }

    }
    @Override
    public void loop () {
    }
}