package RMIEjemplo;

import RMIEjemplo.InterfacesRMI.Operaciones;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OperacionesImp extends UnicastRemoteObject implements Operaciones {
    private double valor;

    public OperacionesImp() throws RemoteException {}

    @Override
    public double sumar(double num1, double num2) throws RemoteException {
        return num1 + num2;
    }

    @Override
    public double restar(double num1, double num2) throws RemoteException {
        return num1 - num2;
    }

    @Override
    public void asignar(double num) throws RemoteException {
        valor = num;
    }

    @Override
    public double recuperar() throws RemoteException {
        return valor;
    }
}
