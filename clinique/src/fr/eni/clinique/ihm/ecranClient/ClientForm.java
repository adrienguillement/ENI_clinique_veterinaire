package fr.eni.clinique.ihm.ecranClient;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.CltManager;
import fr.eni.clinique.bo.Client;

import javax.swing.*;
import java.awt.*;

public class ClientForm extends JPanel {

    private Client client;
    private JTextField code, nom, prenom, adresse, ville, codePostal, assurance, email, numTel, remarque;

    public ClientForm(int idClient){
        try {
            CltManager personnelManager = new CltManager();
            this.client = personnelManager.getClientById(idClient);
        } catch (BLLException e) {
            e.printStackTrace();
        }

        initForm();
    }

    private void initForm() {
        this.setLayout(new GridLayout(0, 2));
        this.add(new JLabel("Code : "));

        this.code = new JTextField(String.valueOf(client.getCode()));
        this.code.setEditable(false);
        JTextField codeClient = this.code;
        this.add(codeClient);

        this.add(new JLabel("Nom : "));
        this.nom = new JTextField(client.getNom());
        this.add(this.nom);

        this.add(new JLabel("Prenom : "));
        this.prenom = new JTextField(client.getPrenomClient());
        this.add(this.prenom);

        this.add(new JLabel("Email : "));
        this.email = new JTextField(client.getEmail());
        this.add(this.email);

        this.add(new JLabel("Adresse : "));
        this.adresse = new JTextField(client.getAdresse1());
        this.add(this.adresse);

        this.add(new JLabel("Code postal : "));
        this.codePostal = new JTextField(client.getCodePostal());
        this.add(this.codePostal);

        this.add(new JLabel("Ville : "));
        this.ville = new JTextField(client.getVille());
        this.add(this.ville);

        this.add(new JLabel("Assurance : "));
        this.assurance = new JTextField(client.getAssurance());
        this.add(this.assurance);

        this.add(new JLabel("Numéro tel : "));
        this.numTel = new JTextField(client.getNumTel());
        this.add(this.numTel);

        this.add(new JLabel("Remarque : "));
        this.remarque = new JTextField(client.getRemarque());
        this.add(this.remarque);
    }
}