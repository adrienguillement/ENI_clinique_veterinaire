package fr.eni.clinique.ihm.ecranRDV;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Personnel;

import javax.swing.table.AbstractTableModel;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AgendaTableModele extends AbstractTableModel{

    private List<Agenda> listeAgenda;
    private String[] agendaColonnes = {"Heure", "Nom du client", "Animal", "Race", "Date"};

    private PersonnelManager personnelManager;
    private AnimalManager animalManager;

    private Personnel veto;
    private Animal animal;


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
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object ret = null;

        try {
            personnelManager = new PersonnelManager();
            animalManager = new AnimalManager();
        } catch (BLLException e) {
            e.printStackTrace();
        }
        switch (columnIndex){
            case 0:
                java.util.Date date = listeAgenda.get(rowIndex).getDateRdv();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                int hour = cal.get(Calendar.HOUR);
                int minute = cal.get(Calendar.MINUTE);
                String completeHeure = "";
                if(hour < 10) {
                    completeHeure += "0" + String.valueOf(hour);
                }
                else {
                    completeHeure += String.valueOf(hour);
                }
                if(minute < 10){
                    completeHeure += ":0" + String.valueOf(minute);
                }

                ret = completeHeure;
                break;
            case 1:
                veto = personnelManager.getPersonnelById(listeAgenda.get(rowIndex).getCodeVeto());
                ret=veto.getNom();
                break;
            case 2:
                animal = animalManager.getFromCode(listeAgenda.get(rowIndex).getCodeAnimal());
                ret = animal.getNomAnimal();
                break;
            case 3:
                animal = animalManager.getFromCode(listeAgenda.get(rowIndex).getCodeAnimal());
                ret = animal.getRace().getRace();
                break;
            case 4:
                ret = listeAgenda.get(rowIndex).getDateRdv();
            default:
                break;
        }
        return ret;
    }


    public List<Agenda> getListeAgenda() {
        return listeAgenda;
    }

    public void setListeAgenda(List<Agenda> listeAgenda) {
        this.listeAgenda = listeAgenda;
        this.fireTableDataChanged();
    }
}
