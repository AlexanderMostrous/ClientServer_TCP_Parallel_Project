import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Each SimpleServerTCP object listens to a different port number.
 */
public class SimpleServerTCP extends Thread{

	private int myPort;

	public SimpleServerTCP(int port) throws IOException
	{
		myPort = port;
	}

	@Override
	public void run(){
		
		try 
		{
			
			while(true)
			{
				ServerSocket connectionSocket = null;
				connectionSocket = new ServerSocket(myPort);
				
				System.out.println("Server is listening to port: " + myPort);
				Socket dataSocket;
				
				dataSocket = connectionSocket.accept();
				
				System.out.println("Received request from " + dataSocket.getInetAddress());

				ServerThread d = new ServerThread(dataSocket, ServerSideMain.ad);
				System.out.println("New ServerThread is running");
				d.run();
				connectionSocket.close();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		

		
		
	}
}			

