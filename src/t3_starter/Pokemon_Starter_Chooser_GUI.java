package t3_starter;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Pokemon_Starter_Chooser_GUI extends JFrame implements ActionListener{

	private static File fileSelected = null;
	private static String[] pokemonGenI = { "Bulbasaur", "Ivysaur", "Venasaur" };
	
	public Pokemon_Starter_Chooser_GUI() {
		setTitle("Turtl3Box Starter Chooser");
		setSize(300,300);
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
	
	public void openFile() {
		JFileChooser fc = new JFileChooser();
		File sf = null;
		//fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fc.showOpenDialog(fc);
		if(result == JFileChooser.APPROVE_OPTION) {
			sf = fc.getSelectedFile();
		}
		fileSelected =sf;
	}
	
	private void setMenu() 
	{
		// Sets the menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
				
		// Creates the direction menu
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
				
		// Creates menu items
		menuFile.add(makeMenuItem("Open", "openFile", this));
	}
	
	
	private void setMainScreen() 
	{
		//Create the combo box, select item at index 4.
		//Indices start at 0, so 4 specifies the pig.
		JComboBox firstStartPokemon = new JComboBox(pokemonGenI);
		//firstStartPokemon.setSelectedIndex(4);
		firstStartPokemon.addActionListener(this);
		firstStartPokemon.setVisible(true);
		add(firstStartPokemon);
		
		JButton dButton = new JButton();
		dButton.setPreferredSize(new Dimension(80, 40));
		
		dButton = new JButton();
		dButton.setText("Set Bulbasaur to Ivysaur");
		dButton.addActionListener(this);
		add(dButton);
		//add(dButton, BorderLayout.NORTH);
		
	}
	
	// Menu creators
	private JMenuItem makeMenuItem(String label, String method, Object target)
	{
		JMenuItem menuItem = new JMenuItem(label);
		menuItem.addActionListener((ActionListener)EventHandler.create(ActionListener.class, target, method));
		return menuItem;
	}
		
	public static void main(String[] args) {
		new Pokemon_Starter_Chooser_GUI();
	}
	
	
	private static void print(String s) {
		System.out.println(s);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton sButton = (JButton)e.getSource();
		String buttonTitle = sButton.getText();

		switch(buttonTitle)
		{
			case "Set Bulbasaur to Ivysaur":
				setBulb();
				break;
			default:
				print("Massive Failure!! \nContact support, this shouldn't happen \nBut congrats on the Easter egg ;)");
				break;
		}	
	}
	
	
	private static void setBulb() {
		if(fileSelected == null) {
			print("Error, no file selected");
		}
		else {
			int offset = 0x00169BB5;
			int intByte = 02;
			try {
				RandomAccessFile raf = new RandomAccessFile(fileSelected, "rw");
				raf.seek(offset);
				raf.write(intByte);
				raf.close();
				print("File changed");
			}
			catch(Exception ex) {
				print("FAILED!!! "+ex.toString());
			}
		}
	}
}
