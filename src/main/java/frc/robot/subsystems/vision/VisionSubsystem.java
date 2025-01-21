package frc.robot.subsystems.vision;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RobotToCamTransforms;

public class VisionSubsystem extends SubsystemBase {

    BulldogPhotonCamera[] cameras = {
        new BulldogPhotonCamera("BulldogCam1", RobotToCamTransforms.PHOTON_CAM_POSE)
    };

    List<Pose2d> camPoses = new ArrayList<Pose2d>();
    List<Double> camTimestamps = new ArrayList<Double>();

    public VisionSubsystem() {}

    @Override
    public void periodic() {

        camPoses.clear();
        camTimestamps.clear();

        for (int i = 0; i < cameras.length; i++) {

            cameras[i].updateVision();
            processCamera(i);

        }

    }

    public void processCamera(int camNum) {

        camPoses.add(
            new Pose2d(
                cameras[camNum].camEstimatedX,
                cameras[camNum].camEstimatedY,
                new Rotation2d(cameras[camNum].camEstimatedAngle)
            )
        );

        camTimestamps.add(cameras[camNum].timestamp);

    }

    public List<Pose2d> getCameraPoses() {
        return camPoses;
    }

    public List<Double> getCameraTimestamps() {
        return camTimestamps;
    }
    
}
