package com.hotel_alura.models;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private ComboPooledDataSource dataSources;
    public DBConnection(){
        ComboPooledDataSource pooledDatasource = new ComboPooledDataSource();
        pooledDatasource.setJdbcUrl("jdbc:mysql://localhost/alura_hotel?useTimeZone=true&serverTimeZone=UTC");
        pooledDatasource.setUser("root");
        pooledDatasource.setPassword("");
        pooledDatasource.setMaxPoolSize(10);
        this.dataSources = pooledDatasource;
    }
    public Connection getConnection(){
        try {
            return this.dataSources.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}