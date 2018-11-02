/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netplace.entidad;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author Cess
 */
public class Sale {

    private int idSale;
    private User user;
    private Product product;
    private int quantity;
    private String dateTime;
    private double subtotal;

    public Sale() {
    }

    public Sale(int idSale, User user, Product product, int quantity, String dateTime, double subtotal) {
        this.idSale = idSale;
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.subtotal = subtotal;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getSubtotal() {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.00", dfs);
        subtotal = this.quantity * this.getProduct().getSalePrice();
        String price = df.format(subtotal);
        return Double.parseDouble(price);
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

}
