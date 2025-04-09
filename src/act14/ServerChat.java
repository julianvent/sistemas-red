package act14;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerChat {
    public Registry registry;
    private static JTextArea inbox;
    private JTextField msgTextField;
    private JButton sendButton;
    private JFrame frame;
    private static ChatInterface chat;

    private ServerChat() throws RemoteException, NotBoundException {
        createRegistry();
        createUI();
        chat = (ChatInterface) registry.lookup("chat");
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        new ServerChat();

        new Thread(() -> {
            while (true) {
                try {
                    String msg = chat.receiveMessage("Server");
                    if (msg != null && !msg.isEmpty()) {
                        inbox.append("Cliente: " + msg + '\n');
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void createRegistry() {
        try {
            registry = LocateRegistry.createRegistry(1099);
            ChatInterface chat = new ChatImplementation();
            registry.rebind("chat", chat);
            System.out.println("Servidor RMI listo :DDDDD");
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

        sendButton.addActionListener(e -> {
           String msg = msgTextField.getText();
           msgTextField.setText("");
            try {
                chat.sendMessage("Client", msg);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
