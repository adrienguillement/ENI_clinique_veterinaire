package fr.eni.clinique.dal;


import fr.eni.clinique.bo.Personnel;

import java.util.List;

public class AppliTestDAL extends DAOFactory{

    public static void main(String[] args) {

        DAOPersonne DAOPersonnel = DAOFactory.getPersonneDAO();

        //TODO...
        try {
            //Sélection de toutes les races
            List<Personnel> personnels = DAOFactory.getPersonneDAO().selectAll();
            System.out.println("Sélection de tous les personnels  : " + personnels.toString() );


            //Test de la DAL
            //List<Personnels> personnels = DAOFactory.getPersonnelsDAO().selectAll();
            //System.out.println("ajout effectuer de : " + personnels.toString());

        } catch (DALException e) {
            e.printStackTrace();
        }

    }

}

