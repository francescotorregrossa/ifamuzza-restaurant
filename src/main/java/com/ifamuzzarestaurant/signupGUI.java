package com.ifamuzzarestaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.ifamuzzarestaurant.model.Auth;
import com.ifamuzzarestaurant.model.BankTransfer;
import com.ifamuzzarestaurant.model.ReceiptMethod;
import com.ifamuzzarestaurant.model.User;

import org.apache.http.concurrent.FutureCallback;

public class SignupGUI extends JFrame{
    //componenti grafici lato sinistro
    private JLabel genLabel = new JLabel("Insert your restaurant's information",SwingConstants.CENTER);
    private JLabel emailLabel = new JLabel("Email:",SwingConstants.RIGHT);
    private JTextField emailField = new JTextField();
    private JLabel passwordLabel = new JLabel("Password:",SwingConstants.RIGHT);
    private JPasswordField passwordField = new JPasswordField();
    private JLabel confirmPasswordLabel = new JLabel("Confirm password:",SwingConstants.RIGHT);
    private JPasswordField confirmpasswordField = new JPasswordField();
    private JLabel telefonoLabel = new JLabel("Phone:",SwingConstants.RIGHT);
    private JTextField telefonoField = new JTextField();
    private JLabel nomeLabel = new JLabel("Name:",SwingConstants.RIGHT);
    private JTextField nomeField = new JTextField();
    private JLabel indirizzoLabel = new JLabel("Address:",SwingConstants.RIGHT);
    private JTextField indirizzoField = new JTextField();
    private JLabel accontoLabel = new JLabel("Down payment %:",SwingConstants.RIGHT);
    Integer[] perc = new Integer[] {0,10,20,30,40,50,60,70,80,90,100};
    private JComboBox<Integer> percentualiAcconto = new JComboBox<>(perc);
    private JLabel ibanLabel = new JLabel("IBAN:",SwingConstants.RIGHT);
    private JTextField ibanField = new JTextField();
    private JButton registratiButton = new JButton("Create account");
    private JLabel errorLabel = new JLabel("",SwingConstants.CENTER);

    //variabili per pulsante registrati    
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    //componenti grafici lato destro orari
    int yComp = 0;
    private JLabel orariMsg= new JLabel("Opening times(\"hh:mm-hh:mm\")",SwingConstants.CENTER);
    private JLabel[] giorniLabel = new JLabel[7];
    private JTextField[] giorniFields = new JTextField[7];
    private String[] giorni = new String[]{"Monday:","Tuesday:", "Wednesday:", "Thursday:", "Friday:", "Saturday:", "Sunday:"};
    private String[] giorniShort = new String[]{"mon","tue", "wed", "thu", "fri", "sat", "sun"};


    private JPanel panel = new JPanel();
    

    public SignupGUI(){

        super("Sign Up");
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());
        panel.setLayout(null);

