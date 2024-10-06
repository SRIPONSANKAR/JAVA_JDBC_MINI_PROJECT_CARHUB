package com.spscarstore;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory {
    private List<Car> carList;
    Statement stmt;
    ResultSet rs;
    DBconnection db = new DBconnection(); 
    Connection con;

    public Inventory() {
        this.carList = new ArrayList<>();
    }

    public List<Car> getCarList() {
        return carList;
    }

    private String generateCarID() {
        int nextId = carList.size() + 1;
        return String.format("c%02d", nextId);
    }

    public void addCar(Car car) {
        try {
            con = db.getDBconnection(); 
            stmt = con.createStatement();
            String carID = generateCarID(); 
            String qry = "INSERT INTO cars(id, brand, price, model, color, year) VALUES('" 
                + carID + "','" + car.getBrand() + "','" + car.getPrice() + "','" 
                + car.getModel() + "','" + car.getColor() + "','" + car.getYear() + "')";
            stmt.executeUpdate(qry);
            car.setId(carID); // Set the ID to the car object
            carList.add(car); // Add car to local list
            System.out.println("Car added successfully to the inventory with ID: " + car.getId());
        } catch (Exception ex) {
            System.out.println("Error adding car: " + ex.getMessage());
        }
    }

    public void updateCar(Car car) {
        try {
            con = db.getDBconnection();
            stmt = con.createStatement();
            String qry = "UPDATE cars SET brand='" + car.getBrand() 
                + "', price='" + car.getPrice() + "', model='" + car.getModel() 
                + "', color='" + car.getColor() + "', year='" + car.getYear() 
                + "' WHERE id='" + car.getId() + "'";
            int count = stmt.executeUpdate(qry);
            if (count > 0) {
                System.out.println("Car updated successfully.");
            } else {
                System.out.println("Car with the specified ID not found.");
            }
        } catch (Exception ex) {
            System.out.println("Error updating car: " + ex.getMessage());
        }
    }

    public void removeCarByModel(String model) {
        Car carToRemove = null;
        
        // Find the car in the local carList
        for (Car car : carList) {
            if (car.getModel().equalsIgnoreCase(model)) {
                carToRemove = car;
                break;
            }
        }

        // If the car is found in the list, remove it
        if (carToRemove != null) {
            carList.remove(carToRemove);

            // Remove the car from the database
            try {
                con = db.getDBconnection();
                stmt = con.createStatement();
                String qry = "DELETE FROM cars WHERE model='" + model + "'"; // Change to the correct identification
                int count = stmt.executeUpdate(qry);
                if (count > 0) {
                    System.out.println("Car removed successfully from the database.");
                } else {
                    System.out.println("Car with the specified model not found in the database.");
                }
            } catch (Exception ex) {
                System.out.println("Error removing car from database: " + ex.getMessage());
            }
        } else {
            System.out.println("Car with the specified model not found in the inventory.");
        }
    }

    public void searchByBrand(String brand) {
        boolean found = false;
        for (Car car : carList) {
            if (car.getBrand().equalsIgnoreCase(brand)) {
                System.out.println(car);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cars found for the specified brand.");
        }
    }

    public void sortCars(String sortBy) {
        switch (sortBy.toLowerCase()) {
            case "price":
                Collections.sort(carList); // Uses Comparable to sort by price
                System.out.println("Cars sorted by price.");
                break;
            case "brand":
                Collections.sort(carList, CarComparators.sortByBrand); // Uses Comparator to sort by brand
                System.out.println("Cars sorted by brand.");
                break;
            case "year":
                Collections.sort(carList, CarComparators.sortByYear); // Uses Comparator to sort by year
                System.out.println("Cars sorted by year.");
                break;
            default:
                System.out.println("Invalid sort option. Please choose 'price', 'brand', or 'year'.");
        }
        displayCars();
    }

    public void displayCars() {
        if (carList.isEmpty()) {
            System.out.println("No cars available in the inventory.");
        } else {
            System.out.println("Car List:");
            for (Car car : carList) {
                System.out.println(car);
            }
        }
    }
}
