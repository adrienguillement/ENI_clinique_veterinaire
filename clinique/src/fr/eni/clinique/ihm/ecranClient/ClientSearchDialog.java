package fr.eni.clinique.ihm.ecranClient;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.CltManager;

import javax.swing.*;
import java.awt.*;

public class ClientSearchDialog extends JDialog {

    private JButton rechercher;
    private JTextField rechercherField;
    private ClientTable clientTable;
    private ClientFrame clientFrame;


    public ClientSearchDialog(Frame parent, ClientFrame clientFrame){
        super(parent, "Rechercher un client", true);
        this.clientFrame = clientFrame;

        JPanel panel = new JPanel();

        // Field rechercher
        rechercherField = new JTextField();
        rechercherField.setPreferredSize(new Dimension(150,25));

        // Bouton rechercher
        rechercher = new JButton("Rechercher client");
        rechercher.addActionListener(e -> {
            try {
                CltManager clientManager = new CltManager();
                clientTable.getModele().setClients(clientManager.searchClient(rechercherField.getText()));
            } catch (BLLException e1) {
                e1.printStackTrace();
            }
        });

        panel.add(rechercherField);
        panel.add(rechercher);
        panel.add(this.getClientTable().getTableHeader(),BorderLayout.NORTH);
        panel.add(getClientTable(), BorderLayout.CENTER);

        panel.setVisible(true);

        getContentPane().add(panel, BorderLayout.CENTER);

        setSize(new Dimension(500, 250));
        //setResizable(false);
        setLocationRelativeTo(parent);
        this.setResizable(false);
        this.setVisible(true);
    }

    private ClientTable getClientTable() {

        clientTable = new ClientTable(clientFrame, this);
        return clientTable;
    }
}
