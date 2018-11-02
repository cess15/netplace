/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netplace.dao;

import com.netplace.entidad.Entry;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Cess
 */
public class EntryDAO {

    private Conexion cn;

    public EntryDAO() {
        cn = new Conexion();
    }

    public String createEntry(Entry entry) throws SQLException {
        String create = "No se ha podido registrar la compra";
        try {
            PreparedStatement ps = this.cn.createConection().prepareStatement("insert into entries (id_user,id_product,quantity,price,date_time)values(?,?,?,?,?);");
            ps.setInt(1, entry.getUser().getIdUser());
            ps.setInt(2, entry.getProduct().getIdProduct());
            ps.setInt(3, entry.getQuantity());
            ps.setDouble(4, entry.getPrice());
            ps.setString(5, entry.getDateTime());
            if (ps.executeUpdate() > 0) {
                create = "La compra ha sido registrada";
            }
        } catch (Exception e) {
            create = "Error: " + e.getMessage();
        }
        this.cn.createConection().close();
        return create;
    }

    public ResultSet getEntries() throws SQLException {
        ResultSet rs = null;
        try {
            PreparedStatement ps = this.cn.createConection().prepareStatement("select e.id_entries,p.name as product,p.description,e.quantity,e.price,u.username,e.date_time from entries e join products p on p.id_product=e.id_product join users u on e.id_user=u.id_user order by date_time desc;");
            rs = ps.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        this.cn.createConection().close();
        return rs;
    }
}
