package t3_starter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import staticValues.PokemonValues;
import t3_Libs.LibHexEditor;

import java.nio.ByteBuffer;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class T3_Starter_GUI {

	private JFrame frame;
	private static File fileSelected = null;
	private static JTextField txtBoxLevel;
	private static JComboBox DropDownFirstPoke;
	private static JComboBox DropDownSecondPoke;
	private static JComboBox DropDownThirdPoke;
	private static JLabel ROMName;
	private static JLabel ROMCode;
	private static JLabel ROMLang;
	
	PokemonValues pkValues = new PokemonValues();
	static LibHexEditor libHex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					T3_Starter_GUI window = new T3_Starter_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public T3_Starter_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Turtl3Box Presents: T3-Starter");
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMenu();
		
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("33px"),
				ColumnSpec.decode("145px"),
				ColumnSpec.decode("91px"),
				ColumnSpec.decode("145px:grow"),
				ColumnSpec.decode("91px:grow"),
				ColumnSpec.decode("145px"),},
			new RowSpec[] {
				RowSpec.decode("65px"),
				RowSpec.decode("16px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("27px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "2, 1, fill, top");
		
		JLabel ROMName = new JLabel("Name: ");
		frame.getContentPane().add(ROMName, "2, 3");
		
		
		JLabel ROMCode = new JLabel("Code: ");
		frame.getContentPane().add(ROMCode, "4, 3");
		
		JLabel ROMLang = new JLabel("Language: ");
		frame.getContentPane().add(ROMLang, "6, 3");
		
		JLabel lblFirstPokemon = new JLabel("1st Pokemon");
		frame.getContentPane().add(lblFirstPokemon, "2, 5, fill, top");
		
		JLabel lblSecondPokemon = new JLabel("2nd Pokemon");
		frame.getContentPane().add(lblSecondPokemon, "4, 5, fill, top");
		
		JLabel lblThirdPokemon = new JLabel("3rd Pokemon");
		frame.getContentPane().add(lblThirdPokemon, "6, 5, fill, top");
		
		DropDownFirstPoke = new JComboBox(pkValues.getGenI());
		frame.getContentPane().add(DropDownFirstPoke, "2, 7, fill, top");
		
		DropDownSecondPoke = new JComboBox(pkValues.getGenI());
		frame.getContentPane().add(DropDownSecondPoke, "4, 7, fill, top");
		
		DropDownThirdPoke = new JComboBox(pkValues.getGenI());
		frame.getContentPane().add(DropDownThirdPoke, "6, 7, fill, top");
		
		JLabel lblLevel = new JLabel("Level: ");
		frame.getContentPane().add(lblLevel, "2, 10, right, default");
		
		//textField = new JTextField();
		//frame.getContentPane().add(textField, "3, 11, fill, default");
		//textField.setColumns(10);
		
		JComboBox DropDownItemPlayer = new JComboBox();
		frame.getContentPane().add(DropDownItemPlayer, "5, 10, fill, default");

	}
	
	/* Helper method to initialize.
	 * Sets up the menu items */
	private void setMenu() {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuOpenROM = new JMenuItem("Open ROM");
		menuOpenROM.addActionListener((ActionListener)EventHandler.create(ActionListener.class, this, "openFile"));
		mnNewMenu.add(menuOpenROM);
		
		JMenuItem menuSaveROM = new JMenuItem("Save ROM");
		menuSaveROM.addActionListener((ActionListener)EventHandler.create(ActionListener.class, this, "saveFile"));
		mnNewMenu.add(menuSaveROM);
	}
	
	
	
	
	//--------------------
	// Menu Functions
	//--------------------
	public void openFile() {
		JFileChooser fc = new JFileChooser();
		File sf = null;
		//fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fc.showOpenDialog(fc);
		if(result == JFileChooser.APPROVE_OPTION) {
			sf = fc.getSelectedFile();
			libHex = new LibHexEditor(sf);
			setComboBoxes();
		}
		else if(result == JFileChooser.CANCEL_OPTION) {
			// Do nothing??
		}
		else {
			String s = "An unknow error occured.";
			JOptionPane.showMessageDialog(frame, this, s, JOptionPane.OK_OPTION);
		}
		fileSelected =sf;
	}
	
	public void saveFile() {
		// Save 1st Pokemon
		libHex.setHexAtOffset(0x00169BB5, DropDownFirstPoke.getSelectedIndex());
		// Save 2nd Pokemon
		libHex.setHexAtOffset(0x00169D82, DropDownSecondPoke.getSelectedIndex());
		// Save 3rd Pokemon
		libHex.setHexAtOffset(0x00169DB8, DropDownThirdPoke.getSelectedIndex());
		// Save Level
		libHex.setHexAtOffset(0x00169C8E, Integer.parseInt(txtBoxLevel.getText()));
		// Save Held Item
	}
	
	private static void setComboBoxes() {
		// Set selected value to value stored in ROM
		int num = LibHexEditor.getHexAtOffset(0x00169BB5, 2);		
		DropDownFirstPoke.setSelectedIndex(num);
		num = LibHexEditor.getHexAtOffset(0x00169D82, 2);		
		DropDownSecondPoke.setSelectedIndex(num);
		num = LibHexEditor.getHexAtOffset(0x00169DB8, 2);		
		DropDownThirdPoke.setSelectedIndex(num);
		
		// Set Level from ROM
		num = LibHexEditor.getHexAtOffset(0x00169C8E, 1);
		txtBoxLevel.setText(num+"");
	}
	
	/*
	 * Sets up the Labels for ROM information
	 * */
	private static void setROMLabels() {
		ROMName.setText("Name: "+ LibHexEditor.getHexAtOffset(0x000000A0, 12));
		ROMCode.setText("Code: "+ LibHexEditor.getHexAtOffset(0x000000AC, 4));
		ROMLang.setText("Language: "+ LibHexEditor.getHexAtOffset(0x000000AF, 1));
	}
}
