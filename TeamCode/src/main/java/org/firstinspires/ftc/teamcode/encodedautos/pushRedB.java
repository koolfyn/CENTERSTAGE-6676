
package org.firstinspires.ftc.teamcode.encodedautos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.DriveTrain;
import org.firstinspires.ftc.teamcode.main.Encoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name="PUSH Red Back")
public class pushRedB extends OpMode {
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
                driveTrain.forward(2,600);
                //purple scored
                break;

            case NONE:
            case MIDDLE:
                encoded.closeClaw();
                driveTrain.backward(31,600);
                encoded.retractTilt();
                driveTrain.forward(4,700);
                driveTrain.turnLeft(19.5,400);
                //purple scored
                break;

            case RIGHT:
                encoded.closeClaw();
                driveTrain.backward(20,700);
                encoded.retractTilt();
                driveTrain.strafeLeft(13,600);
                encoded.stopBot(1);
                driveTrain.backward(1,700);
                driveTrain.forward(3,700);
                break;

        }
    }

    @Override
    public void loop() {

    }

}
