package org.firstinspires.ftc.teamcode.bluevision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous()
    public class SimpleOpenCVBlue extends OpMode{

    private DrawRectangleProcessorBlue drawRectangleProcessor;
    private VisionPortal visionPortal;

    @Override
    public void init() {
        drawRectangleProcessor = new DrawRectangleProcessorBlue();
        visionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), drawRectangleProcessor);

    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {
        visionPortal.stopStreaming();
    }

    @Override
    public void loop() {

    }
}
