package frc.robot.subsystems.elevator;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {

    private final TalonFX leftMotor;
    private final TalonFX rightMotor;
    private final PIDController pidController;

    private ElevatorPosition targetPos = ElevatorPosition.LEVEL1;

    public ElevatorSubsystem() {

        leftMotor = new TalonFX(1);
        rightMotor = new TalonFX(2);

        rightMotor.setControl(new Follower(1, true));

        leftMotor.setNeutralMode(NeutralModeValue.Brake);
        rightMotor.setNeutralMode(NeutralModeValue.Brake);

        pidController = new PIDController(0, 0, 0);
        pidController.setTolerance(0.5);

    }

    public enum ElevatorPosition {
        LEVEL1,
        LEVEL2,
        LEVEL3,
        LEVEL4,
        CORAL
    }
    
}
