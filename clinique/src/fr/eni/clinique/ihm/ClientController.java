package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.CltManager;
import fr.eni.clinique.bo.Client;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClientController {

    private static ClientController instance;
    private CltManager clientM = new CltManager();

    private GeneralController controllerBase;

    //Fields
    private JTextField code, nom, prenom, adresse, ville, codePostal, assurance, email, numTel, remarque;


    // constructeur
    private ClientController() throws BLLException {
        getEcran();
    }

    /**
     * Gestion singleton
     * @return
     */
    public static ClientController getInstance() {
        if(instance == null){
            try {
                instance = new ClientController();
            } catch (BLLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    // récupération ihm
    public GeneralController getEcran() {
        if(controllerBase == null){
            controllerBase = GeneralController.getInstance();
        }
        return controllerBase;
    }


    // initialisation de l'onglet client
    public void init(){
        controllerBase.getPanel_personnel().setVisible(false);
        controllerBase.getPanel_client().setVisible(true);
        try{
            Client client = clientM.getFirst();
            mainDisplay(clientM.getClientById(client.getCode()));
        }catch(BLLException e){
            e.printStackTrace();
        }
    }

    public void mainDisplay(List<Client> listeClient){
        // NOUVEAU PANEL
        controllerBase.getPanel_client_result().removeAll();

        // AFFICHAGE DES DONNEES
        for (Client client : listeClient) {
            JPanel panel = new JPanel();//LIGNE
            panel.setLayout(new GridLayout(0 , 2));
            GridLayout gbc = new GridLayout(0, 2);

            //Affichage des données du client
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

            controllerBase.getPanel_client_result().add(panel);//AJOUT DE LA LIGNE
        }
        //Actualisation
        controllerBase.getPanel_client().revalidate();
    }

    // affichage des clients sous forme de liste sur l'IHM
    public void displayResult(List<Client> listeClient) {
        // NOUVEAU PANEL
        GridBagConstraints gbc = new GridBagConstraints();
        int gridy = 0;
        controllerBase.getPanel_client_result().removeAll();

        // AFFICHAGE DES DONNEES
        for (Client client : listeClient) {
            JPanel panel = new JPanel();//LIGNE
            panel.setLayout(new GridBagLayout());
            gbc.insets = new Insets(20, 10, 10, 10);
            panel.add(new JLabel(client.getPrenomClient() + " " + client.getNom() + " - " + client.getEmail()), gbc);
            gbc.gridx = 2;

            //Ligne de séparation
            panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
            gbc.gridy = gridy;//ORDRE DE LA LIGNE
            gbc.gridwidth = 3;//TAILLE DE LA LIGNE
            controllerBase.getPanel_client_result().add(panel, gbc);//AJOUT DE LA LIGNE
            gridy++;
        }
        //Actualisation
        controllerBase.getPanel_client().revalidate();
    }

    public void supprimerClient() {
        try {
            Client client = new Client(Integer.parseInt(this.code.getText()), this.nom.getText(), this.prenom.getText(), this.adresse.getText(), null, this.codePostal.getText(), this.ville.getText(), this.numTel.getText(), this.assurance.getText(), this.email.getText(), null, false);

            this.clientM.deleteClient(client);
            controllerBase.getPanel_client().revalidate();
            JOptionPane.showMessageDialog(null, "Utilisateur supprimé", null, JOptionPane.INFORMATION_MESSAGE);

        } catch (BLLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problème lors de la suppression", null, JOptionPane.ERROR_MESSAGE);
        }
    }

}
