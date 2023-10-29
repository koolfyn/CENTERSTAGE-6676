package org.firstinspires.ftc.teamcode.autos;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.RobotEncoded;

@Autonomous
public class blueBack extends LinearOpMode {

    @Override
    public void runOpMode(){
        RobotEncoded robotencoded = new RobotEncoded(hardwareMap, telemetry);

        robotencoded.forward(12,800);
        sleep(1500);
        robotencoded.runIntake(500);
        sleep(900);
        robotencoded.runIntake(0);
        sleep(100);
        robotencoded.backward(3,800);
        sleep(500);
        robotencoded.strafeLeft(20,800);
        sleep(2000);
        robotencoded.runIntake(1000);
        sleep(500);
        robotencoded.runIntake(0);
        sleep(100);
}
}
