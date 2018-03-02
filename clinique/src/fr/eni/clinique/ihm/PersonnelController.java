package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Personnel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PersonnelController {

    private static PersonnelController instance;
    private static PersonnelManager personnelM;

    static {
        try{
            personnelM = new PersonnelManager();
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    private static GeneralController controllerBase;

    private PersonnelController() throws BLLException{
        getEcran();
    }

    public static PersonnelController getInstance(){
        if(instance==null){
            try{
                instance= new PersonnelController();
            } catch (BLLException e){
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * Appel de l'IHM du personnel
     * @return
     */
    public GeneralController getEcran() {
        if(controllerBase == null){
            controllerBase = GeneralController.getInstance();
        }
        return controllerBase;
    }

    /**
      *
      * Initialisation du menu personnel
      */
    public static void init(){
        controllerBase.getPanel_client().setVisible(false);
        controllerBase.getPanel_personnel().setVisible(true);
        try{
            displayResult(personnelM.getPersonnels());
        }catch(BLLException e){
            e.printStackTrace();
        }
    }

    /**
     * Affichage du personnel dans une liste
     * @param listePersonnel
     */
    public static void displayResult(List<Personnel> listePersonnel){
        GridBagConstraints gbc = new GridBagConstraints();
        int gridy = 0;
        controllerBase.getPanel_personnel_result().removeAll();
        for(Personnel personnel : listePersonnel){
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            gbc.insets = new Insets(20,10,10,10);
            panel.add(new JLabel(personnel.getNom()+" - "+personnel.getRole()), gbc);
            gbc.gridx = 2;
            panel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.black));
            gbc.gridy = gridy;
            gbc.gridwidth = 3;
            controllerBase.getPanel_personnel_result().add(panel, gbc);
            gridy++;
        }
        controllerBase.getPanel_personnel().revalidate();
    }
}