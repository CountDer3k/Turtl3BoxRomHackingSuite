package a_starter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JFileChooser;

public class StarterPokemonChanger {

	public static void main(String[] args) {
		int offset = 0x00169BB5;
		int intByte = 05;
		try {
			File myFile = FileSelector();
			RandomAccessFile raf = new RandomAccessFile(myFile, "rw");
			raf.seek(offset);
			raf.write(intByte);
			raf.close();
			print("File changed");
		}
		catch(Exception ex) {
			print("FAILED!!! "+ex.toString());
		}
		
	}
	
	private static File FileSelector() throws IOException {
		JFileChooser fc = new JFileChooser();
		File sf = null;
		//fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fc.showOpenDialog(fc);
		if(result == JFileChooser.APPROVE_OPTION) {
			sf = fc.getSelectedFile();
		}
		return sf;
	}
	
	private static void print(String s) {
		System.out.println(s);
	}

}
