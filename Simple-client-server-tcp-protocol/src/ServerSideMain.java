import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSideMain {

	public static AirportData ad = new AirportData();
	public static int myPort = 1234;
	public static ArrayList<Socket> connections = new ArrayList<Socket>();
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		try
		{
			ServerSocket connectionSocket = null;
			connectionSocket = new ServerSocket(myPort);
			System.out.println("Server is listening to port: " + myPort);	
			Socket dataSocket;
			while(true)
			{
				dataSocket = connectionSocket.accept();
				ServerThread st = new ServerThread(dataSocket);
				System.out.println("Server says: Received request from " + dataSocket.getInetAddress());
				
				st.run();
				connections.add(dataSocket);
				
				for(Socket s : connections)
					if(s.isClosed())
						connections.remove(s);
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}