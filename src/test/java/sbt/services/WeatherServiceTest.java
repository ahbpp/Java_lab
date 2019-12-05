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

    @Mock
    private ResponserToWheather responserToWheather_mock = Mockito.mock(ResponserToWheather.class);

    @Test
    public void parseResponse() {
        ArrayList<Double> ans =
                weatherService.parseResponse(responserToWheather.getResponse(1571259600));
        Double[] expected_arr = {7.58, 7.53, 6.47, 6.43, 6.29, 7.89, 7.8,
                7.78, 8.98, 10.74, 12.77, 14.94, 16.11, 18.0, 19.0, 19.0,
                18.96, 17.07, 16.14, 14.54, 15.02, 14.27, 14.55, 14.26};
        ArrayList<Double> expected_list = new ArrayList<>(Arrays.asList(expected_arr));
        assertTrue(expected_list.equals(ans));
    }

    @Test
    public void getMaxTemperatureFromDate() {
        String testDate = "2019-10-17";
        Double answer = weatherService.getMaxTemperatureFromDate(testDate, responserToWheather);
        assertEquals(19.0, answer, 0.01);
    }

    @Test
    public void getMaxTemperatureFromSeconds() {
        long testSeconds = 1571259600;
        Double answer = weatherService.getMaxTemperatureFromSeconds(testSeconds, responserToWheather);
        assertEquals(19.0, answer, 0.01);

    }

}