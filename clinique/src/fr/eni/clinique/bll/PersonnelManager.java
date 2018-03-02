package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
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
            throw new BLLException("Erreur récupération du personnels", e);
        }
        return personnels;
    }

    public Personnel insertPersonnel(Personnel personnel) throws  BLLException{
        try{
            validerPersonnel(personnel);
            daoPersonnel.insert(personnel);
        } catch (DALException e){
            throw new BLLException("Echec insert personnel", e);
        }
        return personnel;
    }

    public void deletePersonnel(Personnel personnel) throws BLLException{
        try{
            daoPersonnel.delete(personnel);
        }catch (DALException e){
            throw new BLLException("echec delete personnel: "+ personnel, e);
        }
    }

    public void validerPersonnel(Personnel personnel) throws BLLException{
        boolean valide = true;
        StringBuffer sb = new StringBuffer();

        if(null==personnel){
            valide=false;
            sb.append("personnel null");
        }
        if(null==personnel.getNom() || 0==personnel.getNom().trim().length()){
            valide=false;
            sb.append("nom du personnel null");
        }
        if(null==personnel.getRole() || 0==personnel.getRole().trim().length()){
            valide=false;
            sb.append("role personnel null");
        }
        if(null==personnel.getMotPasse() || 0==personnel.getMotPasse().trim().length()){
            valide=false;
            sb.append("mot de passe null");
        }

        if(!valide){
            throw new BLLException(sb.toString());
        }
    }
}
