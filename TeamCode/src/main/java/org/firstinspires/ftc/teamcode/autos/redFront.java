package org.firstinspires.ftc.teamcode.autos;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.RobotEncoded;

@Autonomous
public class redFront extends LinearOpMode {

    @Override
    public void runOpMode(){
        RobotEncoded robotencoded = new RobotEncoded(hardwareMap, telemetry);
        waitForStart();

        robotencoded.forward(30,750);
        sleep(1500);
        robotencoded.backward(25,500);
        sleep(1500);
        robotencoded.strafeRight(100,800);
        sleep(10000);

        while (opModeIsActive()) {sleep(20);}

    }
}
