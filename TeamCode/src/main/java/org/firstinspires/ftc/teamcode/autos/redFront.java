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
                robotEncoded.closeClaw();
                robotEncoded.backward(34, 700);
                robotEncoded.turnLeft(20, 700);
                robotEncoded.backward(3, 500);
                robotEncoded.forward(3, 700);
                robotEncoded.strafeLeft(23, 700);
                robotEncoded.forward(36, 700);
                robotEncoded.armScoreAuto();
                robotEncoded.forward(43, 700);
                robotEncoded.strafeRight(21, 700); //apriltag scan placement
                robotEncoded.forward(9,700);
               // robotEncoded.forward(3,700);
                robotEncoded.stopBot(1);
                robotEncoded.backdropClawTilt();
                robotEncoded.openClaw();
                robotEncoded.stopBot(1);
                robotEncoded.backward(5,700);
                robotEncoded.armtoGround();
                robotEncoded.strafeLeft(20,700);
                robotEncoded.forward(15,750);

                break;

            case NONE:
            case MIDDLE:
                robotEncoded.closeClaw();
                robotEncoded.backward(34, 700);
                robotEncoded.forward(5, 700);
                robotEncoded.strafeRight(10,700);
                robotEncoded.backward(12,700);
                robotEncoded.turnLeft(30,700); // to get claw front-facing
                robotEncoded.forward(56, 800);
                robotEncoded.strafeRight(25,700); // apriltag scan placement
                robotEncoded.stopBot(1);
                robotEncoded.armScoreAuto();
                robotEncoded.backdropClawTilt();
                robotEncoded.openClaw();
                robotEncoded.stopBot(1);
                robotEncoded.backward(4,700);
                robotEncoded.strafeLeft(23,700);
                robotEncoded.armtoGround();
                robotEncoded.forward(5,750);

                break;

            case RIGHT:
                robotEncoded.closeClaw();
                robotEncoded.backward(34, 700);
                robotEncoded.turnRight(20, 700);
                robotEncoded.backward(3, 500);
                robotEncoded.forward(3, 700);
                robotEncoded.strafeRight(23, 700);
                robotEncoded.turnRight(40,700); // to get claw front-facing
                robotEncoded.forward(30, 700);
                robotEncoded.forward(56, 700);
                robotEncoded.strafeRight(21, 700); // apriltag scan placement
                robotEncoded.stopBot(1);
                robotEncoded.armScoreAuto();
                robotEncoded.backdropClawTilt();
                robotEncoded.openClaw();
                robotEncoded.stopBot(1);
                robotEncoded.backward(4,700);
                robotEncoded.strafeLeft(23,700);
                robotEncoded.armtoGround();
                robotEncoded.forward(5,750);

                break;

        }
    }
    @Override
    public void loop () {

         

        }
    }
