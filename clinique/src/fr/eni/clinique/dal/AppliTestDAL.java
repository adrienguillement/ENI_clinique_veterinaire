package src.fr.eni.clinique.dal;
import src.fr.eni.clinique.bo.Races;

import java.util.List;

public class AppliTestDAL {

    public static void main(String[] args) {

        DAORaces DAORace = DAOFactory.getRaceDAO();

        //TODO...
        try {
            //Sélection de toutes les races
            List<Races> races = DAORace.selectAll();
            System.out.println("Sélection de tous les articles  : " + races.toString() );


        } catch (DALException e) {
            e.printStackTrace();
        }

    }

}