        //aggiungo componenti al container
        c.add(panel, BorderLayout.CENTER);
        panel.add(genLabel);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmpasswordField);
        panel.add(telefonoField);
        panel.add(telefonoLabel);
        panel.add(nomeField);
        panel.add(nomeLabel);
        panel.add(indirizzoLabel);
        panel.add(indirizzoField);
        panel.add(accontoLabel);
        panel.add(percentualiAcconto);
        panel.add(ibanLabel);
        panel.add(ibanField);
        panel.add(registratiButton);
        panel.add(orariMsg);
        panel.add(errorLabel);

        for(int i = 0; i<7;i++){
            giorniLabel[i] = new JLabel(giorni[i]);
            giorniFields[i] = new JTextField();
            panel.add(giorniLabel[i]);
            panel.add(giorniFields[i]);

        }
        

        //disposizione elementi grafici sinistra
        genLabel.setBounds(50,10,230,25);
        emailLabel.setBounds(50,40,80,25);
        passwordLabel.setBounds(50,80,80,25);
        confirmPasswordLabel.setBounds(10,120,130,25);
        nomeLabel.setBounds(50, 160,80,25);
        telefonoLabel.setBounds(50, 200,80 ,25);
        indirizzoLabel.setBounds(50,240,80,25);
        accontoLabel.setBounds(10,280,120,25);
        ibanLabel.setBounds(50,320,80,25);
        emailField.setBounds(140,40,150,25);
        passwordField.setBounds(140,80,150,25);
        confirmpasswordField.setBounds(140,120,150,25);
        nomeField.setBounds(140,160,150,25);
        telefonoField.setBounds(140,200,150,25);
        indirizzoField.setBounds(140,240,150,25);
        percentualiAcconto.setBounds(140,280,150,25);
        ibanField.setBounds(140,320,250,25);
        errorLabel.setBounds(160,360,340,30);
        registratiButton.setBounds(230,400,200,30);

        //disposizione elementi grafici destra orari
        orariMsg.setBounds(350,10,230,25);
        
        for(int i = 0; i<7;i++){
            yComp = yComp+40;
            giorniLabel[i].setBounds(350,yComp,80,25);
            giorniFields[i].setBounds(440,yComp,150,25);
        }

        //actionperformed di pulsante registrati
        registratiButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){

                errorLabel.setText("");
                String p1 = sb1.append(passwordField.getPassword()).toString();
                String p2 = sb2.append(confirmpasswordField.getPassword()).toString();

                if (!p1.equals(p2)) {
                    errorLabel.setText("<html><font color='red'>passwords must be identical</font></html>");
                    return;
                }
                else {
                    User u = new User();
                    u.setEmail(emailField.getText());
                    u.setPassword(p1);
                    u.setName(nomeField.getText());
                    u.setPhone(telefonoField.getText());
                    u.setAddress(indirizzoField.getText());
                    u.setDownPayment(Integer.valueOf(String.valueOf(percentualiAcconto.getSelectedItem())));

                    ArrayList<String> openingTimes = new ArrayList<>();
                    for (int i = 0; i < giorni.length; i++) {
                        JTextField giorno = giorniFields[i];
                        if (!giorno.getText().equals("")) {
                            openingTimes.add(giorniShort[i] + " " + giorno.getText());
                        }
                    }
                    u.setOpeningTime(openingTimes.toArray(new String[1]));
                    
                    BankTransfer r = new BankTransfer();
                    r.setHolder(nomeField.getText());
                    r.setAddress(indirizzoField.getText());
                    r.setIBAN(ibanField.getText());
                    u.setReceiptMethod(r);

                    Auth.getInstance().signup(u, new FutureCallback<User>(){
                    
                        @Override public void failed(Exception ex) {
                            ex.printStackTrace();
                            errorLabel.setText("<html><font color='red'>" + ex.getMessage() + "</font></html>");
                        }
                        @Override public void cancelled() { System.out.println("error"); }
                        @Override public void completed(User result) {
                            dispose();
                            DashboardGUI h = new DashboardGUI();
                        }
                        
                    });
                }
                				
			}
        });
        
        //focus su email
        emailField.addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent fe){
                if(emailField.getForeground()==Color.RED){
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
			}
        });
        
        //focus su nome
        nomeField.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent fe){
                if(nomeField.getForeground()==Color.RED){
                    nomeField.setText("");
                    nomeField.setForeground(Color.BLACK);
                }
            }
        });


        //focus su numero
        telefonoField.addFocusListener(new FocusAdapter(){
            @Override
                public void focusGained(FocusEvent fe){
                    if(telefonoField.getForeground()==Color.RED){
                        telefonoField.setText("");
                        telefonoField.setForeground(Color.BLACK);
                        }
                    }
                });


        //settaggi per il frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        screenWidth = (screenWidth/2)-320;
        int screenHeigth = (int) screenSize.getHeight();
        screenHeigth = (screenHeigth/2) - 235;
        this.setVisible(true);
        this.setBounds(screenWidth,screenHeigth,640,470);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

    }
}