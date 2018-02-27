package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personne;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOConnection;
import fr.eni.clinique.dal.DAOFactory;

public class ConnectionManager{

    private static DAOConnection daoConnection;

    public ConnectionManager() {
        daoConnection = DAOFactory.getConnectionDAO();
    }

    public Personne getConnection(String login, String mdp) throws DALException{

        System.out.println("> ConnectionManager");
        Personne personne = daoConnection.getConnection(login, mdp);

        return personne;
    }
}
