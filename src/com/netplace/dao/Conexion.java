/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netplace.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Cess
 */
public class Conexion {

    public Connection createConection() {
        Connection cn = null;
        try {
            Class.forName("org.postgresql.Driver");
            cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Cyber", "postgres", "sistemas");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en Conexion", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return cn;
    }

}
