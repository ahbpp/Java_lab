package sbt.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import sbt.jpa_data.EntityRate;
import sbt.jpa_data.RateCrudRepository;
import sbt.responsers.ResponserToRBC;
import sbt.utils.Utils;

import java.util.*;


@Component
public class RBCService {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    Utils utils;
    @Autowired private RateCrudRepository rateCrudRepository;

    @Transactional
    public void saveRate(Double rate, int days) {
        Date date = Calendar.getInstance().getTime();
        rateCrudRepository.save(new EntityRate(rate, date, days));
    }

    @Transactional
    public double getMaxRateForPeriod(int lastdays, ResponserToRBC responserToRBC) {
        Optional<Double> base_result = getTodayRate(lastdays);
        System.out.println(base_result);
        if (base_result.isPresent()) {
            System.out.println("Base");
            return base_result.get();
        }
        System.out.println("Net");
        double result = utils.getMaxFromArray(getRateForPeriod(lastdays, responserToRBC));
        saveRate(result, lastdays);
        return result;
    }

    public ArrayList<Double> getRateForPeriod(int lastdays, ResponserToRBC responserToRBC) {
        return parseResponse(responserToRBC.getResponse(lastdays));
    }


    public Optional<Double> getTodayRate(int lastdays) {
        Date date = Calendar.getInstance().getTime();
        Optional<EntityRate> result = rateCrudRepository.findByDateAndDays(EntityRate.getDateFormater().format(date), lastdays);
        return result.map(EntityRate::getRate);
    }

    public ArrayList<Double> parseResponse(String responseString) {
        String[] lines = responseString.split("\n");



        ArrayList<Double> ans = new ArrayList<>();
        for (String line : lines) {
            System.out.println("Line:=" + line);
            String[] elements = line.split(",");
            ans.add(Double.parseDouble(elements[elements.length - 1]));
        }
        return ans;
    }

}