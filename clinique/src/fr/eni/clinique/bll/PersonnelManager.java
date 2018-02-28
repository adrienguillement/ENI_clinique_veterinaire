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
            personne = daoPersonnels.selectAll();
        }catch(DALException e){
            e.printStackTrace();
            throw new BLLException("Erreur récupération du personnel", e);
        }
        return personne;
    }

    public void validerPersonne(Personne personne) throws BLLException{
        boolean valide = true;
        StringBuffer sb = new StringBuffer();

        if(null==personne){
            valide = false;
            sb.append("Pas de personnel");
        }
        if(null==personne.getNom() || 0==personne.getNom().trim().length()){
            valide = false;
            sb.append("Nom de l'employé inconnu");
        }
        if(null==personne.getRole() || 0==personne.getRole().trim().length()){
            valide = true;
            sb.append("Pas de role");
        }
        if(null==personne.getMotPasse() || 0==personne.getMotPasse().trim().length()){
            valide = true;
            sb.append("Pas de mot de passe");
        }

        if(!valide){
            throw new BLLException(sb.toString());
        }
    }

}
