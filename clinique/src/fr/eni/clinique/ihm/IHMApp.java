package src.fr.eni.clinique.ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import fr.eni.clinique.ihm.InternalFrame1;

public class IHMApp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// INSTANCE
	private static IHMApp instance;

	//menu
	private JMenuBar menuBarre;
	private JMenu menuAgenda;
	private JMenu acceuil_connection;

	//Clients
	private JPanel panel_client;
	private JPanel panel_client_result;


	private IHMApp() {
		//Titre
		this.setTitle("Clinique vétérinaire");

		// Réglage de la taille du conteneur
		this.setSize(900, 655);
		this.setResizable(false);


		// Réglage de la position du conteneur
		this.setLocationRelativeTo(null);

		// Fermeture de l'application JAVA lorsque on clique sur la croix
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Affichage fenetre
		this.setVisible(true);

		this.setupIHM();
	}

	public static IHMApp getInstance(){
		if(instance == null){
			instance = new IHMApp();
		}
		return instance;
	}

	public void setupIHM() {
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

		//Menu Agenda
		menu = new JMenu("Agenda");
		menu.setActionCommand("agenda");
		menu.addActionListener(this);
		menuBarre.add(menu);

		//Menu
		menu = new JMenu("Gestion du personnel");
		menu.setActionCommand("gestionPersonnel");
		menu.addActionListener(this);
		menuBarre.add(menu);

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
			JLabel titreClient = new JLabel("Clients");
			titreClient.setFont(new Font(titreClient.getFont().getName(), titreClient.getFont().getStyle(), 30));
			panel_client.add(titreClient,gbc);
			gbc.gridy = 1;
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
