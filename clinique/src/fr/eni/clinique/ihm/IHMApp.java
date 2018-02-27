package src.fr.eni.clinique.ihm;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.InternalFrame1;

public class IHMApp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JDesktopPane desktopPane;
	private JMenuBar menuBarre;
	private JMenu menuAgenda;
	private InternalFrame1 frm1;
	private static IHMApp instance;
	private JMenu acceuil_connection;


	public IHMApp() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension screenSize = new Dimension(1000, 750);
		setBounds(0, 0, screenSize.width, screenSize.height);

		//Titre
		setTitle("Clinique vétérinaire");

		// initialiser l'ecran MDI
		desktopPane = new JDesktopPane();

		// Associer le JDesktopPane à la JFrame
		setContentPane(desktopPane);

		// Barre de menus
		setJMenuBar(getMenuBarre());
		
		//Frame interne exemple		
		//desktopPane.add(getFrm1());
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

	public static IHMApp getInstance(){
		if(instance == null){
			instance = new IHMApp();
		}
		return instance;
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
			getFrm1().setVisible(true);
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
			System.out.println("gestion clients");
			break;
		default:
			System.out.println("Probleme e=" + e);
		}
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public JMenuBar getMenuBarre() {
		if (menuBarre == null) {
			menuBarre = new JMenuBar();

			createMenuBar();
		}
		return menuBarre;
	}

	public InternalFrame1 getFrm1() {
		if(frm1== null){
			frm1 = new InternalFrame1();
		}
		return frm1;
	}

	public JMenu getAccueil_connection(){
		if(acceuil_connection  == null){
			acceuil_connection = new JMenu("Acceuil");
		}
		return acceuil_connection;
	}

}
