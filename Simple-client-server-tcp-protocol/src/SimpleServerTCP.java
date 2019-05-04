import java.net.*;
import java.io.*;

public class SimpleServerTCP {
	
	private static final int PORT = 1234;
	private static AirportData ad = new AirportData();
	
	@SuppressWarnings("resource")
	public static void main(String args[]) throws IOException {

		//TODO  java.net.BindException debug then run
		ServerSocket connectionSocket = new ServerSocket(PORT);
		System.out.println("Server is listening to port: " + PORT);	

		while(true)
		{
		Socket dataSocket = connectionSocket.accept();
		System.out.println("Received request from " + dataSocket.getInetAddress());

		Deliverer d = new Deliverer(dataSocket, ad);
		System.out.println("New Deliverer is running");
		d.run();
		
		/*
		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		
		
		String inmsg, outmsg;
		inmsg = in.readLine();
		ServerProtocol app = new ServerProtocol();
		outmsg = app.processRequest(inmsg);
		out.println(outmsg);
		*/
		}
		//dataSocket.close();
		//System.out.println("Data socket closed");	
	}
}			

