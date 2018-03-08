package fr.eni.clinique.ihm.ecranRDV;

import fr.eni.clinique.bll.*;
import fr.eni.clinique.bo.Agenda;
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
import java.util.Date;
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
            JOptionPane.showMessageDialog(null, "Impossible de charger l'application.", null, JOptionPane.ERROR_MESSAGE);

        }
    }

    private PersonnelManager personnelManager;
    {
        try {
            personnelManager = new PersonnelManager();
        } catch (BLLException e) {
            JOptionPane.showMessageDialog(null, "Impossible de charger l'application.", null, JOptionPane.ERROR_MESSAGE);

        }
    }

    private AnimalManager animalManager;
    {
        try {
            animalManager = new AnimalManager();
        } catch (BLLException e) {
            JOptionPane.showMessageDialog(null, "Impossible de charger l'application.", null, JOptionPane.ERROR_MESSAGE);

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
        //chargement liste clients
        List<Personnel> personnels = null;
        try {
            personnels = personnelManager.getPersonnels();
        } catch (BLLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération du personnel.", null, JOptionPane.ERROR_MESSAGE);

        }
        //affichage d'une comboBox avec properties spécifiques à l'objet
        veterinaireComboBox = new JComboBox(new DefaultComboBoxModel(personnels.toArray()));
        veterinaireComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Personnel personnel = (Personnel) value;
                setText(personnel.getNom());
                return this;
            }
        });
        panelPar.add(veterinaireComboBox);

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
        //chargement liste clients
        List<Client> clients = null;
        try {
            clients = clientManager.getCatalogue();
        } catch (BLLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des clients.", null, JOptionPane.ERROR_MESSAGE);

        }
        //affichage d'une comboBox avec properties spécifiques à l'objet
        clientComboBox = new JComboBox(new DefaultComboBoxModel(clients.toArray()));
        clientComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Client client = (Client) value;
                setText(client.getNom() + "-" + client.getPrenomClient());
                return this;
            }
        });
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
        Client client = (Client) clientComboBox.getSelectedItem();
        System.out.println(panelQuand.getModel().getValue());

        java.util.Date dateUtil = (java.util.Date) panelQuand.getModel().getValue();
        java.sql.Date dateRDV = new java.sql.Date(dateUtil.getTime());

        Personnel veto = (Personnel) veterinaireComboBox.getSelectedItem();
        System.out.println(client.getCode());
        Agenda newRdv = new Agenda(veto.getCodePers(), dateRDV, client.getCode());
        try {
            AgendaManager agendaManager = new AgendaManager();
            agendaManager.insert(newRdv);
            JOptionPane.showMessageDialog(null, "Nouvelle réservation effectuée.", null, JOptionPane.INFORMATION_MESSAGE);

        } catch (BLLException e) {
            JOptionPane.showMessageDialog(null, "Problème lors de la réservation", null, JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(newRdv);


    }

    public void LoadListes() throws BLLException{
        List<Animal> listeAnimal = animalManager.getListeAnimaux();

        for(Animal elt:listeAnimal){
            animalComboBox.addItem(elt.getNomAnimal());
        }
    }
}
