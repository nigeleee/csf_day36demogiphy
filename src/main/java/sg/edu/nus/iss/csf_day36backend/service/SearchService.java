package sg.edu.nus.iss.csf_day36backend.service;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class SearchService {
    
    @Value("${api.key}")
    private String apiKey;

    private static final String URL = "https://api.giphy.com/v1/gifs/search";

    public List<String> searchGif(String q) {
        String builtUrl = UriComponentsBuilder.fromUriString(URL)
                .queryParam("api_key", apiKey)
                .queryParam("q", q)
                .queryParam("limit", 20)
                .toUriString();
        
        RequestEntity<Void> get = RequestEntity.get(builtUrl).build();        
    
        RestTemplate template = new RestTemplate();

    
        try {
			ResponseEntity<String> resp = template.exchange(get, String.class);
			String payload = resp.getBody();
			JsonReader reader = Json.createReader(new StringReader(payload));
			JsonObject result = reader.readObject();
			JsonArray data = result.getJsonArray("data");
			return data.stream()
				.map(v -> v.asJsonObject())
				.map(v -> v.getJsonObject("images").getJsonObject("fixed_height").getString("url"))
				.toList();
		} catch (Exception ex) {
			// 400 - 500
			ex.printStackTrace();
		}

		return new LinkedList<>();
	}

}
 


