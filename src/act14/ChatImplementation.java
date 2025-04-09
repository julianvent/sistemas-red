package act14;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ChatImplementation extends UnicastRemoteObject implements ChatInterface {
    // user : lastMessage
    private Map<String, String> users;

    public ChatImplementation() throws RemoteException {
        users = new HashMap<String, String>();
    }

    @Override
    public String sendMessage(String user, String message) throws RemoteException {
        users.put(user, message);
        receiveMessage();
        return "Message sent successfully";
    }

    @Override
    public String receiveMessage() throws RemoteException {

    }
}
