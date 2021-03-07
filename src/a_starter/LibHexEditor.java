package a_starter;

import java.io.File;
import java.io.RandomAccessFile;

public class LibHexEditor {

	private static File file;
	
	public LibHexEditor(File file) {
		this.file = file;
	}
	
	/*
	 * Takes an offset & bytes (in hex) to replace in the ROM
	 * returns success of this function 
	 * */
	public static boolean setHexAtOffset(int offset, int newBytes) {
		if(file == null) {
			printErr("Error, no file selected");
			return false;
		}
		else {
			try {
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				raf.seek(offset);
				raf.write(newBytes);
				raf.close();
				return true;
			}
			catch(Exception ex) {
				printErr("FAILED!!! "+ex.toString());
				return false;
			}
		}
	}
	
	
	public static int getHexAtOffset(int offset, int amountOfBytes) {
		byte[] b = new byte[amountOfBytes];
		if(file == null) {
			printErr("Error, no file selected");
			return -1;
		}
		else {
			try {
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				raf.seek(offset);
				raf.read(b, 0, amountOfBytes);
				raf.close();
				return byteArrayToInt(b);
			}
			catch(Exception ex) {
				printErr("FAILED!!! "+ex.toString());
				return -1;
			}
		}
	}
	

	private static int byteArrayToInt(byte[] b) {
		int value = 0;
		String temp = "";
		
		value = Integer.parseInt(b[0]+"");
		
		// Used for checking pokemonNumber
		//?? Need to account for leading zeroes to be removed from each byte
		if(b.length == 2) {
			if(b[1] == 1) {
				
			}
			else {
				temp = b[0]+"";
				value = Integer.parseInt(temp);
			}
		}
		
		printErr(value);
		return value;
	}
	
	
	
	
	
	
	
	
	
	
	
	private static void printErr(Object o) {
		System.out.println(o.toString());
	}
	
}
