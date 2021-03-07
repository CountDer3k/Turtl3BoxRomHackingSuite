package a_starter;

/*

Der3k Burrola
PT3

TODO:
	make sure the buttons are just button sized.

 */
import	java.awt.*;
import	java.awt.event.*;
import	javax.swing.*;
import	java.beans.*;
 


public class GUI extends JFrame implements ActionListener
{
	private JPanel color = new JPanel();
	
	public GUI() 
	{
		// Set up the default things
		setTitle("GUI PT3");
		setSize(300,200);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter()
				{ public void windowClosing(WindowEvent e)
					{ exit(); }
				});

		// Load everything for screen here
		setMenu();
		setMainScreen();
		
		setVisible(true);
	}
	
	public void exit()
	{
		if (JOptionPane.showConfirmDialog(this,
					"Do you want to end this program?", "End Program",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION)
			System.exit(0);
	}
	
	
	// Next 4 are called by menu buttons or screen buttons
	public void setNorth() 
	{
		SetDisplayFromClick("North");
	}
	
	public void setSouth() 
	{
		SetDisplayFromClick("South");
	}
	
	public void setWest() 
	{
		SetDisplayFromClick("West");
	}
	
	public void setEast() 
	{
		SetDisplayFromClick("East");
	}
	
	public void OpenFile() {
		SetDisplayFromClick("Open");
	}
	
	// Called by the above 4 methods to display the Option Pane
	private void SetDisplayFromClick(String s) 
	{
		JOptionPane.showMessageDialog(this, s, "Directions", JOptionPane.OK_OPTION);
	}
	
	private void setMenu() 
	{
		// Sets the menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// Creates the direction menu
		JMenu directions = new JMenu("Directions");
		menuBar.add(directions);
		
		// Creates the 4 menu items
		directions.add(makeMenuItem("North", "setNorth", this));
		directions.add(makeMenuItem("South", "setSouth", this));
		directions.add(makeMenuItem("East", "setEast", this));
		directions.add(makeMenuItem("West", "setWest", this));
		directions.add(makeMenuItem("Open", "OpenFile", this));
	}
	
	// Draws the 4 main buttons to the screen on the borderlayout
	private void setMainScreen() 
	{
		JButton dButton = new JButton();
		dButton.setPreferredSize(new Dimension(40, 40));
		
		dButton = new JButton();
		dButton.setText("North");
		dButton.addActionListener(this);
		add(dButton, BorderLayout.NORTH);
		
		dButton = new JButton();
		dButton.setText("South");
		dButton.addActionListener(this);
		add(dButton, BorderLayout.SOUTH);
		
		dButton = new JButton();
		dButton.setText("West");
		dButton.addActionListener(this);
		add(dButton, BorderLayout.WEST);
		
		dButton= new JButton();
		dButton.setText("East");
		dButton.addActionListener(this);
		add(dButton, BorderLayout.EAST);
	}
	
	
	// Menu creators
	private JMenuItem makeMenuItem(String label, String method, Object target)
	{
		JMenuItem menuItem = new JMenuItem(label);
		menuItem.addActionListener((ActionListener)EventHandler.create(ActionListener.class, target, method));
		return menuItem;
	}
	
	
	// Event Handler
	public void actionPerformed(ActionEvent e)
	{
		JButton sButton = (JButton)e.getSource();
		String buttonTitle = sButton.getText();

		switch(buttonTitle)
		{
			case "North":
				setNorth();
				break;
			case "South":
				setSouth();
				break;
			case "West":
				setWest();
				break;
			case "East":
				setEast();
				break;
			default:
				SetDisplayFromClick("Massive Failure!! \nContact support, this shouldn't happen \nBut congrats on the Easter egg ;)");
				break;
		}
	}
	
	
	public static void main(String[] args) 
	{
		new GUI();
	}
}
