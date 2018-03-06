package fr.eni.clinique.ihm.ecranRDV;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.Properties;

public class PriseRendezVousFrame extends JInternalFrame {

    private JLabel clientLabel, animalLabel, veterinaireLabel, heureLabel;
    private JComboBox clientComboBox, animalComboBox, veterinaireComboBox, dateComboBox;
    private JFrame parent;
    private AgendaTable agendaTable;

    public PriseRendezVousFrame(JFrame parent){
        //Ecran avec un titre, redimensionable, fermable, agrandissable, iconifiable
        super("Gestion du Personnel", true, true, true,true);
        this.parent = parent;
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(null);
        setSize(400,600);
        setContentPane(getPanelPriseRendezVous());
    }


    public JPanel getPanelPriseRendezVous(){
        JPanel panelPriseRendezVous = new JPanel();
        panelPriseRendezVous.add(getPanelPour());
        panelPriseRendezVous.add(getPanelPar());
        panelPriseRendezVous.add(getPanelQuand());
        panelPriseRendezVous.add(getPanelTable());
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
        JPanel panelTable = new JPanel();
        agendaTable = new AgendaTable();
        panelTable.add(agendaTable);
        TitledBorder border = new TitledBorder("liste agenda");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        panelTable.setBorder(border);

        return panelTable;
    }
}
