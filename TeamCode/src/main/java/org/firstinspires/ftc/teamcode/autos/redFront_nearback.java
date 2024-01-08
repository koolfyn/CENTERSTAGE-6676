package org.firstinspires.ftc.teamcode.autos;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.main.RobotEncoded;

@Autonomous
public class redFront_nearback extends LinearOpMode {

    @Override
    public void runOpMode(){
        RobotEncoded robotencoded = new RobotEncoded(hardwareMap, telemetry);
        waitForStart();

        robotencoded.backward(26,700);
        sleep(1500);
        robotencoded.forward(24,500);
        sleep(1500);
        robotencoded.strafeLeft(103,800);
        sleep(2000);
        robotencoded.forward(5,1500);
        robotencoded.backward(5,1500);


    }
}
