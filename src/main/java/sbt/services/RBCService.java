package sbt.services;


import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import sbt.jpa_data.EntityRate;
import sbt.jpa_data.RateCrudRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class RBCService {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired private RestTemplate restTemplate;
    @Autowired private RateCrudRepository rateCrudRepository;

    @Transactional
    public void saveRate(Double rate) {
        LocalDateTime now = LocalDateTime.now();
        rateCrudRepository.save(new EntityRate(rate, dateTimeFormatter.format(now)));
    }

    @Transactional
    public double getMaxRateForPeriod(int lastdays) {
        Optional<EntityRate> base_result = getTodayRate();
        if (base_result.isPresent()) {
            return base_result.map(EntityRate::getRate).get().doubleValue();
        }
        double result = getMaxFromArray(parseResponse(getResponse(lastdays)));
        saveRate(result);
        return result;
    }

    public String getResponse(int lastdays) {
        String url = "http://export.rbc.ru/free/selt.0/free.fcgi?period=DAILY&tickers=USD000000TOD&separator=,&data_format=BROWSER&lastdays=";
        ResponseEntity<String> response = restTemplate.getForEntity(url + String.valueOf(lastdays), String.class);
        return response.getBody();
    }


    public Optional<EntityRate> getTodayRate() {
        LocalDateTime now = LocalDateTime.now();
        Optional<EntityRate> result = rateCrudRepository.findByDate(dateTimeFormatter.format(now));
        return result;
    }

    public ArrayList<Double> parseResponse(String responseString) {
        String[] lines = responseString.split("\n");

        ArrayList<Double> ans = new ArrayList<>();
        for (String line : lines) {
            System.out.println(line);
            String[] elements = line.split(",");
            ans.add(Double.parseDouble(elements[elements.length - 1]));
        }
        return ans;
    }

    public Double getMaxFromArray(List<Double> doubleList) {
        double max = Double.MIN_VALUE;
        for (Double d : doubleList) {
            max = Math.max(max, d);
        }
        return max;
    }

}