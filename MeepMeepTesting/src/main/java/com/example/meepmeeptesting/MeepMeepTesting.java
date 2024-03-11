package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(46, 46, Math.toRadians(190), Math.toRadians(190), 13.8)
                .followTrajectorySequence(drive ->

                        drive.trajectorySequenceBuilder(new Pose2d(12, 74, Math.toRadians(270)))
                                .lineToConstantHeading(new Vector2d(12,30)) // to spikemark
//                                .addTemporalMarker(0,()-> {encoded.armtoGround();})
//                                .addTemporalMarker(0.5,()->{encoded.openBottomClaw();})
                                .lineToConstantHeading(new Vector2d(12, 45)) // back up
                                .splineToLinearHeading(new Pose2d(50,36), Math.toRadians(0)) // to bd
//                                .addTemporalMarker(0,()-> {encoded.armtoLowSetLine();})
//                                .addTemporalMarker(0.5, ()-> {encoded.openTopClaw();})
//                                .addTemporalMarker(0.5,()-> {encoded.closeClaw();})
                                .lineToConstantHeading(new Vector2d(43, 36)) // back up
                                .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
                                //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)


                                .build()
                                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}