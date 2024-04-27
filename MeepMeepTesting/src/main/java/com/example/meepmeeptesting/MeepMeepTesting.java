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
                        drive.trajectorySequenceBuilder(new Pose2d(-37, -61.5, Math.toRadians(90)))
                                .waitSeconds(1)
                                //.addTemporalMarker(0,()->{encoded.closeClaw();})
                                //.addTemporalMarker(0.5,()->{encoded.armtoGroundAuto();})
                                .lineTo(new Vector2d(-45,-50))
                                .waitSeconds(2)
                                //.addTemporalMarker(2,()-> {encoded.openBottomClaw();})
                                //.addTemporalMarker(2.5,()-> {encoded.armScoreAuto();})
                                //purple dropped
                                .lineToSplineHeading(new Pose2d(-36,-59,Math.toRadians(0)))
                                .waitSeconds(0.1)
                                .splineTo(new Vector2d(10,-59),Math.toRadians(0))
                                .splineTo(new Vector2d(53,-29),Math.toRadians(0))
                                .waitSeconds(1)
                                .back(5)
                                //.addTemporalMarker(9,()-> {encoded.openTopClaw();})
                                .lineTo(new Vector2d(50,-58.5))
                                //yellow dropped
                                //next few line is cycle to pixel stack not done yet
                                .setReversed(true)
                                .splineTo(new Vector2d(10,-58),Math.toRadians(180))
                                .splineTo(new Vector2d(-35,-58),Math.toRadians(180))
                                .setReversed(false)
                                .lineToSplineHeading(new Pose2d(-51,-47,Math.toRadians(140)))
                                .waitSeconds(2)
                                //.addTemporalMarker(17,()-> {encoded.armtoPixelStack();})
                                //.addTemporalMarker(16,()-> {encoded.closeClaw();})
                                //.addTemporalMarker(17,()-> {encoded.armScoreAuto();})
                                //at pixel stack
                                .lineToSplineHeading(new Pose2d(-36,-59,Math.toRadians(0)))
                                .waitSeconds(1)
                                .splineTo(new Vector2d(10,-57),Math.toRadians(0))
                                .splineTo(new Vector2d(53,-29),Math.toRadians(0))
//                                .addTemporalMarker(18,()-> {
//                                    encoded.openTopClaw();
//                                    encoded.openBottomClaw();})
                                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}