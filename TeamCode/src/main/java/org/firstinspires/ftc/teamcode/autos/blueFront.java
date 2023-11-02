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

        robotencoded.forward(28,1000);
        sleep(1500);
        robotencoded.backward(24,900);
        sleep(1500);
        robotencoded.strafeLeft(101,800);
        sleep(8000);
        robotencoded.forward(5,1500);
        robotencoded.backward(5,1500);

    }
}
