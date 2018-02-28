package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.RaceManager;
import fr.eni.clinique.bo.Animal;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class IHMAnimal extends JFrame{

    private AnimalManager animalManager;
    private RaceManager raceManager;
    //private ModeleDynamiqueObjetAnimal modele = new ModeleDynamiqueObjetAnimal();
    private JTable table;

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

    private void setIHMAnimal(List<Animal> Animaux) {
        this.setTitle("Liste animaux pour 1 client ");
        this.setSize(new Dimension(400,200));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        table = new JTable();

        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel boutons = new JPanel();

        /*boutons.add(new JButton(new AddAction()));
        boutons.add(new JButton(new RemoveAction()));*/

        getContentPane().add(boutons, BorderLayout.SOUTH);

        pack();

        //TODO
    }

    /*private class AddAction extends AbstractAction {
        private AddAction() {
            super("Ajouter");
        }

        public void actionPerformed(ActionEvent e) {
            modele.addAmi(new Ami("Megan", "Sami", Color.green, false, Sport.NATATION));
        }
    }

    private class RemoveAction extends AbstractAction {
        private RemoveAction() {
            super("Supprimmer");
        }

        public void actionPerformed(ActionEvent e) {
            int[] selection = tableau.getSelectedRows();

            for(int i = selection.length - 1; i >= 0; i--){
                modele.removeAmi(selection[i]);
            }
        }
    }*/
}

