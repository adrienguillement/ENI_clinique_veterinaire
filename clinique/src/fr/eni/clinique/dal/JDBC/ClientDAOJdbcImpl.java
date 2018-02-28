package fr.eni.clinique.dal.JDBC;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOJdbcImpl implements DAOClient {


    private static final String sqlSelectAll = "SELECT * from client";

    @Override
    public List<Client> selectAll() throws DALException {
        Connection cnx = null;
        Statement rqt = null;
        ResultSet rs = null;

        List<Client> liste = new ArrayList<Client>();
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.createStatement();
            rs = rqt.executeQuery(sqlSelectAll);
            Client race = null;

            while (rs.next()) {
                race = new Client(rs.getInt("codeClient"),
                        rs.getString("nomClient"),
                        rs.getString("prenomClient"),
                        rs.getString("adresse1"),
                        rs.getString("adresse2"),
                        rs.getString("codePostal"),
                        rs.getString("ville"),
                        rs.getString("numTel"),
                        rs.getString("assurance"),
                        rs.getString("email"),
                        rs.getString("remarque"),
                        rs.getBoolean("archive"));
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
