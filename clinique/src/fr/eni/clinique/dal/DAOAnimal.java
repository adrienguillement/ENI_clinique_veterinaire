package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;

import java.util.List;

public interface DAOAnimal extends DAO{

    public List<Animal> selectByClient(Client client) throws DALException;
    public Animal selectByCode(int code) throws DALException;
    public void delete(Animal animal) throws DALException;


}
