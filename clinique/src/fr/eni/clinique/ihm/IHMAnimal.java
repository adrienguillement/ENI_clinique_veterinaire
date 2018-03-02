package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.RaceManager;
import fr.eni.clinique.bo.Animal;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class IHMAnimal extends JFrame{

    //GLOBAL
    private AnimalManager animalManager;
    private RaceManager raceManager;

    //SetIHMAjoutOuModification
    private JLabel clientLabel, codeLabel, nomLabel, couleurLabel, especeLabel, raceLabel, tatouageLabel, sexeLabel;
    private JTextField clientTextField, codeTextField, nomTextfield, couleurTextField, tatouageTextField;
    private JComboBox sexeComboBox, EspeceComboBox, RaceComboBox;

    //SetIHMAnimal
    private JTable table;
    private Animal animalSelected = null;

    // Lancement de l'application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try{
                    IHMAnimal window = new IHMAnimal();
                    window.setVisible(true);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public IHMAnimal(){
        try {
            animalManager = new AnimalManager();
        } catch (BLLException e) {
            e.printStackTrace();
        }

        List<Animal> animaux = animalManager.getListeAnimaux();

        setIHMAnimal(animaux);
    }

    private void setIHMAjoutOuModifcationAnimal(Animal animal, Boolean estModification){
        this.setTitle("Liste animaux pour 1 client ");
        this.setSize(new Dimension(400,200));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.gridwidth = 1;

        gbc.gridy = 0;
        gbc.gridx = 0;
        panel.add(clientLabel,gbc);
    }


    private void setIHMAnimal(List<Animal> animaux) {
        this.setTitle("Liste animaux pour 1 client ");
        this.setSize(new Dimension(400,200));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        //variables locales ca pas de co bdd atm
        String[] columns = new String[] {
                "Code", "Nom", "Sexe", "Couleur", "Race", "Espece"
        };

        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
                {1, "Ludo", "M", "Bleu", "Labrador", "Chien"},
                {21, "Jack Souslo", "F", "Violet", "Poisson papillon", "Poisson"},
        };

        //TODO: Creation de la classe model
        //table = new JTable(animaux);
        //add the table to the frame
        this.add(new JScrollPane(table));

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String selectedData = null;

                System.out.println("avant cast");

                int codeAnimal = (int)(table.getValueAt(table.getSelectedRow(), 0));
                if(codeAnimal != 0){
                    animalSelected.setCodeAnimal(codeAnimal);
                    System.out.printf(animalSelected.toString());
                }
                //System.out.println("Selected: " + selectedData);
            }

        });
    }
}

