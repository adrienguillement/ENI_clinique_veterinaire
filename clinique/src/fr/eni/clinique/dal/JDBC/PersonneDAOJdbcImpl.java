package fr.eni.clinique.dal.JDBC;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOPersonne;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonneDAOJdbcImpl implements DAOPersonne{
    private static final String selectAll = "select CodePers, Nom, MotPasse, Role, Archive from Personnel WHERE Archive=0 ORDER BY CodePers ASC";
    private static final String selectByVet = "select CodePers, Nom, MotPasse, Role, Archive from Personnel where Role= vet";
    private static final String selectById = "select CodePers, Nom, MotPasse, Role, Archive from Personnel where CodePers= ?";
    private static final String insert = "insert into Personnel(Nom, MotPasse, Role, Archive) values(?,?,?,?)";
    private static final String update = "update Personnel set Nom=?, MotPasse=?,Role=? where CodePers=?";
    private static final String delete = "update Personnel set Archive=1 where CodePers=?";

    public List<Personnel> selectAll() throws DALException{
        Connection cnx = null;
        Statement stt = null;
        ResultSet rs = null;
        List<Personnel> liste = new ArrayList<Personnel>();
        try{
            cnx = JdbcTools.getConnection();
            stt = cnx.createStatement();
            rs = stt.executeQuery(selectAll);
            Personnel per = null;

            while (rs.next()) {
                per = new Personnel(rs.getInt("CodePers"),
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

    public List<Personnel> selectByVet() throws DALException{
        Connection cnx = null;
        Statement stt = null;
        ResultSet rs = null;
        List<Personnel> liste = new ArrayList<Personnel>();
        try{
            cnx = JdbcTools.getConnection();
            stt = cnx.createStatement();
            rs = stt.executeQuery(selectByVet);
            Personnel per = null;

            while (rs.next()) {
                per = new Personnel(rs.getInt("CodePers"),
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


    @Override
    public void delete(Personnel personnel) throws DALException {

    }


    public Personnel selectById(int CodePers) throws DALException{
        Connection cnx = null;
        PreparedStatement stt = null;
        ResultSet rs = null;

        Personnel personnel = null;
        try{
            cnx = JdbcTools.getConnection();
            stt = cnx.prepareStatement(selectById);
            stt.setInt(1, CodePers);

            rs = stt.executeQuery();
            if(rs.next()){
                personnel = new Personnel(rs.getInt("CodePers"),
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


    public Personnel insert(Object data) throws DALException{
        Personnel personnel = (Personnel)data;
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


    @Override
    public void update(Object unPersonnel) throws DALException{
        Personnel personnel = (Personnel)unPersonnel;
        Connection cnx = null;
        PreparedStatement stt = null;
        try{
            cnx = JdbcTools.getConnection();
            stt = cnx.prepareStatement(update);
            stt.setString(1, personnel.getNom());
            stt.setString(2, personnel.getMotPasse());
            stt.setString(3, personnel.getRole());
            stt.setInt(4,personnel.getCodePers());

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

    @Override
    public void delete(Object unPersonnel) throws DALException {
        Personnel personnel = (Personnel)unPersonnel;
        Connection cnx = null;
        PreparedStatement stt = null;
        try{
            cnx = JdbcTools.getConnection();
            stt = cnx.prepareStatement(delete);
            stt.setInt(1,personnel.getCodePers());
            stt.executeUpdate();
        } catch (SQLException e){
            throw new DALException("delete personnel failed -", e);
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
    }
}
