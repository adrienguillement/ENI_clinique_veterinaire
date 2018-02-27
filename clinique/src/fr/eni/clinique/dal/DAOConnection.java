package src.fr.eni.clinique.dal;

import src.fr.eni.clinique.bo.Personne;

public interface DAOConnection{

    public Personne getConnection(String login, String Mdp) throws DALException;
}
