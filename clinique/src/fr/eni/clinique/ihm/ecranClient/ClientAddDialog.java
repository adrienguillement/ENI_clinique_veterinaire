package fr.eni.clinique.ihm.ecranClient;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.CltManager;
import fr.eni.clinique.bo.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientAddDialog extends JDialog {

    private JLabel codeLabel, nomLabel, prenomLabel, adresseLabel, codePostalLabel,
            villeLabel, numLabel, assuranceLabel, eMailLabel;
    private JTextField codeTextField, nomTextField, prenomTextField, adresseTextField, codePostalTextField,
            villeTextField, numTextField, assuranceTextField, eMailTextField;
    private JButton ajouter;
    private CltManager clientManager;
    private Client client;

    /**
     * JDialog d'ajout d'un client.
     * @param parent
     */
    public ClientAddDialog(Frame parent) {
        super(parent, "Ajouter un client", true);

        JPanel panel = new JPanel();


        this.codeLabel = new JLabel("Code client : ");
        this.codeTextField = new JTextField(10);
        this.codeTextField.setEditable(false);

        this.nomLabel = new JLabel("Nom: ");
        this.nomTextField = new JTextField(10);

        this.prenomLabel = new JLabel("Prenom: ");
        this.prenomTextField = new JTextField(10);

        this.adresseLabel = new JLabel("Adresse: ");
        this.adresseTextField = new JTextField(10);

        this.codePostalLabel = new JLabel("Code postal: ");
        this.codePostalTextField = new JTextField(10);

        this.villeLabel = new JLabel("Ville: ");
        this.villeTextField = new JTextField(10);

        this.numLabel = new JLabel("Num tel: ");
        this.numTextField = new JTextField(10);

        this.assuranceLabel = new JLabel("Assurance: ");
        this.assuranceTextField = new JTextField(10);

        this.eMailLabel = new JLabel("eMail: ");
        this.eMailTextField = new JTextField(10);

        this.ajouter = new JButton("Ajouter");
        this.ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    client = new Client(nomTextField.getText(), prenomTextField.getText(), adresseTextField.getText(), null, codePostalTextField.getText(), villeTextField.getText(), numTextField.getText(), assuranceTextField.getText(), eMailTextField.getText(), null, false);
                    clientManager = new CltManager();

                    if(clientManager.validerClient(client) == true) {
                        clientManager.insertClient(client);
                        JOptionPane.showMessageDialog(null, "Utilisateur ajouté", null, JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Les champs ne sont pas correctement complétés", null, JOptionPane.WARNING_MESSAGE);
                    }
                } catch (BLLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Problème lors de l'ajout, vérifier que tout les champs sont complétés correctement.", null, JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.gridwidth = 1;

        gbc.gridy = 0;
        gbc.gridx = 0;
        panel.add(nomLabel,gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        panel.add(nomTextField,gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(prenomLabel,gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        panel.add(prenomTextField,gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(adresseLabel,gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        panel.add(adresseTextField,gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(codePostalLabel,gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        panel.add(codePostalTextField,gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        panel.add(villeLabel,gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        panel.add(villeTextField,gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        panel.add(numLabel,gbc);

        gbc.gridy = 5;
        gbc.gridx = 1;
        panel.add(numTextField,gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        panel.add(assuranceLabel,gbc);

        gbc.gridy = 6;
        gbc.gridx = 1;
        panel.add(assuranceTextField,gbc);

        gbc.gridy = 7;
        gbc.gridx = 0;
        panel.add(eMailLabel,gbc);

        gbc.gridy = 7;
        gbc.gridx = 1;
        panel.add(eMailTextField,gbc);

        gbc.gridy = 8;
        gbc.gridx = 0;
        panel.add(ajouter, gbc);



        panel.setVisible(true);


        getContentPane().add(panel, BorderLayout.CENTER);

        setSize(new Dimension(500,600));
        //setResizable(false);
        setLocationRelativeTo(parent);
        this.setVisible(true);

    }
}
