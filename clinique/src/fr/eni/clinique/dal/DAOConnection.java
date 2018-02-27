package src.fr.eni.clinique.dal;

import src.fr.eni.clinique.bo.Personnels;

public interface DAOConnection{
    public Personnels getConnection(String login, String Mdp) throws DALException;
}
