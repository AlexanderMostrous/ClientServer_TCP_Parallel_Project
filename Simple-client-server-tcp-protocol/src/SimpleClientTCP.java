import java.net.*;
import java.io.*;
public class SimpleClientTCP {
	private static final String HOST = "localhost";
	private static final int PORT = 1234;

	public static void main(String args[]) throws IOException {

		//InetAddress address = InetAddress.getByName(HOST);
		Socket socket = new Socket(HOST, PORT);

		InputStream is = socket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = socket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);

		System.out.println("Connection to " + HOST + " established");

		//String inmsg, outmsg;
		//ClientProtocol app = new ClientProtocol();

		//outmsg = app.prepareRequest();
		out.println("XY4389");
		String inmsg = in.readLine();
		//app.processReply(inmsg);
		
		System.out.println("Client says: "+ inmsg);

		socket.close();
		System.out.println("Data Socket closed");
	}
}			

