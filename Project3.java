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

class MyFrameClass extends JFrame {
    MyPanel drawPanel;

    MyFrameClass() {
        Container cp;
        cp = getContentPane();

        drawPanel = new MyPanel();
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
}

class MyPanel extends JPanel implements ActionListener {
    Random rand = new Random();
    Timer timer;
    Vector<LivingThing> myStars = new Vector<LivingThing>();

    MyPanel() {
        for(int i = 0; i < 3; i++)
            myStars.addElement(Star.getRandom());
        timer = new Timer( 10, this);
        timer.setCoalesce(true);
        timer.setActionCommand("TIMER");
        timer.start();
        System.out.println(this.getWidth());
    }
 
    @Override 
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1); //Clears panel
        Graphics2D g;
        g = (Graphics2D) g1; //Graphics2D objects are easier to work with.

        for(LivingThing star : myStars) {
            star.draw(g);
        }
        System.out.println(this.getWidth());    
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("TIMER")) {
            for(LivingThing star : myStars) {
                star.updatePosition();
            }
            repaint();
            System.out.println("Action event for timer received.");
        }
    }
}