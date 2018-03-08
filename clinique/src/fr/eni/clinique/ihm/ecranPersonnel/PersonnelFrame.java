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
    private static PersonnelManager personnelManager;

    static {
        try {
            personnelManager = new PersonnelManager();

        } catch (BLLException e) {
            JOptionPane.showMessageDialog(null, "Impossible de contacter la base de données.", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Constructeur
     * @param parent
     */
    public PersonnelFrame(JFrame parent){
        //Ecran avec un titre, redimensionable, fermable, agrandissable, iconifiable
        super("Gestion du Personnel", true, true, true,true);
        this.parent = parent;
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(null);
        setSize(400,340);
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
    public JPanel getPanelPersonnel(){
        JPanel panel_personnel = new JPanel();
        panel_personnel.setOpaque(true);
        panel_personnel.setLayout(new BorderLayout());
        getPersonnelTable();
        panel_personnel.add(this.personnelTable.getTableHeader(),BorderLayout.NORTH);
        panel_personnel.add(personnelTable);
        return panel_personnel;
    }

    public PersonnelTable getPersonnelTable() {
        //if(personnelTable==null){
            personnelTable = PersonnelTable.setPersonnelTable();
        //}
        return personnelTable;
    }

    /**
     * Ajout d'un personnel
     * @return PersonnelAjout
     */
    public PersonnelAjout getPersonnelAjout(){
        personnelAjout = new PersonnelAjout(parent);
        try {
           personnelTable.getModele().setPersonnels(personnelManager.getPersonnels());
        } catch (BLLException e) {
            JOptionPane.showMessageDialog(null, "Impossible de récupérer la liste du personnel.", null, JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Impossible de modifier le personnel de l'application.", null, JOptionPane.ERROR_MESSAGE);
        }
        return personnelEdit;
    }

    /**
     * Panel modale d'ajout de personnel
     * @return
     */
    private JPanel getPanelButton(){
        JPanel panelButton = new JPanel();

        ajouter = new JButton(new ImageIcon("lib/003-add.png"));
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
        modifier = new JButton(new ImageIcon("lib/002-minus.png"));
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
        supprimer = new JButton(new ImageIcon("lib/001-padlock.png"));
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
                    JOptionPane.showMessageDialog(null, "Impossible de supprimer le compte.", null, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panelButton.add(ajouter);
        panelButton.add(supprimer);
        panelButton.add(modifier);
        return panelButton;
    }
}
