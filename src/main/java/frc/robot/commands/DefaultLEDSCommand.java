package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.leds.LEDSubsystem;

public class DefaultLEDSCommand extends Command {

    private final LEDSubsystem m_leds;

    public DefaultLEDSCommand(LEDSubsystem leds) {

        m_leds = leds;
        addRequirements(m_leds);

    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {

        m_leds.setLEDPattern(
            DriverStation.getAlliance().isPresent()
            ? DriverStation.getAlliance().get() == DriverStation.Alliance.Blue
                ? LEDPattern.solid(Color.kBlue)
                : LEDPattern.solid(Color.kRed)
            : LEDPattern.solid(Color.kBlack)
        );

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {}
    
}
