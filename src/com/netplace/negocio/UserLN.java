/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netplace.negocio;

import com.netplace.dao.UserDAO;
import com.netplace.entidad.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Cess
 */
public class UserLN {

    private UserDAO userDAO;

    public UserLN() {
        userDAO = new UserDAO();
    }

    public boolean access(User user) {
        boolean userdataccess = false;
        try {
            userdataccess = userDAO.access(user) ? true : false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return userdataccess;
    }

    public void createUser(User user) {
        String response = "";
        try {
            response = userDAO.createUser(user);
        } catch (Exception e) {
            response = "Error: " + e.getMessage();
        }
        JOptionPane.showMessageDialog(null, response);
    }

    public void updateUser(User user) {
        String response = "";
        try {
            response = userDAO.updateUser(user);
        } catch (Exception e) {
            response = "Error: " + e.getMessage();
        }
        JOptionPane.showMessageDialog(null, response);
    }

    public void deleteUser(int id_user) {
        String response = "";
        try {
            response = userDAO.deleteUser(id_user);
        } catch (Exception e) {
            response = "Error: " + e.getMessage();
        }
        JOptionPane.showMessageDialog(null, response);
    }

    public User getUserId(int id_user) {
        User user = null;
        try {
            ResultSet rs = userDAO.showUser(id_user);
            if (rs.next()) {
                user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return user;
    }

    public List<User> getUsers() {
        List<User> lUser = new ArrayList<User>();
        try {
            ResultSet rs = userDAO.getUser();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                lUser.add(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return lUser;
    }

    public User getUserByCredential(String user, String password) {
        User users = null;
        try {
            ResultSet rs = userDAO.getUserByCredentials(user, password);
            if (rs.next()) {
                users = new User();
                users.setIdUser(rs.getInt("id_user"));
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));
                users.setName(rs.getString("name"));
            }
        } catch (Exception e) {

        }
        return users;
    }
}
