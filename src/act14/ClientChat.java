package act14;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientChat {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 500);
        frame.setLayout(null);

        JTextField msgTextField = new JTextField();
        msgTextField.setBounds(10, 10, 400, 30);

        JButton sendButton = new JButton("Enviar");
        sendButton.setBounds(430, 10, 75, 30);

        JTextArea inbox = new JTextArea();
        inbox.setBounds(10, 50, 475, 400);
        inbox.setEditable(false);

        frame.add(msgTextField);
        frame.add(sendButton);
        frame.add(inbox);

        frame.setVisible(true);
        frame.setTitle("Cliente");

        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            ChatInterface chat = (ChatInterface) registry.lookup("chat");

            sendButton.addActionListener(e -> {
                String msg = msgTextField.getText();
                msgTextField.setText("");
                try {
                    chat.sendMessage("Server", msg);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            });

            new Thread(() -> {
                while (true) {
                    try {
                        String msg = chat.receiveMessage("Client");
                        if (msg != null && !msg.isEmpty()) {
                            inbox.append("Server: " + msg + '\n');
                        }
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();

        } catch (Exception e) {
            System.err.println("Client exception " + e);
        }
    }
}
