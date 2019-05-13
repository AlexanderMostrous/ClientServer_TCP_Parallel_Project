import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Reader implements Runnable{

	private ArrayList<String> requestList;
	private int myPort;
	private String myHost;
	private Socket mySocket;
	private BufferedReader in;
	private PrintWriter out;
	
	
	public Reader(String host, int port)
	{
		requestList = new ArrayList<>();
		myPort = port;
		myHost = host;

	}
	@Override
	public void run() 
	{
		establishConnectionWithServer();
		requestRandomReads();
		
		
		try 
		{
			mySocket.close();
			System.out.println("Data Socket closed");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

	private void requestRandomReads()
	{
		Random rand = new Random();
		int counter = 0;
		int readsLimit = rand.nextInt(10);//Random number between [0,10) = read requests from server
		
		do
		{
			
		out.println(rand.nextInt(this.requestList.size()));//Get random element of list.
		
		
		String inmsg;
		try {
			inmsg = in.readLine();
			System.out.println("Client says: "+ inmsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		counter++;
		}while(counter<readsLimit);
		
	}
	private void establishConnectionWithServer()
	{
		
		try{
			mySocket = new Socket(myHost,myPort);

			InputStream is = mySocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			OutputStream os = mySocket.getOutputStream();
			out = new PrintWriter(os,true);

			System.out.println("Connection to " + myHost + " established");
			
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			finally
			{
				System.out.println("Something went really bad!");
			}
	}
	private void initializeRequestList()
	{
		requestList.add("READ <DB9761>");//EXISTS
		requestList.add("READ <KT4129>");
		requestList.add("READ <AB1217>");//EXISTS
		requestList.add("READ <CV7771>");
		requestList.add("READ <XV4389>");//EXISTS
	}

}
