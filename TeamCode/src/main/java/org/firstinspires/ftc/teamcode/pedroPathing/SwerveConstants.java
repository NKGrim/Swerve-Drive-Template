package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.geometry.Pose;
import com.pedropathing.math.Vector;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class SwerveConstants {
    public  double xVelocity = 81.34056;
    /** The Lateral Velocity of the Robot - Different for each robot
     *  Default Value: 65.43028 */
    public  double yVelocity = 65.43028;

    private  double[] convertToPolar = Pose.cartesianToPolar(xVelocity, -yVelocity);
    
    public String frontLeftPodName = "frontLeft";
    public String frontRightPodName = "frontRight";
    public String backLeftPodName = "backLeft";
    public String backRightPodName = "backRight";

    public  DcMotorSimple.Direction frontLeftDirection = DcMotorSimple.Direction.FORWARD;
    public  DcMotorSimple.Direction frontRightDirection = DcMotorSimple.Direction.FORWARD;
    public  DcMotorSimple.Direction backLeftDirection = DcMotorSimple.Direction.FORWARD;
    public  DcMotorSimple.Direction backRightDirection = DcMotorSimple.Direction.FORWARD;

    public  boolean useVoltageCompensation = false;
    public  double nominalVoltage = 12.0;
    public  double staticFrictionCoefficient = 0.1;

    public  double maxPower = 1;
    public  double motorCachingThreshold = 0.01;
    public  boolean useBrakeModeInTeleOp = false;

    public SwerveConstants() {
        defaults();
    }

    public SwerveConstants xVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
        return this;
    }

    public SwerveConstants yVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
        return this;
    }

    public SwerveConstants maxPower(double maxPower) {
        this.maxPower = maxPower;
        return this;
    }

    public SwerveConstants leftFrontPodName(String leftFrontPodName) {
        this.frontLeftPodName = leftFrontPodName;
        return this;
    }

    public SwerveConstants leftRearPodName(String leftRearPodName) {
        this.backLeftPodName = leftRearPodName;
        return this;
    }

    public SwerveConstants rightFrontPodName(String rightFrontPodName) {
        this.frontRightPodName = rightFrontPodName;
        return this;
    }

    public SwerveConstants rightRearPodName(String rightRearPodName) {
        this.backRightPodName = rightRearPodName;
        return this;
    }

    public SwerveConstants leftFrontMotorDirection(DcMotorSimple.Direction leftFrontMotorDirection) {
        this.frontLeftDirection = leftFrontMotorDirection;
        return this;
    }

    public SwerveConstants leftRearMotorDirection(DcMotorSimple.Direction leftRearMotorDirection) {
        this.backLeftDirection = leftRearMotorDirection;
        return this;
    }

    public SwerveConstants rightFrontMotorDirection(DcMotorSimple.Direction rightFrontMotorDirection) {
        this.frontRightDirection = rightFrontMotorDirection;
        return this;
    }

    public SwerveConstants rightRearMotorDirection(DcMotorSimple.Direction rightRearMotorDirection) {
        this.backRightDirection = rightRearMotorDirection;
        return this;
    }

    public SwerveConstants motorCachingThreshold(double motorCachingThreshold) {
        this.motorCachingThreshold = motorCachingThreshold;
        return this;
    }

    public SwerveConstants useBrakeModeInTeleOp(boolean useBrakeModeInTeleOp) {
        this.useBrakeModeInTeleOp = useBrakeModeInTeleOp;
        return this;
    }

    public SwerveConstants useVoltageCompensation(boolean useVoltageCompensation) {
        this.useVoltageCompensation = useVoltageCompensation;
        return this;
    }

    public SwerveConstants nominalVoltage(double nominalVoltage) {
        this.nominalVoltage = nominalVoltage;
        return this;
    }

    public SwerveConstants staticFrictionCoefficient(double staticFrictionCoefficient) {
        this.staticFrictionCoefficient = staticFrictionCoefficient;
        return this;
    }

    public double getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public double getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(double maxPower) {
        this.maxPower = maxPower;
    }

    public String getFrontLeftPodName() {
        return frontLeftPodName;
    }

    public void setFrontLeftPodName(String leftFrontMotorName) {
        this.frontLeftPodName = leftFrontMotorName;
    }

    public String getBackLeftPodName() {
        return backLeftPodName;
    }

    public void setBackLeftPodName(String leftRearMotorName) {
        this.backLeftPodName = leftRearMotorName;
    }

    public String getFrontRightPodName() {
        return frontRightPodName;
    }

    public void setFrontRightPodName(String rightFrontMotorName) {
        this.frontRightPodName = rightFrontMotorName;
    }

    public String getBackRightPodName() {
        return backRightPodName;
    }

    public void setBackRightPodName(String rightRearMotorName) {
        this.backRightPodName = rightRearMotorName;
    }

    public DcMotorSimple.Direction getFrontLeftPodDirection() {
        return frontLeftDirection;
    }

    public void setFrontLeftPodDirection(DcMotorSimple.Direction leftFrontMotorDirection) {
        this.frontLeftDirection = leftFrontMotorDirection;
    }

    public DcMotorSimple.Direction getbackLeftPodDirection() {
        return backLeftDirection;
    }

    public void setbackLeftPodDirection(DcMotorSimple.Direction leftRearMotorDirection) {
        this.backLeftDirection = leftRearMotorDirection;
    }

    public DcMotorSimple.Direction getFrontRightPodDirection() {
        return frontRightDirection;
    }

    public void setFrontRightPodDirection(DcMotorSimple.Direction rightFrontMotorDirection) {
        this.frontRightDirection = rightFrontMotorDirection;
    }

    public DcMotorSimple.Direction getbackRightPodDirection() {
        return backRightDirection;
    }

    public void setbackRightPodDirection(DcMotorSimple.Direction rightRearMotorDirection) {
        this.backRightDirection = rightRearMotorDirection;
    }

    public double getMotorCachingThreshold() {
        return motorCachingThreshold;
    }

    public void setMotorCachingThreshold(double motorCachingThreshold) {
        this.motorCachingThreshold = motorCachingThreshold;
    }

    public boolean isUseBrakeModeInTeleOp() {
        return useBrakeModeInTeleOp;
    }

    public void setUseBrakeModeInTeleOp(boolean useBrakeModeInTeleOp) {
        this.useBrakeModeInTeleOp = useBrakeModeInTeleOp;
    }

    /**
     * This method sets the default values for the SwerveConstants class.
     * It is called in the constructor of the SwerveConstants class.
     */
    public void defaults() {
        xVelocity = 81.34056;
        yVelocity = 65.43028;
        convertToPolar = Pose.cartesianToPolar(xVelocity, -yVelocity);
        maxPower = 1;
        frontLeftPodName = "frontLeft";
        backLeftPodName = "backRight";
        frontRightPodName = "frontRight";
        backRightPodName = "backRight";
        frontLeftDirection = DcMotorSimple.Direction.REVERSE;
        frontRightDirection = DcMotorSimple.Direction.REVERSE;
        backLeftDirection = DcMotorSimple.Direction.FORWARD;
        backRightDirection = DcMotorSimple.Direction.FORWARD;
        motorCachingThreshold = 0.01;
        useBrakeModeInTeleOp = false;
        useVoltageCompensation = false;
        nominalVoltage = 12.0;
        staticFrictionCoefficient = 0.1;
    }

}
