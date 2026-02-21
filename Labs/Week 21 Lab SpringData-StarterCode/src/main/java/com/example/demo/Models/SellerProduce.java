package com.example.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Seller_Produce")
public class SellerProduce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "produce_id")
    private Produce produce;

    private double price;
    private int quantity;

    public SellerProduce() {}

    public SellerProduce(User seller, Produce produce, double price, int quantity) {
        this.seller = seller;
        this.produce = produce;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() { return id; }

    public User getSeller() { return seller; }
    public void setSeller(User seller) { this.seller = seller; }

    public Produce getProduce() { return produce; }
    public void setProduce(Produce produce) { this.produce = produce; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}