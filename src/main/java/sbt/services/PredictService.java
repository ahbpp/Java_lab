package sbt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sbt.responsers.ResponserToRBC;
import sbt.responsers.ResponserToWheather;
import sbt.utils.MSR;
import sbt.utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Component
public class PredictService {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Autowired
    Utils utils;
    @Autowired
    RBCService rbcService;
    @Autowired
    WeatherService weatherService;
    @Autowired
    MSR msr;


    public Double getPredictDollarToday(ResponserToRBC responserToRBC, ResponserToWheather responserToWheather, int days) {
        String today_date = dateTimeFormatter.format(LocalDateTime.now());
        long today_seconds = utils.parseDate(today_date);
        long inp_seconds = utils.prevDay(today_seconds);
        ArrayList<Double> dollarHistory = rbcService.getRateForPeriod(days, responserToRBC);
        ArrayList<Double> weatherHistrory = weatherService.getTempretureForPeriod(inp_seconds, days, responserToWheather);
        msr.fit(weatherHistrory, dollarHistory);
        Double todayTemp = weatherService.getMaxTemperatureFromSeconds(today_seconds, responserToWheather);
        Double predDollar = msr.predict(todayTemp);
        return predDollar;
    }
}
