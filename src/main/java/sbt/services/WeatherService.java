package sbt.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sbt.parser.Data;
import sbt.parser.Hourly;
import sbt.parser.MaxTempreture;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class WeatherService {
    final String TOKEN = "ae61464104921456935eb914baa3abe6";


    @Bean
    public RestTemplate restTemplateWhether() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplateWhether;

    public long parseDate(String dateString) {
        // YYYY-MM-DD HH:MM:SS
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            System.out.println("ParseException has been catch!");
            date = new Date();
        }

        return date.getTime() / 1000;
    }

    public String getResponse(long time) {
        String url = "https://api.darksky.net/forecast/" + TOKEN + "/55.3601,37.5589," + time +
                "?exclude=currently&units=auto";
        ResponseEntity<String> response = restTemplateWhether.getForEntity(url, String.class);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new IllegalStateException();
        }
        return response.getBody();
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
    public Double getMaxList(List<Double> tempList){
        double max = -Double.MAX_VALUE;
        for (Double d :tempList) {
            max = Math.max(max, d);
        }
        return max;
    }
    public Double getMaxTemperatureFromResponse(){
        String date = "2019-11-27";
        return getMaxList(parseResponse(getResponse(parseDate(date))));
    }
}
