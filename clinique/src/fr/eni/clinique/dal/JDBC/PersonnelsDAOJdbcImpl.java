package src.fr.eni.clinique.dal.JDBC;

import com.sun.org.apache.bcel.internal.classfile.Code;
import src.fr.eni.clinique.dal.DALException;
import src.fr.eni.clinique.dal.DAOPersonnels;
import src.fr.eni.clinique.bo.Personnels;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonnelsDAOJdbcImpl implements DAOPersonnels{
    private static final String selectAll = "select CodePers, Nom, MotPasse, Role, Archive from Personnels";
    private static final String selectByNom = "select CodePers, Nom, MotPasse, Role, Archive from Personnels where Nom= ?";
    private static final String selectById = "select CodePers, Nom, MotPasse, Role, Archive from Personnels where CodePers= ?";
    private static final String insert = "insert into Personnels(Nom, MotPasse, Role, Archive) values(?,?,?,?)";
    private static final String update = "update Personnels set Nom=?, MotPasse=?,Role=?,Archive=? where CodePers=?";
    private static final String delete = "delete from Personnels where id=?";


    public List<Personnels> selectAll() throws DALException{
        Connection cnx = null;
        Statement stt = null;
        ResultSet rs = null;
        List<Personnels> liste = new ArrayList<Personnels>();
        try{
            cnx = src.fr.eni.clinique.dal.JDBC.JdbcTools.getConnection();
            stt = cnx.createStatement();
            rs = stt.executeQuery(selectAll);
            Personnels per = null;

            while (rs.next()) {
                per = new Personnels(rs.getInt("CodePers"),
                        rs.getString("Nom"),
                        rs.getString("MotPasse"),
                        rs.getString("Role"),
                        rs.getBoolean("Archive"));
                liste.add(per);
            }
        } catch (SQLException e){
            throw new DALException("Select all failed - ", e);
        } finally {
            try{
                if (rs != null){
                    rs.close();
                }
                if (stt != null){
                    stt.close();
                }
                if (cnx != null){
                    cnx.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return liste;
    }

    public Personnels selectByNom(String Nom) throws DALException{
        Connection cnx = null;
        PreparedStatement stt = null;
        ResultSet rs = null;

        Personnels personnels = null;
        try{
            cnx = src.fr.eni.clinique.dal.JDBC.JdbcTools.getConnection();
            stt = cnx.prepareStatement(selectByNom);
            stt.setString(1, Nom);

            rs = stt.executeQuery();
            if(rs.next()){
                personnels = new Personnels(rs.getInt("CodePers"),
                        rs.getString("Nom"),
                        rs.getString("MotPasse"),
                        rs.getString("Role"),
                        rs.getBoolean("Archive"));
            }
        } catch (SQLException e){
            throw new DALException("select by nom failed = "+ Nom, e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cnx != null) {
                    cnx.close();
                }
                if (stt != null) {
                    stt.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return personnels;
    }

    public Personnels selectById(int CodePers) throws DALException{
        Connection cnx = null;
        PreparedStatement stt = null;
        ResultSet rs = null;

        Personnels personnels = null;
        try{
            cnx = src.fr.eni.clinique.dal.JDBC.JdbcTools.getConnection();
            stt = cnx.prepareStatement(selectById);
            stt.setInt(1, CodePers);

            rs = stt.executeQuery();
            if(rs.next()){
                personnels = new Personnels(rs.getInt("CodePers"),
                        rs.getString("Nom"),
                        rs.getString("MotPasse"),
                        rs.getString("Role"),
                        rs.getBoolean("Archive"));
            }
        } catch (SQLException e) {
            throw new DALException("select by id failed = "+ CodePers, e);
        } finally {
            try{
                if (rs != null){
                    rs.close();
                }
                if (cnx != null){
                    cnx.close();
                }
                if (stt != null){
                    stt.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return personnels;
    }

    public Personnels insert(Object data) throws DALException{
        Personnels personnels = (Personnels)data;
        Connection cnx = null;
        PreparedStatement stt = null;
        try{
            cnx = src.fr.eni.clinique.dal.JDBC.JdbcTools.getConnection();
            stt = cnx.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stt.setString(1,personnels.getNom());
            stt.setString(2,personnels.getMotPasse());
            stt.setString(3,personnels.getRole());
            stt.setBoolean(4,personnels.isArchive());

            int nbRows = stt.executeUpdate();
            if(nbRows == 1){
                ResultSet rs = stt.getGeneratedKeys();
                if(rs.next()){
                    personnels.setCodePers(rs.getInt(1));
                }
            }
        } catch (SQLException e){
            throw new DALException("insert personnels failed - "+personnels, e);
        } finally {
            try{
                if(stt != null){
                    stt.close();
                }
                if(cnx != null){
                    cnx.close();
                }
            } catch (SQLException e){
                throw new DALException("close failed", e);
            }
        }
        return personnels;
    }

    public void update(Object data) throws DALException{
        Personnels personnels = (Personnels)data;
        Connection cnx = null;
        PreparedStatement stt = null;
        try{
            cnx = src.fr.eni.clinique.dal.JDBC.JdbcTools.getConnection();
            stt = cnx.prepareStatement(update);
            stt.setString(1,personnels.getNom());
            stt.setString(2,personnels.getMotPasse());
            stt.setString(3,personnels.getRole());
            stt.setBoolean(4,personnels.isArchive());

            stt.executeUpdate();
        } catch (SQLException e){
            throw new DALException("update personnels failed - "+personnels, e);
        } finally {
            try{
                if(stt != null){
                    stt.close();
                }
                if(cnx != null){
                    cnx.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void delete(int CodePers) throws DALException{
        Connection cnx = null;
        PreparedStatement stt = null;
        try{
            cnx = src.fr.eni.clinique.dal.JDBC.JdbcTools.getConnection();
            stt = cnx.prepareStatement(delete);
            stt.setInt(1, CodePers);
            stt.executeUpdate();
        } catch (SQLException e){
            throw new DALException("delete personnels failed -", e);
        } finally {
            try {
                if(stt != null){
                    stt.close();
                }
                if(cnx != null){
                    cnx.close();
                }
            } catch (SQLException e){
                throw new DALException("close failed", e);
            }
        }
    }
}
