package fr.eni.clinique.bll;

import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOClient;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.bo.Client;
import java.util.List;

public class CltManager {

    private static DAOClient daoClient;

    public CltManager() throws BLLException{

        daoClient = DAOFactory.getClientDAO();
    }

    /**
     * insert d'un client
     * */
    public void insertClient(Client client) throws BLLException{
        try{
            validerClient(client);
            daoClient.insert(client);
        }catch(DALException e){
            throw new BLLException("Echec insertClient-client : "+client, e);
        }
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
            throw new BLLException("Erreur récupération catalogue", e);
        }
        return clients;
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
