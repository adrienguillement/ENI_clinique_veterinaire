package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Client;

import java.util.List;

public interface DAOClient extends DAO {
    public List<Client> selectById(int idClient) throws DALException;
    public void delete(Client client) throws DALException;
    public Client selectFirstClient() throws DALException;

}
