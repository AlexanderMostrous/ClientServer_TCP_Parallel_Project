import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread implements Runnable{
	private Socket mySocket;
	public ServerThread(Socket aSocket)
	{		
		this.mySocket = aSocket;
	}

	public void run() 
	{
		try 
		{
			Scanner in = new Scanner(mySocket.getInputStream());
			String clientRequest;
			while(true)
			{
				if(in.hasNextLine())
				{
					clientRequest = in.nextLine();
					if(!clientRequest.equals("END"))
					{
						ServerProtocol sp = new ServerProtocol();
						String reply = sp.processRequest(clientRequest);

						PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);	
						out.println(reply);
					}
					else							
						break;							
				}						
			}
			in.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				mySocket.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}



