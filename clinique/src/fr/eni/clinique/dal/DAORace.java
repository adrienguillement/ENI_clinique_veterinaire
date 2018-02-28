package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Race;

public interface DAORace extends DAO {

    public Race selectByRace(String uneRace) throws DALException;
}
