package src.fr.eni.clinique.ihm;

import src.fr.eni.clinique.bll.BLLException;
import src.fr.eni.clinique.bll.PersonnelManager;
import src.fr.eni.clinique.bo.Personne;

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
}
