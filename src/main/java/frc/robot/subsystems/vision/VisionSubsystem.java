package frc.robot.subsystems.vision;

import java.util.ArrayList;
import java.util.List;

import org.photonvision.PhotonCamera;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {

    // TODO: Need to get camera name from the PhotonVision dashboard
    PhotonCamera[] cameras = {
        new PhotonCamera("")
    };

    List<Pose2d> camPoses = new ArrayList<Pose2d>();
    List<Double> camTimestamps = new ArrayList<Double>();

    public VisionSubsystem() {}

    @Override
    public void periodic() {
        camPoses.clear();
        camTimestamps.clear();

        for (PhotonCamera cam : cameras) {

            var results = cam.getAllUnreadResults();
            if (!results.isEmpty()) {
                
                var result = results.get(results.size() - 1);
                if (result.hasTargets()) {

                    

                }

            }
        }
    }
    
}
