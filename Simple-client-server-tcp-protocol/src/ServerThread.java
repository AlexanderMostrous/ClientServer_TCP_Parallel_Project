import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable{

	private AirportData data;
	private String clientCommand = "";
	private Socket mySocket;
	public ServerThread(Socket aSocket, String aRequest, AirportData ad)
	{		
		this.mySocket = aSocket;
		this.clientCommand = aRequest;
		this.data = ad;
	}

	public void run() {

		while(true)
		{
			try 
			{
				if(!clientCommand.equals("END"))
				{
					String l[];
					if(clientCommand.startsWith("READ"))
					{
						l = data.getLine_READ(clientCommand.substring(6,clientCommand.length()-1));
						//l now looks like: l["XY4352","Arrival","12:40"]
						//System.out.println("The line found was: l[0] = "+l[0]+", l[1] = "+l[1]+", l[2] = "+l[2]);
						PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);
						if(l==null)
							out.println("RERR "+clientCommand);
						else
							out.println("ROK <"+l[0]+"> <"+l[1]+"> <"+l[2]+">");
					}
					else if(clientCommand.startsWith("WRITE"))
					{
						System.out.println("Client command starts with WRITE");
						System.out.println("clientCommand.substring(6) = "+clientCommand.substring(6,clientCommand.length()));

						String message = clientCommand.substring(6,clientCommand.length());
						message = message.replaceAll("\\s", "");
						l = data.getLine_READ(message);
						String returnableMessage = this.data.addLine_WRITE(l[1], l[3], l[5]);

						PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);
						out.println(returnableMessage);
					}
					else
						System.out.println("BUG! Client command does NOT start with READ or WRITE!!!");
				} 
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			break;
		}
	}

	public void setClientCommand(String cc)
	{
		this.clientCommand = cc;
	}

}
