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
public class Product {

    private int idProduct;
    private String name;
    private double salePrice;
    private int stock;
    private String description;

    public Product() {
    }

    public Product(int idProduct, String name, double salePrice, int stock, String description) {
        this.idProduct = idProduct;
        this.name = name;
        this.salePrice = salePrice;
        this.stock = stock;
        this.description = description;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
