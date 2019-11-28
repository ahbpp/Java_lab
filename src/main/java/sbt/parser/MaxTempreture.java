package sbt.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaxTempreture {
    private Double temperature = null;

    public Double getTemperature() { return this.temperature;}
    public void setTemperature(Double temperature){ this.temperature = temperature;}
}
