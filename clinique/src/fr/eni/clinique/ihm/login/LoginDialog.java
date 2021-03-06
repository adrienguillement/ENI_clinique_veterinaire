package fr.eni.clinique.ihm.login;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.ihm.IHMapp;

public class LoginDialog extends JDialog {

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private IHMapp IHMapp;

    public LoginDialog(Frame parent, IHMapp ihmApp) {
        super(parent, "Login", true);
        //
        this.IHMapp = IHMapp;
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.insets = new Insets(5, 5, 5, 5);

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Nom utilisateur: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        tfUsername.addActionListener(e -> {
            login.connect(getUsername(), getPassword(), ihmApp);
            if(login.getConnectionSucceed()){
                if(ihmApp.getJMenuBar() != null){
                    ihmApp.setMenuBarre(null);
                    ihmApp.setJMenuBar(ihmApp.getMenuBarre());
                    ihmApp.getMenuBarre().updateUI();
                }
                else{
                    ihmApp.setJMenuBar(ihmApp.getMenuBarre());
                }
                dispose();
            }
        });
        panel.add(tfUsername, cs);

        lbPassword = new JLabel("Mot de passe: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        pfPassword.addActionListener(e -> {
            login.connect(getUsername(), getPassword(), ihmApp);
            if(login.getConnectionSucceed()){
                if(ihmApp.getJMenuBar() != null){
                    ihmApp.setMenuBarre(null);
                    ihmApp.setJMenuBar(ihmApp.getMenuBarre());
                    ihmApp.getMenuBarre().updateUI();
                }
                else{
                    ihmApp.setJMenuBar(ihmApp.getMenuBarre());
                }
                dispose();
            }
        });
        panel.add(pfPassword, cs);

        panel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("Connexion");

        btnLogin.addActionListener(e -> {
            login.connect(getUsername(), getPassword(), ihmApp);
            if(login.getConnectionSucceed()){
                if(ihmApp.getJMenuBar() != null){
                    ihmApp.setMenuBarre(null);
                    ihmApp.setJMenuBar(ihmApp.getMenuBarre());
                    ihmApp.getMenuBarre().updateUI();
                }
                else{
                    ihmApp.setJMenuBar(ihmApp.getMenuBarre());
                }
                dispose();
            }
        });

        JPanel bp = new JPanel();
        bp.add(btnLogin);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public String getUsername() {
        return tfUsername.getText().trim();
    }

    public String getPassword() {
        return new String(pfPassword.getPassword());
    }
}
