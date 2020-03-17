package sbt.jpa_data;


import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="Dollar")
public class EntityRate {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Double rate;
    private int days;
    private String date;

    public EntityRate(Double rate, Date date, int days) {
        this.rate = rate;
        DateFormat dateFormater = new SimpleDateFormat("yyyy/MM/dd");
        this.date = dateFormater.format(date);
        this.days = days;
    }

    public Double getRate(){
        return this.rate;
    }
}
