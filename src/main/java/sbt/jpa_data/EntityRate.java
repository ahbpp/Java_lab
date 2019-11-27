package sbt.jpa_data;


import javax.persistence.*;

@Entity
@Table(name="Dollar")
public class EntityRate {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Double rate;
    private String date;

    public  EntityRate() {}

    public EntityRate(Double rate, String date) {
        this.rate = rate;
        this.date = date;
    }

    public Double getRate(){
        return this.rate;
    }
    public String getDate(){
        return this.date;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
