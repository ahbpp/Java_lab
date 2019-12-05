package sbt.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hourly {
    private Data hourly = null;

    public Data getHourly() {
        return this.hourly;
    }
    public void setHourly(Data hourly){
        this.hourly = hourly;
    }
}
