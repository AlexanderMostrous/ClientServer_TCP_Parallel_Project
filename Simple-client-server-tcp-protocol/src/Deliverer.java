import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Deliverer implements Runnable{

	private Socket socket;
	private AirportData data;
	
	public Deliverer(Socket aSocket, AirportData ad){
		this.socket = aSocket;
		this.data = ad;
	}
	@Override
	public void run() {
		System.out.println("Connected: " + socket);
		String clientCommand = "";
        try {
        	Scanner in = new Scanner(socket.getInputStream());
        	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while (in.hasNextLine()) {
                clientCommand = in.nextLine();
            }
            
            if(clientCommand.startsWith("READ"))
            	;
        } catch (Exception e) {
            System.out.println("Error:" + socket);
        } finally {
            try { socket.close(); } catch (IOException e) {}
            System.out.println("Closed: " + socket);
        }
	}
	
	

}
