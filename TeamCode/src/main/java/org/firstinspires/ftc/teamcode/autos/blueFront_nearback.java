package org.firstinspires.ftc.teamcode.autos;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.RobotEncoded;

@Autonomous
public class blueFront_nearback extends LinearOpMode {

    @Override
    public void runOpMode(){
        RobotEncoded robotencoded = new RobotEncoded(hardwareMap, telemetry);
        waitForStart();

        robotencoded.backward(28,700);
        sleep(1000);
        robotencoded.forward(23,700);
        sleep(3000);
        robotencoded.strafeRight(101,800);
        sleep(1000);
        robotencoded.forward(5,1500);
        robotencoded.backward(5,1500);

    }
}
