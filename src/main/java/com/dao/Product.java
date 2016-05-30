package com.dao;

import javax.persistence.Entity;

@Entity
public class Product extends AbstractEntity {

    private final String productName;
    private final Long productPrice;

    // Never used
    private Product() {
        this.productName = null;
        this.productPrice = null;
    }

    public Product(String productName, long productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%d, productName='%s', productPrice='%s']",
                getId(), productName, productPrice);
    }

}