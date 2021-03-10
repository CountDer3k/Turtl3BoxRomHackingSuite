package t3_Libs;

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
				printErr("setHexAtOffse: "+ex.toString());
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
				printErr("getHexAtOffset: "+ex.toString());
				return -1;
			}
		}
	}
	
	public static String getStringAtOffset(int offset, int amountOfBytes) {
		byte[] b = new byte[amountOfBytes];
		if(file == null) {
			printErr("Error, no file selected");
			return null;
		}
		else {
			try {
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				raf.seek(offset);
				raf.read(b, 0, amountOfBytes);
				raf.close();
				return byteArrayToString(b);
			}
			catch(Exception ex) {
				printErr("getHexAtOffset: "+ex.toString());
				return null;
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
		else {
			for(int i = 1; i < b.length; i++) {
				//value = value + b[i];
			}
		}
		return value;
	}
	
	private static String byteArrayToString(byte[] b) {
		String hexString = "";
		// Makes a string out of the bytes in the array
		for(int i = 0; i < b.length; i++) {
			// Change the bytes to hex
			hexString = hexString + Integer.toHexString(b[i]);
		}
		
		return hexToAscii(hexString.toLowerCase());
		
	}
	
	private static String hexToAscii(String hexStr) {
	    String output = "";
	    
	    for (int i = 0; i < hexStr.length(); i += 2) {
	        String str = hexStr.substring(i, i + 2);
	        char value = (char) Integer.parseInt(str, 16);
	        output = output + value;
	    }
	    return output;
	}
	
	
	
	
	
	
	
	
	
	
	
	private static void printErr(Object o) {
		System.out.println(o.toString());
	}
	
}
