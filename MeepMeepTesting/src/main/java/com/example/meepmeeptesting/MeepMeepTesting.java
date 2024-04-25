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
<<<<<<< HEAD

                                drive.trajectorySequenceBuilder(new Pose2d(-30, 70, Math.toRadians(270))) // 20 seconds

                                        .lineToConstantHeading(new Vector2d(-46, 38)) // to right spikemark
                                        //    .splineToLinearHeading(new Pose2d(-38,58.5), Math.toRadians(300))
                      .lineToConstantHeading(new Vector2d(42, 58.5)) // fly under truss
                                        .splineToLinearHeading(new Pose2d(52,42), Math.toRadians(0)) // to bd
                                        // //                       .addDisplacementMarker(()-> {encoded.armtoLowSetLine();})
////                       .addDisplacementMarker(()-> {encoded.openTopClaw();})
////                       .addDisplacementMarker(()-> {encoded.closeClaw();})
                                        .lineToConstantHeading(new Vector2d(43, 36)) // back up from bd
                                           .splineToConstantHeading(new Vector2d(67,13), Math.toRadians(0)) // spline into park (RIGHT)
//                      //.splineToConstantHeading(new Vector2d(60,58.5), Math.toRadians(0)) // spline into park (LEFT)
=======
                        drive.trajectorySequenceBuilder(new Pose2d(-37, 61.5, Math.toRadians(270)))
                                .waitSeconds(1)
                                //.addTemporalMarker(0,()->{encoded.closeClaw();})
                                //.addTemporalMarker(0.5,()->{encoded.armtoGroundAuto();})
                                .lineToSplineHeading(new Pose2d(11.5,48,Math.toRadians(225)))
                                .waitSeconds(1)
                                //.addTemporalMarker(2,()->{encoded.openBottomClaw();})
                                //.addTemporalMarker(2.5,()->{encoded.armScoreAuto();})
                                //purple dropped
                                .lineToSplineHeading(new Pose2d(53.5,28,Math.toRadians(0)))
                                .waitSeconds(1.5)
                                //.addTemporalMarker(5.5,()->{encoded.openTopClaw();})
                                //yellow dropped
                                .back(5)
                                .lineTo(new Vector2d(50,58.5))
>>>>>>> master
                                .build()

                                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}