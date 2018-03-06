package fr.eni.clinique.ihm.ecranRDV;

import fr.eni.clinique.bo.Agenda;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AgendaTableModele extends AbstractTableModel{

    private List<Agenda> listeAgenda = new ArrayList<>();
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
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
