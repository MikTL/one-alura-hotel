package com.hotel_alura.models;

import java.math.BigDecimal;
import java.sql.Date;

public class Booking {
	private int idBooking;
    private Date entryDate;
    private Date exitDate;
    private BigDecimal value;
    private String paymentMethod;

    public Booking(Date entryDate, Date exitDate, BigDecimal value, String paymentMethod) {
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.value = value;
        this.paymentMethod = paymentMethod;
    }

    public Booking() {
		// TODO Auto-generated constructor stub
	}
    
    public int getIdBooking() {
        return idBooking;
    }
    public void setIdBooking(int idBooking) {
         this.idBooking= idBooking;
    }
	public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
