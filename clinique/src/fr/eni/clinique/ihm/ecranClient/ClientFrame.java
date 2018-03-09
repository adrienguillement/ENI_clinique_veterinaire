package fr.eni.clinique.ihm.ecranClient;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.CltManager;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.ihm.IHMapp;
import fr.eni.clinique.ihm.ecranAnimal.AnimalDialog;
import fr.eni.clinique.ihm.ecranAnimal.AnimalTable;
import fr.eni.clinique.ihm.ecranAnimal.AnimalTableModele;
import fr.eni.clinique.ihm.ecranPersonnel.PersonnelAjout;
import fr.eni.clinique.ihm.login.LoginDialog;
import javafx.scene.layout.BorderPane;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class ClientFrame extends JInternalFrame {

    private JButton ajouter, rechercher, modifier, supprimer, annuler;
    private JButton ajouterAnimal, modifierAnimal, supprimerAnimal;
    private JTextField rechercherField;
    private ClientTable panelSearch;
    private JPanel panel_client;
    private JPanel panel_client_result, panel_client_buttons;
    private JPanel panel_client_add;
    private Client client;
    private JTextField code, nom, prenom, adresse, ville, codePostal, assurance, email, numTel, remarque;
    private CltManager clientManager = new CltManager();

    private AnimalTable animalTable;
    private Animal selectedAnimal;
    private AnimalTableModele animalTableModel;
    private AnimalManager animalManager = new AnimalManager();

    private ClientSearchDialog clientSearch;
    private ClientAddDialog clientAddDialog;
    private AnimalDialog animalDialog;
    private JFrame parent;

    /**
     * Constructeur
     */
    public ClientFrame(JFrame parent) throws BLLException {
        super("Gestion des client", true, true, true,true);
        this.parent = parent;

        client = clientManager.getClientById(clientManager.getFirst().getCode());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBounds(0, 0, 900, 600);
        clientManager = new CltManager();
        setContentPane(getPanelClient());
    }


    private JPanel getPanelClient(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(getPanelButton(), BorderLayout.PAGE_START);

        try {
            client = clientManager.getFirst();
            panel.add(getPanelFormClient(), BorderLayout.LINE_START);
        } catch (BLLException e) {
            e.printStackTrace();
        }

        panel.add(getPanelAnimaux());

        panel.setVisible(true);
        return panel;
    }

    /**
     * Panel correspondant à la gestion des animaux
     * @return
     */
    private JPanel getPanelAnimaux() {

        JPanel panelAnimal = new JPanel();

        JPanel tableAnimal = new JPanel();
        tableAnimal.setLayout(new BorderLayout());
        getTableAnimal();

        tableAnimal.add(animalTable.getTableHeader(), BorderLayout.NORTH);
        tableAnimal.add(getTableAnimal(), BorderLayout.CENTER);

        panelAnimal.add(tableAnimal);
        panelAnimal.add(getPanelButtonAnimal());
        return panelAnimal;
    }

    /**
     * Boutons pour gérer les animaux
     * @return
     */
    private JPanel getPanelButtonAnimal() {
        JPanel panelButton = new JPanel();

        // Bouton ajouter un animal
        ajouterAnimal = new JButton("Ajouter");
        ajouterAnimal.addActionListener(e -> getAnimalDialog());

        // Bouton modifier un animal
        modifierAnimal = new JButton("Modifier");
        modifierAnimal.addActionListener(e -> {
            selectedAnimal = animalTable.getModele().getAnimaux().get(animalTable.getSelectedRow());
            getAnimalDialog(selectedAnimal);
        });

        supprimerAnimal = new JButton("Supprimer");
        supprimerAnimal.addActionListener(e -> {
            try{
                animalManager = new AnimalManager();
                selectedAnimal = animalTable.getModele().getAnimaux().get(animalTable.getSelectedRow());
                animalManager.deleteAnimal(selectedAnimal);
                animalTable.getModele().setAnimaux(animalManager.getFromClient(client));
                JOptionPane.showMessageDialog(null, "Animal supprimé.", null, JOptionPane.INFORMATION_MESSAGE);

            } catch (BLLException error){
                error.printStackTrace();
                JOptionPane.showMessageDialog(null, "Problème lors de la suppression du client.", null, JOptionPane.ERROR_MESSAGE);

            }

        });

        panelButton.add(ajouterAnimal);
        panelButton.add(supprimerAnimal);
        panelButton.add(modifierAnimal);

        return panelButton;
    }

    /**
     * JDialog ajout animal sans animal sélectionné
     * @return
     */
    private AnimalDialog getAnimalDialog() {
        animalDialog = new AnimalDialog(parent, this);
        animalDialog.setVisible(true);

        return animalDialog;
    }

    /**
     * JDialog ajout animal avec animal sélectionné
     * @param selectedAnimal
     * @return
     */
    private AnimalDialog getAnimalDialog(Animal selectedAnimal) {
        animalDialog = new AnimalDialog(parent, selectedAnimal, this);
        animalDialog.setVisible(true);
        return animalDialog;
    }

    private AnimalTable getTableAnimal() {
        animalTable = new AnimalTable(client);
        return animalTable;
    }

    private JPanel getPanelFormClient() throws BLLException {
        JPanel panelBorder = new JPanel();
        panelBorder.setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Code : "), gbc);

        gbc.gridx = 1;
        this.code = new JTextField(String.valueOf(client.getCode()));
        this.code.setEditable(false);
        JTextField codeClient = this.code;
        codeClient.setPreferredSize(new Dimension(120,20));
        panel.add(codeClient, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Nom : "), gbc);
        nom = new JTextField(client.getNom());
        nom.setPreferredSize(new Dimension(120,20));
        gbc.gridx = 1;
        panel.add(this.nom, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Prenom : "), gbc);
        prenom = new JTextField(client.getPrenomClient());
        prenom.setPreferredSize(new Dimension(120,20));
        gbc.gridx = 1;
        panel.add(this.prenom, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Email : "), gbc);
        email = new JTextField(client.getEmail());
        email.setPreferredSize(new Dimension(120,20));
        gbc.gridx = 1;
        panel.add(this.email, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Adresse : "), gbc);
        adresse = new JTextField(client.getAdresse1());
        adresse.setPreferredSize(new Dimension(120,20));
        gbc.gridx = 1;
        panel.add(this.adresse, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Code postal : "), gbc);
        codePostal = new JTextField(client.getCodePostal());
        codePostal.setPreferredSize(new Dimension(120,20));
        gbc.gridx = 1;
        panel.add(this.codePostal, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Ville : "), gbc);
        ville = new JTextField(client.getVille());
        ville.setPreferredSize(new Dimension(120,20));
        gbc.gridx = 1;
        panel.add(this.ville, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(new JLabel("Assurance : "), gbc);
        assurance = new JTextField(client.getAssurance());
        assurance.setPreferredSize(new Dimension(120,20));
        gbc.gridx = 1;
        panel.add(this.assurance, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(new JLabel("Numéro tel : "), gbc);
        numTel = new JTextField(client.getNumTel());
        numTel.setPreferredSize(new Dimension(120,20));
        gbc.gridx = 1;
        panel.add(this.numTel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(new JLabel("Remarque : "), gbc);
        remarque = new JTextField(client.getRemarque());
        remarque.setPreferredSize(new Dimension(120,20));
        gbc.gridx = 1;
        panel.add(this.remarque, gbc);

        panelBorder.add(panel, BorderLayout.NORTH);
        return panelBorder;
    }

    private ClientSearchDialog getClientSearch(){
        clientSearch = new ClientSearchDialog(parent, this);

        return clientSearch;
    }

    private ClientAddDialog getClientAddDialog() {
        clientAddDialog = new ClientAddDialog(parent);

        return clientAddDialog;
    }

    private void getClientDelete(){
        try {
            client = new Client(Integer.valueOf(code.getText()), nom.getText(), prenom.getText(), adresse.getText(), null, codePostal.getText(), ville.getText(), numTel.getText(), assurance.getText(), email.getText(), remarque.getText(), false);
            clientManager.deleteClient(client);
            JOptionPane.showMessageDialog(null, "Utilisateur supprimé", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (BLLException e) {
            JOptionPane.showMessageDialog(null, "Problème lors de la suppression du client.", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Initialisation panel button
     * @return
     */
    private JPanel getPanelButton() {
        JPanel panelBoutton = new JPanel();
        panelBoutton.setLayout(new GridBagLayout());

        panelBoutton.setBorder(BorderFactory.createLineBorder(Color.black));
        // Bouton rechercher
        rechercher = new JButton("Rechercher");
        rechercher.addActionListener(e -> {
            getClientSearch();
        });

        // Bouton ajouter
        ajouter = new JButton("Ajouter");
        ajouter.addActionListener(e -> {
            getClientAddDialog();
        });

        supprimer = new JButton("Supprimer");
        supprimer.addActionListener(e -> {
            getClientDelete();
        });

        // Bouton modifier
        modifier = new JButton("Modifier");
        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    client = new Client(Integer.valueOf(code.getText()), nom.getText(), prenom.getText(), adresse.getText(), null, codePostal.getText(), ville.getText(), numTel.getText(), assurance.getText(), email.getText(), remarque.getText(), false);
                    clientManager = new CltManager();

                    if(clientManager.validerClient(client) == true) {
                        clientManager.updateClient(client);
                        JOptionPane.showMessageDialog(null, "Utilisateur modifié", null, JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Les champs ne sont pas correctement complétés", null, JOptionPane.WARNING_MESSAGE);
                    }
                } catch (BLLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Problème lors de l'ajout, vérifier que tout les champs sont complétés correctement.", null, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Bouton annuler
        annuler = new JButton("Annuler");
        annuler.addActionListener(e -> {
            try{
                animalManager.update(selectedAnimal);
            }catch(Exception error){
                animalManager.insert(selectedAnimal);
            }
            animalTable.getModele().setAnimaux(animalManager.getFromClient(client));
        });

        panelBoutton.add(rechercher);
        panelBoutton.add(ajouter);
        panelBoutton.add(supprimer);
        panelBoutton.add(modifier);

        //Permet annulation modification ou suppression animal
        panelBoutton.add(annuler);
        return panelBoutton;
    }

    private ClientTable getPanelSearch() {

        panelSearch = new ClientTable(this, null);
        return panelSearch;
    }

    public void getClientSelected(Client client){
        this.getPanelSearch().setVisible(false);
        try {
            this.setClient(clientManager.getClientById(client.getCode()));
        } catch (BLLException e) {
            e.printStackTrace();
        }

        //mise à jour de l'interfce graphique
        this.code.setText(String.valueOf(client.getCode()));
        this.nom.setText(client.getNom());
        this.prenom.setText(client.getPrenomClient());
        this.adresse.setText(client.getAdresse1());
        this.codePostal.setText(client.getCodePostal());
        this.ville.setText(client.getVille());
        this.assurance.setText(client.getAssurance());
        this.numTel.setText(client.getNumTel());

        animalTable.getModele().setAnimaux(animalManager.getFromClient(client));
    }

    public Client getClient() {
        return client;
    }

    public AnimalTable getAnimalTable(){
        return animalTable;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setAnimalTable(AnimalTable animalTable){
        this.animalTable = animalTable;
    }
}
