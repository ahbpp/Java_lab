package sbt.parser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    private MaxTempreture[] data;

    public MaxTempreture[] getData() { return this.data;}
    public void setData(MaxTempreture[] data){ this.data = data;}

}
