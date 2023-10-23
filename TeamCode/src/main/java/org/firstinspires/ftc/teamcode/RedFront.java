package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class RedFront extends LinearOpMode {

    @Override
    public void runOpMode() {
        RobotEncoded robotencoded = new RobotEncoded(hardwareMap, telemetry);

        robotencoded.forward(12,800);
        sleep(2000);
        robotencoded.runIntake(500);
        sleep(900);
        robotencoded.runIntake(0);
        sleep(100);
        robotencoded.backward(20,800);
        sleep(500);
        robotencoded.strafeRight(30,800);
        sleep(5000);
        robotencoded.runIntake(1000);
        sleep(500);
    }
//    while (opModeIsActive()) {sleep(20);}
}
