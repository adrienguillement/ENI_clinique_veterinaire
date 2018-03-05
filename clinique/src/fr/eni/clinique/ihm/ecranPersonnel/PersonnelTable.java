package fr.eni.clinique.ihm.ecranPersonnel;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Personnel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PersonnelTable extends JTable{
    private static PersonnelTable instance;
    private PersonnelTableModele modele;
   public List<Personnel> listePersonnels = new ArrayList<>();
    private PersonnelTable(){
        try {
            PersonnelManager personnelManager = new PersonnelManager();
            GridBagConstraints gbc = new GridBagConstraints();
            modele = new PersonnelTableModele(personnelManager.getPersonnels());
            this.listePersonnels = personnelManager.getPersonnels();
            this.setModel(modele);
            instance=this;
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
