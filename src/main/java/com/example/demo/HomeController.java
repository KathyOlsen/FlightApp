package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
                              @RequestParam(name="date") String date){
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
        flightRepository.save(flight);
        return "redirect:/";
    }

    @PostMapping("/processsearch")
    public String processSearch(Model model,
                                @RequestParam(name="SearchSelector") String optionvalue,
                                @RequestParam(name="search") String search) {
        if(optionvalue.equalsIgnoreCase("DepartureLoc")){
            model.addAttribute("flights", flightRepository.findByDepartureLoc(search));
        }else if(optionvalue.equalsIgnoreCase("ArrivalLoc")){
            model.addAttribute("flights", flightRepository.findByArrivalLoc(search));
        }else if(optionvalue.equalsIgnoreCase("Airline")){
            model.addAttribute("flights", flightRepository.findByAirline(search));
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
