package sbt;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import sbt.controllers.RBCController;
import sbt.services.RBCService;
import sbt.responsers.ResponserToRBC;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RBCControllerTest {

    Double data = 65.45;

    @LocalServerPort
    private int port;


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RBCController controller;

    @Autowired
    private ResponserToRBC responserToRBC;

    @Mock
    private RBCService rbc_mock = Mockito.mock(RBCService.class);


    @Before
    public void setUp() throws Exception {
        Mockito.when(rbc_mock.getMaxRateForPeriod(30, responserToRBC)).thenReturn(data);
    }

    @Test
    public void loadControllerTest() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void indexSimpleTest() {
//        System.out.println(this.restTemplate.getForObject("http://localhost:" + port + "rbc", String.class).contains("dollar_rate"));
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "rbc", String.class).contains("dollar_rate " + data));

    }
}