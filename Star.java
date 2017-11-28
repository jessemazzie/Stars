import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Star extends LivingThing {
    static Random rand;
    int numTips;
    int innerRadius;
    int outerRadius;

    public static Star getRandom() {
        Star star = new Star();

        if(rand == null) 
            rand = new Random();
        
        star.numTips = 4 + rand.nextInt(4); //between 4 and 7 points on the star
        star.innerRadius = 5 + rand.nextInt(5);
        star.outerRadius = star.innerRadius + 4 + rand.nextInt(10);
        star.currentXPosition = rand.nextInt(250);
        star.currentYPosition = rand.nextInt(250);
        star.xSpeed = 5 - rand.nextInt(10);
        star.ySpeed = 5 - rand.nextInt(10);
        star.orientationAngle = 1 + rand.nextInt(6);
        star.timeLastUpdated = System.currentTimeMillis(); //set last updated time to time of creation.
        star.energyDecayFactor = 0.95;
        star.color = Color.BLACK;

        return star;    
    }

    @Override
    void draw(Graphics2D g) {
        double theta = orientationAngle;

        int[] x = new int[numTips * 2];
        int[] y = new int[numTips * 2];
        
        g.setColor(color);
        
        for(int i = 0; i < numTips * 2; i += 2) {
            x[i] = (int) (currentXPosition + innerRadius * Math.cos(theta));
            y[i] = (int) (currentYPosition + innerRadius * Math.sin(theta));
            
            theta = theta + (Math.PI) / numTips;
            
            x[i + 1] = (int) (currentXPosition + outerRadius * Math.cos(theta));
            y[i + 1] = (int) (currentYPosition + outerRadius * Math.sin(theta));

            theta = theta + (Math.PI) / numTips;
        }

        g.drawPolygon(x, y, numTips * 2);
    }
}