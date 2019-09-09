import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSideMain {

	public static AirportData ad = new AirportData();
	//public static int myPort = 1234;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		int portNumber = 1200, connectionsNumber = 1;
		try
		{
			ServerSocket connectionSocket = new ServerSocket(portNumber);
			Socket dataSocket;
			while(connectionsNumber<=10)
			{
				
				
				System.out.println("Server is listening to port: #"+connectionsNumber+" - "+ portNumber);	
				

				dataSocket = connectionSocket.accept();
				System.out.println("Server says: Received request from " + dataSocket.getInetAddress());
				(new Thread(new ServerThread(dataSocket))).start();
			

				connectionsNumber++;
				
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}