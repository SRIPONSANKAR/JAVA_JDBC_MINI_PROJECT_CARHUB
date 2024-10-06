package com.spscarstore;

public class Car implements Comparable<Car> {
    private String id;
    private String brand;
    private double price;
    private String model;
    private String color;
    private int year;

    // Constructor
    public Car(String model, String brand, double price, int year, String color) {
        this.id = ""; // Placeholder; ID will be set later
        this.brand = brand;
        this.price = price;
        this.model = model;
        this.color = color;
        this.year = year;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(Car other) {
        return Double.compare(this.price, other.price); // Sort by price
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Brand: %s, Model: %s, Price: %.2f, Color: %s, Year: %d", 
            id, brand, model, price, color, year);
    }
}
