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
@Autonomous(name="blue Back")
public class blueBack extends OpMode {
    private FirstVisionProcessor visionProcessor;
    private VisionPortal visionPortal;
    private Encoded encoded;
    private DriveTrain driveTrain;

    @Override
    public void init() {
        encoded = new Encoded(hardwareMap, telemetry);
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
                driveTrain.strafeRight(12.5,800);
                driveTrain.backward(4,800);
                driveTrain.forward(7,500);
                driveTrain.turnRight(20,800);
                encoded.armScoreAuto();
                driveTrain.forward(27,800);
                encoded.stopBot(1);
//                encoded.openClaw();
                driveTrain.backward(4,800);
                driveTrain.strafeLeft(22,800);
                encoded.armtoGround();
                driveTrain.forward(6,800);
                break;

            case NONE:
            case MIDDLE:
                encoded.closeClaw();
                driveTrain.backward(29,800);
                encoded.retractTilt();
                driveTrain.forward(4,800);
                driveTrain.turnRight(19.5,700);
                driveTrain.forward(10,800);
                driveTrain.strafeRight(3,800);
                encoded.armScoreAuto();
                driveTrain.forward(25,800);
                encoded.stopBot(2);
//                robotEncoded.openClaw();
                encoded.stopBot(1);
                driveTrain.backward(4,800);
                driveTrain.strafeLeft(30,800);
                encoded.armtoGround();
                driveTrain.forward(4,800);
                break;

            case RIGHT:
                encoded.closeClaw();
                driveTrain.backward(29,800);
                driveTrain.turnRight(20.5,300);
                driveTrain.backward(3,700);
                encoded.armScoreAuto();
                driveTrain.forward(32,900);
                driveTrain.strafeRight(6,900);
                driveTrain.forward(8,900);
                encoded.stopBot(1);
//                robotEncoded.openClaw();
                encoded.stopBot(2);
                driveTrain.backward(4, 900);
                driveTrain.strafeLeft(38,900);
                encoded.armtoGround();
                driveTrain.forward(4,800);
                break;
        }
    }

    @Override
    public void loop() {

    }

}