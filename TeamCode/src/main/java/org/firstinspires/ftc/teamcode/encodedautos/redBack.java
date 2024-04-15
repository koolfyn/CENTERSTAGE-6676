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
@Autonomous(name="Red Back")
public class redBack extends OpMode {
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
                driveTrain.backward(28,600);
                driveTrain.turnLeft(20,700);
                driveTrain.backward(3,600);
                driveTrain.forward(3,600);
                //purple scored
                driveTrain.strafeLeft(8,700);
                encoded.stopBot(0.5);
                encoded.armScoreAuto();
                encoded.backdropClawTilt();
                driveTrain.forward(40,700);
                encoded.openTopClaw();
                encoded.stopBot(1);
                driveTrain.backward(16,700);
                //yellow pixel scored
                encoded.closeClaw();
                encoded.armtoGround();
                driveTrain.strafeRight(40,700);
                driveTrain.forward(10,700);
                //robot parked

                break;

            case NONE:
            case MIDDLE:
                encoded.closeClaw();
                driveTrain.backward(30,600);
                encoded.retractTilt();
                driveTrain.forward(4,700);
                driveTrain.turnLeft(20.5,400);
                encoded.stopBot(1);
                //purple scored
                driveTrain.strafeLeft(3,400);
                encoded.armScoreAuto();
                encoded.backdropClawTilt();
                driveTrain.forward(41,700);
                encoded.openTopClaw();
                encoded.stopBot(0.8);
                //yellow scored
                driveTrain.backward(10,700);
                encoded.closeClaw();
                encoded.armtoGround();
                driveTrain.strafeRight(27,700);
                driveTrain.forward(10,700);
                //park

                break;

            case RIGHT:
                encoded.closeClaw();
                driveTrain.backward(20,700);
                encoded.retractTilt();
                driveTrain.strafeLeft(13,600);
                encoded.stopBot(1);
                driveTrain.backward(1,700);
                driveTrain.forward(3,700);
                //purple pixel scored
                driveTrain.strafeLeft(16,700);
                driveTrain.backward(3,600);
                driveTrain.turnLeft(20,700);
                encoded.stopBot(1);
                encoded.armScoreAuto();
                driveTrain.forward(15,700);
                encoded.openTopClaw();
                //yellow pixel scored
                encoded.stopBot(1);
                driveTrain.backward(12,700);
                encoded.closeClaw();
                encoded.armtoGround();
                driveTrain.strafeRight(22,700);
                driveTrain.forward(10,700);
                //robot parked

                break;

        }
    }

    @Override
    public void loop() {

    }

}