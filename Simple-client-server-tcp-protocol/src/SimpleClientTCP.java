import java.net.*;
import java.io.*;
public class SimpleClientTCP {
	private static final String HOST1 = "localhost",HOST2 = HOST1;
	private static final int PORT1 = 1234, PORT2 = 2345;

	public static void main(String args[]) throws IOException {

		Reader r1 = new Reader(HOST1,PORT1);
		Reader r2 = new Reader(HOST2,PORT2);
		
		r1.start();
		r2.start();

	}
}			

