import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
public class ClientSideMain {
	public static final String myHost = "localhost";

	public static void main(String args[]) throws IOException {

		int readers, writers;
		readers = 6;
		writers = 2;
		int portNumber = 1200;
		System.out.println("ClientMain says: "+readers+" readers and "+writers+" writers will now be created.");
		for(int i=0;i<readers;i++)
			new Thread(new ClientThread("READER",portNumber)).start();
		
		for(int i=0;i<writers;i++)		
			new Thread(new ClientThread("WRITER",portNumber)).start();
			
		
		

	}
}			

