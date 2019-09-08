import java.util.ArrayList;

public abstract class ClientProtocol {

	protected ArrayList<String> requestList;
	
	public abstract void initializeRequestList();
	public ArrayList<String> getRequestList()
	{
		return requestList;
	}
	
}
