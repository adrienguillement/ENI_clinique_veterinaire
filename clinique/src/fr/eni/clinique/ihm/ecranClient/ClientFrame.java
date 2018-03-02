package fr.eni.clinique.ihm.ecranClient;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import fr.eni.clinique.ihm.IHMapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrame extends JInternalFrame {

    private JButton ajouter, rechercher;
    private JTextField rechercherField;
    private ClientTable panelSearch;


    /**
     * Constructeur
     */
    public ClientFrame(int idClient) {
        super("Gestion des client", true, true, true,true);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBounds(0, 0, 900, 600);
        setContentPane(getPanelClient(idClient));
    }

    /**
     * Initialisation panel client
     * @return
     */
    private JPanel getPanelClient(int idClient) {
        JPanel panelClient = new JPanel();
        panelClient.setOpaque(true);

        panelClient.add(getPanelSearch(), BorderLayout.LINE_START);
        return panelClient;
    }

    /**
     * Initialisation panel button
     * @return
     */
    private JPanel getPanelButton() {
        JPanel panelBoutton = new JPanel();

        // Bouton ajouter
        ajouter = new JButton("Ajouter client");
        ajouter.addActionListener(e -> System.out.println("Ajouter client"));

        // Bouton rechercher
        rechercher = new JButton("Rechercher client");
        rechercher.addActionListener(e -> System.out.println("rechercher client"));

        panelBoutton.add(rechercherField);
        panelBoutton.add(rechercher);
        panelBoutton.add(ajouter);
        return panelBoutton;
    }

    private ClientTable getPanelSearch() {
        if(panelSearch == null) {
            panelSearch = new ClientTable();
        }
        return panelSearch;
    }
}
