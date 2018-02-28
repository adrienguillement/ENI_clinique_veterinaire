package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOConnection;
import fr.eni.clinique.dal.DAOFactory;

public class ConnectionManager{

    private static DAOConnection daoConnection;

    public ConnectionManager() {
        daoConnection = DAOFactory.getConnectionDAO();
    }

    public Personnel getConnection(String login, String mdp) throws DALException{

        System.out.println("> ConnectionManager");
        Personnel personnel = daoConnection.getConnection(login, mdp);

        return personnel;
    }
}
