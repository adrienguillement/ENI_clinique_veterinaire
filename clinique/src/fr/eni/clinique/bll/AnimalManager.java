package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOAnimal;
import fr.eni.clinique.dal.DAOFactory;

import java.util.List;

public class AnimalManager {
    private static DAOAnimal daoAnimal;

    public AnimalManager()throws BLLException{

        daoAnimal = new DAOFactory().getAnimalDAO();
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
}
