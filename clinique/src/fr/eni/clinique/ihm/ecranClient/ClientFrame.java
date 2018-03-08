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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class ClientFrame extends JInternalFrame {

    private JButton ajouter, rechercher, modifier, supprimer;
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
        System.out.printf(client.toString());
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(getPanelButton(), BorderLayout.PAGE_START);

        try {
            client = clientManager.getFirst();
            panel.add(getPanelFormClient(), BorderLayout.LINE_START);
        } catch (BLLException e) {
            JOptionPane.showMessageDialog(null, "Impossible de récupérer un client.", null, JOptionPane.ERROR_MESSAGE);
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
        panelAnimal.setLayout(new GridLayout(0,1));

        panelAnimal.add(getTableAnimal());
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
                AnimalManager animalManager = new AnimalManager();
                selectedAnimal = animalTable.getModele().getAnimaux().get(animalTable.getSelectedRow());
                animalManager.deleteAnimal(selectedAnimal);
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
        System.out.println(selectedAnimal);
        animalDialog = new AnimalDialog(parent, selectedAnimal, this);
        animalDialog.setVisible(true);
        return animalDialog;
    }

    private AnimalTable getTableAnimal() {
        animalTable = new AnimalTable(client);
        return animalTable;
    }

    private JPanel getPanelFormClient() throws BLLException {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 2));
        panel.add(new JLabel("Code : "));

        this.code = new JTextField(String.valueOf(client.getCode()));
        this.code.setEditable(false);
        JTextField codeClient = this.code;
        panel.add(codeClient);

        panel.add(new JLabel("Nom : "));
        this.nom = new JTextField(client.getNom());
        panel.add(this.nom);

        panel.add(new JLabel("Prenom : "));
        this.prenom = new JTextField(client.getPrenomClient());
        panel.add(this.prenom);

        panel.add(new JLabel("Email : "));
        this.email = new JTextField(client.getEmail());
        panel.add(this.email);

        panel.add(new JLabel("Adresse : "));
        this.adresse = new JTextField(client.getAdresse1());
        panel.add(this.adresse);

        panel.add(new JLabel("Code postal : "));
        this.codePostal = new JTextField(client.getCodePostal());
        panel.add(this.codePostal);

        panel.add(new JLabel("Ville : "));
        this.ville = new JTextField(client.getVille());
        panel.add(this.ville);

        panel.add(new JLabel("Assurance : "));
        this.assurance = new JTextField(client.getAssurance());
        panel.add(this.assurance);

        panel.add(new JLabel("Numéro tel : "));
        this.numTel = new JTextField(client.getNumTel());
        panel.add(this.numTel);

        panel.add(new JLabel("Remarque : "));
        this.remarque = new JTextField(client.getRemarque());
        panel.add(this.remarque);
        return panel;
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

        supprimer = new JButton("Supprimer client");
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
        panelBoutton.add(rechercher);
        panelBoutton.add(ajouter);
        panelBoutton.add(supprimer);
        panelBoutton.add(modifier);
        return panelBoutton;
    }

    private ClientTable getPanelSearch() {

        panelSearch = new ClientTable(this, null);
        System.out.printf("De retour dans le clientFrame");
        return panelSearch;
    }

    public void getClientSelected(Client client){
        this.getPanelSearch().setVisible(false);
        try {
            this.setClient(clientManager.getClientById(client.getCode()));
        } catch (BLLException e) {
            JOptionPane.showMessageDialog(null, "Impossible de récupérer le client.", null, JOptionPane.ERROR_MESSAGE);
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
