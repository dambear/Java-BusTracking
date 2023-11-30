/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.busbookingsystem;

import java.sql.Connection;

public class BusBookingSystem {

    public static void main(String[] args) {
        
        Connection con = DBConnection.connect();
        

       
//        String data = "example54646576563@email.com, password123, John, Doe, 1234567890, Example Address";
//        String[] userData = data.split(", ");
//        DBConnection.addUser(userData[0], userData[1], userData[2], userData[3], userData[4], userData[5]);

        // Close the connection when done
        DBConnection.closeConnection();
        
        
        LoginForm LoginForm = new LoginForm();
        LoginForm.setVisible(true);
        LoginForm.pack();
        LoginForm.setLocationRelativeTo(null);

    }
}
