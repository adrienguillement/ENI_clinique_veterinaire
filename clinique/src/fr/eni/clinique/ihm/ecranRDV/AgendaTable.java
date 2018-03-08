package fr.eni.clinique.ihm.ecranRDV;

import fr.eni.clinique.bll.AgendaManager;
import fr.eni.clinique.bll.BLLException;

import javax.swing.*;

public class AgendaTable extends JTable{
    private AgendaTableModele modele;

    /**
     * Constructeur.
     */
    public AgendaTable(){
        AgendaManager agendaManager= new AgendaManager();
        modele = new AgendaTableModele(agendaManager.getListeAgenda());
        this.setModel(modele);
    }
}
