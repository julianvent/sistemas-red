package act16;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(1099);
        DirectoryInterface directory = new DirectoryImplementation();
        registry.rebind("directory", directory);
        System.out.println("Server ready");
    }
}
