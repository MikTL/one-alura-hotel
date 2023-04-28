package com.hotel_alura.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotel_alura.models.Booking;
import com.hotel_alura.models.Guest;

public class CrudDAO {
	 private DBConnection dbConnection;

	    public CrudDAO() {
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
	    public List<Guest> getAllGuests() {
	        String sql = "SELECT id_guest, name, last_name, date_birth, nationality, phone_number, id_booking FROM guests";
	        List<Guest> guests = new ArrayList<>();
	        try (Connection conn = dbConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet resultSet = stmt.executeQuery()) {
	            while (resultSet.next()) {
	                Guest guest = new Guest();
	                guest.setIdGuest(resultSet.getInt("id_guest"));
	                guest.setName(resultSet.getString("name"));
	                guest.setLastName(resultSet.getString("last_name"));
	                guest.setDateOfBirth(resultSet.getDate("date_birth"));
	                guest.setNationality(resultSet.getString("nationality"));
	                guest.setPhoneNumber(resultSet.getString("phone_number"));
	                guest.setBookingId(resultSet.getInt("id_booking"));
	                guests.add(guest);
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException("Error while retrieving bookings", e);
	        }
	        return guests;
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
}
