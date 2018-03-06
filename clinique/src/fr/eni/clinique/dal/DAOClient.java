package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Client;

import java.util.List;

public interface DAOClient extends DAO {
    public Client selectById(int idClient) throws DALException;
    public void delete(Client client) throws DALException;
    public Client selectFirstClient() throws DALException;
    public List<Client> searchClient(String searchValue) throws DALException;
    public void updateClient(Client client) throws DALException;
}
