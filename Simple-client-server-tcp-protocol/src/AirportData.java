//import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class AirportData {
	private static final int colNumber = 3;
	private static final int writerDelay = 3;
	private ReadWriteLock lock;
	private Lock writeLock, readLock;

	private ArrayList<String[]> rows = new ArrayList<String[]>();
	public AirportData()
	{
		initializeDummy();
		lock = new ReentrantReadWriteLock();
		writeLock = lock.writeLock();
		readLock = lock.readLock();
	}
	
	public String  removeLine_DELETE(String code)
	{
		boolean found = false;
		try 
		{
			writeLock.lock();
			for(String[] s : rows)
			{
				if(s[0].equals(code))
				{		
					found = true;
					Thread.sleep(1000*writerDelay);//Delay delete procedure so that locks hold on for a big chunk of time and induce traffic.
					rows.remove(s);
					break;
				}
			}				
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			return "DERR";
		}		
		finally
		{
			writeLock.unlock();
		}
		if(found)
			return "DOK";
		else
			return "DERR";
	}
	
	
	public String editLine_MODIFY(String oldCode, String newCode, String newStatus, String newTime)
	{
		boolean found = false;
		try 
		{
			writeLock.lock();
			for(String[] s : rows)
			{
				if(s[0].equals(oldCode))
				{			
					found = true;
					Thread.sleep(1000*writerDelay);//Delay modifying procedure so that locks hold on for a big chunk of time and induce traffic.
					s[0] = newCode;
					s[1] = newStatus;
					s[2] = newTime;
					break;
				}
			}			
			
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			return "MERR";
		}		
		finally
		{
			writeLock.unlock();
		}
		if(found)
			return "MOK";
		else
			return "MERR";
	}
	
	
	/*
	 * Evresh grammhs ston pinaka me anazhthsh vasei tou kwdikou dromologiou mias pthshs.
	 */
	public String[] getLine_READ(String code)
	{
		try
		{
			readLock.lock();
			String[] s = new String[colNumber];
			boolean found = false;
			int counter = 0;
			while(!found)
			{
				if(rows.get(counter)[0].equals(code))
				{
					found = true;
					s = rows.get(counter);
				}
				counter++;
				if(counter>=rows.size())
					break;
			}
			if(found)		
				return s;
			else
				return null;
		}
		finally
		{
			readLock.unlock();
		}
	}

	public String addLine_WRITE(String code, String status, String time)
	{
		try 
		{
			writeLock.lock();
			rows.add(new String[] {code,status,time});
			Thread.sleep(1000*writerDelay);//Delay writing procedure so that locks hold on for a big chunk of time and induce traffic.
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			return "WERR";
		}		
		finally
		{
			writeLock.unlock();
		}
		return "WOK";
	}

	private void initializeDummy()
	{		
		//1-5
		rows.add(new String[] {"XY4352","Arrival","12:40"});
		rows.add(new String[] {"KY5434","Departure","02:35"});
		rows.add(new String[] {"AG4598","Arrival","17:15"});
		rows.add(new String[] {"BD4311","Arrival","12:00"});
		rows.add(new String[] {"XY4389","Departure","23:30"});

		//6-10
		rows.add(new String[] {"DB9761","Arrival","00:55"});		
		rows.add(new String[] {"CJ1231","Departure","00:55"});
		rows.add(new String[] {"XY4433","Departure","00:55"});
		rows.add(new String[] {"XV4389","Departure","00:55"});
		rows.add(new String[] {"XY5422","Arrival","00:55"});

		//11-15
		rows.add(new String[] {"XY6431","Departure","20:25"});
		rows.add(new String[] {"XH9761","Departure","00:00"});
		rows.add(new String[] {"KY5461","Departure","07:15"});
		rows.add(new String[] {"LO9111","Departure","08:55"});
		rows.add(new String[] {"ST0961","Departure","02:30"});

		//16-20
		rows.add(new String[] {"UJ9761","Arrival","00:55"});
		rows.add(new String[] {"ST0821","Arrival","14:05"});
		rows.add(new String[] {"KA0961","Departure","17:45"});
		rows.add(new String[] {"AB1217","Arrival","02:20"});
		rows.add(new String[] {"KL0964","Departure","04:50"});
	}
}
