package src.fr.eni.clinique.ihm;

import src.fr.eni.clinique.bll.BLLException;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GeneralController {

    private IHMApp ecran;
    private static GeneralController instance;

    //constructeur
    private GeneralController(){
        getEcran();
    }

    //gestion de l'IHM
    public IHMApp getEcran(){
        if(ecran == null){
            ecran = IHMApp.getInstance();
        }
        return ecran;
    }

    //gestion du singleton
    public static GeneralController getInstance(){
        if(GeneralController.instance == null){
            GeneralController.instance = new GeneralController();
        }
        return GeneralController.instance;
    }

    //initialisation IHM
    public void initApp() throws BLLException {
        ConnectionController.getInstance().initMyApp();
        initMyButton();
    }

    public void initMyButton() throws BLLException{
        ecran.getAccueil_connection().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ConnectionController.getInstance().initMyApp();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void error_alert(String message){
        JOptionPane alert = new JOptionPane();
        alert.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public void success_alert(String message){
        JOptionPane alert = new JOptionPane();
        alert.showMessageDialog(null, message, "Succès", JOptionPane.INFORMATION_MESSAGE);
    }
}
