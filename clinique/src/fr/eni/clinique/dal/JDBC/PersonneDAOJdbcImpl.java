package fr.eni.clinique.dal.JDBC;

import fr.eni.clinique.bo.Personne;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOPersonne;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonneDAOJdbcImpl implements DAOPersonne{

    private static final String selectAll = "select CodePers, Nom, MotPasse, Role, Archive from Personnel";
    private static final String selectByNom = "select CodePers, Nom, MotPasse, Role, Archive from Personnel where Nom= ?";
    private static final String selectById = "select CodePers, Nom, MotPasse, Role, Archive from Personnel where CodePers= ?";
    private static final String insert = "insert into Personnel(Nom, MotPasse, Role, Archive) values(?,?,?,?)";
    private static final String update = "update Personnel set Nom=?, MotPasse=?,Role=?,Archive=? where CodePers=?";
    private static final String delete = "delete from Personnel where id=?";

    public List<Personne> selectAll() throws DALException{

        Connection cnx = null;
        Statement stt = null;
        ResultSet rs = null;
        List<Personne> liste = new ArrayList<Personne>();
        try{
            cnx = JdbcTools.getConnection();
            stt = cnx.createStatement();
            rs = stt.executeQuery(selectAll);
            Personne per = null;

            while (rs.next()) {
                per = new Personne(rs.getInt("CodePers"),
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

    public Personne selectByNom(String Nom) throws DALException{
        Connection cnx = null;
        PreparedStatement stt = null;
        ResultSet rs = null;

        Personne personnel = null;
        try{
            cnx = JdbcTools.getConnection();
            stt = cnx.prepareStatement(selectByNom);
            stt.setString(1, Nom);

            rs = stt.executeQuery();
            if(rs.next()){
                personnel = new Personne(rs.getInt("CodePers"),
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
        return personnel;
    }

    public Personne selectById(int CodePers) throws DALException{
        Connection cnx = null;
        PreparedStatement stt = null;
        ResultSet rs = null;

        Personne personnel = null;
        try{
            cnx = JdbcTools.getConnection();
            stt = cnx.prepareStatement(selectById);
            stt.setInt(1, CodePers);

            rs = stt.executeQuery();
            if(rs.next()){
                personnel = new Personne(rs.getInt("CodePers"),
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
        return personnel;
    }

    public Personne insert(Object data) throws DALException{
        Personne personnel = (Personne)data;
        Connection cnx = null;
        PreparedStatement stt = null;
        try{
            cnx = JdbcTools.getConnection();
            stt = cnx.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stt.setString(1, personnel.getNom());
            stt.setString(2, personnel.getMotPasse());
            stt.setString(3, personnel.getRole());
            stt.setBoolean(4, personnel.isArchive());

            int nbRows = stt.executeUpdate();
            if(nbRows == 1){
                ResultSet rs = stt.getGeneratedKeys();
                if(rs.next()){
                    personnel.setCodePers(rs.getInt(1));
                }
            }
        } catch (SQLException e){
            throw new DALException("insert personnel failed - "+ personnel, e);
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
        return personnel;
    }

    public void update(Object data) throws DALException{
        Personne personnel = (Personne)data;
        Connection cnx = null;
        PreparedStatement stt = null;
        try{
            cnx = JdbcTools.getConnection();
            stt = cnx.prepareStatement(update);
            stt.setString(1, personnel.getNom());
            stt.setString(2, personnel.getMotPasse());
            stt.setString(3, personnel.getRole());
            stt.setBoolean(4, personnel.isArchive());

            stt.executeUpdate();
        } catch (SQLException e){
            throw new DALException("update personnel failed - "+ personnel, e);
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
            cnx = JdbcTools.getConnection();
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
