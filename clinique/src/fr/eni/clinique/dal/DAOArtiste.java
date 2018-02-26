package src.fr.eni.clinique.dal;

import fr.eni.spectacle.bo.Artiste;

import java.util.List;

public interface DAOArtiste extends DAO{

    public List<Artiste> selectByNomScene(String email) throws DALException;
}
