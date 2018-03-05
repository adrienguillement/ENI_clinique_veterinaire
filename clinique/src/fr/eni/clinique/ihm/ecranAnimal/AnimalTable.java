package fr.eni.clinique.ihm.ecranAnimal;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.ihm.ecranPersonnel.PersonnelTableModele;

import javax.swing.*;

public class AnimalTable extends JTable {
    private AnimalTableModele modele;
    public AnimalTable(){
        try {
            AnimalManager animalManager = new AnimalManager();
            modele = new AnimalTableModele(animalManager.getListeAnimaux());
            this.setModel(modele);
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    public AnimalTableModele getModele() {
        return modele;
    }
}