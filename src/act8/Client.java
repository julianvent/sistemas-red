package act8;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            Socket conection = new Socket("148.226.203.189", 777);
            System.out.println("Se estableció conexión con el servidor");

            ObjectOutputStream out = new ObjectOutputStream(conection.getOutputStream());
            out.writeObject(input.nextLine());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
