package sbt.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sbt.responsers.ResponserToRBC;
import sbt.responsers.ResponserToWheather;

import static org.junit.Assert.*;

public class ViewerServiceTest {

    @Autowired
    private ResponserToRBC responserToRBC;
    @Autowired
    private ResponserToWheather responserToWheather;

    @Autowired
    private PredictService predictService;

    @Test
    public void getPredictDollarToday() {
    }
}