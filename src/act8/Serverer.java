package act8;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serverer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(777);
            System.out.println("Esperando conexión...");

            Socket conection = server.accept();
            System.out.println("Conexion establecida con " + conection.getInetAddress().toString());

            ObjectInputStream in = new ObjectInputStream(conection.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(conection.getOutputStream());

            conection.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
