/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netplace.negocio;

import com.netplace.dao.ProductDAO;
import com.netplace.entidad.Product;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Cess
 */
public class ProductLN {

    private ProductDAO productDAO;

    public ProductLN() {
        productDAO = new ProductDAO();
    }

    public void createProduct(Product product) {
        String response = "";
        try {
            response = productDAO.createProduct(product);
        } catch (Exception e) {
            response = "Error: " + e.getMessage();
        }
        JOptionPane.showMessageDialog(null, response);
    }

    public void updateProduct(Product product) {
        String response = "";
        try {
            response = productDAO.updateProduct(product);
        } catch (Exception e) {
            response = "Error: " + e.getMessage();
        }
        JOptionPane.showMessageDialog(null, response);
    }

    public void deleteProduct(int id_product) {
        String response = "";
        try {
            response = productDAO.deleteProduct(id_product);
        } catch (Exception e) {
            response = "Error: " + e.getMessage();
        }
        JOptionPane.showMessageDialog(null, response);
    }

    public Product getProductId(int id_product) {
        Product product = null;
        try {
            ResultSet rs = productDAO.showProducts(id_product);
            if (rs.next()) {
                product = new Product();
                product.setIdProduct(rs.getInt("id_product"));
                product.setName(rs.getString("name"));
                product.setSalePrice(rs.getDouble("sale_price"));
                product.setStock(rs.getInt("stock"));
                product.setDescription(rs.getString("description"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return product;
    }

    public List<Product> getProducts() {
        List<Product> lUser = new ArrayList<Product>();
        try {
            ResultSet rs = productDAO.getProducts();
            while (rs.next()) {
                Product product = new Product();
                product.setIdProduct(rs.getInt("id_product"));
                product.setName(rs.getString("name"));
                product.setSalePrice(rs.getDouble("sale_price"));
                product.setStock(rs.getInt("stock"));
                product.setDescription(rs.getString("description"));
                lUser.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return lUser;
    }
}
