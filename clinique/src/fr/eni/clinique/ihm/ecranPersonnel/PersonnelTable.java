package fr.eni.clinique.ihm.ecranPersonnel;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Personnel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PersonnelTable extends JTable{
    private static PersonnelTable setPersonnelTable;
    private PersonnelTableModele modele;
    public List<Personnel> listePersonnels = new ArrayList<>();
<<<<<<< HEAD


    /**
     * Constructeur.
     */
=======
>>>>>>> master
    public PersonnelTable(){
        try {
            PersonnelManager personnelManager = new PersonnelManager();
            GridBagConstraints gbc = new GridBagConstraints();
            modele = new PersonnelTableModele(personnelManager.getPersonnels());
            this.listePersonnels = personnelManager.getPersonnels();
            this.setModel(modele);
            setPersonnelTable=this;
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    /**
<<<<<<< HEAD
     *
=======
     * Getter modele.
>>>>>>> master
     * @return
     */
=======
>>>>>>> master
    public PersonnelTableModele getModele() {
        return modele;
    }

<<<<<<< HEAD
    /**
<<<<<<< HEAD
     *
=======
     * Setter liste table.
>>>>>>> master
     * @return
     */
=======
>>>>>>> master
    public static PersonnelTable setPersonnelTable() {
        setPersonnelTable = new PersonnelTable();
        return setPersonnelTable;
    }


}
