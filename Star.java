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
        star.xSpeed = 1 + rand.nextInt(5);
        star.ySpeed = 1 + rand.nextInt(5);
        star.orientationAngle = 3 + rand.nextInt(6);
        star.timeLastUpdated = System.currentTimeMillis(); //set last updated time to time of creation.
        star.energyDecayFactor = 0.95;
        star.color = Color.YELLOW;

        return star;    
    }

    @Override
    void draw(Graphics2D g) {
        double theta = orientationAngle;
        int tempx, tempy;
        // g.drawRect(currentXPosition, currentYPosition, 40, 40);
        int[] x = new int[numTips * 2];
        int[] y = new int[numTips * 2];
        g.setColor(color);
        for(int i = 0; i < numTips * 2; i += 2) {
            x[i] = currentXPosition + (int) (innerRadius * Math.cos(theta));
            y[i] = currentYPosition + (int) (innerRadius * Math.sin(theta));
            x[i + 1] = currentXPosition + (int) (outerRadius * Math.cos(theta));
            y[i + 1] = currentYPosition + (int) (outerRadius * Math.sin(theta));

            theta = theta + (2*Math.PI) / numTips;
        }



        g.drawPolygon(x, y, numTips * 2);

    }
}