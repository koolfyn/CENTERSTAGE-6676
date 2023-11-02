package org.firstinspires.ftc.teamcode.autos;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.RobotEncoded;

@Autonomous
public class redBack extends LinearOpMode {

    @Override
    public void runOpMode(){
        RobotEncoded robotencoded = new RobotEncoded(hardwareMap, telemetry);
        waitForStart();

        robotencoded.forward(26,700);
        sleep(1500);
        robotencoded.backward(5,500);
        sleep(500);
        robotencoded.strafeRight(45,800);
        sleep(2000);
        robotencoded.forward(5,1500);
        robotencoded.backward(5,1500);

    }
}