package sbt.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import sbt.services.WeatherService;


@RestController
@Component
public class RBCController {

    @Autowired
    private WeatherService service;

    @RequestMapping(value = "/rbc", method = RequestMethod.GET)
    public String index() {
        return "" + service.getMaxTemperatureFromResponse();
    }
}
