package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.RobotContainer.ElevatorLevel;
import frc.robot.subsystems.elevator.ElevatorSubsystem;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorCommands {

    private ElevatorCommands() {}

    public static Command SetElevator(ElevatorSubsystem elevator, ElevatorLevel level) {
        return Commands.runOnce(
            () -> {
                switch (level) {
                    case LEVEL_4_CORAL -> elevator.setPosition(ElevatorConstants.L4_CORAL);
                    case LEVEL_3_CORAL -> elevator.setPosition(ElevatorConstants.L3_CORAL);
                    case LEVEL_2_CORAL -> elevator.setPosition(ElevatorConstants.L2_CORAL);
                    case LEVEL_3_ALGAE -> elevator.setPosition(ElevatorConstants.L3_ALGAE);
                    case LEVEL_2_ALGAE -> elevator.setPosition(ElevatorConstants.L2_ALGAE);
                    case CORAL_STATION -> elevator.setPosition(ElevatorConstants.CORAL_STATION);
                    case HOME -> elevator.setPosition(ElevatorConstants.HOME);
                    default -> elevator.setPosition(ElevatorConstants.HOME);
                }
            }, 
            elevator
        );
    }

    public static Command ZeroElevator(ElevatorSubsystem elevator) {
        return Commands.runEnd(
            () -> {}, 
            () -> {
                elevator.setNeutral();
                elevator.resetSensorPosition(ElevatorConstants.HOME);
            }, 
            elevator
        ).until(() -> elevator.getCurrentPosition() < 1.5 && elevator.getCurrentVelocity() == 0);
    }

    public static Command RaiseElevatorManual(ElevatorSubsystem elevator) {
        return Commands.runEnd(
            () -> elevator.setElevatorManual(0.2), 
            () -> elevator.setNeutral(), 
            elevator
        );
    }

    public static Command LowerElevatorManual(ElevatorSubsystem elevator) {
        return Commands.runEnd(
            () -> elevator.setElevatorManual(-0.1), 
            () -> elevator.setNeutral(), 
            elevator
        );
    }
    
}
