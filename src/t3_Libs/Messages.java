package t3_Libs;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * This class houses all functions that display dialog to the user:
 * 		Warning errors
 * 		Yes/No Popups
 * 		Etc.
 * 
 * This also includes all debugging related functions used during development
 * 
 * */
public class Messages {

	public Messages() {
		
	}
	
	//---------------------
	// Dialog Functions
	//---------------------
	public static void OKDialog(JFrame frame, String message, String title) {
		JOptionPane.showMessageDialog(frame, message, title, JOptionPane.OK_OPTION);
	}
	
	public static void YesNoDialog(JFrame frame, String message, String title) {
		JOptionPane.showMessageDialog(frame, message, title, JOptionPane.YES_NO_OPTION);
	}

	
	
	
	//---------------------
	// Debugging Functions
	//---------------------
	
	public static void print(Object o) {
		System.out.println(o.toString());
	}
}
