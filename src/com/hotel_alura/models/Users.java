package com.hotel_alura.models;
public class Users {
    private int id;
    private String name;
    private String last_name;
    private String user_name;
    private String password;
    private String phone_number;
    
    public Users() {
        // Default constructor
    }
    
    public Users(int id, String name, String last_name, String user_name, 
                String password, String phone_number) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.user_name = user_name;
        this.password = password;
        this.phone_number = phone_number;
        
    }
    
    // Getters and setters for each variable
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLast_name() {
        return last_name;
    }
    
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    
    public String getUser_name() {
        return user_name;
    }
    
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPhone() {
        return phone_number;
    }
    
    public void setPhone(String phone) {
        this.phone_number = phone;
    }
    

    
    @Override
    public String toString() {
        return "User(id=" + id + ", name=" + name + ", last_name=" + last_name 
                + ", user_name=" + user_name + ", password=" + password 
                + ", phone=" + phone_number +")";
    }
}
