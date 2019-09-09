import java.util.ArrayList;
import java.util.Random;

public class ModifyProtocol extends ClientProtocol{

	@Override
	public void initializeRequestList() {
		ArrayList<String> defaultList = new ArrayList<String>();
		defaultList.add("MODIFY <KA0061> <JJ0061> <Arrival> <11:15>");
		defaultList.add("MODIFY <LH0029> <KA1161> <Departure> <10:40>");
		defaultList.add("MODIFY <ME0017> <KA0241> <Departure> <02:40>");
		defaultList.add("MODIFY <RA1171> <KA0061> <Arrival> <12:30>");
		defaultList.add("MODIFY <ST1189> <BB2261> <Arrival> <14:50>");
		defaultList.add("MODIFY <OM1161> <OM4461> <Arrival> <11:30>");
		defaultList.add("MODIFY <WR9921> <KA0017> <Departure> <17:45>");
		defaultList.add("MODIFY <OM4461> <KA0014> <Departure> <12:00>");
		defaultList.add("MODIFY <OU4417> <ST1189> <Arrival> <19:40>");
		defaultList.add("MODIFY <KL4464> <ME0017> <Arrival> <04:50>");
		defaultList.add("MODIFY <GG1164> <LH0029> <Departure> <02:55>");
		defaultList.add("MODIFY <XY2233> <KA0061> <Arrival> <09:20>");	
		
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
