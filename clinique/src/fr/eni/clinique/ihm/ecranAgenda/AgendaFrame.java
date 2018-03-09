package fr.eni.clinique.ihm.ecranAgenda;

import fr.eni.clinique.bll.AgendaManager;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.CltManager;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.ecranRDV.AgendaTable;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class AgendaFrame extends JInternalFrame {

    /**
     * Attributs
     */
    private JFrame parent;

    private JPanel panelDe;
    private JComboBox comboVeto;
    private PersonnelManager personnelManager;
    private JDatePickerImpl datePicker;
    private JDatePanelImpl datePanel;
    private Personnel selectedVeto;

    private JPanel panelAgenda;
    private AgendaTable agendaTable;
    private AgendaManager agendaManager;
    private JButton dossierMedical;

    /**
     * Constructeur
     * @param parent
     * @throws BLLException
     */
    public AgendaFrame(JFrame parent) throws BLLException {
        super("Agenda", true, true, true,false);
        this.parent = parent;
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(null);
        setBounds(0, 0, 900, 600);

        setContentPane(setIHM());
    }


    /**
     * Création de l'IHM
     */
    private JPanel setIHM() throws BLLException {
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(getPanelDe(), BorderLayout.NORTH);
        mainPanel.add(getPanelAgenda(), BorderLayout.CENTER);
        return mainPanel;
    }

    /**
     * Panel header
     */
    private JPanel getPanelDe() throws BLLException {
        panelDe= new JPanel();
        panelDe.setBorder(new TitledBorder("De"));

        /**
         * Ajout combobox
         */
        personnelManager = new PersonnelManager();
        List<Personnel> veterinaires = personnelManager.getPersonnels();
        comboVeto = new JComboBox(new DefaultComboBoxModel(veterinaires.toArray()));
        comboVeto.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Personnel veterinaire = (Personnel) value;
                setText(veterinaire.getNom());
                return this;
            }
        });

        /**
         * AddListener
         */
        comboVeto.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();

                // elt affecté par l'event
                Personnel item = (Personnel) e.getItem();

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    //refresh table agenda
                    try {
                        agendaManager = new AgendaManager();
                    } catch (BLLException e1) {
                        e1.printStackTrace();
                    }
                    selectedVeto = item;
                    Date parsed = null;
                    try{
                        String day = String.valueOf(datePanel.getModel().getDay());
                        String month = String.valueOf(datePanel.getModel().getMonth()+1);
                        String year = String.valueOf(datePanel.getModel().getYear());

                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                        try {
                            parsed = format.parse(year + "-" + month + "-" + day);
                        } catch (ParseException error) {
                            error.printStackTrace();
                        }

                        java.sql.Timestamp dateRDV = new java.sql.Timestamp(parsed.getTime());
                        dateRDV.setTime(parsed.getTime());
                    } catch(Exception error){
                        java.sql.Timestamp dateRDV = new Timestamp(System.currentTimeMillis());
                    }

                    java.sql.Timestamp dateRDV = new java.sql.Timestamp(parsed.getTime());
                    dateRDV.setTime(parsed.getTime());
                    agendaTable.getModele().setListeAgenda(agendaManager.selectByDateAndPersonneID(dateRDV, item.getCodePers()));
                }
            }
        });

        /**
         * Ajout DatePicker
         */
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Aujourd'hui");
        p.put("text.month", "Mois");
        p.put("text.year", "Année");

        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        datePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String day = String.valueOf(datePanel.getModel().getDay());
                String month = String.valueOf(datePanel.getModel().getMonth()+1);
                String year = String.valueOf(datePanel.getModel().getYear());

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = null;

                try {
                    parsed = format.parse(year + "-" + month + "-" + day);
                } catch (ParseException error) {
                    error.printStackTrace();
                }

                java.sql.Timestamp dateRDV = new java.sql.Timestamp(parsed.getTime());
                dateRDV.setTime(parsed.getTime());
                try {
                    agendaManager = new AgendaManager();
                } catch (BLLException e1) {
                    e1.printStackTrace();
                }
                agendaTable.getModele().setListeAgenda(agendaManager.selectByDateAndPersonneID(dateRDV, selectedVeto.getCodePers()));
            }
        });

        panelDe.add(new JLabel("Vétérinaire"));
        panelDe.add(comboVeto);

        panelDe.add(new JLabel("Date"));
        panelDe.add(datePicker);


        return panelDe;
    }

    /**
     * Panel Agenda
     */
    public JPanel getPanelAgenda() {
        panelAgenda = new JPanel();
        panelAgenda.setLayout(new BorderLayout());
        agendaTable = new AgendaTable(this);

        dossierMedical = new JButton("DossierMédical");

        panelAgenda.add(this.agendaTable.getTableHeader(),BorderLayout.NORTH);
        panelAgenda.add(agendaTable, BorderLayout.CENTER);

        panelAgenda.add(dossierMedical);

        return panelAgenda;
    }
}
