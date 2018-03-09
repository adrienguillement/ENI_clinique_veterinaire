package fr.eni.clinique.ihm.ecranAgenda;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.CltManager;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogDossier extends JDialog {

    /***
     * Attributs
     */
    private JPanel mainPanel;
    private JPanel topButtonPanel;
    private JPanel infoAnimalPanel, infoAnimalDetail;
    private JPanel antecedentsPanel;
    private AnimalManager animalManager;

    private JButton valider;
    private JPanel nomClient;
    private JTextArea textArea;

    private Animal currentAnimal;


    /**
     * Constructeur
     */
    public DialogDossier(Frame parent, int idAnimal){
        super(parent, "Dossier animal", true);

        try {
            animalManager = new AnimalManager();
            currentAnimal = animalManager.getFromCode(idAnimal);
        } catch (BLLException e) {
            e.printStackTrace();
        }

        getContentPane().add(getTopPanel(), BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.add(getInfoAnimalPanel());
        panel.add(getAntecedantsPanel());
        getContentPane().add(panel, BorderLayout.CENTER);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
        this.setSize(new Dimension(500,450));
        this.setContentPane(getContentPane());
        this.setVisible(true);
    }

    public JPanel getAntecedantsPanel(){
        antecedentsPanel = new JPanel();

        antecedentsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridwidth = 0;

        antecedentsPanel.add(new JLabel("Antécédents/Consultations"), gbc);
        textArea = new JTextArea(15,12);
        textArea.setText(currentAnimal.getAntecedents());

        gbc.gridy = 1;
        antecedentsPanel.add(textArea, gbc);
        return antecedentsPanel;
    }

    /**
     * Get info animal panel (gauche)
     */
    public JPanel getInfoAnimalPanel() {
        infoAnimalPanel = new JPanel();
        infoAnimalPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        nomClient = new JPanel();
        TitledBorder border = new TitledBorder("Client");
        nomClient.setBorder(border);

        Client client = null;
        try {
            CltManager clientManager = new CltManager();
            System.out.println(currentAnimal.getCodeClient());
            System.out.println(clientManager.getClientById(currentAnimal.getCodeClient()));

            client = clientManager.getClientById(currentAnimal.getCodeClient());
        } catch (BLLException e) {
            e.printStackTrace();
        }
         nomClient.add(new JLabel(client.getNom() + " " + client.getPrenomClient()), gbc);

        infoAnimalDetail = new JPanel();
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        infoAnimalPanel.add(new JLabel("Animal : "), gbc);
        gbc.gridx = 1;
        infoAnimalPanel.add(new JLabel(String.valueOf(currentAnimal.getCodeClient())), gbc);

        gbc.gridy = 2;
        infoAnimalPanel.add(new JLabel(currentAnimal.getNomAnimal()), gbc);
        gbc.gridy = 3;
        infoAnimalPanel.add(new JLabel(currentAnimal.getCouleur()), gbc);
        gbc.gridx = 2;
        infoAnimalPanel.add(new JLabel(currentAnimal.getSexe()), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        infoAnimalPanel.add(new JLabel(currentAnimal.getRace().getRace()), gbc);
        gbc.gridy = 5;
        if(currentAnimal.getTatouage().equals(null)){
            infoAnimalPanel.add(new JLabel("Non tatoué"), gbc);
        }else {
            infoAnimalPanel.add(new JLabel(currentAnimal.getTatouage()), gbc);
        }

        infoAnimalPanel.add(nomClient);
        infoAnimalPanel.add(infoAnimalDetail);
        return infoAnimalPanel;
    }


    /**
     * TOP PANEL
     */
    public JPanel getTopPanel(){
        topButtonPanel = new JPanel();
        topButtonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        topButtonPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        valider = new JButton("valider");
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAnimal.setAntecedents(textArea.getText());
                animalManager.update(currentAnimal);
            }
        });

        topButtonPanel.add(valider);
        return topButtonPanel;
    }
}
