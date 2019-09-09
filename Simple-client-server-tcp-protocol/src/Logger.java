import java.io.IOException;
import java.io.PrintWriter;

public abstract class Logger {

	private static PrintWriter writer;
	
	public static void setPath(String txtDirectory)
	{
		try
		{
			writer = new PrintWriter(txtDirectory);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void writeInTxtFile(String text)
	{	
		writer.println(text);
	}
}
