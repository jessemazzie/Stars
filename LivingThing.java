import javax.swing.*;
import java.awt.*;

public abstract class LivingThing {
    int currentXPosition;
    int currentYPosition;
    int xSpeed;
    int ySpeed;
    double orientationAngle; //in radians
    double angularVelocity;
    double linearAcceleration;
    double energyDecayFactor;
    Color color;
    static double timeScalar;
    long lifeTimeMillis;
    long timeLastUpdated;

    void updatePosition(long currentTimeMillis) {
        long deltaTime = currentTimeMillis - timeLastUpdated;
        System.out.println("Time delta is: " + deltaTime); 

        if(currentXPosition >= 470 || currentXPosition <= 10) //TODO: Find these values dynamically.
            xSpeed = -xSpeed;
        if(currentYPosition >= 250 || currentYPosition <= 10) //TODO: Find these values dynamically.
            ySpeed = -ySpeed;

        currentXPosition = (int) (currentXPosition + xSpeed * deltaTime); //ATTN: Lossy conversion.
        currentYPosition = (int) (currentYPosition + ySpeed * deltaTime); //ATTN: Lossy conversion.
        System.out.println("Current x position: " + currentXPosition);
        System.out.println("Current y position: " + currentYPosition);
    }

    void updateLinerVelocity(int deltaScaledMillis) {

    }

    abstract void draw(Graphics2D g);
}