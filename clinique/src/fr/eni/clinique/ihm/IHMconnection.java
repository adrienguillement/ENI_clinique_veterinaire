package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.ConnectionManager;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IHMconnection extends JFrame{

    private static IHMconnection instance;
    private JLabel login,mdp;
    private JTextField login1;
    private JPasswordField mdp1;
    private JButton valider,annuler;
    private ConnectionManager connectionManager = new ConnectionManager();

    private Personnel personnel;

    //Singleton
    public static IHMconnection getInstance(){
        if(instance == null){
            instance = new IHMconnection();
        }
        return instance;
    }

    //constructeur
    public IHMconnection(){
        setIHMconnection();
    }

    // Lancement de l'application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try{
                    IHMconnection window = new IHMconnection();
                    window.setVisible(true);
                } catch (Exception e){
                    System.out.println("Erreur chargement ecran authentification");
                }
            }
        });
    }

    //mise à jour de l'IHM
    private void setIHMconnection(){

        this.setTitle(" Authentification ");
        this.setSize(new Dimension(400,200));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        login = new JLabel("Login");
        login1 = new JTextField();

        mdp = new JLabel("Mot de Passe");
        mdp1 = new JPasswordField();

        valider = new JButton("Valider ");
        annuler = new JButton(" Annuler");


        Container contenu = this.getContentPane();
        contenu.setLayout(null);

        contenu.add(login);
        login.setBounds(20, 20, 100, 20);

        contenu.add(login1);
        login1.setBounds(150, 20, 150, 20);

        contenu.add(mdp);
        mdp.setBounds(22, 55, 100, 20);

        contenu.add(mdp1);
        mdp1.setBounds(150, 55, 150, 20);

        contenu.add(valider);
        valider.setBounds(125,100 ,77 ,20 );

        contenu.add(annuler);
        annuler.setBounds(225, 100, 82, 20);

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //renvoyer un test de connection
                System.out.println("Demande d'authentification");
                testConnection();
            }
        });

        this.setVisible(true);
    }

    public void testConnection()
    {
        try{
            personnel = connectionManager.getConnection(login1.getText(), mdp1.getText());
            if(personnel != null){
                System.out.println("Bienvenue " + personnel.getNom() + "Vos droits sont correspondantes à votre rôle: " + personnel.getRole());
                //init l'utilisateur en cours
                GeneralController.getInstance().setUtilisateurEnCours(personnel);
                GeneralController.getInstance().getEcran();
            }
            else{
                JOptionPane.showMessageDialog(null, "Erreur d'authentification", null, JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (DALException e1){
            e1.printStackTrace();
        }
    }
}


