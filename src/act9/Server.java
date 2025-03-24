package act9;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void  main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(777);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 500);
        frame.setLayout(null);

        JTextField msgTextField = new JTextField();
        msgTextField.setBounds(230, 10, 400, 30);

        JButton button = new JButton("Enviar");
        button.setBounds(650, 10, 75, 30);

        JTextArea inMessagesArea = new JTextArea();
        inMessagesArea.setBounds(230, 50, 400, 400);
        inMessagesArea.setEditable(false);

        DefaultListModel<ManejadorSocket> model = new DefaultListModel<>();
        JList<ManejadorSocket> list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setModel(model);
        list.setBounds(10, 10, 200, 740);

        frame.add(list);
        frame.add(msgTextField);
        frame.add(button);
        frame.add(inMessagesArea);

        frame.setVisible(true);
        frame.setTitle("Server");

        button.addActionListener(e -> {
            String mensaje = msgTextField.getText();
            msgTextField.setText("");

            ManejadorSocket cliente = list.getSelectedValue();
            if (cliente == null) {
                JOptionPane.showMessageDialog(frame, "Por favor seleccione un cliente");
            } else {
                cliente.enviarMensaje(mensaje);
            }
        });

        while (true) {
            Socket cliente = serverSocket.accept();
            ManejadorSocket ms = new ManejadorSocket(cliente, inMessagesArea);
            model.addElement(ms);
            ms.start();
        }
    }
}
