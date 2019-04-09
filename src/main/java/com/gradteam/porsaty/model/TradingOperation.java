package com.gradteam.porsaty.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tawfik on 3/18/2018.
 */
@Entity
public class TradingOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int quantity;
    private double price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    private NormalUser user;

    @ManyToOne
    private Stock stock;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public NormalUser getUser() {
        return user;
    }

    public void setUser(NormalUser user) {
        this.user = user;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
