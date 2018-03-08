package fr.eni.clinique.ihm.ecranRDV;

import fr.eni.clinique.bll.*;
import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.ecranAnimal.AnimalDialog;
import fr.eni.clinique.ihm.ecranClient.ClientAddDialog;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static javax.swing.text.html.HTML.Tag.HEAD;

public class PriseRendezVousFrame extends JInternalFrame {

    private JLabel clientLabel, animalLabel, veterinaireLabel, heureLabel;
    private JComboBox clientComboBox, animalComboBox, veterinaireComboBox, dateComboBox;
    private JButton validerButton, supprimerButton, ajouterClientButton, ajouterAnimalauClientButton;
    private JFrame parent;
    private AgendaTable agendaTable;
    private JDatePanelImpl panelQuand;
    private CltManager clientManager = new CltManager();
    private AnimalManager animalManager = new AnimalManager();
    private AgendaManager agendaManager = new AgendaManager();
    private AnimalDialog animalDialog;
    private ClientAddDialog clientAddDialog;

    private PersonnelManager personnelManager;
    {
        try {
            personnelManager = new PersonnelManager();
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
            e.printStackTrace();
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
        veterinaireComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();

                // elt affecté par l'event
                Object item = event.getItem();

                if (event.getStateChange() == ItemEvent.SELECTED) {

                    //refresh du tableau
                    Personnel personnel = (Personnel)veterinaireComboBox.getSelectedItem();
                    System.out.println(personnel);
                    agendaTable.getModele().setListeAgenda(agendaManager.getListeAgendaFromPersonnel(personnel));
                }
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
            e.printStackTrace();
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
        clientComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();

                // elt affecté par l'event
                Object item = event.getItem();

                if (event.getStateChange() == ItemEvent.SELECTED) {
                    //refresh liste animaux
                    Client clientSelected = (Client)clientComboBox.getSelectedItem();
                    List<Animal> animaux = animalManager.getFromClient(clientSelected);
                    animalComboBox = new JComboBox();
                    for(Animal elt:animaux){
                        animalComboBox.addItem(elt.getNomAnimal());
                    }
                }
            }
        });
        panelPour.add(clientComboBox, gbc);

        gbc.gridx = 2;
        ajouterClientButton = new JButton("+");
        ajouterClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ouverture ecran ajout client
                clientAddDialog = new ClientAddDialog(parent);
                clientAddDialog.setVisible(true);
                List<Client> clients = null;
                try {
                    clients = clientManager.getCatalogue();
                } catch (BLLException e1) {
                    e1.printStackTrace();
                }
                for(Client elt:clients){
                    clientComboBox.addItem(elt);
                }
            }
        });
        panelPour.add(ajouterClientButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        animalLabel = new JLabel("Animal");
        panelPour.add(animalLabel, gbc);

        gbc.gridx = 1;
        Client client = (Client)clientComboBox.getSelectedItem();
        List<Animal> listeAnimal = animalManager.getFromClient(client);
        animalComboBox = new JComboBox();
        for(Animal elt:listeAnimal){
            animalComboBox.addItem(elt.getNomAnimal());
        }
        panelPour.add(animalComboBox, gbc);

        gbc.gridx = 2;
        ajouterAnimalauClientButton = new JButton("+");
        ajouterAnimalauClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ouverture pop up ajout d'un animal
                animalDialog = new AnimalDialog(parent);
                animalDialog.setVisible(true);
                List<Animal> listeAnimal  = new ArrayList<>();
                Client clientSelected = (Client)clientComboBox.getSelectedItem();
                listeAnimal = animalManager.getFromClient(clientSelected);
                animalComboBox = new JComboBox();
                for(Animal elt:listeAnimal){
                    animalComboBox.addItem(elt.getNomAnimal());
                }
                dispose();
            }
        });
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
            try {
                addNewReservation();
            } catch (BLLException e1) {
                e1.printStackTrace();
            }
        });

        supprimerButton = new JButton("Supprimer");

        panelValiderSupprimerPanel.add(validerButton);
        panelValiderSupprimerPanel.add(supprimerButton);

        return panelValiderSupprimerPanel;
    }

    public void addNewReservation() throws BLLException {
        System.out.println("> Nouvelle réservation IHM");
        System.out.println(panelQuand.getModel().getValue());
        Client client = (Client) clientComboBox.getSelectedItem();
        System.out.println(panelQuand.getModel().getValue());

        java.util.Date dateUtil = (java.util.Date) panelQuand.getModel().getValue();
        java.sql.Date dateRDV = new java.sql.Date(dateUtil.getTime());

        Personnel veto = (Personnel) veterinaireComboBox.getSelectedItem();
        System.out.println(client.getCode());
        Agenda newRdv = new Agenda(veto.getCodePers(), dateRDV, client.getCode());
        AgendaManager agendaManager = new AgendaManager();
        agendaManager.insert(newRdv);
        JOptionPane.showMessageDialog(null, "Nouvelle réservation effectuée.", null, JOptionPane.INFORMATION_MESSAGE);
    }

}
