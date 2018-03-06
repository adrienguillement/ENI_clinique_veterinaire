package fr.eni.clinique.ihm.ecranAnimal;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.CltManager;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AnimalDialog extends JDialog{

    private JLabel clientLabel, codeLabel, nomLabel, couleurLabel, especeLabel, raceLabel, tatouageLabel, sexeLabel;
    private JTextField clientTextField, codeTextField, nomTextfield, couleurTextField, tatouageTextField;
    private JComboBox sexeComboBox, especeComboBox, raceComboBox, clientComboBox;
    private JButton ajouterButton, annulerButton;
    private boolean estModification = false;
    private List<Client> clients = new ArrayList<>();
    private List<String> sexes = new ArrayList<>();
    private List<String> especes = new ArrayList<>();
    private List<String> races = new ArrayList<>();
    private List<Animal> animaux = new ArrayList<>();

    private AnimalManager animalManager;

    {
        try {
            animalManager = new AnimalManager();
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

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
        super(parent, "Animal", true);
        this.animal = animal;
        setIHM();


    }

    //methode avec parametre optinnel
    public AnimalDialog(Frame parent){
        super(parent, "Animal", true);
        setIHM();
    }

    public void setIHM(){

        if(animal != null){
            estModification = true;
        }

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        cs.insets = new Insets(5, 5, 5, 5);


        TitledBorder border = new TitledBorder("Client");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);

        JPanel panelClient = new JPanel();
        panelClient.setBorder(border);

        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 3;

        if(estModification){
            Client client = new Client();
            try {
                client = clientManager.getClientById(animal.getCodeClient());
            } catch (BLLException e) {
                e.printStackTrace();
            }

            clientTextField = new JTextField(client.getNom() + " " + client.getPrenomClient());
            panel.add(couleurTextField, cs);
        }
        else{
            clientComboBox = new JComboBox();
            panel.add(clientComboBox, cs);
        }

        codeLabel = new JLabel("Code ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(codeLabel, cs);

        cs.gridx = 1;
        codeTextField = new JTextField(10);
        panel.add(codeTextField, cs);

        cs.gridx = 0;
        cs.gridy = 2;
        nomLabel = new JLabel("Nom ");
        panel.add(nomLabel, cs);

        cs.gridx = 1;
        nomTextfield = new JTextField(10);
        panel.add(codeTextField,cs);

        //combo box sexe
        cs.gridx = 3;
        sexeComboBox = new JComboBox();
        panel.add(sexeComboBox,cs);

        cs.gridx = 0;
        cs.gridy = 3;
        couleurLabel = new JLabel("Couleur ");
        panel.add(couleurLabel,cs);

        cs.gridx = 1;
        couleurTextField = new JTextField(10);
        panel.add(couleurTextField,cs);

        cs.gridx = 0;
        cs.gridy = 4;
        especeLabel = new JLabel("Espèce");
        panel.add(especeLabel, cs);

        cs.gridx = 1;
        especeComboBox = new JComboBox();
        panel.add(especeComboBox, cs);

        //ComboBox Race
        cs.gridx = 3;
        raceComboBox = new JComboBox();
        panel.add(raceComboBox, cs);

        cs.gridx = 0;
        cs.gridy = 5;
        tatouageLabel = new JLabel("Tatouage ");
        panel.add(tatouageLabel, cs);

        cs.gridx = 1;
        tatouageTextField = new JTextField(10);
        panel.add(tatouageTextField, cs);

        cs.gridx = 0;
        cs.gridy = 6;
        ajouterButton = new JButton();
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //on récupère les infos des composants graphiques pour maj animal

                //Ajout ou modification d'un animal
                if(estModification){
                    animalManager.update(animal);
                }
                else{
                    animalManager.insert(animal);
                }
            }
        });
        panel.add(ajouterButton, cs);

        cs.gridx = 1;
        cs.gridy = 6;
        annulerButton = new JButton("Annuler");
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Annuler
            }
        });
        panel.add(annulerButton, cs);

        if(estModification){
            codeTextField.setText(String.valueOf(animal.getCodeAnimal()));
            nomTextfield.setText(animal.getNomAnimal());
            couleurTextField.setText(animal.getCouleur());
            especeLabel.setText(animal.getRace().getEspece());
            tatouageTextField.setText(animal.getTatouage());
            ajouterButton.setText("Modifier");
        }
        else{
            ajouterButton.setText("Ajouter");
            try {
                animaux = DAOFactory.getAnimalDAO().selectAll();
            } catch (DALException e) {
                e.printStackTrace();
            }

            //chargement liste clients
            try {
                clients = clientManager.getCatalogue();
            } catch (BLLException e) {
                e.printStackTrace();
            }
            for(Client elt:clients){
                clientComboBox.addItem(elt.getNom() + " " + elt.getPrenomClient());
            }

            //maj listes
            for(Animal elt:animaux){
                //liste races
                String race = elt.getRace().getRace();
                if(!races.contains(race)){
                    races.add(race);
                    raceComboBox.addItem(elt.getRace().getRace());
                }

                //liste especes
                String espece = elt.getRace().getEspece();
                if(!especes.contains(espece)){
                    especes.add(espece);
                    especeComboBox.addItem(elt.getRace().getEspece());
                }

                //liste sexes
                String sexe = elt.getSexe();
                if(!sexes.contains(sexe)){
                    sexes.add(sexe);
                    sexeComboBox.addItem(elt.getSexe());
                }
            }
        }

        getContentPane().add(panel, BorderLayout.CENTER);

        pack();
        setResizable(true);
    }



}
