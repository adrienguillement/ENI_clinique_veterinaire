package src.fr.eni.clinique.bll;

import src.fr.eni.clinique.bo.Personnels;
import src.fr.eni.clinique.dal.DAOConnection;
import src.fr.eni.clinique.dal.DAOFactory;

public class ConnectionManager{

    private static DAOConnection daoConnection;

    public ConnectionManager() {
        daoConnection = DAOFactory.getConnectionDAO();
    }

    public Personnels getConnection(){


    }
}
