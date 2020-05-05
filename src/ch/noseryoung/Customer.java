package ch.noseryoung;

import java.util.ArrayList;

public class Customer {

    int id;
    String name;
    String surname;
    String street;
    String streetNumber;
    String plz;
    String city;

    public Customer(int id, String name, String surname, String street, String streetNumber, String plz, String city) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.street = street;
        this.streetNumber = streetNumber;
        this.plz = plz;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String printCostumer() {
        return surname + " " + name + "\n"
                + street + " " + streetNumber + "\n"
                + plz + " " + city;
    }
}
