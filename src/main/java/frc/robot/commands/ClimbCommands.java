package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.climbers.ClimbersSubsystem;

public class ClimbCommands {

    private ClimbCommands() {}

    /** ClimbUp is the one that lifts the robot off the ground */
    public static Command ClimbUp(ClimbersSubsystem climbers) {
        return Commands.run(
            () -> climbers.setMotor(1), 
            climbers
        );
    }

    /** ClimbDown is the one that puts the robot back on the ground */
    public static Command ClimbDown(ClimbersSubsystem climbers) {
        return Commands.run(
            () -> climbers.setMotor(-1), 
            climbers
        );
    }
    
}
