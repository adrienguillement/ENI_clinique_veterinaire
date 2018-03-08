package fr.eni.clinique.bll;

import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOClient;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.bo.Client;

import javax.swing.*;
import java.util.List;

public class CltManager {

    /**
     * Attribut
     */
    private static DAOClient daoClient;

    /**
     * Constructeur
     * @throws BLLException
     */
    public CltManager() {

        daoClient = DAOFactory.getClientDAO();
    }

    /**
     * Mise à jour client
     * @param client
     * @throws BLLException
     */
    public void updateClient(Client client) throws BLLException {
        try {
            daoClient.updateClient(client);
        } catch (DALException e) {
            JOptionPane.showMessageDialog(null, "Impossible de mettre à jour ce rendez-vous.", null, JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * Recherche client
     * @param searchValue
     * @return
     * @throws BLLException
     */
    public List<Client> searchClient(String searchValue) throws BLLException {
        List<Client> clients = null;

        try {
            clients = daoClient.searchClient(searchValue);
        } catch (DALException e) {
            JOptionPane.showMessageDialog(null, "Impossible rechercher un client.", null, JOptionPane.ERROR_MESSAGE);

        }

        return clients;
    }

    /**
     * insert d'un client
     */
    public void insertClient(Client client) throws BLLException{
        try{
            daoClient.insert(client);
        }catch(DALException e){
            JOptionPane.showMessageDialog(null, "Impossible d'inserer un nouveau client.", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Suppression client
     * @param client
     * @throws BLLException
     */
    public void deleteClient(Client client) throws BLLException{
        try{
            daoClient.delete(client);
        }catch(DALException e) {
            JOptionPane.showMessageDialog(null, "Impossible de supprimer le client.", null, JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * Récupération premier client DB
     * @return
     * @throws BLLException
     */
    public Client getFirst() throws BLLException {
        Client clients = null;

        try{
            clients = daoClient.selectFirstClient();
        }catch(DALException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Impossible de récupérer les clients.", null, JOptionPane.ERROR_MESSAGE);

        }
        return clients;
    }

    /**
     * Récupération du catalogue des Cliens
     * @return
     * @throws BLLException
     */
    public List<Client> getCatalogue() throws BLLException{
        List<Client> clients = null;

        try{
            clients = daoClient.selectAll();
        }catch(DALException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Impossible de charger les clients.", null, JOptionPane.ERROR_MESSAGE);

        }
        return clients;
    }


    /**
     * Récupération client from code
     * @param id
     * @return
     * @throws BLLException
     */
    public Client getClientById(int id) throws  BLLException {
        Client client = null;

        try {
            client = daoClient.selectById(id);
        } catch (DALException e) {
            JOptionPane.showMessageDialog(null, "Impossible de récupérer le client correspondant.", null, JOptionPane.ERROR_MESSAGE);

        }
        return client;
    }

    /**
     * Valider les données d'un client
     * @param client
     * @throws BLLException
     */
    public boolean validerClient(Client client) throws BLLException{
        boolean valide = true;
        StringBuffer sb = new StringBuffer();
        if(null==client){
            valide = false;
            sb.append("Client null");
        }
        if(null==client.getNom() || 0==client.getNom().trim().length()){
            valide = false;
            sb.append("Nom du client null");
        }
        if(null==client.getPrenomClient() || 0==client.getPrenomClient().trim().length()){
            valide = false;
            sb.append("Prénom du client null");
        }
        if(null==client.getEmail() || 0==client.getEmail().trim().length()){
            valide = false;
            sb.append("Email du client null");
        }
        if(null==client.getAdresse1() || 0==client.getAdresse1().trim().length()){
            valide = false;
            sb.append("Adresse du client null");
        }
        if(null==client.getCodePostal() || 0==client.getCodePostal().trim().length()){
            valide = false;
            sb.append("Code Postal du client null");
        }
        if(null==client.getVille() || 0==client.getVille().trim().length()){
            valide = false;
            sb.append("Ville du client null");
        }

        if(!valide){
            throw new BLLException(sb.toString());
        }
        return valide;
    }
}
