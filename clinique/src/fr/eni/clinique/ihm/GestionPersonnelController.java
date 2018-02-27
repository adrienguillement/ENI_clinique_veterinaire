package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Personne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;




public class GestionPersonnelController
{
    private static IHMApp ecran;
    private static GestionPersonnelController instance;
    private PersonnelManager personnelManager = new PersonnelManager();
    private GeneralController controllerBase = GeneralController.getInstance();

    // constructeur
    private GestionPersonnelController() throws BLLException {
        getEcran();
    }

    // gestion de l'IHM
    public IHMApp getEcran() {
        if(ecran == null){
            ecran = IHMApp.getInstance();
        }
        return ecran;
    }

    public static GestionPersonnelController getInstance() {
        if(instance == null){
            try {
                instance = new GestionPersonnelController();
            } catch (BLLException e) {
                GeneralController.getInstance().error_alert("Problème dans l'application !");
            }
        }
        return instance;
    }

    public void initMyApp(){
        ecran.getPanel_personnel().setVisible(true);

        try{
            this.displayResult(personnelManager.getPersonnels());
        }catch(BLLException e){
            controllerBase.error_alert("Impossible d'afficher les données!");
        }
    }
}
