import javax.swing.*;
import java.awt.*;
import java.util.*;

public abstract class LivingThing implements MortalObject {
    double currentXPosition;
    double currentYPosition;
    double xSpeed;
    double ySpeed;
    double orientationAngle; //in radians
    double angularVelocity;
    double energyDecayFactor;
    Color color;
    Vector<MortalityListener> mortalityListeners;
    static double timeScalar = 1.0;
    static int screenSize;
    long timeOfDeath;
    long timeLastUpdated;
    
    LivingThing() {}
    
    void update(long deltaTime) {
    	if(timeOfDeath < System.currentTimeMillis())
    		death();
    	
    	updateAngularVelocity(deltaTime);
    	updatePosition(deltaTime);
    }

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

    void updateAngularVelocity(long deltaScaledMillis) {
    	orientationAngle = orientationAngle +  angularVelocity * timeScalar;
    }

    abstract void draw(Graphics2D g);
}