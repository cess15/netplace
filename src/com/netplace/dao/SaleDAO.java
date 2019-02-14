/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netplace.dao;

import com.netplace.entidad.Sale;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Cess
 */
public class SaleDAO {

    private Conexion cn;

    public SaleDAO() {
        cn = new Conexion();
    }

    public String createSale(Sale sale) throws SQLException {
        String create = "No se ha podido registrar la venta";
        try {
            PreparedStatement ps = this.cn.createConection().prepareStatement("insert into sales (id_user,id_product,quantity,date_time,subtotal)values(?,?,?,?,?);");
            ps.setInt(1, sale.getUser().getIdUser());
            ps.setInt(2, sale.getProduct().getIdProduct());
            ps.setInt(3, sale.getQuantity());
            ps.setString(4, sale.getDateTime());
            ps.setDouble(5, sale.getSubtotal());
            if (ps.executeUpdate() > 0) {
                create = "La venta ha sido registrada";
            }
        } catch (Exception e) {
            create = "Error: " + e.getMessage();
        }
        this.cn.createConection().close();
        return create;
    }

    public ResultSet getSales() throws SQLException {
        ResultSet rs = null;
        try {
            PreparedStatement ps = this.cn.createConection().prepareStatement("select s.id_sales, p.name as product, p.description, s.quantity,p.sale_price,u.username,s.date_time,s.subtotal from sales s join products p on p.id_product=s.id_product join users u on s.id_user=u.id_user order by date_time desc;");
            rs = ps.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        this.cn.createConection().close();
        return rs;
    }
}