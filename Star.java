import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Star extends LivingThing {
    static Random rand;
    int numTips;
    double innerRadius;
    double outerRadius;

    public static Star getRandom() {
        Star star = new Star();

        if(rand == null) 
            rand = new Random();
        
        star.numTips = 4 + rand.nextInt(4); //between 4 and 7 points on the star
        star.innerRadius = 5 + rand.nextInt(5) + rand.nextDouble();
        star.outerRadius = star.innerRadius + rand.nextInt(3) + rand.nextDouble();
        star.currentXPosition = rand.nextInt(250);
        star.currentYPosition = rand.nextInt(250);
        star.xSpeed = 1 + rand.nextInt(5);
        star.ySpeed = 1 + rand.nextInt(5);
        star.timeLastUpdated = System.currentTimeMillis(); //set last updated time to time of creation.
        star.energyDecayFactor = 0.95;

        return star;    
    }

    @Override
    void draw(Graphics2D g) {
        int theta;
        int tempx, tempy;
        g.drawRect(currentXPosition, currentYPosition, 40, 40);
    }
}