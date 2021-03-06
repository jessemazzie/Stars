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

class MyFrameClass extends JFrame implements ActionListener, ChangeListener, MortalityListener {
    MyPanel drawPanel;
    JPanel buttonPanel;
    JPanel sliderPanel;
    JSlider timeScalarSlider;
    Timer timer;
    Vector<LivingThing> groupOfThings;
    long timeLastUpdated;

    MyFrameClass() {
        Container cp;
        cp = getContentPane();
        
        timeScalarSlider = new JSlider(0, 200);
        timeScalarSlider.setPaintLabels(true);
        timeScalarSlider.addChangeListener(this); 
        timeScalarSlider.setMajorTickSpacing(50);
        timeScalarSlider.setMinorTickSpacing(25);
        timeScalarSlider.setPaintTicks(true);
        setJSliderLabels(timeScalarSlider);
        
        buttonPanel = new JPanel();
        buttonPanel.add(createNewButton("Add new star", "NEW_STAR"));
        buttonPanel.add(createNewButton("Kill all stars", "KILL_ALL"));
        
        sliderPanel = new JPanel();
        sliderPanel.add(new JLabel("Speed:"));
        sliderPanel.add(timeScalarSlider);
        
        
        
        groupOfThings = new Vector<LivingThing>();

        //Create random stars.
        for(int i = 0; i < 3; i++)
            groupOfThings.addElement(Star.getRandom());
        
        for(int i = 0; i < groupOfThings.size(); i++)
        	groupOfThings.get(i).addMortalityListener(this);
        
        timeLastUpdated = System.currentTimeMillis();

        timer = new Timer(10, this);
        timer.setCoalesce(false);
        timer.setActionCommand("TIMER");
        timer.start();

        drawPanel = new MyPanel(groupOfThings);
        setupMainFrame();

        cp.add(drawPanel, BorderLayout.CENTER);
        cp.add(sliderPanel, BorderLayout.EAST);
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
    
    void setJSliderLabels(JSlider sliderToLabel) {
    	Hashtable<Integer, JLabel> tempTable = new Hashtable<Integer, JLabel>();
    	
    	//This is an Integer rather than a primitive int because Hashtable requires it
    	for(Integer i = sliderToLabel.getMinimum(); i < sliderToLabel.getMaximum(); i += sliderToLabel.getMajorTickSpacing()) {
    		tempTable.put(i, new JLabel(i / 100.0 + ""));
    	}
    	
    	sliderToLabel.setLabelTable(tempTable);
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
            if(!groupOfThings.isEmpty()) {
            	for(LivingThing thing : groupOfThings) {
            		thing.update(currentTimeMillis - timeLastUpdated);
            	}
            }
            
            timeLastUpdated = currentTimeMillis;
            drawPanel.repaint();
            //System.out.println("Action event for timer received.");
        } else if(ae.getActionCommand().equals("NEW_STAR")) {
        	groupOfThings.add(Star.getRandom());
        	groupOfThings.lastElement().addMortalityListener(this);
        } else if(ae.getActionCommand().equals("KILL_ALL")) {
        	//TODO: Kill all here.
        	groupOfThings.removeAllElements();
        }
    }

	@Override
	public void stateChanged(ChangeEvent ce) {
		JSlider source = (JSlider) ce.getSource(); //JSliders are the only components that we have that generate ChangeEvents
		
		if(ce.getSource() == timeScalarSlider) {
			System.out.println("Value of time scalar slider changed to: " + source.getValue()/100d);
			Star.timeScalar = source.getValue()/100d;
		}
	}
	
	@Override
	public void deathNoticeEventReceived(MortalObject thingThatDies) {
		System.out.println("Death notice received.");
		groupOfThings.remove(thingThatDies);
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

        //System.out.println("Width: " + this.getWidth());    
    }
}