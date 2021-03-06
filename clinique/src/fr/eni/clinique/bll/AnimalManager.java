package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOAnimal;
import fr.eni.clinique.dal.DAOFactory;

import java.util.List;

public class AnimalManager {
    private static DAOAnimal daoAnimal;

    public AnimalManager()throws BLLException{

        daoAnimal = new DAOFactory().getAnimalDAO();
    }

    public void deleteAnimal(Animal animal) {
        try {
            daoAnimal.delete(animal);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }


    public List<Animal> getFromClient(Client client) {
        List<Animal> animauxClient = null;

        try {
            animauxClient = daoAnimal.selectByClient(client);
        } catch (DALException e) {
            e.printStackTrace();
        }

        return animauxClient;
    }

    public List<Animal> getListeAnimaux(){
        List<Animal> animaux = null;

        try{
            animaux = daoAnimal.selectAll();
        }catch(DALException e){
            e.printStackTrace();
        }
        return animaux;
    }

    public void insert(Animal animal){
        try {
            daoAnimal.insert(animal);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void update(Animal animal){
        try {
            daoAnimal.update(animal);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public Animal getFromCode(int code) {
        Animal animal = null;
        try {
            animal = daoAnimal.selectByCode(code);
        } catch (DALException e){
            e.printStackTrace();
        }
        return animal;
    }
}
