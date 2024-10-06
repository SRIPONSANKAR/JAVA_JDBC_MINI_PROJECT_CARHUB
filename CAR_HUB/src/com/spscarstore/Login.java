package com.spscarstore;

import java.util.Scanner;

public class Login {
    private String currentRole = "user"; // Default role
    private Scanner scanner = new Scanner(System.in);

    public String authenticate() {
        System.out.print("Enter role (admin/user): ");
        String role = scanner.nextLine().trim().toLowerCase();
        if (role.equals("admin") || role.equals("user")) {
            currentRole = role;
            System.out.println("Logged in as: " + currentRole);
        } else {
            System.out.println("Invalid role. Defaulting to user.");
        }
        return currentRole;
    }
}
