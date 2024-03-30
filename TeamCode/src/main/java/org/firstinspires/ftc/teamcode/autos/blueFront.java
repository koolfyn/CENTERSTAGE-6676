package org.firstinspires.ftc.teamcode.autos;

import com.acmerobotics.roadrunner.drive.Drive;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.Encoded;
import org.firstinspires.ftc.teamcode.main.DriveTrain;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Disabled
@Autonomous(name="Blue Front")
public class blueFront extends OpMode {

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
                driveTrain.backward(27, 700);
                driveTrain.turnLeft(24, 700);
                driveTrain.backward(5, 500);
                driveTrain.forward(5, 700);
                encoded.stopBot(1);
                driveTrain.turnRight(48, 700);
                driveTrain.strafeLeft(24, 700);
                driveTrain.forward(12, 800); // forward enough to pass into back
                encoded.armScoreAuto();
                driveTrain.forward(35, 800); // resume going to backdrop
                driveTrain.strafeRight(9, 500);
                encoded.stopBot(1);
                encoded.openTopClaw();

                break;

            case NONE:
            case MIDDLE:
                encoded.closeClaw();
                driveTrain.backward(30,700);
                driveTrain.forward(27, 700);
                driveTrain.turnRight(20, 700);
                driveTrain.forward(84, 800);
                encoded.armScoreAuto();
                driveTrain.strafeRight(20, 500);
                driveTrain.forward(7,800);
                encoded.stopBot(1);
                encoded.openTopClaw();
                encoded.stopBot(1);
                driveTrain.backward(4,800);
                driveTrain.strafeLeft(18,800);
                encoded.armtoGround();
                driveTrain.forward(10,800);
                break;

            case RIGHT:
                encoded.closeClaw();
                driveTrain.backward(27,700);
                driveTrain.turnRight(24, 700);
                driveTrain.backward(5, 500);
                driveTrain.forward(5, 700);
                encoded.stopBot(1);
                driveTrain.strafeLeft(24, 700);
                driveTrain.forward(12, 800);
                encoded.armtoLowSetLine();
                driveTrain.forward(35, 800);
                driveTrain.strafeRight(19, 500);
                encoded.stopBot(1);
                encoded.backdropClawTilt();
                encoded.openTopClaw();

                break;

        }
    }
    @Override
    public void loop() {

    }
}