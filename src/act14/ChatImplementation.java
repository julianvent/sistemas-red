package act14;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ChatImplementation extends UnicastRemoteObject implements ChatInterface {
    // user : ultimo mensaje
    private final Map<String, String> users;

    public ChatImplementation() throws RemoteException {
        users = new HashMap<>();
    }

    @Override
    public String sendMessage(String toUser, String message) throws RemoteException {
        users.put(toUser, message); // guarda al usuario destinatario y el último mensaje que se le envió
        return message;
    }

    @Override
    public String receiveMessage(String user) throws RemoteException {
        String message = users.get(user); // consigue el último mensaje enviado el usuario
        users.replace(user, ""); // el mensaje ha sido recibido
        return message;
    }
}
