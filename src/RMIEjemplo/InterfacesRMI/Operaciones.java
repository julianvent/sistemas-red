package RMIEjemplo.InterfacesRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Operaciones extends Remote {
    double sumar(double num1, double num2) throws RemoteException;
    double restar(double num1, double num2) throws RemoteException;

    void asignar(double num) throws RemoteException;
    double recuperar() throws RemoteException;
}
