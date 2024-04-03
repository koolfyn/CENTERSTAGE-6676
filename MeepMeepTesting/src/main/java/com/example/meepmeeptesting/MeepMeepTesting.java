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

                        drive.trajectorySequenceBuilder((new Pose2d(15, 70, Math.toRadians(270))))
//                                .addTemporalMarker(0,()-> {encoded.closeClaw();})
                                .lineToConstantHeading(new Vector2d(23,35)) // to spikemark
//                                .addDisplacementMarker(()-> {encoded.armtoGround();})
//                                .addDisplacementMarker(()->{encoded.openBottomClaw();})
                                .lineToConstantHeading(new Vector2d(23, 40)) // back up
                                .splineToLinearHeading(new Pose2d(50,42), Math.toRadians(0)) // to bd
//                                .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
//                                .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                                .addDisplacementMarker(()-> {encoded.closeClaw();})
                                .lineToLinearHeading(new Pose2d(38,11)) // safely move from bd
                                .lineToLinearHeading(new Pose2d(25, 11, Math.toRadians(180))) // middle + orientate
                                .lineToConstantHeading(new Vector2d(-55,11)) // to white stack
//                                .addDisplacementMarker(()->{encoded.openBottomClaw();})
//                                .addDisplacementMarker(()-> {encoded.openTopClaw();})
//                                .addDisplacementMarker(()-> {encoded.armtoPixelStack();})
//                                .addDisplacementMarker (()-> {encoded.closeClaw();})
                                .lineToLinearHeading(new Pose2d(-40, 11, Math.toRadians(0))) //  orientate
                                .lineToConstantHeading(new Vector2d(38,11)) // "safe spot"
                                .splineToConstantHeading(new Vector2d(50,42), Math.toRadians(0)) // to bd
//                                .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
//                                .addDisplacementMarker(()-> {encoded.openTopClaw();})
                                .lineToConstantHeading(new Vector2d(42, 42)) // back up from bd
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