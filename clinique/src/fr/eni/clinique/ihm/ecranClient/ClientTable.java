package fr.eni.clinique.ihm.ecranClient;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.ihm.ecranClient.ClientTableModel;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.CltManager;
import fr.eni.clinique.ihm.ecranPersonnel.PersonnelTableModele;

import javax.swing.*;

public class ClientTable extends JTable {

    private Client clientSelected;
    private ClientTableModel model;

    public ClientTable(){
        try {
            CltManager clientManager = new CltManager();

            model = new ClientTableModel(clientManager.getCatalogue());

            addListListener(clientManager);
            this.setModel(model);
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    public ClientTable(String searchValue){
        try {
            CltManager clientManager = new CltManager();

            model = new ClientTableModel(clientManager.searchClient(searchValue));
            model.fireTableDataChanged();
            addListListener(clientManager);

        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    private void addListListener(CltManager clientManager){

        // Clic sur une ligne
        ListSelectionModel cellSelectionModel = getSelectionModel();
        cellSelectionModel.addListSelectionListener(e -> {
            int codeClient = (int)(getValueAt(getSelectedRow(), 0));
            if(codeClient != 0){
                try {
                    clientSelected = clientManager.getClientById(codeClient);
                    clientSelected.setCode(codeClient);
                    ClientFrame.getSelectedClientFromTable(clientSelected);
                } catch (BLLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

}
