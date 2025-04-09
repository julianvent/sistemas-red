package act14;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatInterface extends Remote {
    String sendMessage(String user, String message) throws RemoteException;
    String receiveMessage() throws RemoteException;
}
