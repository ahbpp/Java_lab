package sbt.responsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResponserToRBC {

    @Autowired
    private RestTemplate restTemplate;

    public String getResponse(int lastdays) {
        String url = "http://export.rbc.ru/free/selt.0/free.fcgi?period=DAILY&tickers=USD000000TOD&separator=,&data_format=BROWSER&lastdays=";
        ResponseEntity<String> response = restTemplate.getForEntity(url + lastdays, String.class);
        System.out.println("Result net = " + response.getBody());
        return response.getBody();
    }
}
