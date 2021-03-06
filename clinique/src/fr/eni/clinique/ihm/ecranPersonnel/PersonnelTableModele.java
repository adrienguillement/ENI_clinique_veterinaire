package fr.eni.clinique.ihm.ecranPersonnel;

import fr.eni.clinique.bo.Personnel;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PersonnelTableModele extends AbstractTableModel{
    private List<Personnel> personnels;
    private String[] titreColonnes = {"CodePers","Nom", "Role"};

    public void setPersonnels(List<Personnel> personnels) {
        this.personnels = personnels;
        this.fireTableDataChanged();
    }


    @Override
    public String getColumnName(int index) {
        return titreColonnes[index];
    }

    public PersonnelTableModele(List<Personnel> personnels){
        this.personnels = personnels;
    }

    @Override
    public int getRowCount() {
        return personnels.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object ret = null;
        switch (columnIndex){
            case 0:
                ret=personnels.get(rowIndex).getCodePers();
                break;
            case 1:
                ret=personnels.get(rowIndex).getNom();
                break;
            case 2:
                ret=personnels.get(rowIndex).getRole();
                break;
            default:
                break;
        }
        return ret;
    }

}
