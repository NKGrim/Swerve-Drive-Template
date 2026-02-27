package org.firstinspires.ftc.teamcode;

public class Pose2D {
    public double x,y, angle;
    public Pose2D(double x, double y, double angle)
    {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
    Vector2 getVector2()
    {
        return new Vector2(x,y);
    }
}
