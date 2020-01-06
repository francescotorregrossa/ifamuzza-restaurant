package com.ifamuzzarestaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.ifamuzzarestaurant.model.Auth;

import org.apache.http.concurrent.FutureCallback;

public class DashboardGUI extends JFrame {

    // componenti per pannello actionsPanel
    private JButton modificaDailyButton = new JButton("<html><p align'center'>Edit<br />daily menu</p></html>");
    private JButton modificaMenuButton = new JButton("<html><p align'center'>Edit menu</p></html>");
    private JButton showOrdersButton = new JButton("<html><p align'center'>Show<br />last orders</p></html>");
    private JButton showAllOrdersButton = new JButton("<html><p align'center'>Show<br />all orders</p></html>");
    private JButton updateAccountButton = new JButton("<html><p align'center'>Edit<br />account</p></html>");
    private JButton logoutButton = new JButton("Logout");
    // pannelli
    private JPanel actionsPanel = new JPanel();
    private JPanel updateAccountPanel = new JPanel();
    private JPanel logoutPanel = new JPanel();
    private JPanel dailyMenuPanel = new JPanel();
    private JPanel menuPanel = new JPanel();

    // componenti per il pannello updateAccountPanel che entra in gioco quando premi
    // "Modifica Account"
    private JLabel genLabel = new JLabel("Edit your information:");
    private JLabel emailLabel = new JLabel("Email:", SwingConstants.RIGHT);
    private JTextField emailField = new JTextField();
    private JLabel passwordLabel = new JLabel("New password:", SwingConstants.RIGHT);
    private JPasswordField passwordField = new JPasswordField();
    private JLabel confirmPasswordLabel = new JLabel("Confirm password:", SwingConstants.RIGHT);
    private JPasswordField confirmpasswordField = new JPasswordField();
    private JLabel telefonoLabel = new JLabel("Phone:", SwingConstants.RIGHT);
    private JTextField telefonoField = new JTextField();
    private JLabel nomeLabel = new JLabel("Name:", SwingConstants.RIGHT);
    private JTextField nomeField = new JTextField();
    private JLabel indirizzoLabel = new JLabel("Address:", SwingConstants.RIGHT);
    private JTextField indirizzoField = new JTextField();
    private JLabel accontoLabel = new JLabel("Down payment %:", SwingConstants.RIGHT);
    Integer[] perc = new Integer[] { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
    private JComboBox<Integer> percentualiAcconto = new JComboBox<>(perc);
    private JButton confirmUpdate = new JButton("Update profile");
    private JButton backButton = new JButton("Back");

    public DashboardGUI() {
        super("Dashboard — " + Auth.getInstance().getUser().getName());
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());

        // pannello actionPanel
        c.add(actionsPanel, BorderLayout.WEST);
        actionsPanel.setLayout(null);
        actionsPanel.setPreferredSize(new Dimension(150, 300));
        Border border = BorderFactory.createMatteBorder(0, 0, 0, 3, Color.GRAY);
        actionsPanel.setBorder(border);
        actionsPanel.add(modificaDailyButton);
        actionsPanel.add(modificaMenuButton);
        actionsPanel.add(showOrdersButton);
        actionsPanel.add(showAllOrdersButton);
        actionsPanel.add(updateAccountButton);
        actionsPanel.add(logoutButton);

        modificaDailyButton.setBounds(10, 20, 125, 40);
        modificaMenuButton.setBounds(10, 80, 125, 40);
        showOrdersButton.setBounds(10, 140, 125, 40);
        showAllOrdersButton.setBounds(10, 200, 125, 40);
        updateAccountButton.setBounds(10, 300, 125, 40);
        logoutButton.setBounds(10, 350, 125, 40);

        //pannello daily menu
        dailyMenuPanel.setLayout(null);
        dailyMenuPanel.add(backButton);

        backButton.setBounds(10,10,100,30);

        //pannello updateAccount
        updateAccountPanel.setLayout(null);
        updateAccountPanel.add(emailLabel);
        updateAccountPanel.add(emailField);
        updateAccountPanel.add(passwordLabel);
        updateAccountPanel.add(passwordField);
        updateAccountPanel.add(confirmPasswordLabel);
        updateAccountPanel.add(confirmpasswordField);
        updateAccountPanel.add(telefonoLabel);
        updateAccountPanel.add(telefonoField);
        updateAccountPanel.add(nomeLabel);
        updateAccountPanel.add(nomeField);
        updateAccountPanel.add(indirizzoLabel);
        updateAccountPanel.add(indirizzoField);
        updateAccountPanel.add(accontoLabel);
        updateAccountPanel.add(percentualiAcconto);
        updateAccountPanel.add(genLabel);
        updateAccountPanel.add(confirmUpdate);
        updateAccountPanel.add(backButton);

        genLabel.setBounds(170, 10, 230, 25);
        emailLabel.setBounds(50, 40, 80, 25);
        passwordLabel.setBounds(30, 80, 100, 25);
        confirmPasswordLabel.setBounds(10, 120, 130, 25);
        nomeLabel.setBounds(50, 160, 80, 25);
        telefonoLabel.setBounds(50, 200, 80, 25);
        indirizzoLabel.setBounds(50, 240, 80, 25);
        accontoLabel.setBounds(10, 280, 120, 25);
        emailField.setBounds(140, 40, 150, 25);
        passwordField.setBounds(140, 80, 150, 25);
        confirmpasswordField.setBounds(140, 120, 150, 25);
        nomeField.setBounds(140, 160, 150, 25);
        telefonoField.setBounds(140, 200, 150, 25);
        indirizzoField.setBounds(140, 240, 150, 25);
        percentualiAcconto.setBounds(140, 280, 150, 25);
        confirmUpdate.setBounds(320, 380, 150, 30);
        backButton.setBounds(240, 380, 100, 30);

        // pannello Logout
        JButton b = new JButton("Clicca e vinci iPhone X");
        logoutPanel.setLayout(null);
        logoutPanel.add(b);
        logoutPanel.add(backButton);
        b.setBounds(30, 30, 30, 30);
        backButton.setBounds(70,70,30,30);


        ////////////////////////////////////////////////////////////////////
        //ASCOLTATORI




        // Ascoltatore per pulsante modifica daily menu
        modificaDailyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                c.removeAll();
                c.add(actionsPanel, BorderLayout.WEST);
                c.add(dailyMenuPanel, BorderLayout.CENTER);
                dailyMenuPanel.setBackground(Color.GREEN);
                c.validate();
                c.repaint();
            }
        });

        // Ascoltatore per pulsante modifica account
        updateAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                c.removeAll();
                c.add(actionsPanel, BorderLayout.WEST);
                c.add(updateAccountPanel, BorderLayout.CENTER);
                c.validate();
                c.repaint();
            }
        });

        // Ascoltatore per pulsante esci
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
                dispose();
                Auth.getInstance().logout(new FutureCallback<Void>(){
                    @Override public void failed(Exception ex) {
                        SplashGUI s = new SplashGUI();
                    }
                    @Override public void completed(Void result) {
                        SplashGUI s = new SplashGUI();
                    }
                    @Override public void cancelled() {
                        SplashGUI s = new SplashGUI();
                    }
                });
            }
        });

        // Ascoltatore per pulsante indietro che è presente in ogni schermata
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                c.removeAll();
                c.add(actionsPanel, BorderLayout.WEST);
                c.validate();
                c.repaint();
            }
        });

        // settaggi frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        screenWidth = (screenWidth / 2) - 240;
        int screenHeigth = (int) screenSize.getHeight();
        screenHeigth = (screenHeigth / 2) - 135;
        this.setVisible(true);
        this.setBounds(screenWidth, screenHeigth, 640, 470);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }
}