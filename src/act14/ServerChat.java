package act14;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerChat extends UnicastRemoteObject implements ChatInterface {
    public static Registry registry;
    private JTextArea inbox;
    private JTextField msgTextField;
    private JButton sendButton;
    private JFrame frame;
    private ChatInterface serverChat;

    private ServerChat() throws RemoteException {
        createUI();
    }

    public static void main(String[] args) throws RemoteException {
        ChatInterface serverChat = createRegistry();
    }

    private static ChatInterface createRegistry() {
        try {
            registry = LocateRegistry.createRegistry(1099);
            ChatInterface chat = new ServerChat();
            registry.rebind("chat", chat);
            System.out.println("Servidor RMI listo :DDDDD");
            return chat;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private void createUI() {
        frame = new JFrame();
        inbox = new JTextArea();
        msgTextField = new JTextField();
        sendButton = new JButton("Enviar");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 500);
        frame.setLayout(null);

        msgTextField.setBounds(10, 10, 400, 30);
        sendButton.setBounds(430, 10, 75, 30);
        inbox.setBounds(10, 50, 475, 400);
        inbox.setEditable(false);

        frame.add(msgTextField);
        frame.add(sendButton);
        frame.add(inbox);

        frame.setVisible(true);
        frame.setTitle("Server");
    }

    @Override
    public void sendMessage(String message) throws RemoteException {
        inbox.append(message + "\n");
    }
}
