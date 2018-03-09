package fr.eni.clinique.dal.JDBC;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAO;
import fr.eni.clinique.dal.DAOAnimal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAOJdbcImpl implements DAOAnimal {

    private RaceDAOJdbcImpl raceDAOJdbc = new RaceDAOJdbcImpl();
    private Race race = null;

    private static final String sqlSelectAll = "SELECT * FROM ANIMAL";
    private static final String sqlInsert = "INSERT INTO ANIMAL(NomAnimal, Sexe, Couleur, Race, Espece, CodeClient, Tatouage, Antecedents, Archive) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String sqlUpdate = "UPDATE ANIMAL SET NomAnimal=?, Sexe=? ,Couleur=?, Race=?, Espece=?, CodeClient=?, Tatouage=?, Antecedents=?, Archive=? where CodeAnimal=?";
    private static final String sqlSelectByClient = "SELECT * FROM ANIMAL WHERE CodeClient = ? AND Archive = 0";
    private static final String sqlSelectByCode = "SELECT * FROM ANIMAL WHERE CodeAnimal = ? AND Archive = 0";
    private static final String sqlDelete = "UPDATE ANIMAL set Archive=1 WHERE CodeAnimal = ?";


    @Override
    public void delete(Animal animal) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;

        try{
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlDelete);
            rqt.setInt(1, animal.getCodeAnimal());
            rqt.executeUpdate();
        }catch (SQLException e) {
            throw new DALException("Delete client failed - client = " + animal, e);
        } finally {
            try {
                if (rqt != null){
                    rqt.close();
                }
                if(cnx!=null){
                    cnx.close();
                }
            } catch (SQLException e) {
                throw new DALException("close failed " , e);
            }
        }
    }

    public Animal selectByCode(int codeAnimal) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;
        Animal animal = null;

        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlSelectByCode);
            rqt.setInt(1, codeAnimal);
            rs = rqt.executeQuery();


            while (rs.next()) {
                race = raceDAOJdbc.selectByRace(rs.getString("Race"));
                animal = new Animal(rs.getInt("codeAnimal"),
                        rs.getString("NomAnimal"),
                        rs.getString("Sexe"),
                        rs.getString("Couleur"),
                        race,
                        rs.getInt("CodeClient"),
                        rs.getString("Tatouage"),
                        rs.getString("Antecedents"),
                        rs.getBoolean("Archive"));
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
        return animal;
    }

    public List<Animal> selectByClient(Client client) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;

        List<Animal> liste = new ArrayList<Animal>();
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlSelectByClient);
            rqt.setInt(1, client.getCode());
            rs = rqt.executeQuery();
            Animal animal = null;

            while (rs.next()) {
                race = raceDAOJdbc.selectByRace(rs.getString("Race"));
                animal = new Animal(rs.getInt("codeAnimal"),
                        rs.getString("NomAnimal"),
                        rs.getString("Sexe"),
                        rs.getString("Couleur"),
                        race,
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
                race = raceDAOJdbc.selectByRace(rs.getString("Race"));
                animal = new Animal(rs.getInt("codeAnimal"),
                        rs.getString("NomAnimal"),
                        rs.getString("Sexe"),
                        rs.getString("Couleur"),
                        race,
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
    public Animal insert(Object unAnimal) throws DALException {
        Animal animal = (Animal)unAnimal;
        Connection cnx = null;
        PreparedStatement rqt = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            rqt.setString(1, animal.getNomAnimal());
            rqt.setString(2, animal.getSexe());
            rqt.setString(3, animal.getCouleur());
            rqt.setString(4, animal.getRace().getRace());
            rqt.setString(5, animal.getRace().getEspece());
            rqt.setInt(6, animal.getCodeClient());
            rqt.setString(7, animal.getTatouage());
            rqt.setString(8, animal.getAntecedents());
            rqt.setBoolean(9, animal.isArchive());

            int nbRows = rqt.executeUpdate();
            if(nbRows == 1){
                ResultSet rs = rqt.getGeneratedKeys();
                if(rs.next()){
                    animal.setCodeAnimal(rs.getInt(1));
                }

            }
        }catch(SQLException e){
            throw new DALException("Insert animal - " + animal, e);
        }
        finally {
            try {
                if (rqt != null){
                    rqt.close();
                }
                if(cnx!=null){
                    cnx.close();
                }
            } catch (SQLException e) {
                throw new DALException("close failed - ", e);
            }
        }

        return animal;
    }

    @Override
    public void update(Object unAnimal) throws DALException {
        Animal animal = (Animal)unAnimal;
        Connection cnx = null;
        PreparedStatement rqt = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlUpdate);
            rqt.setString(1, animal.getNomAnimal());
            rqt.setString(2, animal.getSexe());
            rqt.setString(3, animal.getCouleur());
            rqt.setString(4, animal.getRace().getRace());
            rqt.setString(5, animal.getRace().getEspece());
            rqt.setInt(6, animal.getCodeClient());
            rqt.setString(7, animal.getTatouage());
            rqt.setString(8, animal.getAntecedents());
            rqt.setBoolean(9, animal.isArchive());
            rqt.setInt(10, animal.getCodeAnimal());
            rqt.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Update animal failed - " + animal, e);
        } finally {
            try {
                if (rqt != null){
                    rqt.close();
                }
                if(cnx !=null){
                    cnx.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Object data) throws DALException {

    }
}
