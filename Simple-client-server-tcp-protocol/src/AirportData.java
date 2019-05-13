//import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class AirportData {
	private static final int colNumber = 3;
	
	private ArrayList<String[]> rows = new ArrayList<String[]>();
	public AirportData()
	{
		initializeDummy();
	}
	
	
	/*
	 * Evresh grammhs ston pinaka me anazhthsh vasei tou kwdikou dromologiou mias pthshs.
	 */
	public String[] getLine_READ(String code)
	{
		String[] s = new String[colNumber];
		
		boolean found = false;
		int counter = 0;
		
		while(!found)
		{
			System.out.println("Not found yet!!!");
			System.out.println("rows.get(counter)[0] = "+rows.get(counter)[0]+" and code = "+code);
			
			
			if(rows.get(counter)[0].equals(code))
			{
				found = true;
				s = rows.get(counter);
			}

			counter++;

			if(counter>rows.size())
				break;
		}
		
		if(found)		
			return s;
		else
			return null;
	}
	
	public String addLine_WRITE(String code, String status, String time)
	{
		return "WOK";
	}
	
	private void initializeDummy()
	{
		rows.add(new String[] {"XY4352","Arrival","12:40"});
		rows.add(new String[] {"KY5434","Departure","02:35"});
		rows.add(new String[] {"AG4598","Arrival","17:15"});
		rows.add(new String[] {"BD4311","Arrival","12:00"});
		rows.add(new String[] {"XY4389","Departure","23:30"});
		rows.add(new String[] {"XY9761","Departure","00:55"});
	}
}
