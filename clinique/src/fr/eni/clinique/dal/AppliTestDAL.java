package src.fr.eni.clinique.dal;

import src.fr.eni.clinique.bo.Personnels;
import src.fr.eni.clinique.bo.Races;
import java.util.List;

public class AppliTestDAL extends DAOFactory{

    public static void main(String[] args) {

        DAOPersonnels DAOPersonnel = DAOFactory.getPersonnelsDAO();

        //TODO...
        try {
            //Test de la DAL
            List<Personnels> personnels = DAOFactory.getPersonnelsDAO().selectAll();
            System.out.println("ajout effectuer de : " + personnels.toString());
        } catch (DALException e) {
            e.printStackTrace();
        }

    }

}

