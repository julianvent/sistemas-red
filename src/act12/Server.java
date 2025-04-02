package act12;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            HelloImplementation object = new HelloImplementation();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Hello", object);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception " + e.toString());
            e.printStackTrace();
        }
    }
}
