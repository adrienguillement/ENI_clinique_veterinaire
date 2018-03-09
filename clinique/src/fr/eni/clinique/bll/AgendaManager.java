package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOAgenda;
import fr.eni.clinique.dal.DAOFactory;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class AgendaManager {
    private static DAOAgenda daoAgenda;

    public AgendaManager() throws BLLException{
        daoAgenda = new DAOFactory().getAgendaDAO();
    }

    public List<Agenda> selectByDateAndPersonneID(Timestamp date, int id){
        List<Agenda> listeAgenda = null;

        try{
            listeAgenda = daoAgenda.selectByDateAndPersonneID(date, id);
        } catch(DALException e){
            e.printStackTrace();
        }
        return listeAgenda;
    }

    public List<Agenda> getListFromPersonnelId(int id){
        List<Agenda> listeAgenda = null;

        try{
            listeAgenda = daoAgenda.selectByPersonneId(id);
        } catch(DALException e){
            e.printStackTrace();
        }
        return listeAgenda;
    }

    public List<Agenda> getListeAgenda(){
        List<Agenda> listeAgenda= null;

        try{
            listeAgenda = daoAgenda.selectAll();
        }catch(DALException e){
            e.printStackTrace();
        }
        return listeAgenda;
    }

    public void insert(Agenda agenda){
        try {
            daoAgenda.insert(agenda);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void update(Agenda agenda){
        try {
            daoAgenda.update(agenda);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void delete(Agenda agenda){
        try {
            daoAgenda.delete(agenda);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public List<Agenda> getListeAgendaFromPersonnel(Personnel personnel){
        List<Agenda> liste = new ArrayList<>();

        liste = daoAgenda.selectedByPersonnel(personnel);

        return liste;
    }
}
