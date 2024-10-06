package com.spscarstore;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Inventory inventory = new Inventory(); // Create an instance of Inventory class

    public static void main(String[] args) {
        String currentRole = "guest"; // Start with a guest role
        boolean exit = false;

        while (!exit) {
            if (currentRole.equals("guest")) {
                System.out.println("\n--- Welcome to Car Showroom Management System ---");
                System.out.println("Please log in as admin or user.");
                currentRole = login(); // Simulate a login for simplicity

                if (currentRole.equals("invalid")) {
                    continue; // If login failed, ask to re-login
                }
            }

            while (true) {
                displayMenu(currentRole);

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                try {
                    switch (choice) {
                        case 1:
                            if (currentRole.equals("admin")) {
                                addCarFunction();
                            } else {
                                System.out.println("Permission denied! Only admin can add a car.");
                            }
                            break;
                        case 2:
                            inventory.displayCars();
                            break;
                        case 3:
                            searchCarFunction();
                            break;
                        case 4:
                            if (currentRole.equals("admin")) {
                                removeCarFunction();
                            } else {
                                System.out.println("Permission denied! Only admin can remove a car.");
                            }
                            break;
                        case 5:
                            if (currentRole.equals("admin")) {
                                updateCarFunction();
                            } else {
                                System.out.println("Permission denied! Only admin can update a car.");
                            }
                            break;
                        case 6:
                            // Users can sort cars too
                            sortCarFunction();
                            break;
                        case 7:
                            currentRole = logout(); // Log out to switch roles
                            if (currentRole.equals("guest")) {
                                break; // Exit the loop to re-login
                            }
                            break;
                        case 8:
                            exit = true; // Exit the program
                            System.out.println("Exiting the system. Goodbye!");
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid choice.");
                    scanner.nextLine(); // Consume invalid input
                }
            }
        }
    }

    // Simulated login method
    private static String login() {
        System.out.print("Enter role (admin/user): ");
        String role = scanner.nextLine();
        if (role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("user")) {
            return role.toLowerCase(); // Return in lowercase
        }
        System.out.println("Invalid role. Please try again.");
        return "invalid";
    }

    // Display menu options based on role
    private static void displayMenu(String role) {
        System.out.println("\n--- Car Showroom Management ---");
        if (role.equals("admin")) {
            System.out.println("1. Add a new car");
            
        }
        System.out.println("2. Display all cars");
        System.out.println("3. Search car by brand");
        if (role.equals("admin")) {
        	System.out.println("4. Remove car by model");
            System.out.println("5. Update car details");            
        }
        System.out.println("6. Sort cars");
        System.out.println("7. Log out");
        System.out.println("8. Exit");
    }

    // Function to log out and switch roles
    private static String logout() {
        System.out.println("Logging out. Please log in again.");
        return "guest"; // Reset role to guest
    }

    // Function to add a car
    private static void addCarFunction() {
        System.out.print("Enter car model: ");
        String model = scanner.nextLine();

        System.out.print("Enter car brand: ");
        String brand = scanner.nextLine();

        System.out.print("Enter car price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter car year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter car color: ");
        String color = scanner.nextLine();

        Car car = new Car(model, brand, price, year, color);
        inventory.addCar(car);
    }

    // Function to search for a car by brand
    private static void searchCarFunction() {
        System.out.print("Enter brand to search: ");
        String brand = scanner.nextLine();
        inventory.searchByBrand(brand);
    }

    // Function to remove a car by model
    private static void removeCarFunction() {
        System.out.print("Enter model to remove: ");
        String model = scanner.nextLine();
        inventory.removeCarByModel(model);
    }

    // Function to update a car's details
    private static void updateCarFunction() {
        System.out.print("Enter car model to update: ");
        String model = scanner.nextLine();

        System.out.print("Enter new brand: ");
        String newBrand = scanner.nextLine();

        System.out.print("Enter new price: ");
        double newPrice = scanner.nextDouble();

        System.out.print("Enter new year: ");
        int newYear = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new color: ");
        String newColor = scanner.nextLine();

        Car car = new Car(model, newBrand, newPrice, newYear, newColor);
        // Assuming you have logic to find and update the specific car in your inventory
        inventory.updateCar(car);
    }

    // Function to sort cars based on user's choice
    private static void sortCarFunction() {
        System.out.print("Sort by (price/brand/year): ");
        String sortBy = scanner.nextLine();
        inventory.sortCars(sortBy);
    }
}
