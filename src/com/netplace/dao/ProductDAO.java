/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netplace.dao;

import com.netplace.entidad.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Cess
 */
public class ProductDAO {

    private Conexion cn;

    public ProductDAO() {
        cn = new Conexion();
    }

    public String createProduct(Product product) throws SQLException {
        String create = "No se ha podido registrar el producto";
        try {
            PreparedStatement ps = this.cn.createConection().prepareStatement("insert into products (name,sale_price,stock,description)values(?,?,?,?);");
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getSalePrice());
            ps.setInt(3, product.getStock());
            ps.setString(4, product.getDescription());
            if (ps.executeUpdate() > 0) {
                create = "El producto " + product.getName() + " ha sido registrado";
            }
        } catch (Exception e) {
            create = "Error: " + e.getMessage();
        }
        this.cn.createConection().close();
        return create;
    }
    public String updateProduct(Product product) throws SQLException {
        String create = "No se ha podido actualizar el usuario";
        try {
            PreparedStatement ps = this.cn.createConection().prepareStatement("update products set name=?, sale_price=?, description=? where id_product=?");
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getSalePrice());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getIdProduct());
            if (ps.executeUpdate() > 0) {
                create = "El producto " + product.getName() + " ha sido actualizado";
            }
        } catch (Exception e) {
            create = "Error: " + e.getMessage();
        }
        this.cn.createConection().close();
        return create;
    }
    public ResultSet showProducts(int idProducto) throws SQLException {
        ResultSet rs = null;
        try {
            PreparedStatement ps = this.cn.createConection().prepareStatement("select * from products where id_product=?;");
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        this.cn.createConection().close();
        return rs;
    }
    public String deleteProduct(int idProduct) throws SQLException {
        String create = "No se ha podido eliminar el producto";
        try {
            PreparedStatement ps = this.cn.createConection().prepareStatement("delete from products where id_product=?");
            ps.setInt(1, idProduct);
            if (ps.executeUpdate() > 0) {
                create = "El producto ha sido eliminado";
            }
        } catch (Exception e) {
            create = "Error: " + e.getMessage();
        }
        this.cn.createConection().close();
        return create;
    }
    public ResultSet getProducts() throws SQLException {
        ResultSet rs = null;
        try {
            PreparedStatement ps = this.cn.createConection().prepareStatement("select * from products order by name asc;");
            rs = ps.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        this.cn.createConection().close();
        return rs;
    }
}
