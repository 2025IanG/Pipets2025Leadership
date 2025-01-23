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
    private LEDPattern m_pattern;

    public LEDSubsystem() {

        m_led = new AddressableLED(9);
        m_ledBuffer = new AddressableLEDBuffer(60);

        m_led.setLength(m_ledBuffer.getLength());
        m_led.setData(m_ledBuffer);
        m_led.start();

    }

    public void setLEDPattern(LEDPattern pattern) {
        pattern.applyTo(m_ledBuffer);
    }
    
}
