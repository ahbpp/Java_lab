package sbt.utils;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Utils {
    public Double getMaxFromArray(ArrayList<Double> doubleList) {
        double max = -Double.MAX_VALUE;
        for (Double d : doubleList) {
            max = Math.max(max, d);
        }
        return max;
    }

    public Double getSumOfArray(ArrayList<Double> doubleList) {
        Double sum = 0.0;
        for (Double xi: doubleList) {
            sum += xi;
        }
        return sum;
    }

    public ArrayList<Double> arrayProduct(ArrayList<Double> x_arr, ArrayList<Double> y_arr) {
        assert(x_arr.size() == y_arr.size());
        ArrayList<Double> res = new ArrayList<>();
        for (int i = 0; i < y_arr.size(); i++) {
            res.add(x_arr.get(i) * y_arr.get(i));
        }
        return res;
    }

    public long parseDate(String dateString) {
        // YYYY-MM-DD
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            System.out.println("ParseException has been catch!");
            date = new Date();
        }

        return date.getTime() / 1000;
    }

    public long prevDay(long sec_input) {
        return sec_input - 86400;
    }

}
