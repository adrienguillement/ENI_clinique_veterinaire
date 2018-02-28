package fr.eni.clinique.ihm;

import fr.eni.clinique.bo.Personnel;

import javax.swing.*;
import java.awt.*;

public class IHMApplication {

    // instance
    private static IHMApplication instance;

    // Fenetres nécessaires à l'application
    private JFrame login_jframe;
    private JFrame main_jframe;

    // Menu
    private JMenu gestion_client;
    private JMenu gestion_personnel;
    private JMenu prise_rdv;

    // Formulaire de connexion
    private JLabel login_label, password_label;
    private JTextField login_field;
    private JPasswordField mdp_field;
    private JButton valider_login, annuler_login;

    private Personnel personnel;

    // Panel client
    private JPanel panel_client;
    private JPanel panel_client_result;
    private JPanel panel_client_add;
    private JLabel nomLabel, prenomLabel, adresseLabel, codePostalLabel,
            villeLabel, numLabel, assuranceLabel, eMailLabel;
    private JTextField nomTextField, prenomTextField, adresseTextField, codePostalTextField,
            villeTextField, numTextField, assuranceTextField, eMailTextField;
    private JButton valider_ajoutClient, annuler_ajoutClient;

    public static IHMApplication getInstance(){
        if(instance == null){
            instance = new IHMApplication();
        }
        return instance;
    }

    private IHMApplication(){
        this.setupLogin();
    }

    // Setup formulaire de connexion
    private void setupLogin(){

        this.login_jframe = new JFrame();
        // Création du panel
        JPanel panel_login = new JPanel();
        // Grille de 3 lignes deux colonnes
        panel_login.setLayout(new GridLayout(3,2));


        this.login_jframe.add(panel_login);
        this.login_jframe.setVisible(true);
    }


}
