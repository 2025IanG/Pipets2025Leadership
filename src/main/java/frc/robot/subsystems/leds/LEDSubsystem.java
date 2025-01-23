package frc.robot.subsystems.leds;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase {

    private final AddressableLED m_led;
    private final AddressableLEDBuffer m_ledBuffer;
    private LEDPattern m_pattern = LEDPattern.rainbow(255, 128)
        .scrollAtAbsoluteSpeed(MetersPerSecond.of(1), Meters.of(1 / 60.0));

    public LEDSubsystem() {

        m_led = new AddressableLED(9);
        m_ledBuffer = new AddressableLEDBuffer(60);

        m_led.setLength(m_ledBuffer.getLength());
        m_led.setData(m_ledBuffer);
        m_led.start();

    }

    @Override
    public void periodic() {

        m_pattern.applyTo(m_ledBuffer);
        m_led.setData(m_ledBuffer);

    }

        
    
}
