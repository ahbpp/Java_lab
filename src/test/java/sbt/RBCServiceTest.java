package sbt;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import sbt.services.RBCService;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

//@RunWith(MockitoJUnitRunner.class)
public class RBCServiceTest {



    @Mock
    private RBCService rbc_mock = Mockito.mock(RBCService.class);

    String data =
            "USD000000TOD,2019-09-13,64.2225,64.7175,64.125,64.2725,725046000,64.3306\n" +
            "USD000000TOD,2019-09-16,63.835,64.1675,63.57,64,1367191000,63.9049\n" +
            "USD000000TOD,2019-09-17,64.03,64.44,63.97,64.3575,725278000,64.184\n";



    @Before
    public void setUp() throws Exception {
        Mockito.when(rbc_mock.getResponse(30)).thenReturn(data);
    }

    @Test
    public void getResponse() {
        String response = rbc_mock.getResponse(30);
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
    public void getMaxFromArray() {
        ArrayList<Double> testArray = new ArrayList<>();
        for (double d = 0; d < 50; d += 1) {
            testArray.add(d);
        }
        RBCService service = new RBCService();
        assertEquals(49, service.getMaxFromArray(testArray), 0.01);
    }

}