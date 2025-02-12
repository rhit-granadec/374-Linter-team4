package org.requestedTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

//import edu.rosehulman.csse374.editor.TextEditor;

public class TextEditorApp {
	public static void main(String[] args) throws Exception {
		SubstitutionCipher cipher = new SubstitutionCipher();
		InputStream fIn = new FileInputStream("./input_output/encryptedin.txt");
		OutputStream fOut = new FileOutputStream("./input_output/encryptedout.txt");		
		InputStream in = new DecryptionInputStream(fIn, cipher);
		OutputStream out = new EncryptionOutputStream(fOut, cipher);
		
		//TextEditor editor = new TextEditor(in, out);
		//editor.execute();
	}	
}
