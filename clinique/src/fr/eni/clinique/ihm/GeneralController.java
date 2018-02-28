package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;

public class GeneralController {

    // recuperation singleton IHMApp
    private IHMApplication ecran;

    // singleton GeneralController
    private static GeneralController instance;

    private GeneralController(){
        getEcran();
    }

    // méthode singleton
    public static GeneralController getInstance(){
        if(GeneralController.instance == null){
            GeneralController.instance = new GeneralController();
        }
        return GeneralController.instance;
    }

    public IHMApplication getEcran() {
        if(ecran==null){
            ecran = IHMApplication.getInstance();
        }
        return ecran;
    }

    public void initMyapp() throws BLLException {
        LoginController.getInstance().initMyApp();
    }
}
