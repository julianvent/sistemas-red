package act16;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class DirectoryImplementation extends UnicastRemoteObject implements DirectoryInterface {
    final Vector<String> names;

    public DirectoryImplementation() throws RemoteException {
        this.names = new Vector<>();
    }

    @Override
    public String add(String name) throws RemoteException {
        names.add(name);
        System.out.println("Added: " + name);
        return name;
    }

    @Override
    public Vector<String> getNames() throws RemoteException {
        return names;
    }

    @Override
    public String remove(String name) throws RemoteException {
        names.remove(name);
        System.out.println("Removed: " + name);
        return name;
    }
}
