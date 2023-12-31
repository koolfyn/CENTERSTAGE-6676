package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.RobotEncoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

    @Autonomous(name="blue Front")
    public class blueFront extends OpMode {
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
                    robotEncoded.forward(26,700);
                    robotEncoded.turnLeft(23,700);
                    robotEncoded.forward(4,700);
                    robotEncoded.backward(2,900);
                    break;

                case NONE:
                case MIDDLE:
                    robotEncoded.forward(30,900);
                    robotEncoded.backward(4,900);
                    break;

                case RIGHT:
                    robotEncoded.forward(28,800);
                    robotEncoded.turnRight(25,900);
                    robotEncoded.forward(4,700);
                    robotEncoded.backward(3,900);
                    break;

                 }
         }

        @Override
        public void loop() {

        }

    }