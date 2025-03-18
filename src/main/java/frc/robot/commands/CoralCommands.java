package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.coral.CoralFlipperSubsystem;
import frc.robot.subsystems.coral.CoralWheelsSubsystem;
import frc.robot.Constants.CoralConstants;

public class CoralCommands {

    private CoralCommands() {}

    public static Command SetCoralFlipper(CoralFlipperSubsystem flipper, String pos) {
        return Commands.runOnce(
            () -> {
                switch (pos) {
                    case "idle" -> flipper.setPosition(CoralConstants.kIdleAngle);
                    case "scoreLow" -> flipper.setPosition(CoralConstants.kScoreLow);
                    case "scoreHigh" -> flipper.setPosition(CoralConstants.kScoreHigh);
                    case "coralStation" -> flipper.setPosition(CoralConstants.kCoralStation);
                    default -> flipper.setPosition(CoralConstants.kIdleAngle);
                }
            }, 
            flipper
        );
    }
    
    public static Command ZeroCoralFlipper(CoralFlipperSubsystem flipper) {
        return Commands.runEnd(
            () -> {}, 
            () -> {
                flipper.setNeutral();
                flipper.resetSensorPosition(CoralConstants.kIdleAngle);
            }, 
            flipper
        ).until(() -> flipper.getCurrentPosition() < 0.01 && flipper.getCurrentVelocity() == 0);
    }

    public static Command RaiseFlipperManual(CoralFlipperSubsystem flipper) {
        return Commands.runEnd(
            () -> flipper.setCoralFlipperManual(-0.1), 
            () -> flipper.setNeutral(), 
            flipper
        );
    }

    public static Command LowerFlipperManual(CoralFlipperSubsystem flipper) {
        return Commands.runEnd(
            () -> flipper.setCoralFlipperManual(0.1), 
            () -> flipper.setNeutral(), 
            flipper
        );
    }

    public static Command IntakeCoral(CoralWheelsSubsystem wheels) {
        return Commands.runEnd(
            () -> wheels.setWheelMotors(-0.5), 
            () -> wheels.stopWheelMotors(), 
            wheels
        );
    }

    public static Command ExtakeCoral(CoralWheelsSubsystem wheels) {
        return Commands.runEnd(
            () -> wheels.setWheelMotors(0.5), 
            () -> wheels.stopWheelMotors(), 
            wheels
        );
    }

    public static Command DefaultWheels(CoralWheelsSubsystem wheels) {
        return Commands.run(
            () -> wheels.setWheelMotors(-0.05), 
            wheels
        );
    }
    
}
