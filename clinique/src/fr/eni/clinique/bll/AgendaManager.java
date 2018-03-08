package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOAgenda;
import fr.eni.clinique.dal.DAOFactory;

import javax.swing.*;
import java.util.List;

public class AgendaManager {
    private static DAOAgenda daoAgenda;

    public AgendaManager() throws BLLException{
        daoAgenda = new DAOFactory().getAgendaDAO();
    }

    public List<Agenda> getListeAgenda(){
        List<Agenda> listeAgenda= null;

        try{
            listeAgenda = daoAgenda.selectAll();
        }catch(DALException e){
            JOptionPane.showMessageDialog(null, "Impossible de récupérer l'agenda.", null, JOptionPane.ERROR_MESSAGE);

        }
        return listeAgenda;
    }

    public void insert(Agenda agenda){
        try {
            daoAgenda.insert(agenda);
        } catch (DALException e) {
            JOptionPane.showMessageDialog(null, "Impossible de créer un nouveau rendez vous.", null, JOptionPane.ERROR_MESSAGE);

        }
    }

    public void update(Agenda agenda){
        try {
            daoAgenda.update(agenda);
        } catch (DALException e) {
            JOptionPane.showMessageDialog(null, "Impossible de mettre à jour une réservation.", null, JOptionPane.ERROR_MESSAGE);

        }
    }

    public void delete(Agenda agenda){
        try {
            daoAgenda.delete(agenda);
        } catch (DALException e) {
            JOptionPane.showMessageDialog(null, "Impossible de supprimer ce rendez-vous.", null, JOptionPane.ERROR_MESSAGE);

        }
    }
}
