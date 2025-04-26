package act16;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    final DirectoryInterface directory;
    private DefaultListModel<String> listModel;


    public Client() throws NotBoundException, RemoteException {
        directory = getRegistryObject();
        initComponents();
    }

    public static void main(String[] args) throws NotBoundException, RemoteException {
        new Client();
    }

    private DirectoryInterface getRegistryObject() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(1099);
        return (DirectoryInterface) registry.lookup("directory");
    }

    private void initComponents() throws RemoteException {
        JFrame frame = new JFrame();
        frame.setTitle("Cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 550);
        frame.setLayout(null);

        JTextField nameField = new JTextField();
        nameField.setBounds(10, 0, 400, 30);

        JButton sendButton = new JButton("Enviar");
        sendButton.setBounds(410, 0, 75, 30);

        listModel = new DefaultListModel<>();
        updateListModel();
        JList<String> nameList = new JList<>();
        nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nameList.setModel(listModel);
        nameList.setBounds(10, 40, 475, 400);

        JButton deleteButton = new JButton("Eliminar seleccionado");
        deleteButton.setBounds(10, 450, 175, 30);

        JButton updateButton = new JButton("Actualizar");
        updateButton.setBounds(195, 450, 100, 30);

        frame.add(nameField);
        frame.add(sendButton);
        frame.add(nameList);
        frame.add(deleteButton);
        frame.add(updateButton);

        frame.setVisible(true);

        sendButton.addActionListener(e ->
            {
            String name = nameField.getText().trim();
            nameField.setText("");
            if (!name.isEmpty()) {
                try {
                    directory.add(name);
                    updateListModel();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
            });

        deleteButton.addActionListener(e ->
            {
            String selectedName = nameList.getSelectedValue();
            if (selectedName != null) {
                try {
                    directory.remove(selectedName);
                    updateListModel();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor seleccione un nombre");
            }
            });

        updateButton.addActionListener(e ->
            {
            try {
                updateListModel();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            });
    }

    private void updateListModel() throws RemoteException {
        listModel.removeAllElements();
        listModel.addAll(directory.getNames());
    }
}
