import javax.swing.*;
import java.awt.*;

public abstract class LivingThing {
    double currentXPosition;
    double currentYPosition;
    double xSpeed;
    double ySpeed;
    double orientationAngle; //in radians
    double angularVelocity;
    double energyDecayFactor;
    Color color;
    static double timeScalar = 1.0;
    static int screenSize;
    long lifeTimeMillis;
    long timeLastUpdated;

    void updatePosition(long deltaTime) {
        //System.out.println("Time delta is: " + deltaTime); 

        if(currentXPosition >= 470 || currentXPosition <= 10) 
            xSpeed = -xSpeed * energyDecayFactor;
        if(currentYPosition >= 250 || currentYPosition <= 10)
            ySpeed = -ySpeed * energyDecayFactor;

        currentXPosition = (int) (currentXPosition + xSpeed * deltaTime * timeScalar); //ATTN: Lossy conversion.
        currentYPosition = (int) (currentYPosition + ySpeed * deltaTime * timeScalar); //ATTN: Lossy conversion.
//        System.out.println("Current x position: " + currentXPosition);
//        System.out.println("Current y position: " + currentYPosition);
    }

    void updateLinearVelocity(int deltaScaledMillis) {

    }

    abstract void draw(Graphics2D g);
}