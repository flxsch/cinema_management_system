package ch.noseryoung;

public class Customer {

    int id;
    String name;
    String surname;
    String street;
    int streetNumber;
    int PLZ;
    String city;

    public Customer(int id, String name, String surname, String street, int streetNumber, int PLZ, String city) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.street = street;
        this.streetNumber = streetNumber;
        this.PLZ = PLZ;
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

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getPLZ() {
        return PLZ;
    }

    public void setPLZ(int PLZ) {
        this.PLZ = PLZ;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String printCostumer() {
        return surname + " " + name +"\n" + street + " " + streetNumber + "\n" + PLZ + " " + city;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber=" + streetNumber +
                ", PLZ=" + PLZ +
                ", city='" + city + '\'' +
                '}';
    }
}
