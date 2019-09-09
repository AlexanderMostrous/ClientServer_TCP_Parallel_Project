import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
public class ClientSideMain {
	public static final String myHost = "localhost";

	public static void main(String args[]) throws IOException {
		
		//Logger class is responsible for registering the results
		//Set directory in which a new txt file will be created and filled with the Client's data
		String directory = "C:\\Users\\alexandros\\Desktop\\TCP_ClientResults.txt";		
		Logger.setPath(directory);
		
		int readers, writers, modifiers, deleters;//Adjust their respective values accordingly.
		
		readers = 8;
		writers = 2;
		modifiers = 2;
		deleters = 1;
		
		int portNumber = 1200;
		Logger.writeInTxtFile("ClientMain says: "+readers+" readers, "+writers+" writers, "+modifiers+"modifiers and "+deleters+" deleters will now be created.");
		
		//Multi-client scheme. There are 4 different types of clients, all of which are runnable Threads.
		for(int i=0;i<readers;i++)
			new Thread(new ClientThread("READER",portNumber)).start();
		
		for(int i=0;i<writers;i++)		
			new Thread(new ClientThread("WRITER",portNumber)).start();
			
		for(int i=0;i<modifiers;i++)		
			new Thread(new ClientThread("MODIFIER",portNumber)).start();
		
		for(int i=0;i<deleters;i++)		
			new Thread(new ClientThread("DELETER",portNumber)).start();
	}
}			

