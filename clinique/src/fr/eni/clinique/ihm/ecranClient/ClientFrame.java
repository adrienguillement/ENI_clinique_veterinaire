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
    private JLabel codeLabel, nomLabel, prenomLabel, adresseLabel, codePostalLabel,
            villeLabel, numLabel, assuranceLabel, eMailLabel;
    private JTextField codeTextField, nomTextField, prenomTextField, adresseTextField, codePostalTextField,
            villeTextField, numTextField, assuranceTextField, eMailTextField;
    private Client client;
    private CltManager clientManager;
    private ClientSearchDialog clientSearch;
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

    private JPanel getPanelFormClient(int idClient) {
        JPanel panel = new JPanel();

        try{
            this.nomLabel = new JLabel("Nom: ");
            this.nomTextField = new JTextField(getClient().getNom());

            this.prenomLabel = new JLabel("Prenom: ");
            this.prenomTextField = new JTextField(10);

            this.adresseLabel = new JLabel("Adresse: ");
            this.adresseTextField = new JTextField(10);

            this.codePostalLabel = new JLabel("Code postal: ");
            this.codePostalTextField = new JTextField(10);

            this.villeLabel = new JLabel("Ville: ");
            this.villeTextField = new JTextField(10);

            this.numLabel = new JLabel("Num tel: ");
            this.numTextField = new JTextField(10);

            this.assuranceLabel = new JLabel("Assurance: ");
            this.assuranceTextField = new JTextField(10);

            this.eMailLabel = new JLabel("eMail: ");
            this.eMailTextField = new JTextField(10);
        }catch(NullPointerException e){
            this.codeLabel = new JLabel("Code client : ");
            this.codeTextField = new JTextField(10);
            this.codeTextField.setEditable(false);

            this.nomLabel = new JLabel("Nom: ");
            this.nomTextField = new JTextField(10);

            this.prenomLabel = new JLabel("Prenom: ");
            this.prenomTextField = new JTextField(10);

            this.adresseLabel = new JLabel("Adresse: ");
            this.adresseTextField = new JTextField(10);

            this.codePostalLabel = new JLabel("Code postal: ");
            this.codePostalTextField = new JTextField(10);

            this.villeLabel = new JLabel("Ville: ");
            this.villeTextField = new JTextField(10);

            this.numLabel = new JLabel("Num tel: ");
            this.numTextField = new JTextField(10);

            this.assuranceLabel = new JLabel("Assurance: ");
            this.assuranceTextField = new JTextField(10);

            this.eMailLabel = new JLabel("eMail: ");
            this.eMailTextField = new JTextField(10);
        }

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.gridwidth = 1;

        gbc.gridy = 0;
        gbc.gridx = 0;
        panel.add(nomLabel,gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        panel.add(nomTextField,gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(prenomLabel,gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        panel.add(prenomTextField,gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(adresseLabel,gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        panel.add(adresseTextField,gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(codePostalLabel,gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        panel.add(codePostalTextField,gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        panel.add(villeLabel,gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        panel.add(villeTextField,gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        panel.add(numLabel,gbc);

        gbc.gridy = 5;
        gbc.gridx = 1;
        panel.add(numTextField,gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        panel.add(assuranceLabel,gbc);

        gbc.gridy = 6;
        gbc.gridx = 1;
        panel.add(assuranceTextField,gbc);

        gbc.gridy = 7;
        gbc.gridx = 0;
        panel.add(eMailLabel,gbc);

        gbc.gridy = 7;
        gbc.gridx = 1;
        panel.add(eMailTextField,gbc);


       panel.setVisible(true);
       return panel;
    }

    private ClientSearchDialog getClientSearch(){
        clientSearch = new ClientSearchDialog(parent);

        return clientSearch;
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
            try{
                client = new Client(nomTextField.getText(), prenomTextField.getText(), adresseTextField.getText(), null, codePostalTextField.getText(), villeTextField.getText(), numTextField.getText(), assuranceTextField.getText(), eMailTextField.getText(), null, false);
                clientManager = new CltManager();

                if(clientManager.validerClient(client) == true) {
                    clientManager.insertClient(client);
                    JOptionPane.showMessageDialog(null, "Utilisateur ajouté", null, JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Les champs ne sont pas correctement complétés", null, JOptionPane.WARNING_MESSAGE);
                }
            } catch (BLLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Problème lors de l'ajout", null, JOptionPane.ERROR_MESSAGE);
            }
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
