package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOConnection;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.utils.SHA512;

public class ConnectionManager{

    /**
     * Attribut
     */
    private static DAOConnection daoConnection;

    /**
     * Constructeur
     */
    public ConnectionManager() {
        daoConnection = DAOFactory.getConnectionDAO();
    }

    /**
     * Connection
     * @param login
     * @param mdp
     * @return
     * @throws DALException
     */
    public Personnel getConnection(String login, String mdp) throws DALException{

        System.out.println("> ConnectionManager");
        Personnel personnel = daoConnection.getConnection(login, SHA512.getSHA512(mdp, "toto"));

        return personnel;
    }
}
