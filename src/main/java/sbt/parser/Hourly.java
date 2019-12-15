package sbt.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hourly {
    private Data data = null;

    public Data getData() {
        return this.data;
    }
}
