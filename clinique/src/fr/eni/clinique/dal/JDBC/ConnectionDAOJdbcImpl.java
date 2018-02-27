package src.fr.eni.clinique.dal.JDBC;

import src.fr.eni.clinique.bo.Personnels;
import src.fr.eni.clinique.dal.DALException;
import src.fr.eni.clinique.dal.DAOConnection;

import java.sql.*;

public class ConnectionDAOJdbcImpl implements DAOConnection {

    private static final String sqlTestAuthentification =   "SELECT Top 1 CodePers, Nom, MotPasse, Role FROM PERSONNELS " +
                                                            "WHERE Nom = ? " +
                                                            "AND MotPasse = ?";

    public Personnels getConnection(String login, String mdp) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;
        Personnels personne = null;

        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlTestAuthentification);
            rqt.setString(1, login);
            rqt.setString(2, mdp);

            rs = rqt.executeQuery();
            if (rs.next()) {
                personne = new Personnels(rs.getInt("CodePers"),
                        rs.getString("Nom"),
                        rs.getString("MotPasse"),
                        rs.getString("Role"),
                        rs.getBoolean("Archive"));
            }
        } catch (SQLException e) {
            throw new DALException("connection codePers = " + personne.getCodePers(), e);
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
        return personne;
    }
}
