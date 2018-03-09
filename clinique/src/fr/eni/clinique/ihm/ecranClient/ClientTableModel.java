package fr.eni.clinique.ihm.ecranClient;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.AbstractList;
import java.util.List;

public class ClientTableModel extends AbstractTableModel {

    private List<Client> clients;
    private String[] titreColonnes = {"Numero", "Nom", "Prenom", "Mél"};

    public ClientTableModel(List<Client> clients) { this.clients = clients; }

    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int index) {
        return titreColonnes[index];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object data = null;
        switch (columnIndex){
            case 0:
                data=clients.get(rowIndex).getCode();
                break;
            case 1:
                data=clients.get(rowIndex).getNom();
                break;
            case 2:
                data=clients.get(rowIndex).getPrenomClient();
                break;
            case 3:
                data=clients.get(rowIndex).getEmail();
                break;
        }
        return data;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
        this.fireTableDataChanged();
    }

    public List<Client> getClients() {
        return this.clients;
    }
}
