package clinique.src.fr.eni.clinique.bll;
import fr.eni.spectacle.bll.BLLException;

public class AnimauxManager {

    private static DAOAnimaux daoAnimaux;

    public AnimalManager() throws BLLException{
        daoAnimaux = DAOFactory.getAnimalDAO();
    }

    /**
     * Récupération du catalogue des animaux
     * @return
     * @throws BLLException
     */
    public List<Animal> getCatalogue() throws BLLException{
        List<Animal> animaux = null;

        try{
            animaux = daoAnimaux.selectAll();
        }catch(DALException e){
            e.printStackTrace();
            throw new BLLException("Erreur récupération catalogue", e);
        }
        return animaux;
    }

    /**
     * Récupération d'un animal
     * @param id
     * @return
     * @throws BLLException
     */
    public Animal selectAnimalById(int id) throws BLLException{
        Animal animal = null;
        try{
            animal = (Animal)daoAnimaux.selectById(id);
        }catch (DALException e){
            e.printStackTrace();
        }
        return animal;
    }

    /**
     * Ajout d'un animal
     * @param newAnimal
     * @return
     * @throws BLLException
     */
    public Animal addAnimal(Animal newAnimal) throws BLLException{
        Animal animal = null;
        try{
            validerAnimal(newAnimal);
            animal = (Animal)daoAnimaux.insert(newAnimal);
        }catch(DALException e){
            throw new BLLException("Echec addAnimal ", e);
        }
        return animal;
    }

}
