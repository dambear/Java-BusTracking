package com.mycompany.busbookingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

    private static Connection con;

    public static Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:\\Users\\Admin\\Documents\\NetBeansProjects\\BusBookingSystem\\src\\main\\java\\resources\\BusBookingSytem.db"; // Replace this with your database path
            con = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;
    }

    public static void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    
    //-------------------- USER functions --------------------
    
    
     public static boolean authenticateUser(String username, String password) {
        String query = "SELECT * FROM main.UserTable WHERE Username = ? AND Password = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            return rs.next(); // Returns true if a record is found, indicating successful authentication
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static void addUser(String email, String password, String firstName, String lastName, String contactNumber, String address) {
      String query = "INSERT INTO main.UserTable(Email, Password, FirstName, LastName, ContactNumber, Address) VALUES (?, ?, ?, ?, ?, ?)";
      try (PreparedStatement pstmt = con.prepareStatement(query)) {
          pstmt.setString(1, email);
          pstmt.setString(2, password);
          pstmt.setString(3, firstName);
          pstmt.setString(4, lastName);
          pstmt.setString(5, contactNumber);
          pstmt.setString(6, address);
          pstmt.executeUpdate();
      } catch (SQLException e) {
          System.out.println(e);
      }
    }

    public static void updateUser(int id, String newData) {
        String query = "UPDATE your_table_name SET column_name = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, newData);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void deleteUser(int id) {
        String query = "DELETE FROM your_table_name WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Example method to retrieve data
    public static void retrieveUser() {
        String query = "SELECT * FROM your_table_name";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // Process retrieved data
                // Example:
                int id = rs.getInt("id");
                String columnData = rs.getString("column_name");
                System.out.println("ID: " + id + ", Data: " + columnData);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    //-------------------- End User functions --------------------
    
    
    //-------------------- Destenation functions --------------------
    
    public static void addBusInfo(String busname, String buslocation, String bussched) {
      String query = "INSERT INTO main.BusInformationTable(BusName, BusLocation, BusSchedule) VALUES (?, ?, ?)";
      try (PreparedStatement pstmt = con.prepareStatement(query)) {
          
          
          pstmt.setString(1, busname);
          pstmt.setString(2, buslocation);
          pstmt.setString(3, bussched);
          pstmt.executeUpdate();
      } catch (SQLException e) {
          System.out.println(e);
      }
    }
    
    public static void updateBusInfo(String busname, String newBusLocation, String newBusSched) {
    String query = "UPDATE main.BusInformationTable SET BusLocation = ?, BusSchedule = ? WHERE BusName = ?";
    try (PreparedStatement pstmt = con.prepareStatement(query)) {
        pstmt.setString(1, newBusLocation);
        pstmt.setString(2, newBusSched);
        pstmt.setString(3, busname);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
    }
}
    
    public static List<Object[]> searchBusByName(String busName) {
       List<Object[]> busList = new ArrayList<>();

        try (Connection connection = connect()) {
            // Assuming you have a 'bus' table with columns: BusName, BusLocation, BusSchedule
            String query = "SELECT BusName, BusLocation, BusSchedule FROM main.BusInformationTable WHERE BusName LIKE ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "%" + busName + "%");
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Object[] busData = {
                                resultSet.getString("BusName"),
                                resultSet.getString("BusLocation"),
                                resultSet.getString("BusSchedule")
                        };
                        busList.add(busData);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return busList;
    }
    
    
}
