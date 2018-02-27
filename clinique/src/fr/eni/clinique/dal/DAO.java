package src.fr.eni.clinique.dal;

import java.util.List;

public interface DAO<T> {
    public List<T> selectAll() throws DALException;
}
