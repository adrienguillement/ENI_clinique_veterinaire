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
        setSize(880,640);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(getPanelButton(),gbc);

        gbc.gridy = 1;
        panel.add(getPanelPersonnel(),gbc);
        setContentPane(panel);
    }

    //PANEL PERSONNEL

    /**
     *
     * @return
     */
    public JPanel getPanelPersonnel(){
        JPanel panel_personnel = new JPanel();
        panel_personnel.setOpaque(true);
        panel_personnel.setLayout(new BorderLayout());
        getPersonnelTable();
        panel_personnel.add(this.personnelTable.getTableHeader(),BorderLayout.NORTH);
        panel_personnel.add(personnelTable);
        return panel_personnel;
    }

    /**
     *
     * @return
     */
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


    /**
     * JDialog modification d'un personnel.
     * @param personnel
     * @return PersonnelEdit
     */
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
     * Panel modale des boutons d'intéraction
     * @return
     */
    private JPanel getPanelButton(){
        JPanel panelButton = new JPanel();
        ajouter = new JButton(new ImageIcon("lib/ajouter.png"));
        ajouter.setText("Ajouter");
        ajouter.setVerticalTextPosition(SwingConstants.BOTTOM);
        ajouter.setHorizontalTextPosition(SwingConstants.CENTER);
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPersonnelAjout();
                System.out.println("Personnel ajouter");
            }
        });
        modifier = new JButton(new ImageIcon("lib/unlock.png"));
        modifier.setText("Réinitialiser");
        modifier.setVerticalTextPosition(SwingConstants.BOTTOM);
        modifier.setHorizontalTextPosition(SwingConstants.CENTER);
        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int numSelected = personnelTable.getSelectedRow();
                if(numSelected != -1) {
                    System.out.println(numSelected);
                    Personnel personnel = personnelTable.setPersonnelTable().listePersonnels.get(numSelected);
                    getPersonnelEdit(personnel);
                    System.out.println("modification terminer");
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez selectionner la valeur à réinitialiser", null, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        supprimer = new JButton(new ImageIcon("lib/moins.png"));
        supprimer.setText("Supprimer");
        supprimer.setVerticalTextPosition(SwingConstants.BOTTOM);
        supprimer.setHorizontalTextPosition(SwingConstants.CENTER);
        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numSelected = personnelTable.getSelectedRow();
                    if(numSelected != -1) {
                        System.out.println(numSelected);
                        Personnel personnel = personnelTable.listePersonnels.get(numSelected);
                        personnelManager.deletePersonnel(personnel);
                        personnelTable.getModele().setPersonnels(personnelManager.getPersonnels());
                    } else {
                        JOptionPane.showMessageDialog(null, "Veuillez selectionner une valeur à supprimer", null, JOptionPane.ERROR_MESSAGE);
                    }
                } catch (BLLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        panelButton.add(ajouter);
        panelButton.add(supprimer);
        panelButton.add(modifier);
        return panelButton;
    }
}
