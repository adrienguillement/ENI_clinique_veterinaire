package fr.eni.clinique.ihm;

import fr.eni.clinique.ihm.ecranClient.ClientFrame;
import fr.eni.clinique.ihm.ecranPersonnel.PersonnelFrame;

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
    private ClientFrame clientFrame;


    public IHMapp() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);

        // initialiser l'ecran MDI
        desktopPane = new JDesktopPane();

        // Associer le JDesktopPane à la JFrame
        setContentPane(desktopPane);

        // Barre de menus
        setJMenuBar(getMenuBarre());

        //Frame interne exemple
        desktopPane.add(getPersonnelFrame());
        desktopPane.add(getClientFrame());

    }

    // Lancement de l'application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                IHMapp ecran = new IHMapp();

                JDialog login = new JDialog(ecran, "fezfez", Dialog.ModalityType.DOCUMENT_MODAL);
                login.setModal(true);
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

        // Menu personnel
        menuItem = new JMenuItem("Gestion Personnel");
        menuBarre.add(menuItem);
        menuItem.setActionCommand("gestionPersonnel");
        menuItem.addActionListener(this);

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

            case "gestionPersonnel":
                getPersonnelFrame().setVisible(true);
                break;

            case "priseRdv":
                System.out.println("oui");
                break;

            case "gestionClient":
                getClientFrame().setVisible(true);

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

    public PersonnelFrame getPersonnelFrame() {
        if(personnelFrame== null){
            personnelFrame = new PersonnelFrame();
        }
        return personnelFrame;
    }

    /**
     * Getter frame client
     * @return
     */
    public ClientFrame getClientFrame() {
        if(clientFrame == null) {
            clientFrame = new ClientFrame();
        }
        return clientFrame;
    }


}
