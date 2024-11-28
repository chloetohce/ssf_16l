package sg.edu.nus.iss.ssf_16l.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import sg.edu.nus.iss.ssf_16l.model.Country;
import sg.edu.nus.iss.ssf_16l.utility.Constant;

@Service
public class CountryRestService {
    
    RestTemplate restTemplate = new RestTemplate();

    /**
     * Use the RestTemplate to call the API to get the list of countries
     * @return
     */
    public List<Country> getCountries() {
        String countryData = restTemplate.getForObject(Constant.URL_COUNTRY, String.class); //The data that comes back is a string

        JsonReader reader = Json.createReader(new StringReader(countryData));
        JsonObject jObject = reader.readObject();

        JsonObject jData = jObject.getJsonObject("data"); // See API format. Is an object within an object. Array will be []. Object is {}

        List<Country> countries = new ArrayList<>();
        Set<Entry<String, JsonValue>> entries = jData.entrySet();
        for (Entry<String, JsonValue> entry : entries) {
            Country c = new Country(
                    entry.getKey(), 
                    entry.getValue().asJsonObject().getString("country") //Have to cast as JsonObject because the JsonValue is written as another JsonObject
            );
            countries.add(c);
        }
        return countries;
    }
}
