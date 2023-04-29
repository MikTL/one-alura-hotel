package com.hotel_alura.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotel_alura.models.Guest;

public class CrudGuestDAO {
    private DBConnection dbConnection;

    public CrudGuestDAO() {
        this.dbConnection = new DBConnection();
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
            throw new RuntimeException("Error while retrieving guests", e);
        }
        return guests;
    }
    public void updateGuest(Guest guest) {
        String sql = "UPDATE guests SET name=?, last_name=?, date_birth=?, nationality=?, phone_number=?, id_booking=? WHERE id_guest=?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, guest.getName());
            stmt.setString(2, guest.getLastName());
            stmt.setDate(3, guest.getDateOfBirth());
            stmt.setString(4, guest.getNationality());
            stmt.setString(5, guest.getPhoneNumber());
            stmt.setInt(6, guest.getBookingId());
            stmt.setInt(7, guest.getIdGuest());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating guest", e);
        }
    }

    public void deleteGuest(int idGuest) {
        String sql = "DELETE FROM guests WHERE id_guest = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idGuest);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new RuntimeException("No se encontró ningún huésped con el id_guest = " + idGuest);
            }
            System.out.println("El huésped con el id_guest = " + idGuest + " ha sido eliminado exitosamente.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el huésped con id_guest = " + idGuest, e);
        }
    }
}
