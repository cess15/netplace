/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netplace.dao;

import com.netplace.entidad.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Cess
 */
public class UserDAO {

    private Conexion cn;

    public UserDAO() {
        cn = new Conexion();
    }

    public String createUser(User user) throws SQLException {
        String create = "No se ha podido crear el usuario";
        try {
            if (!isUser(user)) {
                PreparedStatement ps = this.cn.createConection().prepareStatement("insert into users (username,password,name)values(?,?,?);");
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getName());
                if (ps.executeUpdate() > 0) {
                    create = "El usuario " + user.getName() + " ha sido creado";
                }
            }
        } catch (Exception e) {
            create = "Error: "+e.getMessage();
        }
        this.cn.createConection().close();
        return create;
    }

    public String updateUser(User user) throws SQLException {
        String create = "No se ha podido actualizar el usuario";
        try {
            if (!isUser(user)) {
                PreparedStatement ps = this.cn.createConection().prepareStatement("update users set username=?, password=?, name=? where id_user=?");
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getName());
                ps.setInt(4, user.getIdUser());
                if (ps.executeUpdate() > 0) {
                    create = "El usuario " + user.getName() + " ha sido actualizado";
                }
            }
        } catch (Exception e) {
            create = "Error: "+e.getMessage();
        }
        this.cn.createConection().close();
        return create;
    }

    public ResultSet showUser(int idUser) throws SQLException {
        ResultSet rs = null;
        try {
            PreparedStatement ps = this.cn.createConection().prepareStatement("select * from users where id_user=?;");
            ps.setInt(1, idUser);
            rs = ps.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        this.cn.createConection().close();
        return rs;
    }

    public String deleteUser(int idUser) throws SQLException {
        String create = "No se ha podido eliminar el usuario";
        try {
            PreparedStatement ps = this.cn.createConection().prepareStatement("delete from users where id_user=?");
            ps.setInt(1, idUser);
            if (ps.executeUpdate() > 0) {
                create = "El usuario ha sido eliminado";
            }
        } catch (Exception e) {
            create = "Error: " + e.getMessage();
        }
        this.cn.createConection().close();
        return create;
    }

    public ResultSet getUser() throws SQLException {
        ResultSet rs = null;
        try {
            PreparedStatement ps = this.cn.createConection().prepareStatement("select * from users where id_user<>1 order by id_user desc;");
            rs = ps.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        this.cn.createConection().close();
        return rs;
    }

    public boolean access(User user) throws SQLException {
        boolean login = false;
        try {
            PreparedStatement ps = this.cn.createConection().prepareStatement("select * from users where username=? and password=?;");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                login = true;
            }
        } catch (Exception e) {
            login = false;
        }
        this.cn.createConection().close();
        return login;
    }

    public ResultSet getUserByCredentials(String user, String password) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = this.cn.createConection().prepareStatement("select * from users where username=? and password=?;");
            ps.setString(1, user);
            ps.setString(2, password);
            rs = ps.executeQuery();
        } catch (Exception e) {
        }
        return rs;
    }

    public boolean isUser(User user) {
        boolean isExist = false;
        try {
            PreparedStatement ps = this.cn.createConection().prepareStatement("select id_user,usernname from users");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isExist = true;
            }
        } catch (Exception e) {

        }
        return isExist;
    }
}
