package fr.eni.clinique.ihm.ecranPersonnel;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;

import javax.swing.*;

public class PersonnelTable extends JTable{
    private PersonnelTableModele modele;
    public PersonnelTable(){
        try {
            PersonnelManager personnelManager = new PersonnelManager();
            modele = new PersonnelTableModele(personnelManager.getPersonnels());
            this.setModel(modele);
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    public PersonnelTableModele getModele() {
        return modele;
    }
}
