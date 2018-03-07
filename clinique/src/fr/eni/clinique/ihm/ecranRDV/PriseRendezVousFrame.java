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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PriseRendezVousFrame extends JInternalFrame {

    private JLabel clientLabel, animalLabel, veterinaireLabel, heureLabel;
    private JComboBox clientComboBox, animalComboBox, veterinaireComboBox, dateComboBox;
    private JButton validerButton, supprimerButton, ajouterClientButton, ajouterAnimalauClientButton;
    private JFrame parent;
    private AgendaTable agendaTable;
    private CltManager clientManager;
    private JDatePanelImpl panelQuand;

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
        super("Prise de rendez-vous", true, true, true,true);
        this.parent = parent;
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(null);
        setSize(800,600);
        setContentPane(getPanelPriseRendezVous());
    }

    public JPanel getPanelPriseRendezVous() throws BLLException{
        JPanel panelPriseRendezVous = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPriseRendezVous.add(getPanelPour(), gbc);

        gbc.gridx = 1;
        panelPriseRendezVous.add(getPanelPar(), gbc);

        gbc.gridx = 2;
        panelPriseRendezVous.add(getPanelQuand(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panelPriseRendezVous.add(getPanelTable(), gbc);
        LoadListes();

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPriseRendezVous.add(getValiderSupprimerPanel(), gbc);

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
        JPanel panelPour = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        TitledBorder border = new TitledBorder("Pour");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);

        gbc.gridx = 0;
        gbc.gridy = 0;
        clientLabel = new JLabel("Client");
        panelPour.add(clientLabel, gbc);

        gbc.gridx = 1;
        clientComboBox = new JComboBox();
        panelPour.add(clientComboBox, gbc);

        gbc.gridx = 2;
        ajouterClientButton = new JButton("+");
        panelPour.add(ajouterClientButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        animalLabel = new JLabel("Animal");
        panelPour.add(animalLabel, gbc);

        gbc.gridx = 1;
        animalComboBox = new JComboBox();
        panelPour.add(animalComboBox, gbc);

        gbc.gridx = 2;
        ajouterAnimalauClientButton = new JButton("+");
        panelPour.add(ajouterAnimalauClientButton, gbc);


        panelPour.setBorder(border);
        panelPour.setPreferredSize(new Dimension(1000, 1000));
        return panelPour;
    }

    public JPanel getPanelQuand(){
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        panelQuand = new JDatePanelImpl(model, p);
        TitledBorder border = new TitledBorder("Quand");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        panelQuand.setBorder(border);

        return panelQuand;
    }

    public JPanel getPanelTable(){
        System.out.println("> getPanelTable");
        JPanel panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());
        agendaTable = getAgendaTable();
        panelTable.add(this.agendaTable.getTableHeader(),BorderLayout.NORTH);
        panelTable.add(agendaTable);

        return panelTable;
    }

    public AgendaTable getAgendaTable(){
        agendaTable = new AgendaTable();
        return agendaTable;
    }

    public JPanel getValiderSupprimerPanel(){
        JPanel panelValiderSupprimerPanel = new JPanel();
        validerButton = new JButton("Valider");
        validerButton.addActionListener(e -> {
            addNewReservation();
        });

        supprimerButton = new JButton("Supprimer");

        panelValiderSupprimerPanel.add(validerButton);
        panelValiderSupprimerPanel.add(supprimerButton);

        return panelValiderSupprimerPanel;
    }

    public void addNewReservation() {
        System.out.println("> Nouvelle réservation IHM");
        System.out.println(panelQuand.getModel().getValue());


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
