package sbt.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sbt.parser.Data;
import sbt.parser.Hourly;
import sbt.parser.MaxTempreture;
import sbt.responsers.ResponserToWheather;
import sbt.utils.Utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


@Component
public class WeatherService {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    Utils utils;

    @Bean
    public RestTemplate restTemplateWhether() {
        return new RestTemplate();
    }

    public ArrayList<Double> parseResponse(String responseString) {
        String[] lines = responseString.split("\n");
        ArrayList<Double> ans = new ArrayList<>();
        for (String obj: lines) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Hourly hourly = objectMapper.readValue(obj, Hourly.class);
                Data data = hourly.getHourly();
                for (MaxTempreture maxTempreture : data.getData()){
                    Double temperature = maxTempreture.getTemperature();
                    ans.add(temperature);
                }
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ans;
    }


    public Double getMaxTemperatureFromDate(String date, ResponserToWheather responserToWheather){
        return getMaxTemperatureFromSeconds(utils.parseDate(date), responserToWheather);
    }

    public Double getMaxTemperatureFromSeconds(long seconds, ResponserToWheather responserToWheather){
        return utils.getMaxFromArray(parseResponse(responserToWheather.getResponse(seconds)));
    }


    public ArrayList<Double> getTempretureForPeriod(long inp_seconds, int lastdays, ResponserToWheather responserToWheather) {
        ArrayList<Double> ans = new ArrayList<>();
//        String today_date = dateTimeFormatter.format(LocalDateTime.now());
//        long inp_seconds = utils.parseDate(today_date);
        for (int i=0; i<lastdays; i++) {
            ans.add(getMaxTemperatureFromSeconds(inp_seconds, responserToWheather));
            inp_seconds = utils.prevDay(inp_seconds);
        }
        return ans;
    }
}
