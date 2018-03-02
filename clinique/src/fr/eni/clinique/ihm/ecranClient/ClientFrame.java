package fr.eni.clinique.ihm.ecranClient;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrame extends JInternalFrame {

    private JButton ajouter;
    private ClientForm panelForm;


    /**
     * Constructeur
     */
    public ClientFrame() {
        super("Gestion des client", true, true, true,true);

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBounds(100, 100,400, 200);
        setContentPane(getPanelClient());
    }

    /**
     * Initialisation panel client
     * @return
     */
    private JPanel getPanelClient() {
        JPanel panelClient = new JPanel();
        panelClient.setOpaque(true);
        panelClient.setLayout(new BorderLayout());
        panelClient.add(getPanelButton(), BorderLayout.PAGE_START);
        panelClient.add(getPanelForm(), BorderLayout.LINE_START);
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

        panelBoutton.add(ajouter);
        return panelBoutton;
    }

    private ClientForm getPanelForm() {
        if(panelForm == null) {
            panelForm = new ClientForm();
        }
        return panelForm;
    }
}
