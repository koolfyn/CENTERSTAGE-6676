package org.firstinspires.ftc.teamcode.autos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.main.RobotEncoded;
import org.firstinspires.ftc.teamcode.vision.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "test roadrunner")
public class testRoadRunner extends OpMode {
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
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        visionPortal.stopStreaming();
        telemetry.addData("Identified", visionProcessor.getSelection());
        switch (visionProcessor.getSelection()) {
            case LEFT:
             // remember to create a start pose before trajectory creation and name them appropriately
                break;

            case NONE:
            case MIDDLE:

                break;

            case RIGHT:

                break;
        }
    }

    @Override
    public void loop() {

    }

}
