package fr.eni.clinique.dal;

import fr.eni.clinique.dal.JDBC.ConnectionDAOJdbcImpl;
import fr.eni.clinique.dal.JDBC.ClientDAOJdbcImpl;
import fr.eni.clinique.dal.JDBC.PersonneDAOJdbcImpl;

public class DAOFactory {

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
