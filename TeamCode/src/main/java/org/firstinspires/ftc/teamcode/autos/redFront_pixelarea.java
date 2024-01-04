package org.firstinspires.ftc.teamcode.autos;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.main.RobotEncoded;

@Autonomous
public class redFront_pixelarea extends LinearOpMode {

    @Override
    public void runOpMode(){
        RobotEncoded robotencoded = new RobotEncoded(hardwareMap, telemetry);
        waitForStart();

        robotencoded.backward(26,700);
        sleep(1500);
        robotencoded.forward(1,600);
        sleep(1000);
        robotencoded.strafeLeft(102,800);
        sleep(2000);
        robotencoded.forward(5,1500);
        robotencoded.backward(5,1500);


    }
}
