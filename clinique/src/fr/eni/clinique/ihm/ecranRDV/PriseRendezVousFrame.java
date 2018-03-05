package fr.eni.clinique.ihm.ecranRDV;


import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PriseRendezVousFrame extends JInternalFrame {

    private JLabel clientLabel, animalLabel, veterinaireLabel, dateLabel, heureLabel;
    private JComboBox clientComboBox, animalComboBox, veterinaireComboBox, dateComboBox;
    private JFrame parent;

    public PriseRendezVousFrame(JFrame parent){
        //Ecran avec un titre, redimensionable, fermable, agrandissable, iconifiable
        super("Gestion du Personnel", true, true, true,true);
        this.parent = parent;
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(null);
        setSize(400,600);
        setIHM();
    }


    public void setIHM(){

        /*DatePicker picker = new JDatePicker();
        picker.setTextEditable(true);
        picker.setShowYearButtons(true);
        jPanel.add((JComponent) picker);*/

        JPanel panelPour = new JPanel();
        TitledBorder border = new TitledBorder("Pour");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        panelPour.setBorder(border);

        clientLabel = new JLabel("Client");
        clientComboBox = new JComboBox();
        panelPour.add(clientLabel);
        panelPour.add(clientComboBox);

        JPanel panelPar = new JPanel();
        border = new TitledBorder("Par");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        panelPour.setBorder(border);

        veterinaireLabel = new JLabel("Vétérinaire");
        
        JPanel panelQuand = new JPanel();
        border = new TitledBorder("Quand");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        panelPour.setBorder(border);
    }
}
