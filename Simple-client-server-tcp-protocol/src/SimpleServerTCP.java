import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
 * Each SimpleServerTCP object listens to a different port number.
 */
public class SimpleServerTCP extends Thread
{

	private int myPort;

	public SimpleServerTCP(int port) throws IOException
	{
		myPort = port;
	}

	@Override
	public void run()
	{

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
				System.out.println("Connected: " + dataSocket);
				try 
				{
					Scanner in = new Scanner(dataSocket.getInputStream());
					String clientRequest;
					while(true)
					{
						if(in.hasNextLine())
						{
							clientRequest = in.nextLine();
							if(!clientRequest.equals("END"))							
								new ServerThread(dataSocket,clientRequest, ServerSideMain.ad).run();							
							else							
								break;		
							System.out.println("New ServerThread is running, in order to process request: "+clientRequest);
						}	

						
					}
					in.close();
				}
				finally
				{
					while(!dataSocket.isClosed())
						;//Energos anamonh, mexri o Reader na kleisei to dataSocket.
					connectionSocket.close();
					System.out.println("Connection Socket closed.");
				}

			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}



	}
}


