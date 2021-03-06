package fr.eni.clinique.dal.JDBC;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOClient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOJdbcImpl implements DAOClient {

    private static final String sqlInsert = "INSERT INTO CLIENT(NomClient, PrenomClient, Adresse1, Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String sqlSelectAll = "SELECT * from client WHERE archive = 0";
    private static final String sqlSelectById = "SELECT * from client WHERE CodeClient = ?";
    private static final String sqlDelete = "UPDATE client SET archive = 1 WHERE CodeClient = ?";
    private static final String sqlFirstClient = "SELECT TOP 1 * FROM client WHERE archive = 0";
    private static final String sqlSearchClient = "SELECT * FROM client WHERE NomClient LIKE ?";
    private static final String sqlUpdateClient = "UPDATE client set NomClient = ?, PrenomClient = ?, Adresse1 = ?, CodePostal = ?, Ville = ?, NumTel = ?, Assurance = ?, Email = ?, Remarque = ? WHERE CodeClient = ?";

    /**
     * Mettre à jour les informations d'un client
     * @param client
     * @throws DALException
     */
    public void updateClient(Client client) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;

        try{
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlUpdateClient);
            rqt.setString(1, client.getNom());
            rqt.setString(2, client.getPrenomClient());
            rqt.setString(3, client.getAdresse1());
            rqt.setString(4, client.getCodePostal());
            rqt.setString(5, client.getVille());
            rqt.setString(6, client.getNumTel());
            rqt.setString(7, client.getAssurance());
            rqt.setString(8, client.getEmail());
            rqt.setString(9, client.getRemarque());
            rqt.setInt(10, client.getCode());

            rqt.executeUpdate();
        }catch (SQLException e) {
            throw new DALException("Delete client failed - client = " + client, e);
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


    public List<Client> searchClient(String searchValue) throws DALException {
        searchValue = "%" + searchValue + "%";
        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;

        List<Client> liste = new ArrayList<Client>();

        try{
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlSearchClient);
            rqt.setString(1, searchValue);
            rs = rqt.executeQuery();

            Client client = null;

            while (rs.next()) {
                client = new Client(rs.getInt("codeClient"),
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
                liste.add(client);
            }
        } catch (SQLException e) {
            throw new DALException("sqlSearch failed - " , e);
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

    /**
     * Retourne le premier client de la table
     * @return
     * @throws DALException
     */
    public Client selectFirstClient() throws DALException {

        Connection cnx = null;
        Statement rqt = null;
        ResultSet rs = null;
        Client client = null;

        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.createStatement();
            rs = rqt.executeQuery(sqlFirstClient);


            if (rs.next()) {
                client = new Client(rs.getInt("codeClient"),
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
        return client;
    }

    @Override
    public void delete(Client client) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;

        try{
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlDelete);
            rqt.setInt(1, client.getCode());
            rqt.executeUpdate();
        }catch (SQLException e) {
            throw new DALException("Delete client failed - client = " + client, e);
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
    public Client insert(Object data) throws DALException {
        Client client = (Client) data;

        Connection cnx = null;
        PreparedStatement rqt = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            rqt.setString(1, client.getNom());
            rqt.setString(2, client.getPrenomClient());
            rqt.setString(3, client.getAdresse1());
            rqt.setString(4, client.getAdresse2());
            rqt.setString(5, client.getCodePostal());
            rqt.setString(6, client.getVille());
            rqt.setString(7, client.getNumTel());
            rqt.setString(8, client.getAssurance());
            rqt.setString(9, client.getEmail());
            rqt.setString(10, client.getRemarque());
            rqt.setBoolean(11, client.isArchive());

            int nbRows = rqt.executeUpdate();
            if(nbRows == 1){
                ResultSet rs = rqt.getGeneratedKeys();
                if(rs.next()){
                    client.setCode(rs.getInt(1));
                }

            }
        }catch(SQLException e){
            throw new DALException("Insert client failed - " + client, e);
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

        return client;
    }

    @Override
    public void update(Object data) throws DALException {

    }

    @Override
    public void delete(Object data) throws DALException {

    }

    /**
     * Retourne un client en fonction de l'ID
     * @param idClient
     * @return
     * @throws DALException
     */
    public Client selectById(int idClient) throws DALException {

        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;

        Client client = null;

        try{
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlSelectById);
            rqt.setInt(1, idClient);
            rs = rqt.executeQuery();



            if(rs.next()){
                client = new Client(rs.getInt("codeClient"),
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
            }
        } catch (SQLException e) {
            throw new DALException("selectByID failed - " , e);
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
        return client;
    }
}
