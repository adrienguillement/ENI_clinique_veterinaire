package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) throws BLLException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    GeneralController.getInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
