package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Date;

public interface FlightRepository extends CrudRepository<Flight,Long> {
//    ArrayList<Flight> findByDate(Date date);
    ArrayList<Flight> findByDepartureLoc(String departureLoc);
    ArrayList<Flight> findByArrivalLoc(String arrivalLoc);
    ArrayList<Flight> findByAirline(String airline);
}
