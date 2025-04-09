package act14;

import RMIEjemplo.InterfacesRMI.Operaciones;
import RMIEjemplo.OperacionesImp;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 500);
        frame.setLayout(null);

        JTextField msgTextField = new JTextField();
        msgTextField.setBounds(10, 10, 400, 30);

        JButton sendButton = new JButton("Enviar");
        sendButton.setBounds(430, 10, 75, 30);

        JTextArea inMessagesArea = new JTextArea();
        inMessagesArea.setBounds(10, 50, 475, 400);
        inMessagesArea.setEditable(false);

        frame.add(msgTextField);
        frame.add(sendButton);
        frame.add(inMessagesArea);

        frame.setVisible(true);
        frame.setTitle("Server");

        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            ChatInterface chat = new ChatImplementation();
            registry.rebind("chat", chat);
            System.out.println("Servidor RMI listo :DDDDD");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
