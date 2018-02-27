package src.fr.eni.clinique.dal.JDBC;

import src.fr.eni.clinique.dal.DALException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import fr.eni.clinique.dal.jdbc.JdbcTools;
import src.fr.eni.clinique.bo.*;

public class RacesDAOJdbcImpl implements DAORaces {
    private static final String sqlSelectById = "SELECT id, nom, nom_scene from races where id = ?";

    private static final String sqlSelectAll = "SELECT id, nom, nom_scene from races";

    private static final String sqlUpdate = "update races set nom=?,nom_scene=? where id=?";

    private static final String sqlInsert = "insert into races(nom,nom_scene) values(?,?)";

    private static final String sqlDelete = "delete from races where id=?";
    private static final String sqlSelectByNomScene = "select nom, nom_scene from races where nom_scene = ?";

    @Override
    public List<Race> selectAll() throws DALException {
        Connection cnx = null;
        Statement rqt = null;
        ResultSet rs = null;
        List<Race> liste = new ArrayList<Artiste>();
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.createStatement();
            rs = rqt.executeQuery(sqlSelectAll);
            Race race = null;

            while (rs.next()) {
                race = new Race(rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("nom_scene"));
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
