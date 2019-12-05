package sbt.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import sbt.responsers.ResponserToWheather;
import sbt.services.PredictService;
import sbt.services.RBCService;
import sbt.responsers.ResponserToRBC;
import sbt.services.WeatherService;


@RestController
@Component
public class RBCController {

    @Autowired
    private RBCService service;
    @Autowired
    private ResponserToRBC responserToRBC;

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private ResponserToWheather responserToWheather;

    @Autowired
    private PredictService predictService;

    @RequestMapping(value = "/rbc", method = RequestMethod.POST)
    @ResponseBody
    public Viewer index(@RequestParam("days") int days) {
        Viewer viewer = new Viewer();
        viewer.value = service.getMaxRateForPeriod(days, responserToRBC);
        return viewer;
    }


    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    @ResponseBody
    public Viewer item(@RequestParam("date") String date) {
        // "yyyy-MM-dd"
        Viewer viewer = new Viewer();
        viewer.value = weatherService.getMaxTemperatureFromDate(date, responserToWheather);
        return viewer;
    }

    @RequestMapping(value = "/predict", method = RequestMethod.POST)
    @ResponseBody
    public Viewer predict(@RequestParam("days") int days) {
        Viewer viewer = new Viewer();
        viewer.value = predictService.getPredictDollarToday(responserToRBC, responserToWheather, days);
        return viewer;
    }
}
