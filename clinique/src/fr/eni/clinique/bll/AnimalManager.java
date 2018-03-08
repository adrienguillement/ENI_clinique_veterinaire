package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOAnimal;
import fr.eni.clinique.dal.DAOFactory;

import javax.swing.*;
import java.util.List;

public class AnimalManager {
    /**
     * Attributes
     */
    private static DAOAnimal daoAnimal;

    /**
     * Constructeurs
     * @throws BLLException
     */
    public AnimalManager()throws BLLException{

        daoAnimal = new DAOFactory().getAnimalDAO();
    }


    /**
     * Suppression des animaux
     * @param animal
     */
    public void deleteAnimal(Animal animal) {
        try {
            daoAnimal.delete(animal);
        } catch (DALException e) {
            JOptionPane.showMessageDialog(null, "Impossible de supprimer un animal.", null, JOptionPane.ERROR_MESSAGE);

        }
    }


    /**
     * Get les animaux du client.
     * @param client
     * @return
     */
    public List<Animal> getFromClient(Client client) {
        List<Animal> animauxClient = null;

        try {
            animauxClient = daoAnimal.selectByClient(client);
        } catch (DALException e) {
            JOptionPane.showMessageDialog(null, "Impossible de récupérer les animaux de ce client.", null, JOptionPane.ERROR_MESSAGE);

        }
        return animauxClient;
    }

    /**
     * Getter
     * @return
     */
    public List<Animal> getListeAnimaux(){
        List<Animal> animaux = null;

        try{
            animaux = daoAnimal.selectAll();
        }catch(DALException e){
            JOptionPane.showMessageDialog(null, "Impossible de récupérer les animaux.", null, JOptionPane.ERROR_MESSAGE);
        }
        return animaux;
    }


    /**
     * Ajout d'un animal
     * @param animal
     */
    public void insert(Animal animal){
        try {
            System.out.println(animal);
            daoAnimal.insert(animal);
        } catch (DALException e) {
            JOptionPane.showMessageDialog(null, "Impossible d'ajouter un nouvel animal.", null, JOptionPane.ERROR_MESSAGE);

        }
    }


    /**
     * MAJ d'un animal
     * @param animal
     */
    public void update(Animal animal){
        try {
            daoAnimal.update(animal);
        } catch (DALException e) {
            JOptionPane.showMessageDialog(null, "Impossible de mettre à jour l'animal.", null, JOptionPane.ERROR_MESSAGE);

        }
    }

/* TODO
    public Animal getFromCode(int code) {
        Animal animal = null;
        try {
            animal = daoAnimal.selectByCode(code);
        } catch (DALException e){
            JOptionPane.showMessageDialog(null, "Impossible de récupérer l'animal.", null, JOptionPane.ERROR_MESSAGE);

        }
        return animal;
    }
 */
}
