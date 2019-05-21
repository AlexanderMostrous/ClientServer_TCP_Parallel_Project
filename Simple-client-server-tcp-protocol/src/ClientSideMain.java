import java.net.*;
import java.io.*;
public class ClientSideMain {
	private static final String HOST1 = "localhost",HOST2 = HOST1;
	private static final int PORT1 = 1234, PORT2 = 2345;

	public static void main(String args[]) throws IOException {

		Reader r = new Reader(HOST1,PORT1);
		Writer w = new Writer(HOST2,PORT2);
		
		r.start();
		w.start();

	}
}			

