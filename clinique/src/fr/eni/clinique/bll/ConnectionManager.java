package src.fr.eni.clinique.bll;

import src.fr.eni.clinique.bo.Personne;
import src.fr.eni.clinique.dal.DALException;
import src.fr.eni.clinique.dal.DAOConnection;
import src.fr.eni.clinique.dal.DAOFactory;

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
