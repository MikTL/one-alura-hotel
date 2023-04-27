package com.hotel_alura.controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.hotel_alura.models.Guest;

public class GuestDAO {
	private DBConnection dbConnection;

    public GuestDAO() {
        this.dbConnection = new DBConnection();
    }

    public void addGuest(Guest guest) {
        String sql = "INSERT INTO guests (name, last_name, date_birth, nationality, phone_number, id_booking) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, guest.getName());
            stmt.setString(2, guest.getLastName());
            stmt.setDate(3, Date.valueOf(guest.getDateOfBirth()));
            stmt.setString(4, guest.getNationality());
            stmt.setString(5, guest.getPhoneNumber());
            stmt.setInt(6, guest.getBookingId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}