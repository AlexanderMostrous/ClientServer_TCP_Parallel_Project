import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSideMain {

	public static AirportData ad = new AirportData();
	//public static int myPort = 1234;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		int portNumber = 1200;
		try
		{
			ServerSocket connectionSocket = new ServerSocket(portNumber);
			Socket dataSocket;
			System.out.println("Server is listening to port: "+ portNumber);
			while(true)
			{
				dataSocket = connectionSocket.accept();
				System.out.println("Server says: Received request from " + dataSocket.getInetAddress());
				(new Thread(new ServerThread(dataSocket))).start();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}