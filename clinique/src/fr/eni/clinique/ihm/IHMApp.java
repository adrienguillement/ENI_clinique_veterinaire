package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.ConnectionManager;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;

import fr.eni.clinique.utils.SHA512;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class IHMApp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// INSTANCE
	private static IHMApp instance;

	//Connexion
    private JLabel login,mdp;
    private JTextField login1;
    private JPasswordField mdp1;
    private JButton valider,annuler;
    private Container containerLogin;
    private ConnectionManager connectionManager = new ConnectionManager();

    private Personnel personnel;

	//menu
	private JMenuBar menuBarre;
	private JMenu menuAgenda;
	private JMenu acceuil_connection;
	private JMenu gestion_personnel;

	//Clients
	private JPanel panel_client;
	private JPanel panel_client_result;

	//Personnel
	private JPanel panel_personnel;
	private JPanel panel_personnel_result;


	private IHMApp() {

	    personnel = new Personnel("secretaire", "123", "sec", false);
		this.setupSec();
	}

	//SINGLETON
	public static IHMApp getInstance(){
		if(instance == null){
			instance = new IHMApp();
		}
		return instance;
	}

	//SETUP LOGIN (FORMULAIRE DE CONNEXION)
	public void setupLogin() {
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


        this.containerLogin = this.getContentPane();
        this.containerLogin.setLayout(null);

        this.containerLogin.add(login);
        login.setBounds(20, 20, 100, 20);

        this.containerLogin.add(login1);
        login1.setBounds(150, 20, 150, 20);

        this.containerLogin.add(mdp);
        mdp.setBounds(22, 55, 100, 20);

        this.containerLogin.add(mdp1);
        mdp1.setBounds(150, 55, 150, 20);

        this.containerLogin.add(valider);
        valider.setBounds(125,100 ,77 ,20 );

        this.containerLogin.add(annuler);
        annuler.setBounds(225, 100, 82, 20);

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //renvoyer un test de connection
                System.out.println("Demande d'authentification");
                connect();
            }
        });
    }

    //Connexion de l'utilisateur
    public void connect()
    {
        try{
            personnel = connectionManager.getConnection(login1.getText(), mdp1.getText());
            if(personnel != null){
                System.out.println("Bienvenue " + personnel.getNom() + "Vos droits sont correspondantes à votre rôle: " + personnel.getRole());
                //init l'utilisateur en cours
                GeneralController.getInstance().setUtilisateurEnCours(personnel);

                //Fermeture fenetre login et lancement IHM

                this.containerLogin.removeAll();


                // Affichage de l'IHM correspondant a l'utilisateur
                if(personnel.getRole().equals("sec"))
                {
                    // Role secrétaire
                    this.setupSec();
                }
                else if(personnel.getRole().equals("vet"))
                {
                    // Role vétérinaire
                    this.setupVet();
                }
                else if(personnel.getRole().equals("adm"))
                {
                    // Role admin
                    this.setupAdm();
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Erreur d'authentification", null, JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (DALException e1){
            e1.printStackTrace();
        }
    }

	public void setupSec() {
        System.out.println("setup sec");
        //Titre
        this.setTitle("Clinique vétérinaire");
        this.setVisible(true);
        // Réglage de la taille du conteneur
        this.setSize(900, 655);
        this.setResizable(false);


        // Réglage de la position du conteneur
        this.setLocationRelativeTo(null);

        // Fermeture de l'application JAVA lorsque on clique sur la croix
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Affichage fenetre
        this.setVisible(true);

        this.setJMenuBar(getMenuBarre());

		// Creation du panel
		JPanel panel_container = new JPanel();
		panel_container.setOpaque(true);
		panel_container.setLayout(new GridLayout(1,1));

		// Mise en place Layout
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridy = 0;
		panel.add(this.getPanel_client(), gbc);

		panel_container.add(panel);

		// SCROLL BAR
		JScrollPane scrollPane = new JScrollPane(panel_container, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(100, 100));
		Container cp = this;
		cp.add(scrollPane);

		this.getContentPane().add(scrollPane);
	}

    public void setupVet() {
        //Titre
        this.setTitle("Clinique vétérinaire");
        this.setVisible(true);
        // Réglage de la taille du conteneur
        this.setSize(900, 655);
        this.setResizable(false);


        // Réglage de la position du conteneur
        this.setLocationRelativeTo(null);

        // Fermeture de l'application JAVA lorsque on clique sur la croix
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Affichage fenetre
        this.setVisible(true);

        this.setJMenuBar(getMenuBarre());

        // Creation du panel
        JPanel panel_container = new JPanel();
        panel_container.setOpaque(true);
        panel_container.setLayout(new GridLayout(1,1));

        // Mise en place Layout
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = 0;

        panel_container.add(panel);

        // SCROLL BAR
        JScrollPane scrollPane = new JScrollPane(panel_container, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(100, 100));
        Container cp = this;
        cp.add(scrollPane);

        this.getContentPane().add(scrollPane);
    }

    public void setupAdm() {
        //Titre
        this.setTitle("Clinique vétérinaire");
        this.setVisible(true);
        // Réglage de la taille du conteneur
        this.setSize(900, 655);
        this.setResizable(false);


        // Réglage de la position du conteneur
        this.setLocationRelativeTo(null);

        // Fermeture de l'application JAVA lorsque on clique sur la croix
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Affichage fenetre
        this.setVisible(true);

        this.setJMenuBar(getMenuBarre());

        // Creation du panel
        JPanel panel_container = new JPanel();
        panel_container.setOpaque(true);
        panel_container.setLayout(new GridLayout(1,1));

        // Mise en place Layout
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = 0;

        panel_container.add(panel);

        // SCROLL BAR
        JScrollPane scrollPane = new JScrollPane(panel_container, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(100, 100));
        Container cp = this;
        cp.add(scrollPane);

        this.getContentPane().add(scrollPane);
    }

	// Lancement de l'application
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				IHMApp ecran = new IHMApp();
				ecran.setVisible(true);
			}
		});
	}

	public void createMenuBar() {

		// Menu Fichier
		JMenu menu = new JMenu("Fichier");
		menuBarre.add(menu);

		// Sous menu Déconnexion
		JMenuItem menuItem = new JMenuItem("Déconnexion");
		menuItem.setActionCommand("deconnexion");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		// Sous menu fermer
		menuItem = new JMenuItem("Fermer");
		menuItem.setActionCommand("fermer");
		menuItem.addActionListener(this);
		menu.add(menuItem);

        if(personnel.getRole().equals("sec")) {
            // Menu Gestion Rendez-vous
            menu = new JMenu("Gestion des rendez-vous");
            menu.setActionCommand("gestionRendezVous");
            menu.addActionListener(this);
            menuBarre.add(menu);


            //Sous menu prise rdv
            menuItem = new JMenuItem("Prise de rendez vous");
            menuItem.setActionCommand("priseRendezVous");
            menuItem.addActionListener(this);
            menu.add(menuItem);

            //Sous menu gestion des clients
            menuItem = new JMenuItem("Gestion des clients");
            menuItem.setActionCommand("gestionDesClients");
            menuItem.addActionListener(this);
            menu.add(menuItem);
        }

        if(personnel.getRole().equals("vet")){
            //Menu Agenda
            menuBarre.add(getMenuAgenda());
        }

        if(personnel.getRole().equals("adm")){
            //Menu personnel
            menuBarre.add(getPersonnel_menu());
        }

	}

	public JMenu getMenuAgenda(){
	    if(menuAgenda==null){
	        menuAgenda = new JMenu("Agenda");
	        menuAgenda.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("cc agenda");
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
        return menuAgenda;
    }

    public JMenu getPersonnel_menu() {
        if(gestion_personnel==null){
            gestion_personnel = new JMenu("Gestion du personnel");
            gestion_personnel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("ROUI");
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

	// Réagir aux clicks sur les menus
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "deconnexion":
			System.out.println("Deconnexion");
			break;
		case "fermer":
			System.exit(0);
			break;
		case "gestionRendezVous":
			System.out.println("gestionRendezVous");
			break;
		case "agenda":
			System.out.println("agenda");
			break;
		case "gestionPersonnel":
			System.out.println("gestion personnel");
			break;
		case "priseRendezVous":
			System.out.println("prise rdv");
			break;
		case "gestionDesClients":
		    GestionClientController.getInstance().initMyApp();
			break;
		default:
			System.out.println("Probleme e=" + e);
		}
	}

	public JMenuBar getMenuBarre() {
		if (menuBarre == null) {
			menuBarre = new JMenuBar();

			createMenuBar();
		}
		return menuBarre;
	}

	public JMenu getAccueil_connection(){
		if(acceuil_connection  == null){
			acceuil_connection = new JMenu("Acceuil");
		}
		return acceuil_connection;
	}

	//PANEL CLIENT
	public JPanel getPanel_client() {
		if(panel_client==null){
			panel_client = new JPanel();
			panel_client.setSize(this.getWidth(), this.getHeight());
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
		if(panel_client_result == null) {
            panel_client_result = new JPanel();
            panel_client_result.setLayout(new GridLayout(0, 1));
        }
		return panel_client_result;
	}

	//PANEL PERSONNEL
	public JPanel getPanel_personnel(){
		if(panel_personnel==null){
			panel_personnel = new JPanel();
			panel_personnel.setSize(this.getWidth(),this.getHeight());
			panel_personnel.setOpaque(true);
			panel_personnel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5,5,5,5);
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 3;
			panel_personnel.add(getPanel_personnel_result(), gbc);
		}
		return panel_personnel;
	}

	public JPanel getPanel_personnel_result(){
		if(panel_personnel_result == null){
			panel_personnel_result = new JPanel();
			panel_personnel_result.setLayout(new GridLayout(0,1));
		}
		return panel_personnel_result;
	}

}
