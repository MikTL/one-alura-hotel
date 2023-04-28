package com.hotel_alura.models;

import java.sql.Date;

public class Guest {
	private int idGuest;
    private String name;
    private String lastName;
    private Date dateOfBirth;
    private String nationality;
    private String phoneNumber;
    private int bookingId;

    public Guest(String name, String lastName, Date dateOfBirth, String nationality, String phoneNumber, int bookingId) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.phoneNumber = phoneNumber;
        this.bookingId = bookingId;
    }

    public Guest() {
		// TODO Auto-generated constructor stub
	}
    
    
	public int getIdGuest() {
		return idGuest;
	}

	public void setIdGuest(int idGuest) {
		this.idGuest = idGuest;
	}

	public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
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

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
    
}
