package fr.eni.clinique.ihm.ecranClient;

import javax.swing.*;

public class ClientSearch extends JInternalFrame {

    private JButton rechercherButton;
    private JTextField rechercherField;

    /**
     * Constructeur
     */
    public ClientSearch() {
        super("Rechercher un client", true, true, true,true);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBounds(0, 0, 900, 600);
        setContentPane(getButtonSearch());
        setContentPane(getPanelSearch());
    }

    private JPanel getButtonSearch() {
        JPanel panelButtonSearch = new JPanel();

        return panelButtonSearch;
    }

    private JPanel getPanelSearch() {
        JPanel panelSearch = new JPanel();

        return panelSearch;
    }
}
