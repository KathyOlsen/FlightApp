package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Date;

public interface FlightRepository extends CrudRepository<Flight,Long> {
    ArrayList<Flight> findByDate(Date date);
    ArrayList<Flight> findByDepartureLocContainingIgnoreCase(String departureLoc);
    ArrayList<Flight> findByArrivalLocContainingIgnoreCase(String arrivalLoc);
    ArrayList<Flight> findByAirlineContainingIgnoreCase(String airline);
    ArrayList<Flight> findByPriceIsLessThanEqual(String price);
    ArrayList<Flight> findByDateAndDepartureLocContainingIgnoreCase(Date date, String departureLoc);
    ArrayList<Flight> findByDateAndArrivalLocContainingIgnoreCase(Date date, String arrivalLoc);
    ArrayList<Flight> findByDateAndAirlineContainingIgnoreCase(Date date, String airline);
    ArrayList<Flight> findByDateAndPriceIsLessThanEqual(Date date, String price);
    ArrayList<Flight> findByDateAndDepartureLocContainingIgnoreCaseAndArrivalLocContainingIgnoreCase
            (Date date, String departureLoc, String arrivalLoc);
    ArrayList<Flight> findByDateAndDepartureLocContainingIgnoreCaseAndAirlineLocContainingIgnoreCase
            (Date date, String departureLoc, String airline);
    ArrayList<Flight> findByDateAndDepartureLocContainingIgnoreCaseAndPriceIsLessThanEqual
            (Date date, String departureLoc, String price);
    ArrayList<Flight> findByDateAndArrivalLocContainingIgnoreCaseAndAirlineContainingIgnoreCase
            (Date date, String arrivalLoc, String airline);
    ArrayList<Flight> findByDateAndArrivalLocContainingIgnoreCaseAndPriceIsLessThanEqual
            (Date date, String arrivalLoc, String price);
    ArrayList<Flight> findByDateAndAirlineContainingIgnoreCaseAndPriceIsLessThanEqual
            (Date date, String arrivalLoc, String airline);
    ArrayList<Flight> findByDateAndDepartureLocContainingIgnoreCaseAndArrivalLocContainingIgnoreCaseAndAirlineContainingIgnoreCase
            (Date date, String departureLoc, String arrivalLoc, String airline);
    ArrayList<Flight> findByDateAndDepartureLocContainingIgnoreCaseAndArrivalLocContainingIgnoreCaseAndPriceIsLessThanEqual
            (Date date, String departureLoc, String arrivalLoc, String price);

}
