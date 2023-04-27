package com.hotel_alura.controllers;

//import java.math.BigDecimal;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hotel_alura.models.Booking;

public class BookingDAO {
    private DBConnection dbConnection;

    public BookingDAO() {
        this.dbConnection = new DBConnection();
    }

    public void addBooking(Booking booking) {
        String query = "INSERT INTO bookings (entry_date, exit_date, value, payment_method) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, booking.getEntryDate());
            stmt.setDate(2, booking.getExitDate());
            stmt.setBigDecimal(3, booking.getValue());
            stmt.setString(4, booking.getPaymentMethod());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting booking into database", e);
        }
    }
    public int getLastBookingId() {
        Connection connection = dbConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lastBookingId = 0;

        try {
            preparedStatement = connection.prepareStatement("SELECT id_bookings FROM bookings ORDER BY id_bookings DESC LIMIT 1");
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                lastBookingId = resultSet.getInt("id_bookings");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el último ID de reservación", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al cerrar las conexiones", e);
            }
        }

        return lastBookingId;
    }
}