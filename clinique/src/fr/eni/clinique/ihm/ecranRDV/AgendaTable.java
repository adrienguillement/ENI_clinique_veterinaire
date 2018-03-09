package fr.eni.clinique.ihm.ecranRDV;

import fr.eni.clinique.bll.AgendaManager;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bo.Agenda;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class AgendaTable extends JTable{
    private AgendaTableModele modele;
    private AgendaManager agendaManager;
    private Agenda rdvSelected;

    {
        try {
            agendaManager = new AgendaManager();
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    public AgendaTable(Container parent){

        modele = new AgendaTableModele(agendaManager.getListeAgenda());
        this.setModel(modele);

        //Cacher le champ n°4 (Date)
        this.getColumnModel().getColumn(4).setMinWidth(0);
        this.getColumnModel().getColumn(4).setMaxWidth(0);
    }

    public AgendaTableModele getModele() {
        return modele;
    }


}
