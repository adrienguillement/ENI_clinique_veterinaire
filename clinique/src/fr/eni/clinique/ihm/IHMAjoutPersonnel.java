package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
//import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.utils.SHA512;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IHMAjoutPersonnel extends JFrame {
    private static IHMAjoutPersonnel instance;
    private JLabel nomLabel, roleLabel, motPasseLabel;
    private JTextField nomTextField, roleTextField;
    private JPasswordField motPasse;
    private JButton valider, annuler;
    private Personnel personnel;
    private PersonnelManager personnelManager;

    public static IHMAjoutPersonnel getInstance() {
        if (instance == null) {
            instance = new IHMAjoutPersonnel();
        }
        return instance;
    }

    public IHMAjoutPersonnel() {
        setIHMAjoutPersonnel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    IHMAjoutPersonnel window = new IHMAjoutPersonnel();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setIHMAjoutPersonnel() {
        this.setTitle("Personnel");
        this.setSize(new Dimension(600, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        nomLabel = new JLabel("nom: ");
        nomTextField = new JTextField(10);

        roleLabel = new JLabel("role: ");
        roleTextField = new JTextField(10);

        motPasseLabel = new JLabel("mot de passe : ");
        motPasse = new JPasswordField(10);

        valider = new JButton("valider");
        annuler = new JButton("annuler");


        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10,10,10);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nomLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nomTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(roleLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(roleTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(motPasseLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(motPasse, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(valider, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(annuler, gbc);

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    personnel = new Personnel(nomTextField.getText(), SHA512.getSHA512(motPasse.getText(), "toto"), roleTextField.getText(), false);
                    personnelManager = new PersonnelManager();
                    personnelManager.insert(personnel);
                } catch (BLLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        this.setContentPane(panel);
        this.setVisible(true);
    }
}