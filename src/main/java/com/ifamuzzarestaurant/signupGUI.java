package com.ifamuzzarestaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class SignupGUI extends JFrame{
    //componenti grafici lato sinistro
    private JLabel genLabel = new JLabel("Inserisci le tue informazioni",SwingConstants.CENTER);
    private JLabel emailLabel = new JLabel("Email:",SwingConstants.RIGHT);
    private JTextField emailField = new JTextField();
    private JLabel passwordLabel = new JLabel("Password:",SwingConstants.RIGHT);
    private JPasswordField passwordField = new JPasswordField();
    private JLabel confirmPasswordLabel = new JLabel("Conferma password:",SwingConstants.RIGHT);
    private JPasswordField confirmpasswordField = new JPasswordField();
    private JLabel telefonoLabel = new JLabel("Telefono:",SwingConstants.RIGHT);
    private JTextField telefonoField = new JTextField();
    private JLabel nomeLabel = new JLabel("Nome locale:",SwingConstants.RIGHT);
    private JTextField nomeField = new JTextField();
    private JLabel indirizzoLabel = new JLabel("Indirizzo:",SwingConstants.RIGHT);
    private JTextField indirizzoField = new JTextField();
    private JLabel accontoLabel = new JLabel("Acconto minimo %:",SwingConstants.RIGHT);
    Integer[] perc = new Integer[] {10,20,30,40,50,60,70,80,90};
    private JComboBox<Integer> percentualiAcconto = new JComboBox<>(perc);
    private JButton registratiButton = new JButton("Registati");
    String regexEmail = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    String regexNome= "[a-zA-Z]+";
    String regexTelefono = "[0-9]+";
    Boolean validSignup = true; 


    //componenti grafici lato destro orari
    int yComp = 0;
    private JLabel orariMsg= new JLabel("Orari di apertura(\"hh:mm-hh:mm\")",SwingConstants.CENTER);
    /*private JLabel lunLabel = new JLabel("Lunedi:");
    private JLabel marLabel = new JLabel("Martedi:");
    private JLabel merLabel = new JLabel("Mercoledi:");
    private JLabel gioLabel = new JLabel("Giovedi:");
    private JLabel venLabel = new JLabel("Venerdi:");
    private JLabel sabLabel = new JLabel("Sabato:");
    private JLabel domLabel = new JLabel("Domenica:");*/
    private JLabel[] giorniLabel = new JLabel[7];
    private JTextField[] giorniFields = new JTextField[7];
    private String[] giorni = new String[]{"Lunedi:","Martedi:", "Mercoledi:", "Giovedi:", "Venerdi:", "Sabato:", "Domenica:"};


    private JPanel panel = new JPanel();
    

    public SignupGUI(){

        super("Schermata di registrazione");
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
        panel.add(registratiButton);
        panel.add(orariMsg);
        /*panel.add(lunLabel);
        panel.add(marLabel);
        panel.add(merLabel);
        panel.add(gioLabel);
        panel.add(venLabel);
        panel.add(sabLabel);
        panel.add(domLabel);*/

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
        emailField.setBounds(140,40,150,25);
        passwordField.setBounds(140,80,150,25);
        confirmpasswordField.setBounds(140,120,150,25);
        nomeField.setBounds(140,160,150,25);
        telefonoField.setBounds(140,200,150,25);
        indirizzoField.setBounds(140,240,150,25);
        percentualiAcconto.setBounds(140,280,150,25);
        registratiButton.setBounds(230,340,100,30);

        //disposizione elementi grafici destra orari
        orariMsg.setBounds(350,10,230,25);
        /*lunLabel.setBounds(350,40,80,25);
        marLabel.setBounds(350,80,80,25);
        merLabel.setBounds(350,120,80,25);
        gioLabel.setBounds(350,160,80,25);
        venLabel.setBounds(350,200,80,25);
        sabLabel.setBounds(350,240,80,25);
        domLabel.setBounds(350,280,80,25);*/
        
        for(int i = 0; i<7;i++){
            yComp = yComp+40;
            giorniLabel[i].setBounds(350,yComp,80,25);
            giorniFields[i].setBounds(440,yComp,150,25);
        }

        //actionperformed di pulsante registrati
        registratiButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
                /*email
                if(!emailField.getText().matches(regexEmail)){
                    validSignup = false;
                    emailField.setForeground(Color.RED);
                    emailField.setText("Email non valida!");
                }

                //nome
                if(!nomeField.getText().matches(regexNome)){
                    validSignup = false;
                    nomeField.setForeground(Color.RED);
                    nomeField.setText("Nome non valido");
                }

                //telefono
                if(!telefonoField.getText().matches(regexTelefono)){
                    telefonoField.setForeground(Color.RED);
                    telefonoField.setText("Numero non valido");
                }*/
                if(validSignup == true){
                    System.out.println("True");
                }
                else{
                    System.out.println("False");
                }
                validSignup = !validSignup;
                				
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