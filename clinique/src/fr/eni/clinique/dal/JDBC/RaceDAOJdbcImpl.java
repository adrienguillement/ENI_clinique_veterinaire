package fr.eni.clinique.dal.JDBC;

import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAORace;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RaceDAOJdbcImpl implements DAORace {

    private static final String sqlSelectAll = "SELECT * from race";
    private static final String sqlSelectByRace =   "SELECT TOP 1 * FROM RACE" +
                                                    "WHERE Espece = ?";

    @Override
    public List<Race> selectAll() throws DALException {
        Connection cnx = null;
        Statement rqt = null;
        ResultSet rs = null;
        List<Race> liste = new ArrayList<Race>();
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.createStatement();
            rs = rqt.executeQuery(sqlSelectAll);
            Race race = null;

            while (rs.next()) {
                race = new Race(rs.getString("race"),
                        rs.getString("espece"));
                liste.add(race);
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
    public Object insert(Object data) throws DALException {
        return null;
    }

    @Override
    public void update(Object data) throws DALException {

    }

    @Override
    public void delete(Object data) throws DALException {

    }

    public Race selectByRace(String uneRace){
        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;

        Race race = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlSelectByRace);
            rqt.setString(1, uneRace);

            rs = rqt.executeQuery();
            if(rs.next()){
                race = new Race(rs.getString("Race"),
                        rs.getString("Espece"));
            }
        } catch (SQLException e) {
            try {
                throw new DALException("selectByRace failed - race = " + race , e);
            } catch (DALException e1) {
                e1.printStackTrace();
            }
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
        return race;
    }
}
