package act9;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 500);
        frame.setLayout(null);

        JTextField msgTextField = new JTextField();
        msgTextField.setBounds(10, 10, 530, 30);

        JButton button = new JButton("Enviar");
        button.setBounds(550, 10, 75, 30);

        JTextArea inMessagesArea = new JTextArea();
        inMessagesArea.setBounds(10, 50, 630, 400);
        inMessagesArea.setEditable(false);

        frame.add(msgTextField);
        frame.add(button);
        frame.add(inMessagesArea);

        frame.setVisible(true);
        frame.setTitle("Cliente");

        ManejadorSocket cliente = new ManejadorSocket(new Socket("148.226.203.244", 777), inMessagesArea);
        cliente.start();
        button.addActionListener(e -> {
            String mensaje = msgTextField.getText();
            msgTextField.setText("");

            cliente.enviarMensaje(mensaje);
        });
    }
}
