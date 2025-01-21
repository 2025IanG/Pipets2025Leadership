package frc.robot.subsystems.vision;

import java.util.List;
import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Transform3d;

public class BulldogPhotonCamera {

    private final PhotonCamera camera;
    private final String name;
    private final Transform3d robotToCam;
    private PhotonPoseEstimator odometry;
    public List<PhotonTrackedTarget> targets;
    public double camEstimatedX;
    public double camEstimatedY;
    public double camEstimatedAngle;
    public double timestamp;


    /**
     * Class for setting up and using a PhotonVision camera.
     * <p>
     * Includes stuff for pose estimation with the camera, that will later be fused with the drive odometry
     * 
     * @param name
     * @param robotToCam
     */
    public BulldogPhotonCamera(String name, Transform3d robotToCam) {

        this.name = name;
        this.robotToCam = robotToCam;
        camera = new PhotonCamera(name);

        odometry = new PhotonPoseEstimator(
            AprilTagFieldLayout.loadField(AprilTagFields.kDefaultField), 
            PhotonPoseEstimator.PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR,
            this.robotToCam
        );

        odometry.setMultiTagFallbackStrategy(PhotonPoseEstimator.PoseStrategy.LOWEST_AMBIGUITY);

    }

    public void updateVision() {

        var results = camera.getAllUnreadResults();
        var result = results.get(results.size() - 1);
        Optional<EstimatedRobotPose> cameraPose = odometry.update(result);

        if (result.hasTargets() && cameraPose.isPresent()) {
            targets = cameraPose.get().targetsUsed;
            
            camEstimatedX = cameraPose.get().estimatedPose.getX();
            camEstimatedY = cameraPose.get().estimatedPose.getY();
            camEstimatedAngle = cameraPose.get().estimatedPose.getRotation().getAngle();
            timestamp = cameraPose.get().timestampSeconds;
        }
        
    }

    public String getName() {
        return name;
    }

    public void setReferencePose(Pose2d pose) {
        odometry.setReferencePose(pose);
    }
    
}
