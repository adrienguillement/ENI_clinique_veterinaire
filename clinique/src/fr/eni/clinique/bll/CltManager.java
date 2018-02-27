package src.fr.eni.clinique.bll;

import src.fr.eni.clinique.dal.DALException;
import src.fr.eni.clinique.dal.DAOClient;
import src.fr.eni.clinique.dal.DAOFactory;
import src.fr.eni.clinique.bo.Clients;

import java.util.List;

public class CltManager {

    private static DAOClient daoClients;

    public CltManager() throws BLLException{

        daoClients = DAOFactory.getClientDAO();
    }

    /**
     * Récupération du catalogue des Cliens
     * @return
     * @throws BLLException
     */
    public List<Clients> getCatalogue() throws BLLException{
        List<Clients> clients = null;

        try{
            clients = daoClients.selectAll();
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
    public void validerClient(Clients client) throws BLLException{
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
        if(null==client.getPrenom() || 0==client.getPrenom().trim().length()){
            valide = true;
            sb.append("Prénom du client null");
        }
        if(null==client.getEmail() || 0==client.getEmail().trim().length()){
            valide = true;
            sb.append("Email du client null");
        }
        if(null==client.getAdresse1() || 0==client.getAdresse1().trim().length()){
            valide = true;
            sb.append("Adresse du client null");
        }
        if(null==client.getCodePostal() || 0==client.getCodePostal().trim().length()){
            valide = true;
            sb.append("Code Postal du client null");
        }
        if(null==client.getVille() || 0==client.getVille().trim().length()){
            valide = true;
            sb.append("Ville du client null");
        }

        if(!valide){
            throw new BLLException(sb.toString());
        }
    }
}
