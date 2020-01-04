package com.ifamuzzarestaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.ifamuzzarestaurant.model.Auth;
import com.ifamuzzarestaurant.model.User;

import org.apache.http.concurrent.FutureCallback;

public class LoginGUI extends JFrame{


    
    
    //Componenti grafici
    private JLabel emailLabel = new JLabel("Email:");
    private JLabel passwordLabel = new JLabel("Password:");
    private JTextField emailField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("Accedi");
    private JCheckBox mostraPasswordCheckBox = new JCheckBox();
    private JLabel mostraPassField = new JLabel("Mostra password");
    private JButton signupLink = new JButton("Prima volta? Registrati ora!");
    private JLabel credenzialiErrate = new JLabel("Credenziali errate!");

    //variabili per pulsante accedi
    Boolean stato = true; //franco mi ha detto fai che una volta da errore una volta no quando accedi
    String userEmail;
    String userPassword;
    StringBuilder sb = new StringBuilder();


    public LoginGUI(){


        super("Schermata di accesso/registrazione");
        Container c = this.getContentPane();
        c.setLayout(null);


        /*border che uso per capire la grandezza dei componenti
        Border border = LineBorder.createGrayLineBorder();
        emailLabel.setBorder(border);
        passwordLabel.setBorder(border);
        emailField.setBorder(border);
        passwordField.setBorder(border);
        loginButton.setBorder(border); 
        signupLink.setBorderPainted(false);
        loginButton.setBorderPainted(false);*/


        //inserisco i componenti grafici nel container
        c.add(emailLabel);
        c.add(passwordLabel);
        c.add(emailField);
        c.add(passwordField);
        c.add(mostraPasswordCheckBox);
        c.add(mostraPassField);
        c.add(loginButton);
        c.add(signupLink);
        c.add(credenzialiErrate);

        //cose estetiche
        credenzialiErrate.setVisible(false);
        credenzialiErrate.setForeground(Color.RED);


        //Disposizione elementi grafici
        emailLabel.setBounds(50,30,80,30);
        passwordLabel.setBounds(50,70,80,30);
        emailField.setBounds(140,30,150,30);
        passwordField.setBounds(140,70,150,30);
        mostraPasswordCheckBox.setBounds(50,120,20,20);
        mostraPassField.setBounds(75,120,120,20);
        loginButton.setBounds(50,160,100,30);
        signupLink.setBounds(160,160,200,30);
        credenzialiErrate.setBounds(310,55,130,25);

        
        //Ascoltatore per decidere se verdere la password oppure no
        mostraPasswordCheckBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
                if(mostraPasswordCheckBox.isSelected()){
                    passwordField.setEchoChar((char) 0);
                }
                else{
                    passwordField.setEchoChar('*');

                }
			}
        });
        
        //Ascoltatore per il pulsante accedi
        loginButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
                
                userEmail = emailField.getText();
                userPassword = sb.append(passwordField.getPassword()).toString();

                Auth.getInstance().login(userEmail, userPassword, new FutureCallback<User>(){
                
                    @Override public void failed(Exception ex) { System.out.println("errore " + ex.getMessage()); }
                    @Override public void cancelled() { System.out.println("cancellato");}
                    @Override public void completed(User result) {
                        System.out.println("risultato " + result);
                    }
                    
                });

                /*
                if(stato == true){
                    userEmail = emailField.getText();
                    userPassword = sb.append(passwordField.getPassword()).toString();
                    credenzialiErrate.setVisible(false);
                    System.out.println("TRUE");
                    HomeGUI h = new HomeGUI();
                    
                }
                else{
                    credenzialiErrate.setVisible(true);
                    System.out.println("FALSE");
                }

                stato = !stato;
                */
						
			}
        });
        
        //focusListener su email field
        emailField.addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent fe){
                if(credenzialiErrate.isVisible() == true){
                emailField.setText("");
                passwordField.setText("");
                }
				credenzialiErrate.setVisible(false);
				
			}
		});
        

        //ascoltatore pulsante registrati
        signupLink.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
                signupGUI s = new signupGUI();
			}
		});

        //settaggi per il frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        screenWidth = (screenWidth/2)-240;
        int screenHeigth = (int) screenSize.getHeight();
        screenHeigth = (screenHeigth/2) - 135;
        this.setVisible(true);
        this.setBounds(screenWidth,screenHeigth,480,270);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
     }
}