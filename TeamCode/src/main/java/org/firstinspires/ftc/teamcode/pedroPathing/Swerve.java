package org.firstinspires.ftc.teamcode.pedroPathing;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.pedropathing.Drivetrain;
import com.pedropathing.math.Vector;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.SwerveModule;

import java.util.ArrayList;
import java.util.List;

public class Swerve extends Drivetrain {
    public SwerveConstants constants;
    private SwerveModule frontLeftSwerve;
    private SwerveModule frontRightSwerve;
    private SwerveModule backLeftSwerve;
    private SwerveModule backRightSwerve;
    private List<SwerveModule> swerves;
    private VoltageSensor voltageSensor;
    private double motorCachingThreshold;
    private boolean useBrakeModeInTeleOp;
    private double staticFrictionCoefficient;

    public Swerve(HardwareMap hardwareMap, SwerveConstants swerveConstants)
    {
        this.constants = swerveConstants;

        SwerveModule frontLeftSwerve = new SwerveModule(hardwareMap, swerveConstants.frontLeftPodName);
        SwerveModule frontRightSwerve = new SwerveModule(hardwareMap, swerveConstants.frontRightPodName);
        SwerveModule backLeftSwerve = new SwerveModule(hardwareMap, swerveConstants.backLeftPodName);
        SwerveModule backRightSwerve = new SwerveModule(hardwareMap, swerveConstants.backRightPodName);
        swerves = List.of(frontLeftSwerve,frontRightSwerve,backLeftSwerve,backRightSwerve);

        this.maxPowerScaling = swerveConstants.maxPower;
        this.motorCachingThreshold = swerveConstants.motorCachingThreshold;
        this.useBrakeModeInTeleOp = swerveConstants.useBrakeModeInTeleOp;

        voltageSensor = hardwareMap.voltageSensor.iterator().next();

        setMotorsToFloat();
    }

    public void updateConstants() {
        frontLeftSwerve.setDirection(constants.frontLeftDirection);
        backLeftSwerve.setDirection(constants.backLeftDirection);
        frontRightSwerve.setDirection(constants.frontRightDirection);
        backRightSwerve.setDirection(constants.backRightDirection);
        this.motorCachingThreshold = constants.motorCachingThreshold;
        this.useBrakeModeInTeleOp = constants.useBrakeModeInTeleOp;
        this.voltageCompensation = constants.useVoltageCompensation;
        this.nominalVoltage = constants.nominalVoltage;
        this.staticFrictionCoefficient = constants.staticFrictionCoefficient;
    }

    public double[] calculateDrive(Vector correctivePower, Vector headingPower, Vector pathingPower, double robotHeading)
    {
        Vector totalTranslation = pathingPower.plus(correctivePower);
        double cos = Math.cos(robotHeading);
        double sin = Math.sin(robotHeading);

        double vx = totalTranslation.getXComponent()*cos + totalTranslation.getYComponent()*sin;
        double vy = -totalTranslation.getXComponent()*sin + totalTranslation.getYComponent()*cos;

        double o = headingPower.getMagnitude();

        double[] drivePowers = new double[4];
        int iter = 0;
        for(SwerveModule swerve : swerves)
        {
            double Vx_i = vx-o*swerve.pos.y;
            double Vy_i = vy+o*swerve.pos.x;

            swerve.setAngle(Math.atan2(Vy_i, Vx_i));
            drivePowers[iter] = (Math.hypot(Vx_i, Vy_i));
            iter++;
        }
        double max = Math.max(Math.max(drivePowers[0], drivePowers[1]), Math.max(drivePowers[2], drivePowers[3]));
        if(max > 1)
        {
            for(int i = 0; i < 4; i++)
            {
                drivePowers[i] =drivePowers[i]/max;
            }
        }

        return drivePowers;
    }

    public void runDrive(double[] drivePowers) {
        for (int i = 0; i < swerves.size(); i++) {
            if(drivePowers[i] > motorCachingThreshold)
            {
                swerves.get(i).setPower(drivePowers[i]);
            }
        }
    }

    public void startTeleopDrive() {
        if (useBrakeModeInTeleOp) {
            setMotorsToBrake();
        }
    }
    @Override
    public void startTeleopDrive(boolean brakeMode) {
        if (brakeMode) {
            setMotorsToBrake();
        } else {
            setMotorsToFloat();
        }
    }

    private void setMotorsToBrake() {
        for (SwerveModule swerve : swerves) {
            swerve.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    /**
     * This sets the motors to the zero power behavior of float.
     */
    private void setMotorsToFloat() {
        for (SwerveModule swerve : swerves) {
            swerve.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }
    public void breakFollowing() {
        for (SwerveModule swerve : swerves) {
            swerve.motor.setPower(0);
        }
        setMotorsToFloat();
    }

    public double xVelocity() {
        return constants.xVelocity;
    }

    public double yVelocity() {
        return constants.yVelocity;
    }

    public void setXVelocity(double xMovement) { constants.setXVelocity(xMovement); }
    public void setYVelocity(double yMovement) { constants.setYVelocity(yMovement); }

    public double getStaticFrictionCoefficient() {
        return staticFrictionCoefficient;
    }

    @Override
    public double getVoltage() {
        return voltageSensor.getVoltage();
    }

    private double getVoltageNormalized() {
        double voltage = getVoltage();
        return (nominalVoltage - (nominalVoltage * staticFrictionCoefficient)) / (voltage - ((nominalVoltage * nominalVoltage / voltage) * staticFrictionCoefficient));
    }

    public String debugString() {
        return "Swerve{" +
                " leftFront=" + frontLeftSwerve +
                ", leftRear=" + backRightSwerve +
                ", rightFront=" + frontRightSwerve +
                ", rightRear=" + backRightSwerve +
                ", motorCachingThreshold=" + motorCachingThreshold +
                ", useBrakeModeInTeleOp=" + useBrakeModeInTeleOp +
                '}';
    }

    public List<SwerveModule> getSwerves() {
        return swerves;
    }
}
