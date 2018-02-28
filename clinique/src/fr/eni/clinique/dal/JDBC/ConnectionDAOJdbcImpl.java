package fr.eni.clinique.dal.JDBC;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOConnection;

import java.sql.*;

public class ConnectionDAOJdbcImpl implements DAOConnection {

    private static final String sqlTestAuthentification =   "SELECT Top 1 CodePers, Nom, MotPasse, Role, Archive FROM PERSONNELS " +
                                                            "WHERE Nom = ? " +
                                                            "AND MotPasse = ?";

    public Personnel getConnection(String login, String mdp) throws DALException {

        System.out.println("> ConnectionDAOJdbcImpl");

        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;
        Personnel personnel = null;

        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlTestAuthentification);
            rqt.setString(1, login);
            rqt.setString(2, mdp);

            rs = rqt.executeQuery();
            if (rs.next()) {
                personnel = new Personnel(
                        rs.getString("Nom"),
                        rs.getString("MotPasse"),
                        rs.getString("Role"),
                        rs.getBoolean("Archive"));
            }
        } catch (SQLException e) {
            throw new DALException("connection codePers = " + personnel.getCodePers(), e);
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
                throw new DALException("close failed " , e);
            }
        }
        return personnel;
    }
}
