package fr.eni.clinique.dal.JDBC;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOAgenda;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AgendaDAOJdbcImpl implements DAOAgenda{

    private static final String sqlSelectAll = "SELECT * FROM AGENDA";
    private static final String sqlInsert = "INSERT INTO AGENDA (CodeVeto, DateRdv, CodeAnimal) VALUES(?, ?, ?)";
    private static final String sqlUpdate = "UPDATE AGENDA set DateRdv = ?, CodeAnimal = ? WHERE CodeVeto = ?";
    private static final String sqlDelete = "DELETE * FROM AGENDA where CodeVeto = ?";
    private static final String sqlSelectedByClient = "SELECT * FROM AGENDA where CodeVeto=?";

    @Override
    public List<Agenda> selectAll() throws DALException {
        Connection cnx = null;
        Statement rqt = null;
        ResultSet rs = null;

        List<Agenda> liste = new ArrayList<>();
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.createStatement();
            rs = rqt.executeQuery(sqlSelectAll);
            Agenda agenda = null;

            Timestamp timestamp;
            java.util.Date date;

            while (rs.next()) {
                timestamp = rs.getTimestamp("DateRdv");
                date =  new java.sql.Date(timestamp.getTime());
                agenda = new Agenda(rs.getInt("CodeVeto"),
                        date,
                        rs.getInt("CodeAnimal"));
                liste.add(agenda);

                System.out.println(date);
            }
        } catch (SQLException e) {
            throw new DALException("selectAll agenda failed - " , e);
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
    public Agenda insert(Object unAgenda) throws DALException {
        Agenda agenda = (Agenda) unAgenda;
        Connection cnx = null;
        PreparedStatement rqt = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

            Date date = new Date(agenda.getDateRdv().getTime());

            rqt.setInt(1, agenda.getCodeVeto());
            rqt.setTimestamp(2, new java.sql.Timestamp(date.getTime()));
            rqt.setInt(3, agenda.getCodeAnimal());

            int nbRows = rqt.executeUpdate();
            if(nbRows == 1){
                ResultSet rs = rqt.getGeneratedKeys();
                if(rs.next()){
                    agenda.setCodeVeto(rs.getInt(1));
                }

            }
        }catch(SQLException e){
            throw new DALException("Insert agenda failed - " + agenda, e);
        }
        finally {
            try {
                if (rqt != null){
                    rqt.close();
                }
                if(cnx!=null){
                    cnx.close();
                }
            } catch (SQLException e) {
                throw new DALException("close failed - ", e);
            }
        }

        return agenda;
    }

    @Override
    public void update(Object data) throws DALException {

    }

    @Override
    public void delete(Object unAgenda) throws DALException {
        Agenda agenda = (Agenda)unAgenda;

        Connection cnx = null;
        PreparedStatement rqt = null;

        try{
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlDelete);
            rqt.setInt(1, agenda.getCodeVeto());
            rqt.executeUpdate();
        }catch (SQLException e) {
            throw new DALException("Delete agenda failed - codeVeto=" + agenda.getCodeVeto(), e);
        } finally {
            try {
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
    }

    @Override
    public List<Agenda> selectedByPersonnel(Personnel personnel) {
        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;

        List<Agenda> liste = new ArrayList<>();
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlSelectedByClient);
            rqt.setInt(1, personnel.getCodePers());
            rs = rqt.executeQuery();
            Agenda agenda = null;

            Timestamp timestamp;
            java.util.Date date;

            while (rs.next()) {
                timestamp = rs.getTimestamp("DateRdv");
                date =  new java.sql.Date(timestamp.getTime());
                agenda = new Agenda(rs.getInt("CodeVeto"),
                        date,
                        rs.getInt("CodeAnimal"));
                liste.add(agenda);

                System.out.println(date);
            }
        } catch (SQLException e) {
            try {
                throw new DALException("selectByClient agenda failed - " , e);
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
        return liste;
    }
}
