package com.hotel_alura.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAcces {
	
    public boolean login(String userName, String password) {
        boolean isValidUser = false;
        DBConnection dbConnection= new DBConnection();
        Connection conn=null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM alura_hotel.users WHERE user_name=? AND password=?";
        
        try {
            // Obtener una conexión a la base de datos
            conn = dbConnection.getConnection();
            
            // Crear una declaración preparada
            stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            stmt.setString(2, password);
            
            // Ejecutar la consulta y obtener los resultados
            rs = stmt.executeQuery();
            
            // Verificar si se encontró un usuario válido
            if (rs.next()) {
                isValidUser = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar todo
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return isValidUser;
    }
}
