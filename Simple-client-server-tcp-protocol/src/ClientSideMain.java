import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
public class ClientSideMain {
	public static final String myHost = "localhost";

	public static void main(String args[]) throws IOException {

		int readers, writers, modifiers, deleters;
		readers = 8;
		writers = 2;
		modifiers = 2;
		deleters = 1;
		int portNumber = 1200;
		System.out.println("ClientMain says: "+readers+" readers and "+writers+" writers will now be created.");
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

