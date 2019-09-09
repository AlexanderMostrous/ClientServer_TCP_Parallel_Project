import java.util.ArrayList;

public abstract class ClientProtocol {

	protected ArrayList<String> requestList = new ArrayList<String>();
	
	public abstract void initializeRequestList();
	
	public ArrayList<String> getRequestList()
	{
		return requestList;
	}
	
}
