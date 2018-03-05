package fr.eni.clinique.ihm.ecranPersonnel;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;

import javax.swing.*;
import java.awt.*;

public class PersonnelTable extends JTable{
    private static PersonnelTable instance;
    private PersonnelTableModele modele;
    private PersonnelTable(){
        try {
            PersonnelManager personnelManager = new PersonnelManager();
            GridBagConstraints gbc = new GridBagConstraints();
            modele = new PersonnelTableModele(personnelManager.getPersonnels());
            this.setModel(modele);
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    public PersonnelTableModele getModele() {
        return modele;
    }

    public static PersonnelTable getInstance() {
        if(instance==null){
            instance= new PersonnelTable();
        }
        return instance;
    }


}
