package com.hotel_alura.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotel_alura.models.Booking;
import com.hotel_alura.models.Guest;

public class CrudBookinDAO {
	 private DBConnection dbConnection;

	    public CrudBookinDAO() {
	        this.dbConnection = new DBConnection();
	    }
	    public List<Booking> getAllBookings() {
	        String sql = "SELECT id_bookings, entry_date, exit_date, value, payment_method FROM bookings";
	        List<Booking> bookings = new ArrayList<>();
	        try (Connection conn = dbConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet resultSet = stmt.executeQuery()) {
	            while (resultSet.next()) {
	                Booking booking = new Booking();
	                booking.setIdBooking(resultSet.getInt("id_bookings"));
	                booking.setEntryDate(resultSet.getDate("entry_date"));
	                booking.setExitDate(resultSet.getDate("exit_date"));
	                booking.setValue(resultSet.getBigDecimal("value"));
	                booking.setPaymentMethod(resultSet.getString("payment_method"));
	                bookings.add(booking);
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException("Error while retrieving bookings", e);
	        }
	        return bookings;
	    }
	    public void updateBooking(Booking booking) {
	        String sql = "UPDATE bookings SET entry_date=?, exit_date=?, value=?, payment_method=? WHERE id_bookings=?";
	        try (Connection conn = dbConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setDate(1, booking.getEntryDate());
	            stmt.setDate(2, booking.getExitDate());
	            stmt.setBigDecimal(3, booking.getValue());
	            stmt.setString(4, booking.getPaymentMethod());
	            stmt.setInt(5, booking.getIdBooking());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            throw new RuntimeException("Error while updating booking", e);
	        }
	    }
	    public void deleteBooking(int idBooking) {
	        String sql = "DELETE FROM bookings WHERE id_bookings = ?";
	        try (Connection conn = dbConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, idBooking);
	            int rowsDeleted = stmt.executeUpdate();
	            if (rowsDeleted == 0) {
	                throw new RuntimeException("No se encontró ninguna reserva con el id_bookings = " + idBooking);
	            }
	            System.out.println("La reserva con el id_bookings = " + idBooking + " ha sido eliminada exitosamente.");
	        } catch (SQLException e) {
	            throw new RuntimeException("Error al eliminar la reserva con id_bookings = " + idBooking, e);
	        }
	    }
	    public List<Booking> searchBookingsById(int idBooking) {
	        String sql = "SELECT id_bookings, entry_date, exit_date, value, payment_method FROM bookings WHERE id_bookings = ?";
	        List<Booking> bookings = new ArrayList<>();
	        try (Connection conn = dbConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, idBooking);
	            ResultSet resultSet = stmt.executeQuery();
	            while (resultSet.next()) {
	                Booking booking = new Booking();
	                booking.setIdBooking(resultSet.getInt("id_bookings"));
	                booking.setEntryDate(resultSet.getDate("entry_date"));
	                booking.setExitDate(resultSet.getDate("exit_date"));
	                booking.setValue(resultSet.getBigDecimal("value"));
	                booking.setPaymentMethod(resultSet.getString("payment_method"));
	                bookings.add(booking);
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException("Error while searching bookings by ID", e);
	        }
	        if (bookings.isEmpty()) {
	            throw new RuntimeException("No se encontraron reservas con el id_bookings = " + idBooking);
	        }
	        return bookings;
	    }
}
