package act11;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClienteAdivinaNumeroGUI {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 777);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());


        // GUI
        JFrame frame = new JFrame("Cliente - Adivina Numero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 400);
        frame.setLayout(null);

        JTextField text = new JTextField();
        text.setBounds(10, 10, 100, 20);

        JButton button = new JButton("Enviar");
        button.setBounds(120, 10, 100, 20);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBounds(10, 50, 400, 300);

        frame.add(text);
        frame.add(button);
        frame.add(textArea);

        frame.setVisible(true);

        button.addActionListener(e -> {
            int number = Integer.parseInt(text.getText());
            text.setText("");
            try {
                out.writeInt(number);
                out.flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });



        Thread inputThread = new Thread(() -> {
            try {
                while (true) {
                    String message = in.readObject().toString();
                    textArea.append("Servidor: " + message + "\n");

                    if (message.equals("Correcto!")) {
                        text.setEnabled(false);
                        button.setEnabled(false);
                        socket.close();
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        inputThread.start();
    }
}
