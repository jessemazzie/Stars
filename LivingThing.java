import javax.swing.*;
import java.awt.*;

public abstract class LivingThing {
    int currentXPosition;
    int currentYPosition;
    int xSpeed;
    int ySpeed;
    double angularVelocity;
    double linearAcceleration;
    double energyDecayFactor;
    static double timeScalar;
    long lifeTimeMillis;
    long timeLastUpdated;

    void updatePosition() {

        if(currentXPosition >= 470 || currentXPosition <= 10) //TODO: Find these values dynamically.
            xSpeed = -xSpeed;
        if(currentYPosition >= 250 || currentYPosition <= 10) //TODO: Find these values dynamically.
            ySpeed = -ySpeed;

        currentXPosition = currentXPosition + xSpeed;
        currentYPosition = currentYPosition + ySpeed;
        System.out.println("Current x pos: " + currentXPosition);
        System.out.println("Current y pos: " + currentYPosition);
    }

    void updateLinerVelocity(int deltaScaledMillis) {

    }

    abstract void draw(Graphics2D g);
}