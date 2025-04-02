package act12;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(null, 1099);
            HelloRemote stub = (HelloRemote) registry.lookup("Hello");
            String response = stub.sayHello();
            System.out.println("Response: " + response); 
        } catch (Exception e) {
            System.err.println("Client exception " + e.toString());
            e.printStackTrace();
        }
    }
}
