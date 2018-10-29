/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netplace;

import com.netplace.presentacion.Login;
import javax.swing.JFrame;

/**
 *
 * @author Cess
 */
public class App {

    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
        login.setResizable(false);
        login.setLocationRelativeTo(null);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
