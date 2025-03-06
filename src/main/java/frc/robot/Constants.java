// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.config.ModuleConfig;
import com.pathplanner.lib.config.PIDConstants;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;
import com.pathplanner.lib.path.PathConstraints;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class DriveConstants {
    // Driving Parameters - Note that these are not the maximum capable speeds of
    // the robot, rather the allowed maximum speeds
    public static final double kMaxSpeedMetersPerSecond = 4.8;
    public static final double kMaxAngularSpeed = 2 * Math.PI; // radians per second

    // Chassis configuration
    public static final double kTrackWidth = Units.inchesToMeters(29.5);
    // Distance between centers of right and left wheels on robot
    public static final double kWheelBase = Units.inchesToMeters(29.5);
    // Distance between front and back wheels on robot
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
        new Translation2d(kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

    // Angular offsets of the modules relative to the chassis in radians
    public static final double kFrontLeftChassisAngularOffset = -Math.PI / 2;
    public static final double kFrontRightChassisAngularOffset = 0;
    public static final double kBackLeftChassisAngularOffset = Math.PI;
    public static final double kBackRightChassisAngularOffset = Math.PI / 2;

    // SPARK MAX CAN IDs
    public static final int kFrontLeftDrivingCanId = 11;
    public static final int kRearLeftDrivingCanId = 31;
    public static final int kFrontRightDrivingCanId = 21;
    public static final int kRearRightDrivingCanId = 41;

    public static final int kFrontLeftTurningCanId = 12;
    public static final int kRearLeftTurningCanId = 32;
    public static final int kFrontRightTurningCanId = 22;
    public static final int kRearRightTurningCanId = 42;

    public static final boolean kGyroReversed = false;

    public static final double kEstimationCoefficient = 0.025;

    public static final ModuleConfig moduleConfig = new ModuleConfig(
      ModuleConstants.kWheelDiameterMeters / 2, 
      kMaxSpeedMetersPerSecond, 
      1, // How much force is lost to friction 
      new DCMotor(
        12, 
        3.6, 
        211, 
        3.6, 
        6784, 
        1
      ), 
      ModuleConstants.kDrivingMotorReduction, 
      50, 
      1
    );

    public static final RobotConfig robotConfig = new RobotConfig(
      Units.lbsToKilograms(132.75), 
      16.914,
      moduleConfig, 
      new Translation2d[] {
        new Translation2d(kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, -kTrackWidth / 2)
      }
    );

    public static final PPHolonomicDriveController ppDriveController = new PPHolonomicDriveController(
      new PIDConstants(40, 0.0, 1), 
      new PIDConstants(14.0, 0.0, 2.5)
    );

    public static final PathConstraints constraints = new PathConstraints(
      AutoConstants.kMaxSpeedMetersPerSecond, 
      AutoConstants.kMaxAccelerationMetersPerSecondSquared, 
      AutoConstants.kMaxAngularSpeedRadiansPerSecond, 
      AutoConstants.kMaxAngularSpeedRadiansPerSecondSquared
    );
  }

  public static final class ModuleConstants {
    // The MAXSwerve module can be configured with one of three pinion gears: 12T,
    // 13T, or 14T. This changes the drive speed of the module (a pinion gear with
    // more teeth will result in a robot that drives faster).
    public static final int kDrivingMotorPinionTeeth = 14;

    // Calculations required for driving motor conversion factors and feed forward
    public static final double kDrivingMotorFreeSpeedRps = NeoMotorConstants.kFreeSpeedRpm / 60;
    public static final double kWheelDiameterMeters = 0.0762;
    public static final double kWheelCircumferenceMeters = kWheelDiameterMeters * Math.PI;
    // 45 teeth on the wheel's bevel gear, 22 teeth on the first-stage spur gear, 15
    // teeth on the bevel pinion
    public static final double kDrivingMotorReduction = (45.0 * 22) / (kDrivingMotorPinionTeeth * 15);
    public static final double kDriveWheelFreeSpeedRps = (kDrivingMotorFreeSpeedRps * kWheelCircumferenceMeters)
        / kDrivingMotorReduction;
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    public static final double kDriveDeadband = 0.1;
  }

  public static final class AutoConstants {
    public static final double kMaxSpeedMetersPerSecond = 3;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

    public static final double kPXController = 1;
    public static final double kPYController = 1;
    public static final double kPThetaController = 1;

    // Constraint for the motion profiled robot angle controller
    public static final TrapezoidProfile.Constraints kThetaControllerConstraints = new TrapezoidProfile.Constraints(
        kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
  }

  public static final class NeoMotorConstants {
    public static final double kFreeSpeedRpm = 6704;
  }

  public static final class ElevatorConstants {

    // From bottom of elevator structure to bottom of second stage
    // If testing doesn't work with these numbers, subtract 4.75 from all position measurements
    public static final double minPos = 0;
    public static final double maxPos = 43.5;

    public static final double max_output = 0.7; // Must be between 0 and 1 inclusive
    public static final double rotationsPerInch = 0.745; // Number needs testing. Factors in gearbox
    public static final double posTolerance = 0.5; // Half an inch of tolerance

    public static final double maxVelocity = 30; // Inches per second
    public static final double maxAcceleration = 60; // Inches per second squared

    // TODO: Change these numbers when we can measure the elevator on the actual robot
    public static final Distance L2_ALGAE = edu.wpi.first.units.Units.Inches.of(21);
    public static final Distance L3_ALGAE = edu.wpi.first.units.Units.Inches.of(36);
    // L1 is min pos
    public static final Distance L2_CORAL = edu.wpi.first.units.Units.Inches.of(9);
    public static final Distance L3_CORAL = edu.wpi.first.units.Units.Inches.of(25.5);
    public static final Distance L4_CORAL = edu.wpi.first.units.Units.Inches.of(48.5);
    public static final Distance CORAL_STATION = edu.wpi.first.units.Units.Inches.of(0);

    public static final Distance HOME = edu.wpi.first.units.Units.Inches.of(0);
    
    public static final int primaryElevatorID = 2;
    public static final int followerElevatorID = 1;
    public static final int limitSwitchPort = 7;

    // I think we need to use SysID tuning to figure out kS and kV and kG?????
    // TODO: Tune PID :(
    public static final double kS = 0;
    public static final double kV = 0;
    public static final double kG = 0;
    public static final double kP = 0.3;
    public static final double kI = 0;
    public static final double kD = 0.005;

  }

  public static final class AlgaeConstants {

    public static final int flipMotorID = 3;
    public static final int rightMotorID = 4;
    public static final int leftMotorID = 5;

    public static final double kP = 0.1;
    public static final double kI = 0;
    public static final double kD = 0;

    public static final Angle kAngleUp = edu.wpi.first.units.Units.Degrees.of(90);
    public static final Angle kAngleDown = edu.wpi.first.units.Units.Degrees.of(0);
  
  }

  public static final class CoralConstants {

    public static final int flipMotorID = 6;
    public static final int wheelMotorID = 7;

    public static final double kP = 0.1;
    public static final double kI = 0;
    public static final double kD = 0;

    // Yes the whole import statement has to be there bc of the other units class colliding with it
    public static final Angle kIdleAngle = edu.wpi.first.units.Units.Degrees.of(10);
    public static final Angle kScoreLow = edu.wpi.first.units.Units.Degrees.of(100);
    public static final Angle kScoreHigh = edu.wpi.first.units.Units.Degrees.of(135);
    public static final Angle kCoralStation = edu.wpi.first.units.Units.Degrees.of(35);

  }

  public static final class RobotToCamTransforms {
    public static final Transform3d kCam1Transform = new Transform3d(
      new Translation3d(Units.inchesToMeters(14.5), 0, Units.inchesToMeters(10.5)), 
      new Rotation3d(0, 0, 0)
    );

    public static final Transform3d kCam2Transform = new Transform3d(
      new Translation3d(Units.inchesToMeters(-14.5), Units.inchesToMeters(-3.25), Units.inchesToMeters(10.5)),
      new Rotation3d(0, Units.degreesToRadians(-30), Units.degreesToRadians(180))
    );
  }
  
}
