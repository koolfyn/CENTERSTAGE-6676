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
                        drive.trajectorySequenceBuilder(new Pose2d(12, -61.5, Math.toRadians(90)))
                        .waitSeconds(1)
                        //.addTemporalMarker(0,()->{encoded.closeClaw();})
                        //.addTemporalMarker(1,()->{encoded.armtoGroundAuto();})
                        .lineToSplineHeading(new Pose2d(23,-54,Math.toRadians(90)))
                        .waitSeconds(1)
                        //.addTemporalMarker(3,()->{encoded.openBottomClaw();})
                       // .addTemporalMarker(4,()->{encoded.armScoreAuto();})
                        //purple dropped
                        .splineTo(new Vector2d(54,-40),Math.toRadians(0))
                        .waitSeconds(2)
                   //     .addTemporalMarker(6,()->{encoded.openTopClaw();})
                        .lineTo(new Vector2d(52,-58.5))
                        //yellow dropped
                        .build()
                                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}