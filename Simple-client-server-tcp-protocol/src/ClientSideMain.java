import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
public class ClientSideMain {
	public static final String myHost = "localhost";

	public static ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	public static void main(String args[]) throws IOException {

		int readers, writers;
		readers = 1;
		writers = 0;
		
		for(int i=0;i<readers;i++)
			clients.add(new ClientThread("READER"));
		for(int i=0;i<writers;i++)
			clients.add(new ClientThread("WRITER"));
		
		for(ClientThread c : clients)
			c.run();

	}
}			

