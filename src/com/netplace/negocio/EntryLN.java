/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netplace.negocio;

import com.netplace.dao.EntryDAO;
import com.netplace.entidad.Entry;
import com.netplace.entidad.Product;
import com.netplace.entidad.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Cess
 */
public class EntryLN {

    private EntryDAO entryDAO;

    public EntryLN() {
        entryDAO = new EntryDAO();
    }

    public List<Entry> getEntries() {
        List<Entry> lEntry = new ArrayList<Entry>();
        try {
            ResultSet rs = entryDAO.getEntries();
            while (rs.next()) {
                Entry entry = new Entry();
                entry.setIdEntry(rs.getInt("id_entries"));
                Product product = new Product();
                product.setName(rs.getString("product"));
                product.setDescription(rs.getString("description"));
                entry.setProduct(product);
                entry.setQuantity(rs.getInt("quantity"));
                entry.setPrice(rs.getDouble("price"));
                User user = new User();
                user.setUsername(rs.getString("username"));
                entry.setUser(user);
                entry.setDateTime(rs.getString("date_time"));
                lEntry.add(entry);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return lEntry;
    }

    public void createEntry(Entry entry) {
        String response = "";
        try {
            response = entryDAO.createEntry(entry);
        } catch (Exception e) {
            response = "Error: en EntryLN" + e.getMessage();
        }
        JOptionPane.showMessageDialog(null, response);
    }
}
