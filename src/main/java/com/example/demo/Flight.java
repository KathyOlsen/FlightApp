package com.example.demo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull
    @Size(min=3)
    private String departureLoc;

    @NotNull
    @Size(min=3)
    private String arrivalLoc;

    @NotNull
    @Size(min=3)
    private String airline;

    @NotNull
    @Size(min=2)
    private String price;

    public Flight() {
    }

    public Flight(@NotNull Date date,
                  @NotNull @Size(min = 3) String departureLoc,
                  @NotNull @Size(min = 3) String arrivalLoc,
                  @NotNull @Size(min = 3) String airline,
                  @NotNull @Min(2) String price) {
        this.date = date;
        this.departureLoc = departureLoc;
        this.arrivalLoc = arrivalLoc;
        this.airline = airline;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDepartureLoc() {
        return departureLoc;
    }

    public void setDepartureLoc(String departureLoc) {
        this.departureLoc = departureLoc;
    }

    public String getArrivalLoc() {
        return arrivalLoc;
    }

    public void setArrivalLoc(String arrivalLoc) {
        this.arrivalLoc = arrivalLoc;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
