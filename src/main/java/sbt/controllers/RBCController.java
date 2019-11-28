package sbt.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import sbt.services.RBCService;
import sbt.responsers.ResponserToRBC;


@RestController
@Component
public class RBCController {

    @Autowired
    private RBCService service;
    @Autowired
    private ResponserToRBC responserToRBC;

    @RequestMapping(value = "/rbc", method = RequestMethod.GET)
    public String index() {
        return "dollar_rate " + service.getMaxRateForPeriod(30, responserToRBC);
    }
}
