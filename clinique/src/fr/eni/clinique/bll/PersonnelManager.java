package src.fr.eni.clinique.bll;

import src.fr.eni.clinique.bo.Personne;
import src.fr.eni.clinique.dal.DALException;
import src.fr.eni.clinique.dal.DAOFactory;
import src.fr.eni.clinique.dal.DAOPersonnels;

import java.util.List;

public class PersonnelManager {
    private static DAOPersonnels daoPersonnels;

    public PersonnelManager() throws BLLException{
        daoPersonnels = DAOFactory.getPersonnelsDAO();
    }

    /**
     *
     * @return
     * @throws BLLException
     */
    public List<Personne> getPersonnels() throws BLLException{
        List<Personne> personne = null;

        try{
            personne = DAOFactory.getPersonnelsDAO().selectAll();
        }catch(DALException e){
            e.printStackTrace();
            throw new BLLException("Erreur récupération du personnels", e);
        }
        return personne;
    }
}
