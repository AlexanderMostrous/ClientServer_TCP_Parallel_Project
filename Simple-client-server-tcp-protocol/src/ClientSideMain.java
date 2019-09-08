import java.io.IOException;
public class ClientSideMain {
	public static final String myHost = "localhost";

	public static void main(String args[]) throws IOException {

		Reader r = new Reader();
		Writer w = new Writer();
		
		r.start();
		w.start();

	}
}			

