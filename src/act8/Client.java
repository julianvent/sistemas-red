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
            Socket conection = new Socket("148.226.202.115", 777);
            System.out.println("Se estableció conexión con el servidor");
            
            ObjectInputStream in = new ObjectInputStream(conection.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(conection.getOutputStream());
            
            while (true) {
                out.writeObject(input.nextLine() + " cambio");
                out.flush();
                
                try {
                    String msg = in.readObject().toString();
                    System.out.println("Respuesta de servidor: " + msg);
                } catch (ClassNotFoundException e) {
                    System.out.println("error");
                }

            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
