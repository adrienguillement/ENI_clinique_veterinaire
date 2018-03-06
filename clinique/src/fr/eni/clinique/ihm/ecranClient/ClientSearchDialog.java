package fr.eni.clinique.ihm.ecranClient;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.CltManager;

import javax.swing.*;
import java.awt.*;

public class ClientSearchDialog extends JDialog {

    private JButton rechercher;
    private JTextField rechercherField;
    private ClientTable clientTable;


    public ClientSearchDialog(Frame parent){


        super(parent, "Rechercher un client", true);
        JPanel panel = new JPanel();

        // Field rechercher
        rechercherField = new JTextField();
        rechercherField.setPreferredSize(new Dimension(150,25));

        // Bouton rechercher
        rechercher = new JButton("Rechercher client");
        rechercher.addActionListener(e -> {
            try {
                CltManager clientManager = new CltManager();

                System.out.println(clientManager.searchClient(rechercherField.getText()));
                getClientTable().getModele().setClients(clientManager.searchClient(rechercherField.getText()));
                System.out.println(getClientTable().getModele().getClients());

            } catch (BLLException e1) {
                e1.printStackTrace();
            }
        });

        panel.add(rechercherField);
        panel.add(rechercher);
        panel.add(getClientTable(), BorderLayout.LINE_START);
        panel.setVisible(true);

        getContentPane().add(panel, BorderLayout.CENTER);

        setSize(new Dimension(parent.getWidth(),parent.getHeight()));
        //setResizable(false);
        setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    private ClientTable getClientTable() {

        clientTable = new ClientTable();
        return clientTable;
    }
}
