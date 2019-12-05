package sbt.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTest {

    @Autowired
    Utils utils;

    @Test
    public void getMaxFromArray() {
        ArrayList<Double> testArray = new ArrayList<>();
        for (double d = 0; d < 50; d += 1) {
            testArray.add(d);
        }
        assertEquals(49, utils.getMaxFromArray(testArray), 0.01);
    }

    @Test
    public void parseDate() {
        String date = "2019-10-17";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(LocalDateTime.now());
        assertEquals(1571259600, utils.parseDate(date));
    }

    @Test
    public void arrayProduct() {
        ArrayList<Double> testArray1 = new ArrayList<>();
        ArrayList<Double> testArray2 = new ArrayList<>();
        for (double d = 0; d < 5; d += 1) {
            testArray1.add(d);
            testArray2.add(d - 2);
        }
        ArrayList<Double> ans = utils.arrayProduct(testArray1, testArray2);
        Double[] expected_arr = {-0.0, -1.0, 0.0, 3.0, 8.0};
        ArrayList<Double> expected_list = new ArrayList<>(Arrays.asList(expected_arr));
        assertTrue(expected_list.equals(ans));
    }
}