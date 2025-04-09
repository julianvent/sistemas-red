package act14;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatImplementation extends UnicastRemoteObject implements ChatInterface {
    public ChatImplementation() throws RemoteException {
    }

    @Override
    public void sendMessage(String message) throws RemoteException {
        System.out.println(message);
    }
}
