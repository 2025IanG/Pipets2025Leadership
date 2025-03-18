package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.AlgaeConstants;
import frc.robot.subsystems.algae.AlgaeFlipperSubsystem;
import frc.robot.subsystems.algae.AlgaeWheelsSubsystem;

public class AlgaeCommands {

    private AlgaeCommands() {}

    public static Command SetAlgaeFlipper(AlgaeFlipperSubsystem flipper, String pos) {
        return Commands.runOnce(
            () -> {
                switch(pos) {
                    case "up" -> flipper.setPosition(AlgaeConstants.kAngleUp);
                    case "down" -> flipper.setPosition(AlgaeConstants.kAngleDown);
                    default -> flipper.setPosition(AlgaeConstants.kAngleDown);
                }
            },
            flipper
        );
    }
    
    public static Command ZeroAlgaeFlipper(AlgaeFlipperSubsystem flipper) {
        return Commands.runEnd(
            () -> {}, 
            () -> {
                flipper.setNeutral();
                flipper.resetSensorPosition(AlgaeConstants.kAngleDown);
            }, 
            flipper
        ).until(() -> flipper.getCurrentPosition() > -0.05 && flipper.getCurrentVelocity() == 0);
    }

    public static Command RaiseFlipperManual(AlgaeFlipperSubsystem flipper) {
        return Commands.runEnd(
            () -> flipper.setAlgaeFlipperManual(-0.1), 
            () -> flipper.setNeutral(), 
            flipper
        );
    }

    public static Command LowerFlipperManual(AlgaeFlipperSubsystem flipper) {
        return Commands.runEnd(
            () -> flipper.setAlgaeFlipperManual(0.1), 
            () -> flipper.setNeutral(), 
            flipper
        );
    }

    public static Command IntakeAlgae(AlgaeWheelsSubsystem wheels) {
        return Commands.runEnd(
            () -> wheels.setWheelMotors(-0.5), 
            () -> wheels.stopWheelMotors(), 
            wheels
        );
    }

    public static Command ExtakeAlgae(AlgaeWheelsSubsystem wheels) {
        return Commands.runEnd(
            () -> wheels.setWheelMotors(0.5), 
            () -> wheels.stopWheelMotors(), 
            wheels
        );
    }
    
}
