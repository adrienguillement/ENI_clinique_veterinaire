package fr.eni.clinique.ihm.ecranRDV;

import fr.eni.clinique.bll.*;
import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static javax.swing.text.html.HTML.Tag.HEAD;

public class PriseRendezVousFrame extends JInternalFrame {

    private JLabel clientLabel, animalLabel, veterinaireLabel, heureLabel;
    private JComboBox clientComboBox, animalComboBox, veterinaireComboBox, dateComboBox, hourComboBox, minutesComboBox;
    private JButton validerButton, supprimerButton, ajouterClientButton, ajouterAnimalauClientButton;
    private JFrame parent;
    private AgendaTable agendaTable;
    private JDatePanelImpl panelCalendar;
    private JDatePickerImpl calendarPicker;
    private JPanel panelQuand;
    private CltManager clientManager = new CltManager();
    private AnimalManager animalManager = new AnimalManager();


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
        return panelPour;
    }


    /**
     * Panel avec la date.
     * @return
     */
    public JPanel getPanelQuand(){
        //Parametres datepicker
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        panelQuand = new JPanel();
        panelQuand.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        TitledBorder border = new TitledBorder("Quand");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);

        gbc.gridwidth = 3;

        panelQuand.add(getPanelCalendar(), gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;

        panelQuand.add(getHourComboBox(), gbc);
        panelQuand.add(new JLabel("h"), gbc);
        panelQuand.add(getMinutesComboBox(), gbc);

        panelQuand.setBorder(border);
        panelQuand.setSize( new Dimension( 200, 24 ) );
        return panelQuand;
    }

    /**
     * Génération minute combobox.
     */
    private JComboBox getMinutesComboBox(){
        minutesComboBox = new JComboBox();
        for(int i=0; i<60; i++){
            if(i<10){
                minutesComboBox.addItem("0" + String.valueOf(i));
            } else {
                minutesComboBox.addItem(String.valueOf(i));
            }

        }

        return minutesComboBox;
    }

    /***
     * génération hour combobox.
     * @return
     */
    private JComboBox getHourComboBox() {
        hourComboBox = new JComboBox();
        for(int i=0; i<24; i++){
            if(i<10) {
                hourComboBox.addItem("0" + String.valueOf(i));
            } else {
                hourComboBox.addItem(String.valueOf(i));
            }
        }
        return hourComboBox;
    }

    public JPanel getPanelCalendar(){
        JPanel panel = new JPanel();
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Aujourd'hui");
        p.put("text.month", "Mois");
        p.put("text.year", "Année");

        panelCalendar = new JDatePanelImpl(model, p);
        calendarPicker = new JDatePickerImpl(panelCalendar, new DateComponentFormatter());
        panel.add(calendarPicker);
        return panelCalendar;
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
        Client client = (Client) clientComboBox.getSelectedItem();

        String day = String.valueOf(panelCalendar.getModel().getDay());
        String month = String.valueOf(panelCalendar.getModel().getMonth()+1);
        String year = String.valueOf(panelCalendar.getModel().getYear());
        String hour = hourComboBox.getSelectedItem().toString();
        String minute = minutesComboBox.getSelectedItem().toString();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsed = null;

        try {
            parsed = format.parse(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + "00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.util.Date dateRDV = new java.sql.Date(parsed.getTime());
        dateRDV.setTime(parsed.getTime());

        Personnel veto = (Personnel) veterinaireComboBox.getSelectedItem();
        Agenda newRdv = new Agenda(veto.getCodePers(), dateRDV, client.getCode());
        AgendaManager agendaManager = new AgendaManager();
        agendaManager.insert(newRdv);
        JOptionPane.showMessageDialog(null, "Nouvelle réservation effectuée.", null, JOptionPane.INFORMATION_MESSAGE);
    }

    public void LoadListes() throws BLLException{
        List<Animal> listeAnimal = animalManager.getListeAnimaux();

        for(Animal elt:listeAnimal){
            animalComboBox.addItem(elt.getNomAnimal());
        }
    }
}
