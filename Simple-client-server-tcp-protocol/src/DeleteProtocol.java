import java.util.ArrayList;
import java.util.Random;

public class DeleteProtocol extends ClientProtocol{

	@Override
	public void initializeRequestList() 
	{
		ArrayList<String> defaultList = new ArrayList<String>();
		defaultList.add("DELETE <KA0061>");
		defaultList.add("DELETE <LH0029>");
		defaultList.add("DELETE <ME0017>");
		defaultList.add("DELETE <RA1171>");
		defaultList.add("DELETE <ST1189>");
		defaultList.add("DELETE <OM1161>");
		defaultList.add("DELETE <WR9921>");
		defaultList.add("DELETE <OM4461>");
		defaultList.add("DELETE <OU4417>");
		defaultList.add("DELETE <KL4464>");
		defaultList.add("DELETE <GG1164>");
		defaultList.add("DELETE <XY2233>");	
		
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
