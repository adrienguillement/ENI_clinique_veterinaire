package fr.eni.clinique.ihm.ecranRDV;

import fr.eni.clinique.bll.AgendaManager;
import fr.eni.clinique.bll.BLLException;

import javax.swing.*;

public class AgendaTable extends JTable{
    private AgendaTableModele modele;
    public AgendaTable(){
        try {
            AgendaManager agendaManager= new AgendaManager();
            modele = new AgendaTableModele(agendaManager.getListeAgenda());
            this.setModel(modele);
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    public AgendaTableModele getModele() {
        return modele;
    }


}
