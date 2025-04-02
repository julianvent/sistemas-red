package act12;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloRemote extends Remote {
    String sayHello() throws RemoteException;
}
