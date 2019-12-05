package sbt.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MSRTest {

    @Autowired
    MSR msr;

    @Test
    public void fitSimpleTest() {
        ArrayList<Double> x_arr = new ArrayList<>();
        for (Double i=0.; i < 100; i+=1) {
            x_arr.add(i);
        }
        msr.fit(x_arr, x_arr);
        assertEquals(msr.getA(), 0.0, 0.01);
        assertEquals(msr.getB(), 1.0, 0.01);
    }

    @Test
    public void fitSquareTest() {
        ArrayList<Double> x_arr = new ArrayList<>();
        ArrayList<Double> y_arr = new ArrayList<>();
        for (Double i=0.; i < 1000; i+=1) {
            x_arr.add(i);
            y_arr.add(i * 2 + 5);
        }
        msr.fit(x_arr, y_arr);
        assertEquals(msr.getA(), 5.0, 0.01);
        assertEquals(msr.getB(), 2.0, 0.01);
    }
}