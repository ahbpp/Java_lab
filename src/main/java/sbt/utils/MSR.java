package sbt.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MSR {
    private Double a;
    private Double b;
    @Autowired
    Utils utils;

    public void fit(ArrayList<Double> x_arr, ArrayList<Double> y_arr) {
        assert(x_arr.size() == y_arr.size());
        Double sumX1 = utils.getSumOfArray(x_arr);
        Double sumX2 = utils.getSumOfArray(utils.arrayProduct(x_arr, x_arr));
        Double sumY1 = utils.getSumOfArray(y_arr);
        Double sumXY = utils.getSumOfArray(utils.arrayProduct(x_arr, y_arr));

        Integer n = x_arr.size();
        b = (n * sumXY - sumX1 * sumY1) / (n * sumX2 - sumX1 * sumX1);
        a = (sumY1 - b * sumX1) / n;
    }

    public Double predict(Double x) {
        return a + b * x;
    }

    public Double getA() {
        return a;
    }

    public Double getB() {
        return b;
    }
}
