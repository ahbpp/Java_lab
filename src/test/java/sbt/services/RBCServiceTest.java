package sbt.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sbt.services.RBCService;
import sbt.responsers.ResponserToRBC;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RBCServiceTest {

    @Autowired
    ResponserToRBC responserToRBC;
    @Autowired
    RBCService service;




    @Mock
    private ResponserToRBC responserToRBC_mock = Mockito.mock(ResponserToRBC.class);

    String data =
            "USD000000TOD,2019-09-13,70.2225,64.7175,64.125,64.2725,725046000,64.3306\n" +
            "USD000000TOD,2019-09-16,63.835,64.1675,63.57,64,1367191000,63.9049\n" +
            "USD000000TOD,2019-09-17,64.03,64.44,63.97,64.3575,725278000,64.184\n";



    @Before
    public void setUp() throws Exception {
        Mockito.when(responserToRBC_mock.getResponse(30)).thenReturn(data);
    }

    @Test
    public void getResponse() {
        String response = responserToRBC.getResponse(30);
        System.out.println(response);
    }

    @Test
    public void parseResponse() {
        RBCService rbc_service = new RBCService();
        ArrayList<Double> res = rbc_service.parseResponse(data);
        ArrayList<Double> expected = new ArrayList<>(Arrays.asList(64.3306, 63.9049, 64.184));
        assertEquals(expected, res);

    }

    @Test
    public void getMaxRateForPeriod() {
        Double res = service.getMaxRateForPeriod(30, responserToRBC_mock);
        assertEquals(64.3306, res, 0.01);
    }

}