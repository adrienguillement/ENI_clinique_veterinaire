package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Personnel;

public interface DAOConnection{

    public Personnel getConnection(String login, String Mdp) throws DALException;
}
