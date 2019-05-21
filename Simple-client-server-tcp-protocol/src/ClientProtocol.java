
public interface ClientProtocol {

	abstract void initializeRequestList();
	abstract void establishConnectionWithServer();
	abstract void sendRequestsToServer();
	abstract void printToConsoleIncomingAnswersFromServer();
	abstract void closeConnectionWithServer();
}
