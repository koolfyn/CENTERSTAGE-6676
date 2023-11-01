package org.firstinspires.ftc.teamcode.autos;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.RobotEncoded;

@Autonomous
public class blueFront extends LinearOpMode {

    @Override
    public void runOpMode(){
        RobotEncoded robotencoded = new RobotEncoded(hardwareMap, telemetry);
        waitForStart();

        robotencoded.forward(23,1000);
        sleep(1500);
//        robotencoded.runIntake(500);
//        sleep(800);
//        robotencoded.runIntake(0);
//        sleep(500);
        robotencoded.backward(21,900);
        sleep(1500);
        robotencoded.strafeLeft(100,900);
        sleep(10000);
//        robotencoded.runIntake(500);
//        sleep(800);
//        robotencoded.runIntake(0);
//        sleep(500);

        while (opModeIsActive()) {sleep(20);}
    }
}
