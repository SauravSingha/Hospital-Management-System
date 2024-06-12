package com.db;

import java.sql.*;
public class UserCRUD {
	private Connection connection;
	public void getConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/votingsystem", "root", "Saurav@123");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

    public UserCRUD() {
        this.getConn();
    }

    // Create operation
    public void createUser(String name, String email) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (name, email) VALUES (?, ?)");
            statement.setString(1, name);
            statement.setString(2, email);
            statement.executeUpdate();
            System.out.println("User created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 // Read operation to print all users
    public void readUsers() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println("User ID: " + id + ", Name: " + name + ", Email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Update operation
    public void updateUser(int id, String newName, String newEmail) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Users SET name = ?, email = ? WHERE id = ?");
            statement.setString(1, newName);
            statement.setString(2, newEmail);
            statement.setInt(3, id);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("User not found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete operation
    public void deleteUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Users WHERE id = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("User not found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserCRUD userCRUD = new UserCRUD();
        
//        // Create a new user
//        userCRUD.createUser("user one", "userOne@example.com");
//        userCRUD.createUser("user two", "userTwo@example.com");
//
        // Read user with ID 1
//        userCRUD.readUsers();
//
        // in main() function
//        userCRUD.updateUser(1, "Saurav", "saurav@gmail.com");
//
        // Delete user with ID 1
        userCRUD.deleteUser(1);
    }
}

