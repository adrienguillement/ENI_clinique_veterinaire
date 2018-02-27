package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personne;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.DAOPersonne;

import java.util.List;

public class PersonnelManager {
    private static DAOPersonne daoPersonnels;

    public PersonnelManager() throws BLLException{
        daoPersonnels = DAOFactory.getPersonneDAO();
    }

    /**
     *
     * @return
     * @throws BLLException
     */
    public List<Personne> getPersonnels() throws BLLException{
        List<Personne> personne = null;

        try{
            personne = DAOFactory.getPersonneDAO().selectAll();
        }catch(DALException e){
            e.printStackTrace();
            throw new BLLException("Erreur récupération du personnels", e);
        }
        return personne;
    }
}
