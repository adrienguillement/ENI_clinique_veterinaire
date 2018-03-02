package fr.eni.clinique.ihm.ecranAnimal;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.CltManager;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class AnimalDialog extends JDialog{

    private JLabel clientLabel, codeLabel, nomLabel, couleurLabel, especeLabel, raceLabel, tatouageLabel, sexeLabel;
    private JTextField clientTextField, codeTextField, nomTextfield, couleurTextField, tatouageTextField;
    private JComboBox sexeComboBox, EspeceComboBox, RaceComboBox;

    private CltManager clientManager;

    {
        try {
            clientManager = new CltManager();
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    //creation d'un param optionnel
    private Animal animal = null;

    public AnimalDialog(Frame parent, Animal animal){
        super(parent, "Login", true);
        this.animal = animal;
        setIHM();


    }

    //methode avec parametre optinnel
    public AnimalDialog(Frame parent){
        super(parent, "Login", true);
        setIHM();
    }

    public void setIHM(){

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        TitledBorder border = new TitledBorder("Client");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);

        JPanel panelClient = new JPanel();
        panelClient.setBorder(border);

        Client client = new Client();
        try {
            client = clientManager.getClientById(animal.getCodeClient());
        } catch (BLLException e) {
            e.printStackTrace();
        }
        clientTextField = new JTextField(client.getNom() + " " + client.getPrenomClient());
        panelClient.add(clientLabel);

        //ajout du panel client dans le panel principal
        panel.add(panelClient);

        codeLabel = new JLabel("Code ");
        cs.gridx = 0;
        cs.gridy = 0;
        panel.add(codeLabel, cs);

        cs.gridx = 1;
        codeTextField = new JTextField();
        panel.add(codeTextField);

        cs.gridx = 0;
        cs.gridy = 1;
        codeLabel = new JLabel("Nom ");

        cs.gridy = 2;
        codeTextField = new JTextField();
        panel.add(codeTextField);
    }



}
