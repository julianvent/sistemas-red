package RMIEjemplo;


import RMIEjemplo.InterfacesRMI.Operaciones;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMICliente {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            Operaciones calculadora = (Operaciones)registry.lookup("calculadora");
            System.out.println("SUMA: " + calculadora.sumar(12, 11));
            System.out.println("RESTA: " + calculadora.restar(12, 11));
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
