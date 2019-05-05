import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Deliverer implements Runnable{

	private Socket socket;
	private AirportData data;

	public Deliverer(Socket aSocket, AirportData ad){
		this.socket = aSocket;
		this.data = ad;
	}
	
	public void run() {
		System.out.println("Connected: " + socket);
		String clientCommand = "";
		try {
			Scanner in = new Scanner(socket.getInputStream());
			
			
			clientCommand = in.nextLine();
			
			System.out.println("clientCommand="+clientCommand);
			String l[];
			if(clientCommand.startsWith("READ"))
			{
				System.out.println("Client command starts with READ");
				System.out.println("clientCommand.substring(6) = "+clientCommand.substring(6,clientCommand.length()-1));
				l = data.getLine_READ(clientCommand.substring(6,clientCommand.length()-1));
				//l now looks like: l["XY4352","Arrival","12:40"]
				System.out.println("The line found was: l[0] = "+l[0]+", l[1] = "+l[1]+", l[2] = "+l[2]);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				if(l.equals(null))
					out.println("RERR");
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
				String[] tokens=message.split("[<>]");
				String returnableMessage = this.data.addLine_WRITE(l[1], l[3], l[5]);
				
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.println(returnableMessage);
			
			}
			else
				System.out.println("BUG! Client command does NOT start with READ or WRITE!!!");
		} 
		catch (Exception e) {
			System.out.println("Error:" + socket);
		} 
		finally 
		{
			try 
			{
				socket.close();
				}
			catch (IOException e) {}
			System.out.println("Closed: " + socket);
		}
	}



}
