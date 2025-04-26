package act16;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface DirectoryInterface extends Remote {
    String add(String name) throws RemoteException;

    Vector<String> getNames() throws RemoteException;

    String remove(String name) throws RemoteException;
}
