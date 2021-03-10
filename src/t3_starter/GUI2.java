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
import java.util.List;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import staticValues.PokemonValues;
import t3_Libs.LibHexEditor;
import t3_Libs.Messages;

import java.nio.ByteBuffer;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class GUI2 {

	private static JFrame frame;
	private static File fileSelected = null;
	private static JTextField txtBoxLevel;
	private static JComboBox DropDownFirstPoke;
	private static JComboBox DropDownSecondPoke;
	private static JComboBox DropDownThirdPoke;
	private static JLabel ROMName;
	private static JLabel ROMCode;
	private static JLabel ROMLang;
	private static String ROMCodeText;
	
	PokemonValues pkValues = new PokemonValues();
	static Messages messages;
	static LibHexEditor libHex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI2 window = new GUI2();
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
	public GUI2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Turtl3Box Presents: T3-Starter");
		frame.setBounds(100, 100, 683, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMenu();
		
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("33px"),
				ColumnSpec.decode("145px"),
				ColumnSpec.decode("91px"),
				ColumnSpec.decode("145px:grow"),
				ColumnSpec.decode("91px:grow"),
				ColumnSpec.decode("145px"),
				ColumnSpec.decode("33px"),},
			new RowSpec[] {
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("30px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("25px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("25px"),
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "2, 1, fill, top");
		
		ROMName = new JLabel();
		ROMName.setText("Name: ");
		frame.getContentPane().add(ROMName, "2, 3");
		
		ROMCode = new JLabel("Code: ");
		frame.getContentPane().add(ROMCode, "4, 3");
		
		ROMLang = new JLabel("Language: ");
		frame.getContentPane().add(ROMLang, "6, 3");
		
		JLabel lblFirstPokemonStatic = new JLabel("1st Pokemon");
		frame.getContentPane().add(lblFirstPokemonStatic, "2, 5, fill, top");
		
		JLabel lblSecondPokemonStatic = new JLabel("2nd Pokemon");
		frame.getContentPane().add(lblSecondPokemonStatic, "4, 5, fill, top");
		
		JLabel lblThirdPokemonStatic = new JLabel("3rd Pokemon");
		frame.getContentPane().add(lblThirdPokemonStatic, "6, 5, fill, top");
		
		DropDownFirstPoke = new JComboBox(pkValues.getGenI());
		frame.getContentPane().add(DropDownFirstPoke, "2, 7, fill, top");
		
		DropDownSecondPoke = new JComboBox(pkValues.getGenI());
		frame.getContentPane().add(DropDownSecondPoke, "4, 7, fill, top");
		
		DropDownThirdPoke = new JComboBox(pkValues.getGenI());
		frame.getContentPane().add(DropDownThirdPoke, "6, 7, fill, top");
		
		JLabel lblLevelStatic = new JLabel("Level: ");
		frame.getContentPane().add(lblLevelStatic, "2, 10, right, default");
		
		txtBoxLevel = new JTextField();
		frame.getContentPane().add(txtBoxLevel, "3, 10, fill, default");
		txtBoxLevel.setColumns(10);
		
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
			// Get ROM Header info
			setROMLabels();
			// Set the starter Pokemon dropdowns &
			setUserPokemonInformation();
			
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
		switch(ROMCodeText) {
			case "BPRE":
				// Save 1st Pokemon
				libHex.setHexAtOffset(0x00169BB5, DropDownFirstPoke.getSelectedIndex());
				// Save 2nd Pokemon
				libHex.setHexAtOffset(0x00169D82, DropDownSecondPoke.getSelectedIndex());
				// Save 3rd Pokemon
				libHex.setHexAtOffset(0x00169DB8, DropDownThirdPoke.getSelectedIndex());
				// Save Level
				libHex.setHexAtOffset(0x00169C8E, Integer.parseInt(txtBoxLevel.getText()));
				// Save Held Item
			break;
			default:
				messages.OKDialog(frame, "This game is not supported", "Game Code Error");
				break;
		}
	}
	
	/*
	 * Sets up all the information for the user's pokemon
	 * Fills the dropdowns, the labels, etc.
	 * Gets all the information from the ROM offsets of the specific game loaded
	 * */
	private static void setUserPokemonInformation() {
		switch(ROMCodeText) {
			case "BPRE":
				List<int[]> offsets = new ArrayList<>();
				
				// 1st Pokemon
				int[] offsetParams = {0x00169BB5, 2};
				offsets.add(offsetParams);
				// 2nd Pokemon
				offsetParams = new int[]{0x00169D82, 2};
				offsets.add(offsetParams);
				// 3rd Pokemon
				//offsets.add({0x00169BB5, 2});
				offsetParams = new int[]{0x00169DB8, 2};
				offsets.add(offsetParams);
				
				

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
				break;
			default:
				Messages.OKDialog(frame, "This game is not supported", "Game Code Error");
				break;
		}
		
	}
	
	
	private static void setDropdowns(List<int[]> offsets) {
		int num = LibHexEditor.getHexAtOffset(offsets.get(0)[0], offsets.get(0)[1]);		
		DropDownFirstPoke.setSelectedIndex(num);
		num = LibHexEditor.getHexAtOffset(offsets.get(1)[0], offsets.get(1)[1]);		
		DropDownSecondPoke.setSelectedIndex(num);
		num = LibHexEditor.getHexAtOffset(offsets.get(2)[0], offsets.get(2)[1]);		
		DropDownThirdPoke.setSelectedIndex(num);
	}
	
	
	/*
	 * Sets up the Labels for ROM information
	 * */
	private static void setROMLabels() {
		ROMName.setText("Name : " + LibHexEditor.getStringAtOffset(0x000000A0, 12));
		ROMCodeText = LibHexEditor.getStringAtOffset(0x000000AC, 4);
		ROMCode.setText("Code: "+ ROMCodeText);
		ROMLang.setText("Language: "+ LibHexEditor.getStringAtOffset(0x000000AF, 1));
	}
	
	private static void print(Object o) {
		System.out.println(o.toString());
	}
}