package fr.eni.clinique.ihm.ecranRDV;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Agenda;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AgendaTableModele extends AbstractTableModel{

    private List<Agenda> listeAgenda;
    private String[] agendaColonnes = {"Heure", "Nom du client", "Animal", "Race"};

    public AgendaTableModele(List<Agenda> listeAgenda){
        this.listeAgenda = listeAgenda;
    }

    @Override
    public String getColumnName(int index) {
        return agendaColonnes[index];
    }

    @Override
    public int getRowCount() {
        return listeAgenda.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object ret = null;
        switch (columnIndex){
            case 0:
                ret=listeAgenda.get(rowIndex).getCodeVeto();
                break;
            case 1:
                ret=listeAgenda.get(rowIndex).getDateRdv();
                break;
            case 2:
                ret=listeAgenda.get(rowIndex).getCodeAnimal();
                break;
            case 3:
                //ret=listeAgenda.get(rowIndex).getCouleur();
                break;
            default:
                break;
        }
        return ret;
    }
}
