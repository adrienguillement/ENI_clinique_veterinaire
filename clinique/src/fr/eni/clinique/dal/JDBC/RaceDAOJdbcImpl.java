package fr.eni.clinique.dal.JDBC;

import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAORace;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RaceDAOJdbcImpl implements DAORace {

    private static final String sqlSelectAll = "SELECT * from races";

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
}
