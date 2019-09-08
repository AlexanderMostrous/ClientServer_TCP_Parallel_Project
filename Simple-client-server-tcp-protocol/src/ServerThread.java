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
						System.out.println("ServerThread "+this+" says: Client request is = "+clientRequest+"; Process begins.");
						ServerProtocol sp = new ServerProtocol();
						String reply = sp.processRequest(clientRequest);
						System.out.println("ServerThread "+this+" says: Request = "+clientRequest+" processed; Reply begins.");

						PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);	
						out.println(reply);
					}
					else
					{
						System.out.println("ServerThread "+this+" says: I just received the \"END\" message and I will now close connection to client.");
						break;
					}
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



