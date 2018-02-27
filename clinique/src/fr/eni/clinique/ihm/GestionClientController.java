package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.CltManager;
import fr.eni.clinique.bo.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestionClientController {

    private IHMApp ecran;
    private static GestionClientController instance;
    private CltManager clientM = new CltManager();
    private GeneralController controllerBase = GeneralController.getInstance();

    // constructeur
    private GestionClientController() throws BLLException {
        getEcran();
    }

    // gestion de l'IHM
    public IHMApp getEcran() {
        if(ecran == null){
            ecran = IHMApp.getInstance();
        }
        return ecran;
    }

    public static GestionClientController getInstance() {
        if(instance == null){
            try {
                instance = new GestionClientController();
            } catch (BLLException e) {
                GeneralController.getInstance().error_alert("Problème dans l'application !");
            }
        }
        return instance;
    }

    //initialisation de l'ecran de connexion
    public void initMyApp(){
        ecran.getPanel_client().setVisible(true);

        try{
            displayResult(clientM.getCatalogue());
        }catch(BLLException e){
            controllerBase.error_alert("Impossible d'afficher les données!");
        }
    }

    // affichage des clients sous forme de liste sur l'IHM
    public void displayResult(List<Client> listeClient) {
        // NOUVEAU PANEL
        GridBagConstraints gbc = new GridBagConstraints();
        int gridy = 0;
        ecran.getPanel_client_result().removeAll();
        // AFFICHAGE DES DONNEES
        for (Client client : listeClient) {
            JPanel panel = new JPanel();//LIGNE
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc_result = new GridBagConstraints();
            gbc.insets = new Insets(20, 10, 10, 10);
            gbc.gridwidth = 1;
            gbc.gridy = 0;
            gbc.gridx = 0;
            panel.add(new JLabel(client.getPrenomClient() + " " + client.getNom() + " - " + client.getEmail()), gbc);
            gbc.gridx = 2;

            panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
            gbc.gridy = gridy;//ORDRE DE LA LIGNE
            gbc.gridwidth = 3;//TAILLE DE LA LIGNE
            ecran.getPanel_client_result().add(panel, gbc);//AJOUT DE LA LIGNE
            gridy++;
        }
        //Actualisation
        ecran.getPanel_client().revalidate();
    }
}
