package fr.eni.clinique.ihm.ecranAnimal;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.ihm.ecranPersonnel.PersonnelTableModele;

import javax.swing.*;

public class AnimalTable extends JTable {
    private AnimalTableModele modele;

    /**
     * Constructeur
     * @param client
     */
    public AnimalTable(Client client){
        try {
            AnimalManager animalManager = new AnimalManager();
            modele = new AnimalTableModele(animalManager.getFromClient(client));
            this.setModel(modele);
        } catch (BLLException e) {
            JOptionPane.showMessageDialog(null, "Impossible de récupérer les animaux du client.", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Récupération du model de la table.
     * @return AnimalTableModele
     */
    public AnimalTableModele getModele() {
        return modele;
    }
}
