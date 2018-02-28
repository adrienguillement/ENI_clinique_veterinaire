package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bo.Personnel;

import javax.swing.*;
import java.awt.*;

public class GeneralController {

    // instance
    private static GeneralController instance;

    // Fenetres nécessaires à l'application
    private JFrame main_jframe;

    // Menu
    private JMenu gestion_client;
    private JMenu gestion_personnel;
    private JMenu prise_rdv;


    // Panel client
    private JPanel panel_client;
    private JPanel panel_client_result;
    private JPanel panel_client_add;
    private JLabel nomLabel, prenomLabel, adresseLabel, codePostalLabel,
            villeLabel, numLabel, assuranceLabel, eMailLabel;
    private JTextField nomTextField, prenomTextField, adresseTextField, codePostalTextField,
            villeTextField, numTextField, assuranceTextField, eMailTextField;
    private JButton valider_ajoutClient, annuler_ajoutClient;

    // Constructeur appelle initMyApp()
    private GeneralController(){
        try {
            this.initMyapp();
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    // méthode singleton
    public static GeneralController getInstance(){
        if(GeneralController.instance == null){
            GeneralController.instance = new GeneralController();
        }
        return GeneralController.instance;
    }


    // affiche la jframe login au lancement
    public void initMyapp() throws BLLException {

        LoginController.initMyApp();
    }
}
