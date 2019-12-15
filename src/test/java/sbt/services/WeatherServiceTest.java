package sbt.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sbt.responsers.ResponserToRBC;
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
    String data = "{\"latitude\":55.3601,\"longitude\":37.5589,\"timezone\":\"Europe/Moscow\",\"hourly\":" +
            "{\"summary\":\"Partly cloudy throughout the day.\",\"icon\":\"partly-cloudy-day\",\"data\":" +
            "[{\"time\":1571259600,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-night\",\"precipIntensity\":" +
            "0,\"precipProbability\":0,\"temperature\":9.03,\"apparentTemperature\":6.01,\"dewPoint\":3.92,\"humidity\":" +
            "0.7,\"pressure\":1020.5,\"windSpeed\":6.01,\"windGust\":15.33,\"windBearing\":198,\"cloudCover\":0.47,\"uvIndex\":" +
            "0,\"visibility\":16.093,\"ozone\":274.8},{\"time\":1571263200,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-n" +
            "ight\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":9.23,\"apparentTemperature\":6.31,\"dewPoint\":" +
            "4.49,\"humidity\":0.72,\"pressure\":1020.1,\"windSpeed\":5.85,\"windGust\":15.06,\"windBearing\":202,\"cloudCover\":" +
            "0.37,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":275.8},{\"time\":1571266800,\"summary\":\"Clear\",\"icon\":\"" +
            "clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":9.42,\"apparentTemperature\":6.62,\"" +
            "dewPoint\":5.02,\"humidity\":0.74,\"pressure\":1019.3,\"windSpeed\":5.65,\"windGust\":14.63,\"windBearing\":205,\"" +
            "cloudCover\":0.31,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":277.1},{\"time\":1571270400,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"" +
            "precipIntensity\":0,\"precipProbability\":0,\"temperature\":9.75,\"apparentTemperature\":7.04,\"dewPoint\":5.63,\"humidity\":0.76,\"pressure\":1018.7,\"" +
            "windSpeed\":5.63,\"windGust\":15.04,\"windBearing\":209,\"cloudCover\":0.24,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":274.3},{\"time\":" +
            "1571274000,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":9.03,\"apparentTemperature\":6.18,\"dewPoint\":6.58,\"humidity\":0.85,\"pressure\":1018.4,\"windSpeed\":5.5,\"windGust\":14.64,\"windBearing\":209,\"cloudCover\":0.15,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":276.3},{\"time\":1571277600,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":9.46,\"apparentTemperature\":6.69,\"dewPoint\":7.17,\"humidity\":0.86,\"pressure\":1017.9,\"windSpeed\":5.61,\"windGust\":14.11,\"windBearing\":211,\"cloudCover\":0.05,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":279.3},{\"time\":1571281200,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":9.96,\"apparentTemperature\":7.27,\"dewPoint\":7.61,\"humidity\":0.85,\"pressure\":1017.5,\"windSpeed\":5.74,\"windGust\":13.64,\"windBearing\":212,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":281.6},{\"time\":1571284800,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":10.38,\"apparentTemperature\":10.38,\"dewPoint\":7.79,\"humidity\":0.84,\"pressure\":1017.6,\"windSpeed\":5.82,\"windGust\":13.37,\"windBearing\":214,\"cloudCover\":0.01,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":282.8},{\"time\":1571288400,\"summary\":\"Clear\",\"icon\":\"clear-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":10.6,\"apparentTemperature\":10.6,\"dewPoint\":7.88,\"humidity\":0.83,\"pressure\":1017.3,\"windSpeed\":5.92,\"windGust\":13.15,\"windBearing\":216,\"cloudCover\":0.05,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":283.5},{\"time\":1571292000,\"summary\":\"Clear\",\"icon\":\"clear-day\",\"precipIntensity\":0.0051,\"precipProbability\":0.01,\"precipType\":\"rain\",\"temperature\":11.09,\"apparentTemperature\":11.09,\"dewPoint\":7.7,\"humidity\":0.8,\"pressure\":1017.2,\"windSpeed\":5.84,\"windGust\":12.52,\"windBearing\":217,\"cloudCover\":0.16,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":283.6},{\"time\":1571295600,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":12.17,\"apparentTemperature\":12.17,\"dewPoint\":7.95,\"humidity\":0.75,\"pressure\":1017,\"windSpeed\":5.9,\"windGust\":12.25,\"windBearing\":218,\"cloudCover\":0.39,\"uvIndex\":1,\"visibility\":16.093,\"ozone\":284.9},{\"time\":1571299200,\"summary\":\"Mostly Cloudy\",\"icon\":\"partly-cloudy-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":13.56,\"apparentTemperature\":13.56,\"dewPoint\":8.16,\"humidity\":0.7,\"pressure\":1016.8,\"windSpeed\":6.01,\"windGust\":12,\"windBearing\":217,\"cloudCover\":0.67,\"uvIndex\":1,\"visibility\":16.093,\"ozone\":286.5},{\"time\":1571302800,\"summary\":\"Mostly Cloudy\",\"icon\":\"partly-cloudy-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":14.59,\"apparentTemperature\":14.59,\"dewPoint\":7.99,\"humidity\":0.65,\"pressure\":1016.3,\"windSpeed\":6.03,\"windGust\":11.77,\"windBearing\":216,\"cloudCover\":0.85,\"uvIndex\":1,\"visibility\":16.093,\"ozone\":287.7},{\"time\":1571306400,\"summary\":\"Mostly Cloudy\",\"icon\":\"partly-cloudy-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":15.67,\"apparentTemperature\":15.67,\"dewPoint\":7.9,\"humidity\":0.6,\"pressure\":1015.9,\"windSpeed\":5.99,\"windGust\":11.43,\"windBearing\":216,\"cloudCover\":0.8,\"uvIndex\":1,\"visibility\":16.093,\"ozone\":288.1},{\"time\":1571310000,\"summary\":\"Mostly Cloudy\",\"icon\":\"partly-cloudy-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":16.61,\"apparentTemperature\":16.61,\"dewPoint\":7.86,\"humidity\":0.56,\"pressure\":1015.4,\"windSpeed\":5.9,\"windGust\":11.08,\"windBearing\":216,\"cloudCover\":0.64,\"uvIndex\":1,\"visibility\":16.093,\"ozone\":288},{\"time\":1571313600,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":16.68,\"apparentTemperature\":16.68,\"dewPoint\":7.76,\"humidity\":0.56,\"pressure\":1015,\"windSpeed\":5.71,\"windGust\":11.04,\"windBearing\":216,\"cloudCover\":0.51,\"uvIndex\":1,\"visibility\":16.093,\"ozone\":288.4},{\"time\":1571317200,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":17.37,\"apparentTemperature\":17.37,\"dewPoint\":7.34,\"humidity\":0.52,\"pressure\":1014.3,\"windSpeed\":4.66,\"windGust\":10.21,\"windBearing\":208,\"cloudCover\":0.49,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":288.1},{\"time\":1571320800,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":16.07,\"apparentTemperature\":16.07,\"dewPoint\":7.25,\"humidity\":0.56,\"pressure\":1014.2,\"windSpeed\":4.62,\"windGust\":12.37,\"windBearing\":209,\"cloudCover\":0.51,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":290.7},{\"time\":1571324400,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-night\",\"precipIntensity\":0.0072,\"precipProbability\":0.03,\"precipType\":\"rain\",\"temperature\":14.95,\"apparentTemperature\":14.95,\"dewPoint\":7.26,\"humidity\":0.6,\"pressure\":1014,\"windSpeed\":4.6,\"windGust\":13.82,\"windBearing\":213,\"cloudCover\":0.52,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":292.1},{\"time\":1571328000,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":14.45,\"apparentTemperature\":14.45,\"dewPoint\":7.11,\"humidity\":0.61,\"pressure\":1013.9,\"windSpeed\":4.59,\"windGust\":13.74,\"windBearing\":224,\"cloudCover\":0.51,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":291},{\"time\":1571331600,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":14.2,\"apparentTemperature\":14.2,\"dewPoint\":6.88,\"humidity\":0.61,\"pressure\":1014,\"windSpeed\":4.59,\"windGust\":12.95,\"windBearing\":237,\"cloudCover\":0.49,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":288.6},{\"time\":1571335200,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":14.01,\"apparentTemperature\":14.01,\"dewPoint\":6.86,\"humidity\":0.62,\"pressure\":1013.8,\"windSpeed\":4.36,\"windGust\":11.24,\"windBearing\":247,\"cloudCover\":0.48,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":286},{\"time\":1571338800,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":13.34,\"apparentTemperature\":13.34,\"dewPoint\":8,\"humidity\":0.7,\"pressure\":1013.7,\"windSpeed\":4.28,\"windGust\":11.24,\"windBearing\":245,\"cloudCover\":0.44,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":284.9},{\"time\":1571342400,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":13.03,\"apparentTemperature\":13.03,\"dewPoint\":6.64,\"humidity\":0.65,\"pressure\":1014.3,\"windSpeed\":4.39,\"windGust\":11.58,\"windBearing\":248,\"cloudCover\":0.4,\"uvIndex\":0,\"visibility\":16.093,\"ozone\":284.2}]},\"daily\":{\"data\":[{\"time\":1571259600,\"summary\":\"Partly cloudy throughout the day.\",\"icon\":\"partly-cloudy-day\",\"sunriseTime\":1571285040,\"sunsetTime\":1571322480,\"moonPhase\":0.62,\"precipIntensity\":0.0015,\"precipIntensityMax\":0.0072,\"precipIntensityMaxTime\":1571324400,\"precipProbability\":0.04,\"precipType\":\"rain\",\"temperatureHigh\":17.66,\"temperatureHighTime\":1571316960,\"temperatureLow\":10.82,\"temperatureLowTime\":1571367840,\"apparentTemperatureHigh\":17.38,\"apparentTemperatureHighTime\":1571316960,\"apparentTemperatureLow\":11.09,\"apparentTemperatureLowTime\":1571367840,\"dewPoint\":7.11,\"humidity\":0.69,\"pressure\":1016.3,\"windSpeed\":5.39,\"windGust\":15.33,\"windGustTime\":1571259600,\"windBearing\":216,\"cloudCover\":0.4,\"uvIndex\":1,\"uvIndexTime\":1571303700,\"visibility\":16.093,\"ozone\":284.3,\"temperatureMin\":8.75,\"temperatureMinTime\":1571274240,\"temperatureMax\":17.66,\"temperatureMaxTime\":1571316960,\"apparentTemperatureMin\":6.01,\"apparentTemperatureMinTime\":1571259600,\"apparentTemperatureMax\":17.38,\"apparentTemperatureMaxTime\":1571316960}]},\"flags\":{\"sources\":[\"cmc\",\"gfs\",\"icon\",\"isd\",\"madis\"],\"nearest-station\":17.192,\"units\":\"si\"},\"offset\":3}\n";

    @Mock
    private ResponserToWheather responserToWheather_mock = Mockito.mock(ResponserToWheather.class);

    @Before
    public void setUp() throws Exception {
        Mockito.when(responserToWheather_mock.getResponse(1571259600)).thenReturn(data);
    }

    @Test
    public void parseResponse() {
        ArrayList<Double> ans =
                weatherService.parseResponse(responserToWheather_mock.getResponse(1571259600));
        Double[] expected_arr = {9.03, 9.23, 9.42, 9.75, 9.03, 9.46, 9.96, 10.38, 10.6, 11.09,
                12.17, 13.56, 14.59, 15.67, 16.61, 16.68, 17.37, 16.07, 14.95, 14.45, 14.2,
                14.01, 13.34, 13.03};
        ArrayList<Double> expected_list = new ArrayList<>(Arrays.asList(expected_arr));
        assertTrue(expected_list.equals(ans));
    }

    @Test
    public void getMaxTemperatureFromDate() {
        String testDate = "2019-10-17";
        Double answer = weatherService.getMaxTemperatureFromDate(testDate, responserToWheather_mock);
        assertEquals(17.37, answer, 0.01);
    }

    @Test
    public void getMaxTemperatureFromSeconds() {
        long testSeconds = 1571259600;
        Double answer = weatherService.getMaxTemperatureFromSeconds(testSeconds, responserToWheather_mock);
        assertEquals(17.37, answer, 0.01);

    }

}