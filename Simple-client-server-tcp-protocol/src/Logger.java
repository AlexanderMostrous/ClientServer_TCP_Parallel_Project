import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Logger {

	private ArrayList<String> log;
	private String myDirectory;
	
	public Logger(String txtDirectory){
		log = new ArrayList<>();
		myDirectory = txtDirectory;
	}
	
	public void addToLog(String input){
		
		log.add(input);
	}
	//C:\Users\alexandros\Dropbox\Shared main-libmos\Parallel Programming
	public void writeInTxtFile(String directory){
		FileWriter writer = null;
		try {
			writer = new FileWriter(directory+"\\parallelResults.txt");

			String newLine = System.getProperty("line.separator");
					
			writer.write("  ");
			
			writer.write(newLine);	

			
		} catch (IOException e) {

			e.printStackTrace();
		}
		finally
		{
			try 
			{
				writer.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
