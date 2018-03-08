package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.ecranAnimal.AnimalDialog;
import fr.eni.clinique.ihm.ecranClient.ClientFrame;
import fr.eni.clinique.ihm.ecranClient.ClientTable;
import fr.eni.clinique.ihm.ecranPersonnel.PersonnelFrame;
import fr.eni.clinique.ihm.ecranRDV.PriseRendezVousFrame;
import fr.eni.clinique.ihm.login.LoginDialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class IHMapp extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JDesktopPane desktopPane;
    private JMenuBar menuBarre;
    private JMenu menuAgenda;
    private PersonnelFrame personnelFrame;

    private static Personnel utilisateurEnCours;
    private static IHMapp instance;
    private ClientFrame clientPanel;
    private PriseRendezVousFrame rendezVousFrame;

    public static IHMapp getInstance() throws BLLException{
        if(IHMapp.instance == null){
            IHMapp.instance = new IHMapp();
        }
        return IHMapp.instance;
    }

    public IHMapp() throws BLLException{
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 900, 700);

        // initialiser l'ecran MDI
        desktopPane = new JDesktopPane();

        // Associer le JDesktopPane à la JFrame
        setContentPane(desktopPane);

        // Barre de menus
        //setJMenuBar(getMenuBarre());

        //Frame interne exemple
        desktopPane.add(getPersonnelFrame());
        desktopPane.add(getClientSearch());
        desktopPane.add(getPriseRendezVousFrame());

        //JDialog pour login (modal)
        final JFrame frame = new JFrame("Authentification");
        LoginDialog loginDlg = new LoginDialog(frame, this);
        loginDlg.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        loginDlg.setVisible(true);

        //JDialog login = new JDialog(this, "fezfez", Dialog.ModalityType.DOCUMENT_MODAL);
        //login.setModal(true);
        //login.setVisible(true);
    }

    // Lancement de l'application
    public static void main(String[] args) throws BLLException {
        SwingUtilities.invokeLater(() -> {
            IHMapp ecran = null;
            try {
                ecran = getInstance();
                ecran.setVisible(true);
            } catch (BLLException e) {
                e.printStackTrace();
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

        if(utilisateurEnCours.getRole().equals("sec")){
            // Menu Gestion RDV
            JMenu menuGestionRDV = new JMenu("Gestion rendez vous");
            menuBarre.add(menuGestionRDV);

            // Sous menu prise rdv
            JMenuItem menuItemPriseRDV = new JMenuItem("Prise de rendez vous");
            menuItemPriseRDV.setActionCommand("priseRdv");
            menuItemPriseRDV.addActionListener(this);
            menuGestionRDV.add(menuItemPriseRDV);

            // Sous menu gestion client
            JMenuItem menuGestionClient = new JMenuItem("Gestion des clients");
            menuGestionClient.setActionCommand("gestionClient");
            menuGestionClient.addActionListener(this);
            menuGestionRDV.add(menuGestionClient);
        }

        if(utilisateurEnCours.getRole().equals("adm")){
            // Menu personnel
            menuItem = new JMenuItem("Gestion du Personnel");
            menuBarre.add(menuItem);
            menuItem.setActionCommand("gestionPersonnel");
            menuItem.addActionListener(this);
        }

        if(utilisateurEnCours.getRole().equals("vet")){
            menuItem = new JMenuItem("Agenda");
            menuBarre.add(menuItem);
            menuItem.setActionCommand("agenda");
            menuItem.addActionListener(this);
        }
    }

    // Réagir aux clicks sur les menus
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "deconnexion":
                System.out.println("Deconnexion");
                //JDialog pour login (modal)
                setUtilisateurEnCours(null);
                final JFrame frame = new JFrame("Authentification");
                LoginDialog loginDlg = new LoginDialog(frame, this);
                loginDlg.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                loginDlg.setVisible(true);

                break;
            case "fermer":
                System.exit(0);
                break;
            case "gestionPersonnel":
                try {
                    getClientSearch().setVisible(false);
                    getPriseRendezVousFrame().setVisible(false);
                } catch (BLLException e1) {
                    e1.printStackTrace();
                }
                getPersonnelFrame().setVisible(true);
                break;

            case "priseRdv":
                getPersonnelFrame().setVisible(false);
                try {
                    getClientSearch().setVisible(false);
                    getPriseRendezVousFrame().setVisible(true);
                } catch (BLLException e1) {
                    e1.printStackTrace();
                }
                break;

            case "gestionClient":
                getPersonnelFrame().setVisible(false);
                try {
                    getClientSearch().setVisible(true);
                } catch (BLLException e1) {
                    e1.printStackTrace();
                }

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

    public void setMenuBarre(JMenuBar menuBarre){
        this.menuBarre = menuBarre;
    }


    public PersonnelFrame getPersonnelFrame() {
        if(personnelFrame== null){
            personnelFrame = new PersonnelFrame(this);
        }
        return personnelFrame;
    }

    public void setUtilisateurEnCours(Personnel utilisateurEnCours) {
        this.utilisateurEnCours = utilisateurEnCours;
    }

    /**
     * Getter frame client
     * @return
     */
    public ClientFrame getClientSearch() throws BLLException {
        if(clientPanel == null) {
            clientPanel = new ClientFrame(this);
        }
        return clientPanel;
    }

    public PriseRendezVousFrame getPriseRendezVousFrame() throws BLLException{
        if(rendezVousFrame == null){
            rendezVousFrame = new PriseRendezVousFrame(this);
        }
        return rendezVousFrame;
    }

    public JMenuBar getJMenuBar(){
        return menuBarre;
    }

}
