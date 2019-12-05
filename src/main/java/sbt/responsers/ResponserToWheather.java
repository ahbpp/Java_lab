package sbt.responsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResponserToWheather {
    final String TOKEN = "ae61464104921456935eb914baa3abe6";

    @Autowired
    private RestTemplate restTemplateWhether;

    public String getResponse(long time) {
        String url = "https://api.darksky.net/forecast/" + TOKEN + "/55.3601,37.5589," + time +
                "?exclude=currently&units=auto";
        ResponseEntity<String> response = restTemplateWhether.getForEntity(url, String.class);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new IllegalStateException();
        }
        return response.getBody();
    }
}
