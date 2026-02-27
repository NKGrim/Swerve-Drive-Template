package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.SwerveConstants;

import java.util.List;

public class SwerveController {
    public SwerveConstants swerveConstants = new SwerveConstants();
    public List<SwerveModule> swervePods;
    public SwerveController(HardwareMap hardwareMap, Telemetry telemetry)
    {
        swervePods = List.of(new SwerveModule(hardwareMap, swerveConstants.frontLeftPodName), new SwerveModule(hardwareMap, swerveConstants.frontRightPodName), new SwerveModule(hardwareMap, swerveConstants.backLeftPodName), new SwerveModule(hardwareMap, swerveConstants.backRightPodName));
    }
    public void moveByVector(Vector2 vector)
    {
        for(SwerveModule swerve : swervePods)
        {
            swerve.setAngle(swerve.getDesiredAngle(vector.x, vector.y, 0));
            swerve.setPower(swerve.getDesiredPower(vector.x, vector.y, 0));
        }
    }
    public void rotateToAngle(double currentAngle, double desiredAngle)
    {
        int dir;
        if(currentAngle < desiredAngle)
        {
            dir = 1;
            for(SwerveModule swerve : swervePods)
            {
                swerve.setAngle(swerve.getDesiredAngle(0,0,1));
            }
        }
        else
        {
            dir = -1;
            for(SwerveModule swerve : swervePods)
            {
                swerve.setAngle(swerve.getDesiredAngle(0,0,-1));
            }
        }
        for(SwerveModule swerve : swervePods) swerve.setPower(swerve.getDesiredPower(0,0, dir));
    }
    public void moveToPose(Pose2D currentPose, Pose2D desiredPose)
    {
        Vector2 moveVector = desiredPose.getVector2().subtract(currentPose.getVector2());
        double turnDir;
        if(currentPose.angle < desiredPose.angle) turnDir=0.4f;
        else turnDir=-0.4f;

        for(SwerveModule swerve : swervePods)
        {
            swerve.setAngle(swerve.getDesiredAngle(moveVector.x, moveVector.y, turnDir));
            swerve.setPower(swerve.getDesiredPower(moveVector.x, moveVector.y, turnDir));
        }
    }
}
