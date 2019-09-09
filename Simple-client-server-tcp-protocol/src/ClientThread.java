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
	private int myPort;
	
	public ClientThread(String type,int port)
	{
		myPort = port;
		if(type=="READER")
		{
			ReadProtocol rp = new ReadProtocol();
			rp.initializeRequestList();
			requestList = rp.getRequestList();
		}
		else if(type=="WRITER")
		{
			WriteProtocol wp = new WriteProtocol();
			wp.initializeRequestList();
			requestList = wp.getRequestList();
		}
		else if(type=="MODIFIER")
		{
			ModifyProtocol mp = new ModifyProtocol();
			mp.initializeRequestList();
			requestList = mp.getRequestList();
		}
		else if(type=="DELETER")
		{
			DeleteProtocol dp = new DeleteProtocol();
			dp.initializeRequestList();
			requestList = dp.getRequestList();
		}
	}
	
	@Override
	public void run() 
	{
		establishConnectionWithServer();
		
		String aRequest, aServerResponse;
		Scanner ins = new Scanner(in);
		while(requestList.size()>0)
		{
			aRequest = requestList.remove(0);//Extract first request of list
			Logger.writeInTxtFile("ClientThread says: I am client"+this+". My next request to Server is: "+aRequest+".");
			out.println(aRequest);
			while(true)
			{
				if(ins.hasNextLine())
				{
					aServerResponse = ins.nextLine();
					Logger.writeInTxtFile("ClientThread says: I am client "+this+". Server responded: "+aServerResponse+".");
					break;
				}					
			}
		}
		Logger.writeInTxtFile("ClientThread says: I am client"+this+". I just finished my requests to Server and I will now send the \"END\" message.");
		out.println("END");
		ins.close();
	}

	private void establishConnectionWithServer() 
	{
		try
		{
			Logger.writeInTxtFile("ClientThread says: myHost is = "+ClientSideMain.myHost+", myPort is = "+this.myPort);
			mySocket = new Socket(ClientSideMain.myHost,this.myPort);
			InputStream is = mySocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			OutputStream os = mySocket.getOutputStream();
			out = new PrintWriter(os,true);
			Logger.writeInTxtFile("ClientThread says: I am client "+this+". "+"I achieved connection with server.\n My socket is: "+mySocket+" and I will make "+requestList.size()+" requests.");
		} 
		catch (IOException e) 
		{
			Logger.writeInTxtFile("ClientThread "+this+" says: establishConnectionWithServer() crashed. Something went really bad!");		
		}
	}
}
