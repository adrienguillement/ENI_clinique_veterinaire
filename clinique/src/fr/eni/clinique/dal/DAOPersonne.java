package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Personnel;

public interface DAOPersonne extends DAO{
    public void delete(Personnel personnel) throws DALException;
    public Personnel selectById(int CodePers) throws DALException;
}
