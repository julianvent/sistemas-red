package act12;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImplementation extends UnicastRemoteObject implements HelloRemote {
    protected HelloImplementation() throws RemoteException {}

    @Override
    public String sayHello() throws RemoteException {
        return "Hello world!";
    }
    
}
