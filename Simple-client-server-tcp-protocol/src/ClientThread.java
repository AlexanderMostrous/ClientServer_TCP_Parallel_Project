import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientThread implements Runnable{

	private ArrayList<String> requestList;
	private Socket mySocket;
	private BufferedReader in;
	private PrintWriter out;
	
	public ClientThread(String type)
	{
		if(type=="READER")
		{
			ReaderProtocol rp = new ReaderProtocol();
			rp.initializeRequestList();
			requestList = rp.getRequestList();
		}
		else//type=="WRITER
		{
			WriterProtocol wp = new WriterProtocol();
			wp.initializeRequestList();
			requestList = wp.getRequestList();
		}
	}
	
	@Override
	public void run() 
	{
		establishConnectionWithServer();
		
		String aRequest, aServerResponse;
		while(requestList.size()>0)
		{
			aRequest = requestList.remove(0);//Extract first request of list
			System.out.println("ClientThread says: I am client"+this+". My next request to Server is: "+aRequest+".");
			out.println(aRequest);
			Scanner ins = new Scanner(in);
			while(true)
			{
				if(ins.hasNextLine())
				{
					aServerResponse = ins.nextLine();
					System.out.println("ClientThread says: I am client "+this+". Server responded: "+aServerResponse+".");
				} 
				else
					break;					
			}
			ins.close();
		}

		System.out.println("ClientThread says: I am client"+this+". I just finished my requests to Server and I will now send the \"END\" message.");
		out.println("END");
	}

	private void establishConnectionWithServer() 
	{
		try
		{
			System.out.println("myHost is: "+ClientSideMain.myHost+", myPort is: "+ServerSideMain.myPort);
			mySocket = new Socket(ClientSideMain.myHost,ServerSideMain.myPort);
			InputStream is = mySocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			OutputStream os = mySocket.getOutputStream();
			out = new PrintWriter(os,true);
			System.out.println("ClientThread says: I am client "+this+". "+"I achieved connection with server. My socket is: "+mySocket+".");
		} 
		catch (IOException e) 
		{
			System.out.println("Something went really bad!");		
		}
	}
}
