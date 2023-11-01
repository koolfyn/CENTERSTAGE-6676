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

        robotencoded.forward(30,750);
        sleep(1500);
//        robotencoded.runIntake(500);
//        sleep(900);
//        robotencoded.runIntake(0);
//        sleep(100);
        robotencoded.backward(5,500);
        sleep(500);
        robotencoded.strafeRight(45,800);
        sleep(5000);
//        robotencoded.runIntake(1000);
//        sleep(500);
//        robotencoded.runIntake(0);
//        sleep(100);

        while (opModeIsActive()) {sleep(20);}

    }
}
