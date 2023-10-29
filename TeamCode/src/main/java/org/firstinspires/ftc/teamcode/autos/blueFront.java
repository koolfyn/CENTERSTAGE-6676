package org.firstinspires.ftc.teamcode.autos;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.RobotEncoded;

@Autonomous
public class blueFront extends LinearOpMode {

    @Override
    public void runOpMode(){
        RobotEncoded robotencoded = new RobotEncoded(hardwareMap, telemetry);

        robotencoded.forward(12,800);
        sleep(1500);
        robotencoded.runIntake(500);
        sleep(800);
        robotencoded.runIntake(0);
        sleep(500);
        robotencoded.backward(12,800);
        sleep(1500);
        robotencoded.strafeLeft(40,800);
        sleep(5000);
        robotencoded.runIntake(500);
        sleep(800);
        robotencoded.runIntake(0);
        sleep(500);

        while (opModeIsActive()) {sleep(20);}
    }
}
