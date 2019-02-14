/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netplace.negocio;

import com.netplace.dao.SaleDAO;
import com.netplace.entidad.Product;
import com.netplace.entidad.Sale;
import com.netplace.entidad.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Cess
 */
public class SaleLN {

    private SaleDAO saleDAO;

    public SaleLN() {
        saleDAO = new SaleDAO();
    }

    public List<Sale> getSales() {
        List<Sale> lSale = new ArrayList<Sale>();
        try {
            ResultSet rs = saleDAO.getSales();
            while (rs.next()) {
                Sale sale = new Sale();
                sale.setIdSale(rs.getInt("id_sales"));
                Product product = new Product();
                product.setName(rs.getString("product"));
                product.setDescription(rs.getString("description"));
                sale.setQuantity(rs.getInt("quantity"));
                product.setSalePrice(rs.getDouble("sale_price"));
                sale.setProduct(product);
                User user = new User();
                user.setUsername(rs.getString("username"));
                sale.setUser(user);
                sale.setDateTime(rs.getString("date_time"));
                sale.setSubtotal(rs.getDouble("subtotal"));
                lSale.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return lSale;
    }

    public void createSale(Sale sale) {
        String response = "";
        try {
            response = saleDAO.createSale(sale);
        } catch (Exception e) {
            response = "Error: en SaleLN" + e.getMessage();
        }
        JOptionPane.showMessageDialog(null, response);
    }
}
