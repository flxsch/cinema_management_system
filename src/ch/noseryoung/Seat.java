package ch.noseryoung;

import java.util.ArrayList;

public class Seat {
    private ArrayList<String> number;
    private int price;

    public Seat(ArrayList<String> number, int price) {
        this.number = number;
        this.price = price;
    }

    public ArrayList<String> getNumber() {
        return number;
    }

    public void setNumber(ArrayList<String> number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "number=" + number +
                ", price=" + price +
                '}';
    }
}
