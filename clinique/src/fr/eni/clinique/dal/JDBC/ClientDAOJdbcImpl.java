package src.fr.eni.clinique.dal.JDBC;

import src.fr.eni.clinique.bo.Races;
import src.fr.eni.clinique.dal.DALException;
import src.fr.eni.clinique.dal.DAOClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOJdbcImpl implements DAOClient {


    private static final String sqlSelectAll = "SELECT * from clients";

    @Override
    public List<Races> selectAll() throws DALException {
        Connection cnx = null;
        Statement rqt = null;
        ResultSet rs = null;
        List<Races> liste = new ArrayList<Races>();
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.createStatement();
            rs = rqt.executeQuery(sqlSelectAll);
            Races race = null;

            while (rs.next()) {
                race = new Races(rs.getString("NomClient"),
                        rs.getString("PrenomClient"));
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
