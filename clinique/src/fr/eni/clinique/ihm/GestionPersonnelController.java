package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Personne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;




public class GestionPersonnelController
{
    private static IHMApp ecran;
    private static GestionPersonnelController instance;
    private PersonnelManager personnelManager = new PersonnelManager();
    private GeneralController controllerBase = GeneralController.getInstance();

    // constructeur
    private GestionPersonnelController() throws BLLException {
        getEcran();
    }

    // gestion de l'IHM
    public IHMApp getEcran() {
        if(ecran == null){
            ecran = IHMApp.getInstance();
        }
        return ecran;
    }

    public static GestionPersonnelController getInstance() {
        if(instance == null){
            try {
                instance = new GestionPersonnelController();
            } catch (BLLException e) {
                GeneralController.getInstance().error_alert("Problème dans l'application !");
            }
        }
        return instance;
    }

    public void initMyApp(){
        ecran.getPanel_personnel().setVisible(true);

        try{
            this.displayResult(personnelManager.getPersonnels());
        }catch(BLLException e){
            controllerBase.error_alert("Impossible d'afficher les données!");
        }
    }

    public void displayResult(List<Personne> listePersonne) {
        // NOUVEAU PANEL
        GridBagConstraints gbc = new GridBagConstraints();
        int gridy = 0;
        ecran.getPanel_personnel_result().removeAll();
        // AFFICHAGE DES DONNEES
        for (Personne personne : listePersonne) {
            JPanel panel = new JPanel();//LIGNE
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc_result = new GridBagConstraints();
            gbc.insets = new Insets(20, 10, 10, 10);
            gbc.gridwidth = 1;
            gbc.gridy = 0;
            gbc.gridx = 0;
            panel.add(new JLabel(personne.getNom()+" - "+personne.getRole()), gbc);
            gbc.gridy = 1;
            panel.add(new JPasswordField(personne.getMotPasse()), gbc);
            gbc.gridx = 2;

            panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
            gbc.gridy = gridy;//ORDRE DE LA LIGNE
            gbc.gridwidth = 3;//TAILLE DE LA LIGNE
            ecran.getPanel_personnel_result().add(panel, gbc);//AJOUT DE LA LIGNE
            gridy++;
        }
        //Actualisation
        ecran.getPanel_personnel().revalidate();
    }
}
