package org.firstinspires.ftc.teamcode.bluevision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;

    @Autonomous(name = "test vision blue")
        public class FirstVisionOpModeBlue extends OpMode{
        private FirstVisionProcessorBlue visionProcessor;
        private VisionPortal visionPortal;

        @Override
        public void init() {
            visionProcessor = new FirstVisionProcessorBlue();
            visionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), visionProcessor);
        }

        @Override
            public void init_loop() {
        }

        @Override
            public void start() {
            visionPortal.stopStreaming();
        }

        @Override
            public void loop() {telemetry.addData("Identified", visionProcessor.getSelection());}
    }
