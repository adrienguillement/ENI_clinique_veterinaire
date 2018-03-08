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
    private ClientFrame clientFrame;


    /**
     * Constructeur.
     * @param clientFrame
     * @param clientSearchDialog
     */
    public ClientTable(ClientFrame clientFrame, ClientSearchDialog clientSearchDialog){
        this.clientFrame = clientFrame;
        try {
            CltManager clientManager = new CltManager();

            model = new ClientTableModel(clientManager.getCatalogue());

            //Add listener onclick sur ligne
            ListSelectionModel cellSelectionModel = getSelectionModel();
            cellSelectionModel.addListSelectionListener(e -> {
                int codeClient = (int)(getValueAt(getSelectedRow(), 0));
                if(codeClient != 0){
                    try {
                        clientSelected = clientManager.getClientById(codeClient);
                        clientSelected.setCode(codeClient);
                        System.out.println((clientSelected.getCode()));
                        clientFrame.getClientSelected(clientSelected);
                        clientSearchDialog.dispose();
                    } catch (BLLException e1) {
                        JOptionPane.showMessageDialog(null, "Impossible de récupérer le client.", null, JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            this.setModel(model);
        } catch (BLLException e) {
            JOptionPane.showMessageDialog(null, "Impossible de récupérer les clients.", null, JOptionPane.ERROR_MESSAGE);
        }
    }
  

    /**
     * Getter model table.
     * @return
     */
    public ClientTableModel getModele() {
        return model;
    }
}
