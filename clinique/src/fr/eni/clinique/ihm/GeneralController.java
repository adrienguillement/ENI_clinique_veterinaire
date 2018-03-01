package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.CltManager;
import fr.eni.clinique.bo.Client;
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
    private JFrame ajout_client;

    // Menu
    private JMenu gestion_client;
    private JMenu gestion_personnel;
    private JMenu prise_rdv;


    // Panel client
    private JPanel panel_client;
    private JPanel panel_client_result, panel_client_buttons;
    private JPanel panel_client_add;
    private JPanel panelButtons;
    private JLabel nomLabel, prenomLabel, adresseLabel, codePostalLabel,
            villeLabel, numLabel, assuranceLabel, eMailLabel;
    private JTextField nomTextField, prenomTextField, adresseTextField, codePostalTextField,
            villeTextField, numTextField, assuranceTextField, eMailTextField;
    private JButton btn_ajoutClient, btn_supprimerClient, valider_ajoutClient, annuler_ajoutClient;
    private Client client;
    private CltManager clientManager;

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

        this.main_jframe.add(this.getPanel_client()).setVisible(false);

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

            gbc.gridx = 0;
            gbc.gridy = 0;
            panel_client.add(getButtons(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            panel_client.add(getPanel_client_result(),gbc);
        }
        return panel_client;
    }

    /**
     * Correspond aux boutons rechercher, ajouter client, supprimer client ...
     * @return
     */
    //TODO Rechercher client
    public JPanel getButtons() {
        if(panelButtons == null){
            panelButtons = new JPanel();

            btn_supprimerClient = new JButton("Supprimer client");
            btn_supprimerClient.setBackground(new Color(231,76,60));
            btn_supprimerClient.setForeground(Color.white);
            btn_supprimerClient.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Suppression client");
                    ClientController.getInstance().supprimerClient();
                }
            });

            btn_ajoutClient = new JButton("Ajouter un client");
            btn_ajoutClient.setBackground(new Color(39,174,96));
            btn_ajoutClient.setForeground(Color.white);
            btn_ajoutClient.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frameAjoutClient();
                }
            });
            panelButtons.add(btn_ajoutClient);
            panelButtons.add(btn_supprimerClient);
        }
        return panelButtons;
    }

    public JPanel getPanel_client_result() {
        if(panel_client_result == null){
            panel_client_result = new JPanel();
            panel_client_result.setLayout(new GridLayout(0,1));
        }
        return panel_client_result;
    }

    public void frameAjoutClient() {
        this.ajout_client = new JFrame();

        this.ajout_client.setTitle(" Clients ");
        this.ajout_client.setSize(new Dimension(600,600));
        this.ajout_client.setLocationRelativeTo(null);
        this.ajout_client.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.ajout_client.setResizable(true);

        this.nomLabel = new JLabel("Nom: ");
        this.nomTextField = new JTextField(10);

        this.prenomLabel = new JLabel("Prenom: ");
        this.prenomTextField = new JTextField(10);

        this.adresseLabel = new JLabel("Adresse: ");
        this.adresseTextField = new JTextField(10);

        this.codePostalLabel = new JLabel("Code postal: ");
        this.codePostalTextField = new JTextField(10);

        this.villeLabel = new JLabel("Ville: ");
        this.villeTextField = new JTextField(10);

        this.numLabel = new JLabel("Num tel: ");
        this.numTextField = new JTextField(10);

        this.assuranceLabel = new JLabel("Assurance: ");
        this.assuranceTextField = new JTextField(10);

        this.eMailLabel = new JLabel("eMail: ");
        this.eMailTextField = new JTextField(10);

        this.valider_ajoutClient = new JButton("Valider ");
        this.annuler_ajoutClient = new JButton(" Annuler");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.gridwidth = 1;

        gbc.gridy = 0;
        gbc.gridx = 0;
        panel.add(nomLabel,gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        panel.add(nomTextField,gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(prenomLabel,gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        panel.add(prenomTextField,gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(adresseLabel,gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        panel.add(adresseTextField,gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(codePostalLabel,gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        panel.add(codePostalTextField,gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        panel.add(villeLabel,gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        panel.add(villeTextField,gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        panel.add(numLabel,gbc);

        gbc.gridy = 5;
        gbc.gridx = 1;
        panel.add(numTextField,gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        panel.add(assuranceLabel,gbc);

        gbc.gridy = 6;
        gbc.gridx = 1;
        panel.add(assuranceTextField,gbc);

        gbc.gridy = 7;
        gbc.gridx = 0;
        panel.add(eMailLabel,gbc);

        gbc.gridy = 7;
        gbc.gridx = 1;
        panel.add(eMailTextField,gbc);

        gbc.gridy = 8;
        gbc.gridx = 0;
        panel.add(this.valider_ajoutClient, gbc);

        gbc.gridy = 8;
        gbc.gridx = 1;
        panel.add(this.annuler_ajoutClient, gbc);

        this.valider_ajoutClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    client = new Client(nomTextField.getText(), prenomTextField.getText(), adresseTextField.getText(), null, codePostalTextField.getText(), villeTextField.getText(), numTextField.getText(), assuranceTextField.getText(), eMailTextField.getText(), null, false);
                    clientManager = new CltManager();

                    if(clientManager.validerClient(client) == true){
                        clientManager.insertClient(client);
                        JOptionPane.showMessageDialog(null, "Utilisateur ajouté", null, JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Les champs ne sont pas correctement complétés", null, JOptionPane.WARNING_MESSAGE);
                    }
                    instance.getPanel_client().revalidate();
                } catch (BLLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Problème lors de l'ajout", null, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        annuler_ajoutClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        this.ajout_client.setContentPane(panel);
        this.ajout_client.setVisible(true);
    }

}
