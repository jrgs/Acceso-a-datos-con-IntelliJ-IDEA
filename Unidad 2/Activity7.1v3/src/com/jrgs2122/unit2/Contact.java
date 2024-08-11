package com.jrgs2122.unit2;

import java.io.Serializable;

public class Contact implements Serializable {
    protected String name;
    protected String surname;
    protected String phone;
    protected String email;
    protected String description;

    public Contact() {}

    public Contact(String name, String surname, String phone, String email, String description) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
