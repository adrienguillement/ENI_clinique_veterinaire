package fr.eni.clinique.ihm.ecranClient;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import fr.eni.clinique.ihm.IHMapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrame extends JInternalFrame {

    private JButton ajouter, rechercher;
    private JTextField rechercherField;
    private ClientForm panelForm;


    /**
     * Constructeur
     */
    public ClientFrame(int idClient) {
        super("Gestion des client", true, true, true,true);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBounds(0, 0, 900, 600);
        setContentPane(getPanelClient(idClient));
    }

    /**
     * Initialisation panel client
     * @return
     */
    private JPanel getPanelClient(int idClient) {
        JPanel panelClient = new JPanel();
        panelClient.setOpaque(true);
        panelClient.setLayout(new BorderLayout());
        panelClient.add(getPanelButton(), BorderLayout.PAGE_START);
        panelClient.add(getPanelForm(idClient), BorderLayout.LINE_START);
        panelClient.add(new JLabel("cc"), BorderLayout.LINE_END);
        return panelClient;
    }

    /**
     * Initialisation panel button
     * @return
     */
    private JPanel getPanelButton() {
        JPanel panelBoutton = new JPanel();

        //Boutton ajouter
        ajouter = new JButton("Ajouter client");
        ajouter.addActionListener(e -> System.out.println("Ajouter client"));

        //Champ et boutton rechercher
        rechercherField = new JTextField();
        rechercherField.setPreferredSize(new Dimension(150,25));
        rechercher = new JButton("Rechercher client");
        rechercher.addActionListener(e -> System.out.println("rechercher client"));

        panelBoutton.add(rechercherField);
        panelBoutton.add(rechercher);
        panelBoutton.add(ajouter);
        return panelBoutton;
    }

    private ClientForm getPanelForm(int idClient) {
        if(panelForm == null) {
            panelForm = new ClientForm(idClient);
        }
        return panelForm;
    }
}
