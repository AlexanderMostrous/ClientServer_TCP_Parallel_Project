import java.util.ArrayList;
import java.util.Random;

public class ReadProtocol extends ClientProtocol{
	
	public void initializeRequestList()
	{
		ArrayList<String> defaultList = new ArrayList<String>();
		defaultList.add("READ <DB9761>");//EXISTS
		defaultList.add("READ <KT4129>");
		defaultList.add("READ <AB1217>");//EXISTS
		defaultList.add("READ <CV7771>");
		defaultList.add("READ <XV4389>");//EXISTS
		defaultList.add("READ <UJ9761>");//EXISTS
		defaultList.add("READ <ST0821>");//EXISTS
		defaultList.add("READ <KA0961>");//EXISTS
		defaultList.add("READ <AB1217>");//EXISTS
		defaultList.add("READ <KL0964>");//EXISTS
		defaultList.add("READ <GG6264>");
		defaultList.add("READ <XY4433>");//EXISTS
		
		Random rand = new Random();
		int counter = 0;
		int readsLimit = rand.nextInt(defaultList.size());//Random number <= size of defaultlist == number of read requests from server - 1

		//Eksagontai tyxaia stoixeia apo th defaultList kai eisagontai sthn requestList
		do
		{
			int index = rand.nextInt(defaultList.size());
			requestList.add(defaultList.remove(index));
			counter++;
		}while(counter<readsLimit);
	}
}
