package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.DAOPersonne;

import javax.swing.*;
import java.util.List;

public class PersonnelManager {

    /**
     * Attribut
     */
    private static DAOPersonne daoPersonnel;

    /**
     * Constructeur
     * @throws BLLException
     */
    public PersonnelManager() throws BLLException{
        daoPersonnel = DAOFactory.getPersonneDAO();
    }

    /**
     * Retourne un personnel from id
     * @param id
     * @return
     */
    public Personnel getPersonnelById(int id) {
        Personnel personnel = null;

        try{
            personnel = DAOFactory.getPersonneDAO().selectById(id);
        }catch(DALException e){
            JOptionPane.showMessageDialog(null, "Impossible de récupérer l'utilisateur.", null, JOptionPane.ERROR_MESSAGE);

        }
        return personnel;
    }

    /**
     * Retourne liste personnels.
     * @return
     * @throws BLLException
     */
    public List<Personnel> getPersonnels() throws BLLException{
        List<Personnel> personnels = null;

        try{
            personnels = DAOFactory.getPersonneDAO().selectAll();
        }catch(DALException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de récupération du personnel.", null, JOptionPane.ERROR_MESSAGE);

        }
        return personnels;
    }

    /**
     * Ajout d'un nouveau personnel
     * @param personnel
     * @return
     * @throws BLLException
     */
    public Personnel insertPersonnel(Personnel personnel) throws  BLLException{
        try{
            validerPersonnel(personnel);
            daoPersonnel.insert(personnel);
        } catch (DALException e){
            JOptionPane.showMessageDialog(null, "Echec de l'insertion du personnel.", null, JOptionPane.ERROR_MESSAGE);
        }
        return personnel;
    }

    /**
     * MAJ d'un personnel.
     * @param personnel
     * @return
     * @throws BLLException
     */
    public Personnel updatePersonnel(Personnel personnel) throws BLLException{
        try{
            validerPersonnel(personnel);
            daoPersonnel.update(personnel);
        } catch (DALException e){
            JOptionPane.showMessageDialog(null, "Impossible de mettre à jour ce personnel.", null, JOptionPane.ERROR_MESSAGE);

        }
        return personnel;
    }

    /**
     * Suppression d'un personnel
     * @param personnel
     * @throws BLLException
     */
    public void deletePersonnel(Personnel personnel) throws BLLException{
        try{
            daoPersonnel.delete(personnel);
        }catch (DALException e){
            JOptionPane.showMessageDialog(null, "Impossible de supprimer ce personnel.", null, JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * Validation d'un personnel
     * @param personnel
     * @throws BLLException
     */
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
