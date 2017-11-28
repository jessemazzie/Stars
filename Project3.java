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
    JPanel buttonPanel;
    JSlider timeScalarSlider;
    Timer timer;
    Vector<LivingThing> groupOfThings;
    long timeLastUpdated;

    MyFrameClass() {
        Container cp;
        cp = getContentPane();
        
        timeScalarSlider = new JSlider(0, 200);
        timeScalarSlider.setMajorTickSpacing(10);
        timeScalarSlider.setMinorTickSpacing(5);
        timeScalarSlider.setPaintTicks(true);
        
        buttonPanel = new JPanel();
        buttonPanel.add(createNewButton("Add new star", "NEW_STAR"));
        buttonPanel.add(createNewButton("Kill all stars", "KILL_ALL"));
        buttonPanel.add(timeScalarSlider);
        
        
        
        groupOfThings = new Vector<LivingThing>();

        //Create random stars.
        for(int i = 0; i < 3; i++)
            groupOfThings.addElement(Star.getRandom());
        timeLastUpdated = System.currentTimeMillis();

        timer = new Timer(10, this);
        timer.setCoalesce(false);
        timer.setActionCommand("TIMER");
        timer.start();

        drawPanel = new MyPanel(groupOfThings);
        setupMainFrame();

        cp.add(drawPanel, BorderLayout.CENTER);
        cp.add(buttonPanel, BorderLayout.SOUTH);
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
    
    JButton createNewButton(String title, String actionCommand) {
    	JButton tempButton;
    	
    	tempButton = new JButton(title);
    	tempButton.setActionCommand(actionCommand);
    	tempButton.addActionListener(this);
    	
    	return tempButton;
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
            
            for(LivingThing thing : groupOfThings) {
                thing.updatePosition(currentTimeMillis - timeLastUpdated);
            }
            timeLastUpdated = currentTimeMillis;
            drawPanel.repaint();
            System.out.println("Action event for timer received.");
        } else if(ae.getActionCommand().equals("NEW_STAR")) {
        	groupOfThings.add(Star.getRandom());
        } else if(ae.getActionCommand().equals("KILL_ALL")) {
        	//TODO: Kill all here.
        	groupOfThings.removeAllElements();
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