import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Reader extends Thread{

	private ArrayList<String> requestList;
	private int myPort, requestsSent;
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
		initializeRequestList();
		establishConnectionWithServer();
		sendRequestsToServer();
		printToConsoleIncomingAnswersFromServer();
		closeConnectionWithServer();
	}

	/*
	 * 
	 * Reader will perform a random number of reads.
	 * 
	 */
	private void initializeRequestList()
	{
		ArrayList<String> defaultList = new ArrayList<String>();
		defaultList.add("READ <DB9761>");//EXISTS
		defaultList.add("READ <KT4129>");
		defaultList.add("READ <AB1217>");//EXISTS
		defaultList.add("READ <CV7771>");
		defaultList.add("READ <XV4389>");//EXISTS
		defaultList.add("READ <UJ9761>");//EXISTS
		defaultList.add("READ <ST0821>");//EXISTS
		defaultList.add("READ <KA0961>");//EXISTS
		defaultList.add("READ <AB1217>");//EXISTS
		defaultList.add("READ <KL0964>");//EXISTS
		defaultList.add("READ <GG6264>");
		defaultList.add("READ <XY4433>");//EXISTS
		

		Random rand = new Random();
		int counter = 0;
		int readsLimit = rand.nextInt(defaultList.size());//Random number <= size of defaultlist == number of read requests from server - 1

		//Eksagontai tyxaia stoixeia apo th defaultList kai eisagontai sthn requestList
		do
		{
			int index = rand.nextInt(defaultList.size());
			requestList.add(defaultList.remove(index));
			counter++;
		}while(counter<readsLimit);
		
		
		
		//Amount of requests-responses is:
		requestsSent = requestList.size();
		System.out.println("I am: "+this+" and i will make "+requestsSent+" requests from server.");
	}

	private void establishConnectionWithServer()
	{

		try{
			System.out.println("myHost is: "+myHost+", myPort is: "+myPort);
			mySocket = new Socket(myHost,myPort);

			InputStream is = mySocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			OutputStream os = mySocket.getOutputStream();
			out = new PrintWriter(os,true);

			System.out.println("I am: "+this+". i managed to establish connection to " + myHost);

		} 
		catch (IOException e) 
		{
			System.out.println("Something went really bad!");		}

	}


	private void sendRequestsToServer()
	{

		for(String read: this.requestList)
		{
			out.println(read);
		}

		System.out.println("Reader finished. END message will now be sent to ServerThread.");
		out.println("END");

	}

	private void printToConsoleIncomingAnswersFromServer()
	{
		int responsesReceived = 0;
		try
		{
			Scanner ins = new Scanner(in);
			while(true)
			{
				if(ins.hasNextLine())
				{
					String serverResponse = ins.nextLine();
					//System.out.println("I am "+this+". Response num = #"+responsesReceived+" "+serverResponse);
					responsesReceived++;
					if(!(responsesReceived<requestsSent))
					{
						System.out.println("All responses where gathered.");
						break;
					}
				} 
				else
					System.out.println("Infinity...");
			}
			in.close();
			ins.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	private void closeConnectionWithServer()
	{
		try 
		{
			mySocket.close();
			System.out.println("I am "+this+" Data Socket closed");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public String toString()
	{
		return this.getName();
	}
}
