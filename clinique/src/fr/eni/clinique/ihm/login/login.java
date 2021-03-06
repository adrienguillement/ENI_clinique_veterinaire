package fr.eni.clinique.ihm.login;

import fr.eni.clinique.bll.ConnectionManager;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.ihm.IHMapp;

import javax.swing.*;

public class login {
    private static boolean connectionSucceed;
    /**
     * méthode demande de connection
     */
    public static void connect(String login, String password,IHMapp ihmApp)
    {
        ConnectionManager connectionManager = new ConnectionManager();
        Personnel personnel = null;
        try{
            try {
                personnel = connectionManager.getConnection(login, password);
            } catch (DALException e) {
                e.printStackTrace();
            }
            if(personnel != null){
                System.out.println("Bienvenue " + personnel.getNom() + "Vos droits sont correspondantes à votre rôle: " + personnel.getRole());
                //init l'utilisateur en cours
                connectionSucceed = true;
                ihmApp.setUtilisateurEnCours(personnel);
            }
            else{
                JOptionPane.showMessageDialog(null, "Erreur d'authentification", null, JOptionPane.ERROR_MESSAGE);
                connectionSucceed = false;
            }
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public static boolean getConnectionSucceed(){
        return connectionSucceed;
    }
}
