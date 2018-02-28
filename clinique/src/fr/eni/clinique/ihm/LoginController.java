package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bo.Personnel;
import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    // singletons
    private GeneralController generalController = GeneralController.getInstance();

    // Formulaire de connexion
    private static Container login_panel;
    private static JLabel login_label, password_label;
    private static JTextField login_field;
    private static JPasswordField password_field;
    private static JButton valider_login, annuler_login;

    private Personnel personnel;
    private static JFrame login_jframe;

    // constructeur
    public LoginController() throws BLLException {
        initMyApp();
    }

    // gestion de l'IHM
    public GeneralController getEcran() {
        if(generalController == null){
            generalController = GeneralController.getInstance();
        }
        return generalController;
    }


    // affichage de la JFrame
    public static void initMyApp() {
        login_jframe = new JFrame();

        // Paramètres de la fenetre
        login_jframe.setTitle("Authentification");
        login_jframe.setSize(new Dimension(400,200));
        login_jframe.setLocationRelativeTo(null);
        login_jframe.setResizable(false);

        login_label = new JLabel("Login");
        login_field = new JTextField();

        password_label = new JLabel("Mot de Passe");
        password_field = new JPasswordField();

        valider_login = new JButton("Valider ");
        annuler_login = new JButton(" Annuler");

        login_panel = login_jframe.getContentPane();
        login_panel.setLayout(null);

        login_panel.add(login_label);
        login_label.setBounds(20, 20, 100, 20);

        login_panel.add(login_field);
        login_field.setBounds(150, 20, 150, 20);

        login_panel.add(password_label);
        password_label.setBounds(22, 55, 100, 20);

        login_panel.add(password_field);
        password_field.setBounds(150, 55, 150, 20);

        login_panel.add(valider_login);
        valider_login.setBounds(125,100 ,77 ,20 );

        login_panel.add(annuler_login);
        annuler_login.setBounds(225, 100, 82, 20);


        valider_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //renvoyer un test de connection
                System.out.println("Demande d'authentification");
            }
        });

        login_jframe.setVisible(true);
    }
}
