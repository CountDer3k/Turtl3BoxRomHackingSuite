package a_starter;

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
import java.nio.ByteBuffer;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class windowStarter {

	private JFrame frame;
	private static File fileSelected = null;
	private static JTextField textField;
	private static JComboBox DropDownFirstPoke;
	private static JComboBox DropDownSecondPoke;
	private static JComboBox DropDownThirdPoke;
	
	PokemonValues pkValues = new PokemonValues();
	static LibHexEditor libHex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windowStarter window = new windowStarter();
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
	public windowStarter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuOpen = new JMenuItem("Open");
		menuOpen.addActionListener((ActionListener)EventHandler.create(ActionListener.class, this, "openFile"));
		mnNewMenu.add(menuOpen);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_3);
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
		
		JLabel lblFirstPokemon = new JLabel("1st Pokemon");
		frame.getContentPane().add(lblFirstPokemon, "2, 2, fill, top");
		
		JLabel lblSecondPokemon = new JLabel("2nd Pokemon");
		frame.getContentPane().add(lblSecondPokemon, "4, 2, fill, top");
		
		JLabel lblThirdPokemon = new JLabel("3rd Pokemon");
		frame.getContentPane().add(lblThirdPokemon, "6, 2, fill, top");
		
		DropDownFirstPoke = new JComboBox(pkValues.getGenI());
		frame.getContentPane().add(DropDownFirstPoke, "2, 4, fill, top");
		
		DropDownSecondPoke = new JComboBox(pkValues.getGenI());
		frame.getContentPane().add(DropDownSecondPoke, "4, 4, fill, top");
		
		DropDownThirdPoke = new JComboBox(pkValues.getGenI());
		frame.getContentPane().add(DropDownThirdPoke, "6, 4, fill, top");
		
		JLabel lblLevel = new JLabel("Level: ");
		frame.getContentPane().add(lblLevel, "2, 10, right, default");
		
		textField = new JTextField();
		frame.getContentPane().add(textField, "3, 10, fill, default");
		textField.setColumns(10);
		
		JComboBox DropDownItemPlayer = new JComboBox();
		frame.getContentPane().add(DropDownItemPlayer, "5, 10, fill, default");

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
		textField.setText(num+"");
	}
}
