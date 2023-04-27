package com.hotel_alura.models;

import java.time.LocalDate;

public class Guest {
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;
    private String phoneNumber;
    private int bookingId;

    public Guest(String name, String lastName, LocalDate dateOfBirth, String nationality, String phoneNumber, int bookingId) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.phoneNumber = phoneNumber;
        this.bookingId = bookingId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getBookingId() {
        return bookingId;
    }
}
