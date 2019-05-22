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
						PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);
						if(l==null)
							out.println("RERR "+clientCommand);
						else
							out.println("ROK <"+l[0]+"> <"+l[1]+"> <"+l[2]+">");
					}
					else if(clientCommand.startsWith("WRITE"))
					{
						String[] s = clientCommand.split(" ");
						s[1]=s[1].substring(1,s[1].length()-1);
						s[2]=s[2].substring(1,s[2].length()-1);
						s[3]=s[3].substring(1,s[3].length()-1);
						
						String returnableMessage = data.addLine_WRITE(s[1], s[2], s[3]);
						PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);
						out.println(returnableMessage+" "+clientCommand);
					}
					else
						System.out.println("CRAZY BUG! Client command does NOT start with READ or WRITE!!!");
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
