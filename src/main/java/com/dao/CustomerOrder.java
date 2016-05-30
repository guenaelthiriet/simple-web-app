package com.dao;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class CustomerOrder extends AbstractEntity {

    @ElementCollection
    @JoinColumn(name="product_id")
    private final Set<Product> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private final Customer customer;

    private final Date orderDate;

    // Never used
    private CustomerOrder() {
        this.products = null;
        this.customer = null;
        this.orderDate = null;
    }

    public CustomerOrder(Set<Product> products, Customer customer, Date orderDate) {
        this.products = products;
        this.customer = customer;
        this.orderDate = orderDate;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return String.format(
                "CustomerOrder[id=%d, products='%s', customer='%s', date='%s']",
                getId(), products, customer, orderDate);
    }

}