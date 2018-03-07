package fr.eni.clinique.ihm.ecranPersonnel;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Personnel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class PersonnelFrame extends JInternalFrame{
    private PersonnelTable personnelTable;
    private PersonnelAjout personnelAjout;
    private PersonnelEdit personnelEdit;
    private JButton ajouter,modifier,supprimer;
    private JFrame parent;
    private Personnel personnel;
    private static PersonnelManager personnelManager;

    static {
        try {
            personnelManager = new PersonnelManager();

        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    public PersonnelFrame(JFrame parent){
        //Ecran avec un titre, redimensionable, fermable, agrandissable, iconifiable
        super("Gestion du Personnel", true, true, true,true);
        this.parent = parent;
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        //setBounds(100, 100,400, 600);
        setLayout(null);
        setSize(400,600);
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
        //if(personnelTable==null){
            personnelTable = PersonnelTable.setPersonnelTable();
        //}
        return personnelTable;
    }

    public PersonnelAjout getPersonnelAjout(){
        personnelAjout = new PersonnelAjout(parent);
        try {
           personnelTable.getModele().setPersonnels(personnelManager.getPersonnels());
        } catch (BLLException e) {
            e.printStackTrace();
        }

        return personnelAjout;
    }

    public PersonnelEdit getPersonnelEdit(Personnel personnel){
        personnelEdit = new PersonnelEdit(parent, personnel);
        try{
            personnelTable.getModele().setPersonnels(personnelManager.getPersonnels());
        } catch (BLLException e){
            e.printStackTrace();
        }
        return personnelEdit;
    }

    /**
     * Panel modale d'ajout de personnel
     * @return
     */
    private JPanel getPanelButton(){
        JPanel panelButton = new JPanel();
        ajouter = new JButton("Ajouter");
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPersonnelAjout();
                System.out.println("Personnel ajouter");
            }
        });
        modifier = new JButton("Modifier");
        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int numSelected = personnelTable.getSelectedRow();
                System.out.println(numSelected);
                Personnel personnel = personnelTable.setPersonnelTable().listePersonnels.get(numSelected);
                getPersonnelEdit(personnel);
                System.out.println("modification terminer");
            }
        });
        supprimer = new JButton("Supprimer");
        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numSelected = personnelTable.getSelectedRow();
                    System.out.println(numSelected);
                    Personnel personnel = personnelTable.listePersonnels.get(numSelected);
                    personnelManager.deletePersonnel(personnel);
                    personnelTable.getModele().setPersonnels(personnelManager.getPersonnels());
                } catch (BLLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        panelButton.add(ajouter);
        panelButton.add(modifier);
        panelButton.add(supprimer);
        return panelButton;
    }
}
