package app;
import java.awt.MediaTracker;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.ImageIcon;

public class Utilities
{
	private Utilities() {}

	public static ImageIcon readIcon(String sFileName) throws java.io.IOException
	{
		ImageIcon icon = new ImageIcon(sFileName);
		
		if (icon.getImageLoadStatus() != MediaTracker.COMPLETE)
		{
			throw new IOException("Unable to load icon: " + sFileName);
		}
	
		return icon;
	}
	
	public static String readFile(String sFileName) throws java.io.IOException
	{
		FileInputStream fisFile = new FileInputStream(sFileName);
		BufferedInputStream bisFile = new BufferedInputStream(fisFile);
		StringBuilder sbFileContents = new StringBuilder();
		int iByte = bisFile.read();

		while (iByte != -1)
		{
			sbFileContents.append((char) iByte);
			iByte = bisFile.read();
		}

		bisFile.close();

		return sbFileContents.toString();
	}
}
