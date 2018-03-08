package fr.eni.clinique.dal.JDBC;

import fr.eni.clinique.dal.Settings;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JdbcTools {
    private static  String urldb;
    private static String userdb;
    private static String passworddb;

    static {

        try {
            Class.forName(Settings.getProperty("driverDB"));
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Le fichier de configuration de la DB est incorrect", null, JOptionPane.ERROR_MESSAGE);

        }
        urldb = Settings.getProperty("urldb");
        userdb = Settings.getProperty("userdb");
        passworddb = Settings.getProperty("passworddb");
    }



    public static Connection getConnection() throws SQLException{
        //Connection connection = DriverManager.getConnection(urldb);
        Connection connection = DriverManager.getConnection(urldb, userdb, passworddb);

        return connection;
    }
}
