package com.dao;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Customer extends AbstractEntity {

    @Column
    private final String email;
    @Column
    private final String firstName;
    @Column
    private final String lastName;
    @Column
    private final String address;

    // Never used
    private Customer() {
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.address = "";
    }

    public Customer(String email, String customerName, String lastName, String address) {
        this.email = email;
        this.firstName = customerName;
        this.lastName = lastName;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s', address='%s']",
                getId(), firstName, lastName, address);
    }

}