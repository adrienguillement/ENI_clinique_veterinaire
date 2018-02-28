package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.DAOPersonne;

import java.util.List;

public class PersonnelManager {
    private static DAOPersonne daoPersonnel;

    public PersonnelManager() throws BLLException{
        daoPersonnel = DAOFactory.getPersonneDAO();
    }

    /**
     *
     * @return
     * @throws BLLException
     */
    public List<Personnel> getPersonnels() throws BLLException{
        List<Personnel> personnels = null;

        try{
            personnels = DAOFactory.getPersonneDAO().selectAll();
        }catch(DALException e){
            e.printStackTrace();
            throw new BLLException("Erreur récupération du personnel", e);
        }
        return personnels;
    }

    public void insertPersonnel(Personnel personnel) throws BLLException{
        try{
            validerPersonnel(personnel);
            daoPersonnel.insert(personnel);
        } catch(DALException e) {
            e.printStackTrace();
            throw new BLLException("Erreur d'ajout de personnel", e);
        }
    }

    public void validerPersonnel(Personnel personnel) throws BLLException{
        boolean valide = true;
        StringBuffer sb = new StringBuffer();

        if(null==personnel){
            valide = false;
            sb.append("Pas de personnel");
        }
        if(null==personnel.getNom() || 0==personnel.getNom().trim().length()){
            valide = false;
            sb.append("Nom de l'employé inconnu");
        }
        if(null==personnel.getRole() || 0==personnel.getRole().trim().length()){
            valide = true;
            sb.append("Pas de role");
        }
        if(null==personnel.getMotPasse() || 0==personnel.getMotPasse().trim().length()){
            valide = true;
            sb.append("Pas de mot de passe");
        }

        if(!valide){
            throw new BLLException(sb.toString());
        }
    }

}
