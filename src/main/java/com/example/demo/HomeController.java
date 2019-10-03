package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {
    @Autowired
    FlightRepository flightRepository;

    @RequestMapping("/")
    public String listFlights(Model model){
        model.addAttribute("flights", flightRepository.findAll());
        return "listAll";
    }

    @GetMapping("/add")
    public String flightForm(Model model){
        model.addAttribute("flight", new Flight());
        return "flightform";
    }

    @PostMapping("/processflight")
    public String processForm(@Valid Flight flight,
                              BindingResult result,
                              @RequestParam(name="date") String date,
                              @RequestParam(name="price") String price){
        if(result.hasErrors()){
            return "flightform";
        }
        String pattern = "yyyy-MM-dd";
        try {
            String formattedDate = date.substring(1,date.length());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date realDate = simpleDateFormat.parse(formattedDate);
            flight.setDate(realDate);
        }
        catch (java.text.ParseException e){
            e.printStackTrace();
        }
        if (!price.startsWith("$")) {
            price = "$" + price;
            flight.setPrice(price);
        }
        flightRepository.save(flight);
        return "redirect:/";
    }

    @PostMapping("/processsearch")
    public String processSearch(Model model,
                                @RequestParam(name="SearchSelector") String option,
                                @RequestParam(name="search") String search,
                                @RequestParam(name="searchD") String searchD,
                                @RequestParam(name="searchP") String searchP) {
        if(option.equals("Date")){
            String pattern = "yyyy-MM-dd";
            System.out.println("searchD: " + searchD);
            try {
                String formattedDate = searchD;
                System.out.println("formattedDate: " + formattedDate);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                Date realDate = simpleDateFormat.parse(formattedDate);
                System.out.println("realDate: " + realDate);
                if(option.equals("DepartureLoc")){
                    if(option.equals("ArrivalLoc")){
                        if(option.equals("Airline")){
                            if(option.equals("Price")){

                            }
                        }
                    }
                    model.addAttribute("flights", flightRepository.findByDateAndDepartureLocContainingIgnoreCase(realDate,search));
                }else if(option.equals("ArrivalLoc")){
                    model.addAttribute("flights", flightRepository.findByDate(realDate));
                }else{
                    model.addAttribute("flights", flightRepository.findByDate(realDate));
                }
            }
            catch (ParseException e){
                e.printStackTrace();
            }
        }else if(option.equalsIgnoreCase("DepartureLoc")){
            model.addAttribute("flights", flightRepository.findByDepartureLocContainingIgnoreCase(search));
        }else if(option.equalsIgnoreCase("ArrivalLoc")){
            model.addAttribute("flights", flightRepository.findByArrivalLocContainingIgnoreCase(search));
        }else if(option.equalsIgnoreCase("Airline")){
            model.addAttribute("flights", flightRepository.findByAirlineContainingIgnoreCase(search));
        }else if(option.equalsIgnoreCase("Price")){
            String searchPrice = "$" + searchP;
            model.addAttribute("flights", flightRepository.findByPriceIsLessThanEqual(searchPrice));
        }
        return "listSearchResults";
    }

    @RequestMapping("/detail/{id}")
    public String showFlight(@PathVariable("id") long id, Model model){
        model.addAttribute("flight", flightRepository.findById(id).get());
        return "showdetail";
    }

    @RequestMapping("/update/{id}")
    public String updateFlight(@PathVariable("id") long id, Model model){
        model.addAttribute("flight", flightRepository.findById(id).get());
        return "flightform";
    }

    @RequestMapping("/delete/{id}")
    public String delFlight(@PathVariable("id") long id, Model model){
        flightRepository.deleteById(id);
        return "redirect:/";
    }
}
