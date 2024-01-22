package org.firstinspires.ftc.teamcode.autos;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.main.RobotEncoded;
import org.firstinspires.ftc.teamcode.main.RobotEncoded;

@Autonomous (name = "test blue back")
public class testnocamblueback extends LinearOpMode {

    @Override
    public void runOpMode(){
        RobotEncoded robotEncoded = new RobotEncoded(hardwareMap, telemetry);
        waitForStart();
        robotEncoded.backward(29,800);
        robotEncoded.turnRight(20,300);
        robotEncoded.backward(4,700);
        robotEncoded.forward(34,900);
        robotEncoded.strafeRight(10,900);
        robotEncoded.forward(5,900);
        robotEncoded.stopBot(1);
        robotEncoded.backward(2, 900);
        robotEncoded.strafeLeft(38,900);
        robotEncoded.forward(12,900);



    }
}