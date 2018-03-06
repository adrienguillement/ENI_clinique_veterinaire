package fr.eni.clinique.ihm.ecranClient;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.CltManager;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.ihm.IHMapp;
import fr.eni.clinique.ihm.ecranPersonnel.PersonnelAjout;
import fr.eni.clinique.ihm.login.LoginDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class ClientFrame extends JInternalFrame {

    private JButton ajouter, rechercher;
    private JTextField rechercherField;
    private ClientTable panelSearch;
    private JPanel panel_client;
    private JPanel panel_client_result, panel_client_buttons;
    private JPanel panel_client_add;

    private Client client;
    private CltManager clientManager;
    private ClientSearchDialog clientSearch;
    private ClientAddDialog clientAddDialog;
    private JFrame parent;

    /**
     * Constructeur
     */
    public ClientFrame(JFrame parent) {
        super("Gestion des client", true, true, true,true);
        this.parent = parent;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBounds(0, 0, 900, 600);
        setContentPane(getPanelClient(2));
    }


    private JPanel getPanelClient(int idClient){
        JPanel panel = new JPanel();
        panel.add(getPanelButton());
        panel.add(getPanelFormClient(2));

        panel.setVisible(true);
        return panel;
    }

    private ClientForm getPanelFormClient(int idClient) {
        ClientForm panel = new ClientForm(idClient);
        return panel;
    }

    private ClientSearchDialog getClientSearch(){
        clientSearch = new ClientSearchDialog(parent);

        return clientSearch;
    }

    private ClientAddDialog getClientAddDialog() {
        clientAddDialog = new ClientAddDialog(parent);

        return clientAddDialog;
    }

    /**
     * Initialisation panel button
     * @return
     */
    private JPanel getPanelButton() {
        JPanel panelBoutton = new JPanel();

        // Bouton rechercher
        rechercher = new JButton("Rechercher");
        rechercher.addActionListener(e -> {
            getClientSearch();
        });

        // Bouton ajouter
        ajouter = new JButton("Ajouter client");
        ajouter.addActionListener(e -> {
            getClientAddDialog();
        });

        panelBoutton.add(ajouter);
        panelBoutton.add(rechercher);
        return panelBoutton;
    }

    private JPanel getPanelSearchButton() {
        JPanel panelBoutton = new JPanel();

        // Field rechercher
        rechercherField = new JTextField();
        rechercherField.setPreferredSize(new Dimension(150,25));

        // Bouton rechercher
        rechercher = new JButton("Rechercher client");
        rechercher.addActionListener(e -> getPanelSearch());

        panelBoutton.add(rechercherField);
        panelBoutton.add(rechercher);
        return panelBoutton;
    }

    private ClientTable getPanelSearch() {

        if(rechercherField.getText().trim().length() != 0) {
            System.out.println("rechercherField non null" + rechercherField.getText());
            panelSearch = new ClientTable();

        } else {
            panelSearch = new ClientTable();
        }

        return panelSearch;
    }

    public void getClientSelected(Client client){
        this.getPanelSearch().setVisible(false);
        getPanelClient(client.getCode());
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
