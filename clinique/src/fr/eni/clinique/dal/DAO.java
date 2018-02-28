package fr.eni.clinique.dal;

import java.util.List;

public interface DAO<T> {
    public List<T> selectAll() throws DALException;
    public T insert(T data) throws DALException;
}
