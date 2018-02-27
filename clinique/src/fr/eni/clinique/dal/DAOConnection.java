package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Personne;

public interface DAOConnection{

    public Personne getConnection(String login, String Mdp) throws DALException;
}
