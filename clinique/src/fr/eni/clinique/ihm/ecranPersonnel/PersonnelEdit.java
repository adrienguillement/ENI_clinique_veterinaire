package fr.eni.clinique.ihm.ecranPersonnel;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.utils.SHA512;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonnelEdit extends JDialog{
    private JLabel nomLabel, roleLabel, motPasseLabel;
    private JTextField nomTextModif, roleTextModif;
    private JComboBox roleBoxModif;
    private JPasswordField motPasse;
    private JButton valider, annuler;
    private Personnel personnel;
    private PersonnelManager personnelManager;

    /**
     * Fenêtre de réinitialisation de personnel
     * @param parent
     * @param personnel
     */
    public PersonnelEdit(Frame parent, Personnel personnel) {
        super(parent, "Edition Personnel", true);
        //
        this.personnel = personnel;

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.CENTER;

        Object[] role = new Object[]{"adm", "vet", "sec"};
        roleBoxModif = new JComboBox(role);

        nomLabel = new JLabel("nom: ");
        nomTextModif = new JTextField(10);

        roleLabel = new JLabel("role: ");
        roleTextModif = new JTextField(10);

        motPasseLabel = new JLabel("mot de passe : ");
        motPasse = new JPasswordField(10);

        valider = new JButton("valider");
        annuler = new JButton("annuler");

        gbc.insets = new Insets(20, 10,10,10);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nomLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nomTextModif, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(roleLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(roleBoxModif, gbc);

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
        /**
         * bouton valider
         */
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object selected = roleBoxModif.getSelectedItem();
                    personnel.setNom(nomTextModif.getText());
                    personnel.setMotPasse((SHA512.getSHA512(motPasse.getText(), "toto")));
                    personnel.setRole(selected.toString());
                    personnelManager = new PersonnelManager();
                    System.out.println(personnel);
                    personnelManager.updatePersonnel(personnel);
                    dispose();
                } catch (BLLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        /**
         * bouton d'annulation
         */
        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        getContentPane().add(panel, BorderLayout.CENTER);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
        this.setContentPane(panel);
        this.setVisible(true);
    }

}
