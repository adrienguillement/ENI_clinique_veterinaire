package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOAgenda;
import fr.eni.clinique.dal.DAOFactory;

import javax.swing.*;
import java.util.List;

public class AgendaManager {

    /**
     * Attributes
     */
    private static DAOAgenda daoAgenda;

    /**
     * Constructeurs
     * @throws BLLException
     */
    public AgendaManager() {
        daoAgenda = new DAOFactory().getAgendaDAO();
    }

    /**
     * Getter
     * @return
     */
    public List<Agenda> getListeAgenda(){
        List<Agenda> listeAgenda= null;

        try{
            listeAgenda = daoAgenda.selectAll();
        }catch(DALException e){
            JOptionPane.showMessageDialog(null, "Impossible de récupérer l'agenda.", null, JOptionPane.ERROR_MESSAGE);

        }
        return listeAgenda;
    }

    /**
     * Nouveau rdv.
     * @param agenda
     */
    public void insert(Agenda agenda){
        try {
            daoAgenda.insert(agenda);
        } catch (DALException e) {
            JOptionPane.showMessageDialog(null, "Impossible de créer un nouveau rendez vous.", null, JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * MAJ rdv
     * @param agenda
     */
    public void update(Agenda agenda){
        try {
            daoAgenda.update(agenda);
        } catch (DALException e) {
            JOptionPane.showMessageDialog(null, "Impossible de mettre à jour une réservation.", null, JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * Suppression rdv
     * @param agenda
     */
    public void delete(Agenda agenda){
        try {
            daoAgenda.delete(agenda);
        } catch (DALException e) {
            JOptionPane.showMessageDialog(null, "Impossible de supprimer ce rendez-vous.", null, JOptionPane.ERROR_MESSAGE);

        }
    }
}
