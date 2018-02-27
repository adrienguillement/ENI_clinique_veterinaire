package fr.eni.clinique.ihm;

import fr.eni.clinique.ihm.IHMApp;
import fr.eni.clinique.bll.BLLException;

public class ConnectionController
{
    private IHMApp ecran;
    private static ConnectionController instance;
    //TODO:
    //private ConnectionManager ConnectionManager = new ConnectionManager();
    private GeneralController Controller;

    //constructeur
    private ConnectionController() throws BLLException {
        getEcran();
    }

    //gestion de l'IHM
    public IHMApp getEcran() {
        if(ecran == null)
            ecran = IHMApp.getInstance();
        return ecran;
    }

    public static ConnectionController getInstance() {
        if(instance == null){
            try {
                instance = new ConnectionController();
            } catch (BLLException e) {
                GeneralController.getInstance().error_alert("Problème dans l'application !");
            }
        }
        return instance;
    }

    //initialisation de l'ecran de connexion
    public void initMyApp(){

    }
}
