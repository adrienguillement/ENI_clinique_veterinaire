package fr.eni.clinique.dal;


import javax.swing.*;
import java.util.Properties;

public class Settings {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(Settings.class.getResourceAsStream("settings.properties"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Impossible de charger les settings de la DB.", null, JOptionPane.ERROR_MESSAGE);

        }
    }

    public static String getProperty(String key){
        String parametre = properties.getProperty(key,null);
        return parametre;
    }
}