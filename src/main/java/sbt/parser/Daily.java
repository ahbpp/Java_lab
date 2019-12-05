package sbt.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Daily {
    private Data daily = null;

    public Data getDaily() {
        return this.daily;
    }
    public void set(Data daily){
        this.daily = daily;
    }
}
