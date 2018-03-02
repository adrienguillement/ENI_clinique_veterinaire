package fr.eni.clinique.ihm.ecranPersonnel;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Personnel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PersonnelFrame extends JInternalFrame{
    private PersonnelTable personnelTable;
    private PersonnelAjout personnelAjout;
    private JButton ajouter,modifier,supprimer;

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
            panel_personnel.add(getPersonnelTable());
            panel_personnel.add(getPanelButton());
        return panel_personnel;
    }

    public PersonnelTable getPersonnelTable() {
        if(personnelTable==null){
            personnelTable = new PersonnelTable();
        }
        return personnelTable;
    }

    public PersonnelAjout getPersonnelAjout(){
        if(personnelAjout==null){
            personnelAjout = new PersonnelAjout();
        }
        return personnelAjout;
    }

    private JPanel getPanelButton(){
        JPanel panelButton = new JPanel();
        ajouter = new JButton("Ajouter");
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ouverture ajout personnel");
                getPersonnelAjout();
            }
        });
        modifier = new JButton("Modifier");
        supprimer = new JButton("Supprimer");

        panelButton.add(ajouter);
        panelButton.add(modifier);
        panelButton.add(supprimer);
        return panelButton;
    }
}
