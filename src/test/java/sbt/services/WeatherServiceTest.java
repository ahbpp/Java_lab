package sbt.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sbt.responsers.ResponserToWheather;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherServiceTest {

    @Autowired
    WeatherService weatherService;
    @Autowired
    ResponserToWheather responserToWheather;


    @Test
    public void parseResponse() {
        ArrayList<Double> ans =
                weatherService.parseResponse(responserToWheather.getResponse(1571259600));
        Double[] expected_arr = {9.13, 9.31, 9.46, 9.74, 9.0, 9.4, 9.87, 10.33, 10.65,
                11.15, 12.18, 13.52, 14.55, 15.69, 16.66, 16.64, 17.36, 15.97, 14.77,
                14.26, 14.03, 13.9, 13.27, 12.98};
        ArrayList<Double> expected_list = new ArrayList<>(Arrays.asList(expected_arr));
        System.out.println(ans);
        assertTrue(expected_list.equals(ans));
    }

    @Test
    public void getMaxTemperatureFromDate() {
        String testDate = "2019-10-17";
        Double answer = weatherService.getMaxTemperatureFromDate(testDate, responserToWheather);
        assertEquals(17.36, answer, 0.01);
    }

    @Test
    public void getMaxTemperatureFromSeconds() {
        long testSeconds = 1571259600;
        Double answer = weatherService.getMaxTemperatureFromSeconds(testSeconds, responserToWheather);
        assertEquals(17.36, answer, 0.01);

    }

}