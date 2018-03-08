package fr.eni.clinique.ihm.ecranAnimal;

import fr.eni.clinique.bo.Animal;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AnimalTableModele extends AbstractTableModel {
  
    /**
     * Attributs
     */
    private List<Animal> animaux;
    private String[] titreColonnes = {"Numero", "Nom", "Sexe", "Couleur", "Race", "Espece", "Tatouage"};

    /**
     * Constructeur
     * @param animaux
     */
    public AnimalTableModele(List<Animal> animaux){
        this.animaux = animaux;
    }

    @Override
    public int getRowCount() {
        return animaux.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int index) {
        return titreColonnes[index];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object ret = null;
        switch (columnIndex){
            case 0:
                ret=animaux.get(rowIndex).getCodeAnimal();
                break;
            case 1:
                ret=animaux.get(rowIndex).getNomAnimal();
                break;
            case 2:
                ret=animaux.get(rowIndex).getSexe();
                break;
            case 3:
                ret=animaux.get(rowIndex).getCouleur();
                break;
            case 4:
                ret=animaux.get(rowIndex).getRace().getRace();
                break;
            case 5:
                ret=animaux.get(rowIndex).getRace().getEspece();
                break;
            default:
                break;
        }
        return ret;
    }


    /**
     * Getter and setter
     */
    public void setAnimaux(List<Animal> animaux){
        this.animaux = animaux;
        this.fireTableDataChanged();
    }

    public List<Animal> getAnimaux() {
        return animaux;
    }

    public Animal getValueByRow(int row){
        return this.animaux.get(row);
    }
}
