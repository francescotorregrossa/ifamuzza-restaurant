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
        screenWidth = (screenWidth/2)-240;
        int screenHeigth = (int) screenSize.getHeight();
        screenHeigth = (screenHeigth/2) - 135;
        this.setVisible(true);
        this.setBounds(screenWidth,screenHeigth,480,270);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

}