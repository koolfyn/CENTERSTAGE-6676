package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.RobotEncoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;


@Autonomous(name="Red Front")
public class redFront extends OpMode {

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
                robotEncoded.backward(27, 700);
                robotEncoded.turnLeft(24, 700);
                robotEncoded.backward(5, 500);
                robotEncoded.forward(5, 700);
                robotEncoded.strafeRight(24, 700);
                robotEncoded.forward(12, 700); // forward enough to pass into back
                robotEncoded.armtoLowSetLine(); // raise when in back to avoid hitting truss
                robotEncoded.forward(25, 700); // resume going to backdrop
                robotEncoded.strafeLeft(15, 600);
                robotEncoded.stopBot(2);
                robotEncoded.backdropClawTilt();
                robotEncoded.openClaw();

                break;

            case NONE:
            case MIDDLE:
                robotEncoded.backward(31, 700);
                robotEncoded.forward(28, 700);
                robotEncoded.turnLeft(24, 700);
                robotEncoded.stopBot(2);
                robotEncoded.forward(12, 700);
                robotEncoded.armtoLowSetLine();
                robotEncoded.forward(25, 700);
                robotEncoded.strafeLeft(12, 600);
                robotEncoded.stopBot(1);
                robotEncoded.backdropClawTilt();
                robotEncoded.openClaw();

                break;

            case RIGHT:
                robotEncoded.backward(27, 700);
                robotEncoded.turnRight(24, 700);
                robotEncoded.backward(5, 700);
                robotEncoded.forward(5, 600);
                robotEncoded.strafeLeft(24, 700);
                robotEncoded.turnRight(48, 700);
                robotEncoded.stopBot(2);
                robotEncoded.forward(12, 700);
                robotEncoded.armtoLowSetLine();
                robotEncoded.forward(25, 700);
                robotEncoded.strafeLeft(9, 700);
                robotEncoded.stopBot(1);
                robotEncoded.backdropClawTilt();
                robotEncoded.openClaw();

                break;

        }
    }
    @Override
    public void loop () {

         

        }
    }
