package fr.eni.clinique.ihm.ecranPersonnel;

import fr.eni.clinique.bo.Personnel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PersonnelFrame extends JInternalFrame{
    private PersonnelTable personnelTable;

    public PersonnelFrame(){
        //Ecran avec un titre, redimensionable, fermable, agrandissable, iconifiable
        super("Personnel", true, true, true,true);

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBounds(100, 100,400, 200);
        setContentPane(getPanelPersonnel());
    }



    //PANEL PERSONNEL
    public JPanel getPanelPersonnel(){
        JPanel panel_personnel = new JPanel();
        panel_personnel.setOpaque(true);
        panel_personnel.setLayout(new FlowLayout());
        panel_personnel.add(getPanelButton());
        panel_personnel.add(getPersonnelTable());
        return panel_personnel;
    }

    public PersonnelTable getPersonnelTable() {
        if(personnelTable==null){
            personnelTable = new PersonnelTable();
        }
        return personnelTable;
    }

    private JPanel getPanelButton(){
        JPanel panelButton = new JPanel();
        panelButton.add(new JButton("Ajouter"));
        panelButton.add(new JButton("Modifier"));
        panelButton.add(new JButton("Supprimer"));
        return panelButton;
    }
}
