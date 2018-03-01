package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bo.Personnel;

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
                    System.out.println("GESTIONPERSONNEL CONTROLLER GETINSTANCE INIT ici");
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

}
