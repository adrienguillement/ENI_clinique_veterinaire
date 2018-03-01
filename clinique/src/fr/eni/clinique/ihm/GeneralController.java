package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.utils.SHA512;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GeneralController {

    // instance
    private static GeneralController instance;

    private Personnel utilisateurEnCours = null;

    // Fenetres nécessaires à l'application
    private JFrame main_jframe;
    private JFrame ajout_personnel;

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

    // Panel personnel
    private JPanel panel_personnel;
    private JPanel panel_personnel_result;
    private JPanel panel_personnel_add;
    private JLabel nomPersonnel, rolePersonnel, motPassePersonnel;
    private JTextField nomPersonnelText, rolePersonnelText;
    private JPasswordField motPasse;
    private JButton valider_AjoutPersonnel, annuler_AjoutPersonnel, ajouterPersonnel, modifierPersonnel, supprimerPersonnel;
    private Personnel personnel;
    private PersonnelManager personnelManager;


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


    public void setUtilisateurEnCours(Personnel utilisateurEnCours) {
        this.utilisateurEnCours = utilisateurEnCours;
    }

    public void setupMainJFrame(){
        // Parametres de la fenetre
        this.main_jframe = new JFrame();
        this.main_jframe.setTitle("Clinique vétérinaire");
        this.main_jframe.setSize(new Dimension(4000,2000));
        this.main_jframe.setLocationRelativeTo(null);
        this.main_jframe.setResizable(false);
        this.main_jframe.setJMenuBar(this.getJMenuBar());

        this.main_jframe.add(this.getPanel_client());

        main_jframe.setVisible(true);
    }

    public JMenuBar getJMenuBar(){
        JMenuBar menubar = new JMenuBar();
        menubar.add(this.getGestion_client_menu());
        menubar.add(this.getGestion_personnel_menu());

        return menubar;
    }

    
    public JMenu getGestion_client_menu() {
        gestion_client = new JMenu("Gestion des clients");

        //Sous menu prise rdv
        JMenuItem menuItemRdv = new JMenuItem("Prise de rendez vous");
        menuItemRdv.setActionCommand("priseRendezVous");
        menuItemRdv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("prise rdv");
            }
        });
        gestion_client.add(menuItemRdv);

        JMenuItem menuItemClients = new JMenuItem("Gestion des clients");
        menuItemClients.setActionCommand("gestionDesClients");
        menuItemClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientController.getInstance().init();
            }
        });
        gestion_client.add(menuItemClients);
        return gestion_client;
    }

    public JMenu getGestion_personnel_menu(){
        if(this.gestion_personnel==null){
            this.gestion_personnel = new JMenu("Gestion du personnel");
            this.gestion_personnel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    PersonnelController.getInstance().init();
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }
        return gestion_personnel;
    }

    //PANEL CLIENT
    public JPanel getPanel_client() {
        if(panel_client==null){
            panel_client = new JPanel();
            panel_client.setOpaque(true);
            // Mise en place Layout
            panel_client.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            panel_client.add(getPanel_client_result(),gbc);
        }
        return panel_client;
    }

    public JPanel getPanel_client_result() {
        if(panel_client_result == null){
            panel_client_result = new JPanel();
            panel_client_result.setLayout(new GridLayout(0,1));
        }
        return panel_client_result;
    }
    //PANEL PERSONNEL
    public JPanel getPanel_personnel(){
        if(panel_personnel==null){
            panel_personnel = new JPanel();
            panel_personnel.setOpaque(true);
            panel_personnel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            panel_personnel.add(getPanel_personnel_result(),gbc);
        }
        return panel_personnel;
    }

    public JButton getAjouterPersonnel() {
        if(ajouterPersonnel == null){
            ajouterPersonnel = new JButton("Ajouter personnel");
            ajouterPersonnel.setBackground(new Color(39,174,96));
            ajouterPersonnel.setForeground(Color.white);
            ajouterPersonnel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frameAjoutPersonnel();
                }
            });
        }
        return ajouterPersonnel;
    }

    public JPanel getPanel_personnel_result(){
        if(panel_personnel_result== null){
            panel_personnel_result = new JPanel();
            panel_personnel_result.setLayout(new GridLayout(0,1));
        }
        return panel_personnel_result;
    }

    private void frameAjoutPersonnel() {
        this.ajout_personnel = new JFrame();

        this.ajout_personnel.setTitle("Personnel");
        this.ajout_personnel.setSize(new Dimension(600, 400));
        this.ajout_personnel.setLocationRelativeTo(null);
        this.ajout_personnel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ajout_personnel.setResizable(true);

        nomPersonnel = new JLabel("nom: ");
        nomPersonnelText = new JTextField(10);

        rolePersonnel = new JLabel("role: ");
        rolePersonnelText = new JTextField(10);

        motPassePersonnel = new JLabel("mot de passe : ");
        motPasse = new JPasswordField(10);

        valider_AjoutPersonnel = new JButton("valider");
        annuler_AjoutPersonnel = new JButton("annuler");


        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10,10,10);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nomPersonnel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nomPersonnelText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(rolePersonnel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(rolePersonnelText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(motPassePersonnel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(motPasse, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(valider_AjoutPersonnel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(annuler_AjoutPersonnel, gbc);

        valider_AjoutPersonnel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    personnel = new Personnel(nomTextField.getText(), SHA512.getSHA512(motPasse.getText(), "toto"), rolePersonnelText.getText(), false);
                    personnelManager = new PersonnelManager();
                    personnelManager.insertPersonnel(personnel);
                } catch (BLLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        annuler_AjoutPersonnel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        this.ajout_personnel.setContentPane(panel);
        this.ajout_personnel.setVisible(true);
    }
}
