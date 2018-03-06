package fr.eni.clinique.ihm.ecranRDV;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.CltManager;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PriseRendezVousFrame extends JInternalFrame {

    private JLabel clientLabel, animalLabel, veterinaireLabel, heureLabel;
    private JComboBox clientComboBox, animalComboBox, veterinaireComboBox, dateComboBox;
    private JFrame parent;
    private AgendaTable agendaTable;
    private CltManager clientManager;

    {
        try {
            clientManager = new CltManager();
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    private PersonnelManager personnelManager;

    {
        try {
            personnelManager = new PersonnelManager();
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    private AnimalManager animalManager;

    {
        try {
            animalManager = new AnimalManager();
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    public PriseRendezVousFrame(JFrame parent) throws BLLException{
        //Ecran avec un titre, redimensionable, fermable, agrandissable, iconifiable
        super("Gestion du Personnel", true, true, true,true);
        this.parent = parent;
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(null);
        setSize(400,600);
        setContentPane(getPanelPriseRendezVous());
    }

    public JPanel getPanelPriseRendezVous() throws BLLException{
        JPanel panelPriseRendezVous = new JPanel();
        panelPriseRendezVous.add(getPanelPour());
        panelPriseRendezVous.add(getPanelPar());
        panelPriseRendezVous.add(getPanelQuand());
        panelPriseRendezVous.add(getPanelTable());

        LoadListes();

        return panelPriseRendezVous;
    }

    public JPanel getPanelPar(){
        JPanel panelPar = new JPanel();
        TitledBorder border = new TitledBorder("Par");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        panelPar.setBorder(border);

        veterinaireLabel = new JLabel("Vétérinaire");
        veterinaireComboBox = new JComboBox();
        panelPar.add(veterinaireLabel);
        panelPar.add(veterinaireComboBox);

        return panelPar;
    }

    public JPanel getPanelPour(){
        JPanel panelPour = new JPanel();
        TitledBorder border = new TitledBorder("Pour");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        panelPour.setBorder(border);

        clientLabel = new JLabel("Client");
        clientComboBox = new JComboBox();
        panelPour.add(clientLabel);
        panelPour.add(clientComboBox);

        animalLabel = new JLabel("Animal");
        animalComboBox = new JComboBox();
        panelPour.add(animalLabel);
        panelPour.add(animalComboBox);

        return panelPour;
    }

    public JPanel getPanelQuand(){
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl panelQuand = new JDatePanelImpl(model, p);
        TitledBorder border = new TitledBorder("Quand");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        panelQuand.setBorder(border);

        return panelQuand;
    }

    public JPanel getPanelTable(){
        System.out.println("> getPanelTable");
        JPanel panelTable = new JPanel();
        panelTable.setLayout(new GridLayout(0, 1));
        agendaTable = getAgendaTable();
        panelTable.add(this.agendaTable.getTableHeader());
        panelTable.add(getAgendaTable());

        return panelTable;
    }

    public AgendaTable getAgendaTable(){
        agendaTable = new AgendaTable();
        return agendaTable;
    }

    public void LoadListes() throws BLLException{

        List<Client> listeClient = clientManager.getCatalogue();
        List<Animal> listeAnimal = animalManager.getListeAnimaux();
        List<Personnel> listePersonnel = personnelManager.getPersonnels();

        for(Client elt:listeClient){
            clientComboBox.addItem(elt.getNom() + " " + elt.getPrenomClient());
        }

        for(Animal elt:listeAnimal){
            animalComboBox.addItem(elt.getNomAnimal());
        }

        for(Personnel elt:listePersonnel){
            veterinaireComboBox.addItem(elt.getNom());
        }
    }
}
