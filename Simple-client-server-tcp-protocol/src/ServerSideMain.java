import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSideMain {

	public static AirportData ad = new AirportData();	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException 
	{	
		//Logger class is responsible for registering the results
		//Set directory in which a new txt file will be created and filled with the Server's data
		String directory = "C:\\Users\\alexandros\\Desktop\\TCP_ServerResults.txt";		
		Logger.setPath(directory);
		
		int portNumber = 1200;
		try
		{
			ServerSocket connectionSocket = new ServerSocket(portNumber);
			Socket dataSocket;
			Logger.writeInTxtFile("Server is listening to port: "+ portNumber);
			while(true)//Multi-threaded Server scheme
			{
				dataSocket = connectionSocket.accept();
				Logger.writeInTxtFile("Server says: Received request from " + dataSocket.getInetAddress());
				(new Thread(new ServerThread(dataSocket))).start();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}