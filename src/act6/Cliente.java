package act6;

import java.io.*;
import java.net.Socket;

public class Cliente {
    private Socket clientSocket;
    public BufferedReader in;
    public PrintWriter out;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        return in.readLine();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }

        System.out.print("Enviar mensaje: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Cliente cliente = new Cliente();
        cliente.startConnection("localhost", 8080);
        String response = cliente.sendMessage(in.readLine());
        System.out.println(response);
        cliente.stopConnection();
    }
}
