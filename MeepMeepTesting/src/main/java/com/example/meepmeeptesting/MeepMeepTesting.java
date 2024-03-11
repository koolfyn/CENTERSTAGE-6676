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

                                drive.trajectorySequenceBuilder(new Pose2d(12, 74, Math.toRadians(90))) // 21 seconds
                                        .lineToConstantHeading(new Vector2d(12,32)) // to spikemark
                                       // .addTemporalMarker(0,()-> {robotEncoded.armtoGround();})
                                        //.addTemporalMarker(0.5,()->{robotEncoded.openBottomClaw();})
                                        .lineToConstantHeading(new Vector2d(12, 37)) // back up
                                        .splineToLinearHeading(new Pose2d(50,36), Math.toRadians(0)) // to bd
                                       // .addTemporalMarker(0,()-> {robotEncoded.armtoLowSetLine();})
                                        //.addTemporalMarker(0.5, ()-> {robotEncoded.openTopClaw();})
                                        //.addTemporalMarker(0.5,()-> {robotEncoded.closeClaw();})
                                        .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
                                        .lineToLinearHeading(new Pose2d(0, 11, Math.toRadians(180))) // middle + orientate
                                        .lineToConstantHeading(new Vector2d(-60,11)) // to white stack
                                       // .addTemporalMarker(0.5, ()-> {robotEncoded.armtoPixelStack();})
                                        //.addTemporalMarker(0.5, ()-> {robotEncoded.closeClaw();})
                                        .lineToLinearHeading(new Pose2d(0, 11, Math.toRadians(0))) // middle + orientate
                                        .lineToConstantHeading(new Vector2d(38,11)) // "safe spot"
                                       .splineToConstantHeading(new Vector2d(50,36), Math.toRadians(0)) // to bd
                                       // .addTemporalMarker(0,()-> {robotEncoded.armtoLowSetLine();})
                                      //  .addTemporalMarker(0.5, ()-> {robotEncoded.openTopClaw();})
                                        .lineToConstantHeading(new Vector2d(42, 36)) // back up from bd
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