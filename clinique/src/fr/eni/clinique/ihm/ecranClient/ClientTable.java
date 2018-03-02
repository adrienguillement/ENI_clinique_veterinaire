package fr.eni.clinique.ihm.ecranClient;

import fr.eni.clinique.ihm.ecranClient.ClientTableModel;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.CltManager;
import fr.eni.clinique.ihm.ecranPersonnel.PersonnelTableModele;

import javax.swing.*;

public class ClientTable extends JTable {

    private ClientTableModel model;
    public ClientTable(){
        try {
            CltManager clientManager = new CltManager();
            model = new ClientTableModel(clientManager.getCatalogue());
            this.setModel(model);
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }
    private ClientTableModel getModele() {
        return model;
    }
}
