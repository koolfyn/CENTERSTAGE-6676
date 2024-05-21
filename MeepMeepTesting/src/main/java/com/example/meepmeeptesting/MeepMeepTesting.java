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
                        drive.trajectorySequenceBuilder(new Pose2d(-30, 70, Math.toRadians(270)))
//                                .addTemporalMarker(0,()-> {encoded.closeClaw();})
//                                .addTemporalMarker(0.09,()-> {encoded.armtoGroundAuto();})


                                .lineToConstantHeading(new Vector2d(-35, 51)) // to spikemark
//                                .addTemporalMarker(1,()-> {encoded.openBottomClaw();})
//                                .addTemporalMarker(1.1,()-> {encoded.armScoreAuto();})
                                .lineToLinearHeading(new Pose2d(-46, 65, Math.toRadians(0))) // orientate + line up for truss
                                .lineToConstantHeading(new Vector2d(42, 65)) // fly under truss
                                .splineToLinearHeading(new Pose2d(49.5,40), Math.toRadians(0)) // to bd
                                .waitSeconds(0.9)
//                                .addTemporalMarker(6.5,()-> {encoded.openTopClaw();})
                                //.lineToConstantHeading(new Vector2d(43, 36)) // back up from bd
                                .lineToLinearHeading(new Pose2d(42,42, Math.toRadians(182))) // back up & turn




                                .lineToConstantHeading(new Vector2d(42, 64)) // line up for truss
                                .lineToConstantHeading(new Vector2d(-42, 64)) // fly under truss
                                .lineToLinearHeading(new Pose2d(-49.8, 48.5, Math.toRadians(200))) // orientate + to stack
                                .lineToLinearHeading(new Pose2d(-53.8, 48.5, Math.toRadians(200))) // orientate + to stack
//                                .addTemporalMarker(9.4,()->{encoded.openBottomClaw();})
//                                .addTemporalMarker(9.4,()-> {encoded.openTopClaw();})
//                                .addTemporalMarker(9.6,()-> {encoded.armtoPixelStack();})
//                                .addTemporalMarker (14.5,()-> {encoded.closeClaw();})
//                                .addTemporalMarker(14.8,()-> {encoded.armScoreAuto();})
                                //  .lineToConstantHeading(new Vector2d(-42, 67)) // orientate + line up for truss
                                .lineToLinearHeading(new Pose2d(-42,64, Math.toRadians(180)))
                                .lineToConstantHeading(new Vector2d(42, 64)) // fly under truss
                                // .lineToConstantHeading(new Vector2d(42, 60)) // to not hit wall
                                //  .splineToLinearHeading(new Pose2d(49, 44), Math.toRadians(0)) // to bd
                                .lineToSplineHeading(new Pose2d(49.5,44, Math.toRadians(0))) // to bd
//                                .addTemporalMarker(20.4,()-> {encoded.openBottomClaw();})
//                                .addTemporalMarker(20.8,()-> {encoded.openTopClaw();})
                                .lineToConstantHeading(new Vector2d(42, 44)) // back up from bd
                                .splineToConstantHeading(new Vector2d(58,16), Math.toRadians(0)) // spline into park (RIGHT)
                                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}