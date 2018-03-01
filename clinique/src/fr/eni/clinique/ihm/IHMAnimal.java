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

    private AnimalManager animalManager;
    private RaceManager raceManager;
    //private ModeleDynamiqueObjetAnimal modele = new ModeleDynamiqueObjetAnimal();
    private JTable table;
    private Animal animalSelected;

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

        //List<Animal> animaux = animalManager.getListeAnimaux();

        setIHMAnimal(null);
    }

    private void setIHMAnimal(List<Animal> Animaux) {
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
        table = new JTable(data, columns);
        //add the table to the frame
        this.add(new JScrollPane(table));

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String selectedData = null;

                int[] selectedRow = table.getSelectedRows();
                int[] selectedColumns = table.getSelectedColumns();

                for (int i = 0; i < selectedRow.length; i++) {
                    for (int j = 0; j < selectedColumns.length; j++) {
                        selectedData = (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
                    }
                }
                System.out.println("avant cast");
                //String selectedValue = ;
                System.out.println("second cast");
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

