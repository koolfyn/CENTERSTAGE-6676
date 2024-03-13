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

                        drive.trajectorySequenceBuilder((new Pose2d(-35, 70, Math.toRadians(270))))
                             //   .addTemporalMarker(0,()-> {encoded.closeClaw();})
                       .lineToConstantHeading(new Vector2d(-40, 50)) // positioning
                        .lineToLinearHeading(new Pose2d(-37, 39, Math.toRadians(0))) // orientation
                        .lineToConstantHeading(new Vector2d(-35, 39)) // slow push to spikemark
//                       .addDisplacementMarker(()-> {encoded.armtoGroundAuto();})
//                       .addDisplacementMarker(()->{encoded.openBottomClaw();})
                        .lineToConstantHeading(new Vector2d(-38, 39)) // safe backup
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 35.5), Math.toRadians(0)) // to bd
//                       .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
//                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                       .addDisplacementMarker(()-> {encoded.closeClaw();})
                        // if we have should
                        .lineToConstantHeading(new Vector2d(42, 35.5)) // back up from bd
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // line up for truss
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // fly under truss
                        .lineToLinearHeading(new Pose2d(-47, 35, Math.toRadians(180))) // orientate + to stack
                        .lineToLinearHeading(new Pose2d(-55, 35, Math.toRadians(180))) // orientate + to stack
//                       .addDisplacementMarker(()->{encoded.openBottomClaw();})
//                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                       .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
//                       .addDisplacementMarker (()-> {encoded.closeClaw();})
                        .lineToConstantHeading(new Vector2d(-50, 35))
                        .lineToConstantHeading(new Vector2d(-42, 58.5)) // orientate + line up for truss
                        .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                        .splineToLinearHeading(new Pose2d(50, 35.5), Math.toRadians(0)) // to bd
//                       .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
//                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
                        .lineToConstantHeading(new Vector2d(42, 35.5)) // back up from bd
                        .splineToConstantHeading(new Vector2d(60,9), Math.toRadians(0)) // spline into park (RIGHT)
//                        //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)

                                .build()
                );




        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}