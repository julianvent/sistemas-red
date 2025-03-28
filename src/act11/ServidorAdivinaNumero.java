package act11;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServidorAdivinaNumero {
    static int number;

    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        number = rand.nextInt(101);

        ServerSocket ss = new ServerSocket(777);

        Socket cliente = ss.accept();

        System.out.println("Cliente conectado");
        System.out.println("Numero secreto: " + number);

        ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());

        while (true) {
            int guess = in.readInt();
            if (guess == number) {
                System.out.println("Juego finalizado. Cerrando conexion");
                out.writeObject("Correcto!");
                out.flush();
                ss.close();
                break;
            } else if (guess > number) {
                out.writeObject("Muy alto");
            } else {
                out.writeObject("Muy bajo");
            }
            out.flush();
        }
    }
}
