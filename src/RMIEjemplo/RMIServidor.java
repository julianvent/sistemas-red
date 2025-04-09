package RMIEjemplo;

import RMIEjemplo.InterfacesRMI.Operaciones;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServidor {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            Operaciones operaciones = new OperacionesImp();

            registry.rebind("calculadora", operaciones);

            System.out.println("Servidor RMI listo :DDDDD");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
