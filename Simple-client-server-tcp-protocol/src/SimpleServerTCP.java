import java.net.*;
import java.io.*;

public class SimpleServerTCP {
	
	private static final int PORT = 1234;
	private static AirportData ad = new AirportData();
	
	//@SuppressWarnings("resource")
	public static void main(String args[]) throws IOException {

		/TODO java.net.BindException debug then run
		/TODO Next task is parallelilsm with ReadWriteLock 
		ServerSocket connectionSocket = new ServerSocket(PORT);
		System.out.println("Server is listening to port: " + PORT);	

		while(true)
		{
		Socket dataSocket = connectionSocket.accept();
		System.out.println("Received request from " + dataSocket.getInetAddress());

		Deliverer d = new Deliverer(dataSocket, ad);
		System.out.println("New Deliverer is running");
		d.run();
		
		}
	}
}			

