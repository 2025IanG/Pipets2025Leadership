package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.drive.DriveSubsystem;

public class DriveCommands {

    private DriveCommands() {}

    public static Command SlideLeft(DriveSubsystem drive) {
        return Commands.run(
            () -> drive.driveRobotRelative(
                new ChassisSpeeds(0, 0.25, 0)
            ), 
            drive
        );
    }

    public static Command SlideRight(DriveSubsystem drive) {
        return Commands.run(
            () -> drive.driveRobotRelative(
                new ChassisSpeeds(0, -0.25, 0)
            ), 
            drive
        );
    }
    
    public static Command DefaultDrive(DriveSubsystem drive, XboxController controller) {
        return Commands.run(
            () -> drive.drive(
                -MathUtil.applyDeadband(controller.getLeftY() * 0.9, OIConstants.kDriveDeadband), 
                -MathUtil.applyDeadband(controller.getLeftX() * 0.9, OIConstants.kDriveDeadband), 
                -MathUtil.applyDeadband(controller.getRightX() * 0.9, OIConstants.kDriveDeadband),
                true
            ), 
            drive
        );
    }
    
}
