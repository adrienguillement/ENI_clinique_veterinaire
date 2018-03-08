package fr.eni.clinique.dal;

import fr.eni.clinique.dal.JDBC.*;

public class DAOFactory {

    /**
     * GETTERS DAO
     * @return
     */
    public static DAOAnimal getAnimalDAO(){
        DAOAnimal animalDAO = null;
        try{
            animalDAO = new AnimalDAOJdbcImpl();
        } catch (Exception e){
            e.printStackTrace();
        }
        return animalDAO;
    }

    public static DAOAgenda getAgendaDAO(){
        DAOAgenda agendaDAO = null;

        try{
            agendaDAO = new AgendaDAOJdbcImpl();
        } catch (Exception e){
            e.printStackTrace();
        }
        return agendaDAO;
    }

    public static DAORace getRaceDAO()  {
        DAORace racesDAO=null;
        try {
            racesDAO=(DAORace) Class.forName("src.fr.eni.clinique.dal.JDBC.RacesDAOJdbcImpl").newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return racesDAO;
    }


    public static ClientDAOJdbcImpl getClientDAO()
    {
        return new ClientDAOJdbcImpl();
    }

    public static DAOConnection getConnectionDAO()
    {
        DAOConnection connectionDAO = null;
        try{
            connectionDAO = new ConnectionDAOJdbcImpl();
        } catch (Exception e){
            e.printStackTrace();
        }
        return connectionDAO;
    }
  
    public static PersonneDAOJdbcImpl getPersonneDAO()
    {
        return new PersonneDAOJdbcImpl();
    }
}
