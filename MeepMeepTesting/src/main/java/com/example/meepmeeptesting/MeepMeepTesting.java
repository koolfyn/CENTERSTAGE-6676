package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

       // Pose2d startPose = (new Pose2d(-35, 70, Math.toRadians(270)));
      //  drive.setPoseEstimate(startPose);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(46, 46, Math.toRadians(190), Math.toRadians(190), 13.8)
                .followTrajectorySequence(drive ->

                                drive.trajectorySequenceBuilder(new Pose2d(15, 74, Math.toRadians(270))) // 20 seconds
                                .lineToConstantHeading(new Vector2d(23,46)) // to spikemark
                                //purple down
//                                .addTemporalMarker(0,()-> {encoded.armtoGround();})
//                                .addTemporalMarker(0.5,()->{encoded.openBottomClaw();})
                                .lineToConstantHeading(new Vector2d(23, 55)) // back up
                                .splineToLinearHeading(new Pose2d(50,42), Math.toRadians(0)) // to bd
                                //yellow on bd
//                                .addTemporalMarker(0,()-> {encoded.armtoLowSetLine();})
//                                .addTemporalMarker(0.5, ()-> {encoded.openTopClaw();})
//                                .addTemporalMarker(0.5,()-> {encoded.closeClaw();})
                                .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
                                .lineToLinearHeading(new Pose2d(25, 11, Math.toRadians(180))) // middle + orientate
                                .lineToConstantHeading(new Vector2d(-55,11)) // to white stack
                                //pick up pixel stack
//                                .addTemporalMarker(0.5, ()-> {encoded.armtoPixelStack();})
//                                .addTemporalMarker(0.5, ()-> {encoded.closeClaw();})
                                        .lineToConstantHeading(new Vector2d(-50,11)) // backup
                                        .lineToLinearHeading(new Pose2d(-40, 11, Math.toRadians(0))) //  orientate
                                .lineToConstantHeading(new Vector2d(38,11)) // "safe spot"
                                .splineToConstantHeading(new Vector2d(50,42), Math.toRadians(0)) // to bd
//                                //drop pixel stack on bd
//                                .addTemporalMarker(0,()-> {encoded.armtoLowSetLine();})
//                                .addTemporalMarker(0.5, ()-> {encoded.openTopClaw();})
                                .lineToConstantHeading(new Vector2d(42, 42)) // back up from bd
                                .splineToConstantHeading(new Vector2d(60,10), Math.toRadians(0)) // spline into park (RIGHT)
                                //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
                                //if we have enough time
                                //.lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
                                //.lineToLinearHeading(new Pose2d(25, 11, Math.toRadians(180))) // middle + orientate
                                //.lineToConstantHeading(new Vector2d(-55,112e)) // to white stack
                                //  .waitSeconds(.5) // pick up stack
                                //  .lineToLinearHeading(new Pose2d(-40, 11, Math.toRadians(0))) // orientate
                                // .lineToConstantHeading(new Vector2d(38,11)) // // "safe spot"
                                // .splineToConstantHeading(new Vector2d(50,42), Math.toRadians(0)) // to bd
                                // .waitSeconds(.5) // place 2nd stack
                                // .lineToConstantHeading(new Vector2d(42, 42)) // back up from bd
                                .build()

                                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}