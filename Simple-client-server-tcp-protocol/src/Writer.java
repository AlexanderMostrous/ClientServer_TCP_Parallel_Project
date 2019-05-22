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

public class Writer extends Thread implements ClientProtocol{

	private ArrayList<String> requestList;
	private int myPort, requestsSent;
	private String myHost;
	private Socket mySocket;
	private BufferedReader in;
	private PrintWriter out;

	public Writer(String host, int port)
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
	@Override
	public void initializeRequestList() {
		ArrayList<String> defaultList = new ArrayList<String>();
		defaultList.add("WRITE <DB0061> <Arrival> <11:40>");
		defaultList.add("WRITE <KT0029> <Arrival> <10:40>");
		defaultList.add("WRITE <AB0017> <Arrival> <02:40>");
		defaultList.add("WRITE <CV1171> <Arrival> <12:30>");
		defaultList.add("WRITE <XV1189> <Arrival> <12:10>");
		defaultList.add("WRITE <UJ1161> <Arrival> <17:20>");
		defaultList.add("WRITE <ST9921> <Departure> <17:45>");
		defaultList.add("WRITE <KA4461> <Departure> <12:00>");
		defaultList.add("WRITE <AB4417> <Departure> <19:40>");
		defaultList.add("WRITE <KL4464> <Departure> <14:50>");
		defaultList.add("WRITE <GG1164> <Departure> <04:55>");
		defaultList.add("WRITE <XY2233> <Departure> <08:20>");	
		
		Random rand = new Random();
		int counter = 0;
		int writesLimit = rand.nextInt(defaultList.size());//Random number <= size of defaultlist == number of write requests from server - 1
		//Eksagontai tyxaia stoixeia apo th defaultList kai eisagontai sthn requestList
		do
		{
			int index = rand.nextInt(defaultList.size());
			requestList.add(defaultList.remove(index));
			counter++;
		}while(counter<writesLimit);
		
		//Amount of requests-responses is:
		requestsSent = requestList.size();

	}

	@Override
	public void establishConnectionWithServer() {
		try{
			System.out.println("myHost is: "+myHost+", myPort is: "+myPort);
			mySocket = new Socket(myHost,myPort);
			InputStream is = mySocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			OutputStream os = mySocket.getOutputStream();
			out = new PrintWriter(os,true);
		} 
		catch (IOException e) 
		{
			System.out.println("Something went really bad!");		}
	}

	@Override
	public void sendRequestsToServer() {
		for(String write: this.requestList)
		{
			out.println(write);
		}
		out.println("END");
	}

	@Override
	public void printToConsoleIncomingAnswersFromServer() {

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
				{
					System.out.println("Infinity...");
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			}
			in.close();
			ins.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void closeConnectionWithServer() {
		try 
		{
			mySocket.close();
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
