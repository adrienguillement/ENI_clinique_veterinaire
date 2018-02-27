package src.fr.eni.clinique.dal;

import src.fr.eni.clinique.bo.Personnels;
import src.fr.eni.clinique.bo.Races;
import java.util.List;

public class AppliTestDAL extends DAOFactory{

    public static void main(String[] args) {

        DAOPersonnels DAOPersonnel = DAOFactory.getPersonnelsDAO();

        //TODO...
        try {
            //Sélection de toutes les races
            List<Personnels> personnels = DAOFactory.getPersonnelsDAO().selectAll();
            System.out.println("Sélection de tous les articles  : " + personnels.toString() );


        } catch (DALException e) {
            e.printStackTrace();
        }

    }

}

