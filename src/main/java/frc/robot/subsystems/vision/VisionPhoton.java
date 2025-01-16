package frc.robot.subsystems.vision;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Transform3d;

public class VisionPhoton {

    private final PhotonCamera camera;
    private final String name;
    private final Transform3d robotToCam;
    private PhotonPoseEstimator odometry;
    public List<PhotonTrackedTarget> targets;

    /**
     * Class for setting up and using a PhotonVision camera
     * <p> Will be used in {@link frc.robot.subsystems.vision.VisionSubsystem.java} to have multiple cameras
     * 
     * @param name
     * @param robotToCam
     */
    public VisionPhoton(String name, Transform3d robotToCam) {

        this.name = name;
        this.robotToCam = robotToCam;
        camera = new PhotonCamera(name);

        odometry = new PhotonPoseEstimator(
            AprilTagFieldLayout.loadField(AprilTagFields.kDefaultField), 
            PhotonPoseEstimator.PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR,
            robotToCam
        );

        odometry.setMultiTagFallbackStrategy(PhotonPoseEstimator.PoseStrategy.LOWEST_AMBIGUITY);

    }

    public void updateVision() {

        var results = camera.getAllUnreadResults();

        
    }
    
}
