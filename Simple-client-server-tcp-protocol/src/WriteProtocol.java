import java.util.ArrayList;
import java.util.Random;

public class WriteProtocol extends ClientProtocol{

	
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

	}
}
