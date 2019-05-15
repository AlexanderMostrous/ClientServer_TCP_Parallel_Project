import java.io.IOException;

public class ServerSideMain {

	public static AirportData ad = new AirportData();

	public static void main(String[] args) throws IOException {

	SimpleServerTCP ss1 = new SimpleServerTCP(1234);
	
	ss1.start();
	
	SimpleServerTCP ss2 = new SimpleServerTCP(2345);
	
	ss2.start();


	}

}
