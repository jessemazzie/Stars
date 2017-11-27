import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

public class Project3 {
    public static void main(String[] args) {
        new MyFrameClass();
    }
}

class MyFrameClass extends JFrame implements ActionListener {
    MyPanel drawPanel;
    Timer timer;
    Vector<LivingThing> groupOfThings;

    MyFrameClass() {
        Container cp;
        cp = getContentPane();

        groupOfThings = new Vector<LivingThing>();

        //Create random stars.
        for(int i = 0; i < 3; i++)
            groupOfThings.addElement(Star.getRandom());

        timer = new Timer(1, this);
        timer.setCoalesce(true);
        timer.setActionCommand("TIMER");
        timer.start();

        drawPanel = new MyPanel(groupOfThings);
        setupMainFrame();

        cp.add(drawPanel, BorderLayout.CENTER);
    }

    void setupMainFrame() {
        Toolkit tk;
        Dimension d;

        tk = Toolkit.getDefaultToolkit();
        d = tk.getScreenSize();
        setSize(d.width/4, d.height/4);
        setLocation(d.width/4, d.height/4);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Star visualizer.");

        setVisible(true);
    }

    /**
     * Required for implementing ActionListener.
     * 
     */        
    @Override
    public void actionPerformed(ActionEvent ae) {
        long currentTimeMillis; 
        if(ae.getActionCommand().equals("TIMER")) {
            currentTimeMillis = System.currentTimeMillis();
            timer.stop();
            
            for(LivingThing thing : groupOfThings) {
                thing.updatePosition(currentTimeMillis);
            }
            drawPanel.repaint();
            System.out.println("Action event for timer received.");
            timer.start();
        }
    }
}

/**
 * JPanel subclass created for the purpose of overriding paintComponent to paint a group of living things. 
 */
class MyPanel extends JPanel {
    Random rand = new Random();
    Vector<LivingThing> groupOfThings;

    MyPanel(Vector<LivingThing> groupOfThings) {
        this.groupOfThings = groupOfThings;
    }
 
    @Override 
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1); //Clears panel
        Graphics2D g;
        g = (Graphics2D) g1; //Graphics2D objects are easier to work with.

        for(LivingThing thing : groupOfThings) {
            thing.draw(g);
        }

        System.out.println("Width: " + this.getWidth());    
    }
}