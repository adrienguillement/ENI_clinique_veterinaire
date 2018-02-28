package fr.eni.clinique.dal.JDBC;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAO;
import fr.eni.clinique.dal.DAOAnimal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAOJdbcImpl implements DAOAnimal {

    private static final String sqlSelectAll = "SELECT * FROM ANIMAL";

    @Override
    public List<Animal> selectAll() throws DALException {
        Connection cnx = null;
        Statement rqt = null;
        ResultSet rs = null;

        List<Animal> liste = new ArrayList<Animal>();
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.createStatement();
            rs = rqt.executeQuery(sqlSelectAll);
            Animal animal = null;

            while (rs.next()) {
                animal = new Animal(rs.getInt("codeAnimal"),
                        rs.getString("NomAnimal"),
                        rs.getString("Sexe"),
                        rs.getString("Couleur"),
                        rs.getString("Race"),
                        rs.getInt("CodeClient"),
                        rs.getString("Tatouage"),
                        rs.getString("Antecedents"),
                        rs.getBoolean("Archive"));
                liste.add(animal);
            }
        } catch (SQLException e) {
            throw new DALException("selectAll failed - " , e);
        } finally {
            try {
                if (rs != null){
                    rs.close();
                }
                if (rqt != null){
                    rqt.close();
                }
                if(cnx!=null){
                    cnx.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return liste;
    }

    @Override
    public Animal insert(Object data) throws DALException {
        return null;
    }
}
