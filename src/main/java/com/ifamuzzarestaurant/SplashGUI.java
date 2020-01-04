package com.ifamuzzarestaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.ifamuzzarestaurant.model.Auth;
import com.ifamuzzarestaurant.model.User;

import org.apache.http.concurrent.FutureCallback;

public class SplashGUI extends JFrame {

    public SplashGUI() {
        super("iFamuzza");
        Container c = this.getContentPane();
        c.setLayout(null);
        
        JProgressBar bar = new JProgressBar();
        bar.setIndeterminate(true);
        bar.setBounds(25,30,150,20);
        c.add(bar);

        Auth.getInstance(new FutureCallback<Auth>() {
            
            @Override public void failed(Exception ex) { 
                LoginGUI l = new LoginGUI();
                dispose();
            }
            @Override public void cancelled() {
                LoginGUI l = new LoginGUI();
                dispose();
            }
            @Override public void completed(Auth result) {
                
                if (result.isLoggedIn()) {
                    HomeGUI h = new HomeGUI();
                }
                else {
                    LoginGUI l = new LoginGUI();
                }
                dispose();

            }

        });

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        screenWidth = (screenWidth/2)-100;
        int screenHeigth = (int) screenSize.getHeight();
        screenHeigth = (screenHeigth/2) - 50;
        this.setVisible(true);
        this.setBounds(screenWidth,screenHeigth,200,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

}