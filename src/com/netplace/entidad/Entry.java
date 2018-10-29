/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netplace.entidad;

/**
 *
 * @author Cess
 */
public class Entry {

    private int idEntry;
    private User user;
    private Product product;
    private int quantity;
    private double price;
    private String dateTime;
    private double subtotal;
    

    public Entry() {
    }

    public Entry(int idEntry, User user, Product product, int quantity, double price, String dateTime) {
        this.idEntry = idEntry;
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.dateTime = dateTime;
    }

    public int getIdEntry() {
        return idEntry;
    }

    public void setIdEntry(int idEntry) {
        this.idEntry = idEntry;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getSubTotal() {
        this.subtotal=this.price*this.quantity;
        return subtotal;
    }
}
